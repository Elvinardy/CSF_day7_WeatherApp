package iss.day18_workshop_httprequest.service;

import java.io.Serializable;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import iss.day18_workshop_httprequest.Constants;
import iss.day18_workshop_httprequest.model.WeatherModel;

// Can use an Interface class to link the cacheService and WeatherService design method.
@Service
public class WeatherService implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    
    private final String key;


    public WeatherService() {
        key = System.getenv(Constants.ENV_OPENWEATHER_KEY);

        if (Objects.isNull(key)) {
            logger.warn(Constants.ENV_OPENWEATHER_KEY + " is not set!");
        }
    }
    
    public WeatherModel getWeather(String city){
        
        final String url = UriComponentsBuilder      //  Building the query to be send out to the API
            .fromUriString(Constants.URL_WEATHER)
            .queryParam("q", city.replaceAll(" ", "+"))  
            .queryParam("appid", key)
            .queryParam("units", "metric")
            .toUriString();

        final RequestEntity<Void> req = RequestEntity.get(url).build();  //  Sending a request to the API
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);  // Getting a response to the API

       /*  if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException(
                "Error: status code %s".formatted(resp.getStatusCode().toString()));
        }
        final String body = resp.getBody();

        logger.info("payload: " + body);

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray readings = result.getJsonArray("weather");
            final String cityName = result.getString("name");
            final float temperature = (float)result.getJsonObject("main").getJsonNumber("temp").doubleValue();
            return readings.stream()
                    .map(v -> (JsonObject)v)
                    .map(WeatherModel::create)   // map each JsonObject in the array into the Weather Model
                    .map(w -> { 
                        w.setCityName(cityName);
                        w.setTemp(temperature);
                        return w;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }       

        return Collections.emptyList(); */
        return WeatherModel.create(resp.getBody());
    }
  /*   public WeatherModel getCity (final String city) {
        WeatherModel result = (WeatherModel)RedisTemplate.opsForHash()
                            .get(Constants.CITY_ENTITY, city);
        
        return result;
    } */

}
