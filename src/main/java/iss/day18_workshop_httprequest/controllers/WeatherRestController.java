package iss.day18_workshop_httprequest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.day18_workshop_httprequest.model.WeatherModel;
import iss.day18_workshop_httprequest.service.WeatherService;



@RestController
@RequestMapping(path="/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherRestController {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherRestController.class);

    @Autowired
    private WeatherService weatherSvc;

    @CrossOrigin
    @GetMapping(path="{city}")
    // no need to include value='city' if the string name is the same
    public ResponseEntity<String> getWeatherAsJson (@PathVariable String city) {
        logger.info("City >> " + city);

      WeatherModel weather = weatherSvc.getWeather(city);
      return ResponseEntity.ok(weather.toJson().toString());
        
     /*    RequestEntity<Void> req = RequestEntity
                        .get(Constants.URL_WEATHER)
                        .accept(MediaType.APPLICATION_JSON)
                        .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader();
            JsonObject data = reader.readObject();
        } catch (Exception ex) {
            logger.warn("Error occured: " + ex.getMessage());
        }
          */
        
    }
}
