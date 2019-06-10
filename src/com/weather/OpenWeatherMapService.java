package com.weather;
import Model.WeatherData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class OpenWeatherMapService {

    private final String serviceUri = "api.openweathermap.org";
    private final String path = "/data/2.5/weather";
    private final String key = "4ea98f74622072acddf2f67deb259125";

    private final Logger logger = LoggerFactory.getLogger(OpenWeatherMapService.class);

    public WeatherData GetWeatherData(String query) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = BuildUri(query);
        HttpGet httpGet = new HttpGet(uri);

        ResponseHandler<WeatherData> rh = new ResponseHandler<WeatherData>() {
            public WeatherData handleResponse(HttpResponse response) throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if(statusLine.getStatusCode() >= 300) {
                    logger.error("Status code: {}", statusLine.getStatusCode());
                    logger.error("Reason: {}", statusLine.getReasonPhrase());
                    //throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    logger.error("Response contains no content");
                    //throw new ClientProtocolException("Response contains no content");
                }
                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(), charset);
                return gson.fromJson(reader, WeatherData.class);
            }
        };

        return httpClient.execute(httpGet, rh);
    }

    private URI BuildUri (String query) throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(serviceUri)
                .setPath(path)
                .setParameter("q", query)
                .setParameter("units", "metric")
                .setParameter("APPID", key)
                .build();
        logger.info("Service call uri: {}", uri);
        return uri;
    }
}