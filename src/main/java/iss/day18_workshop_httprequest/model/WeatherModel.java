package iss.day18_workshop_httprequest.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class WeatherModel {
    
    private String cityName;
    private String main;
    private String description;
    private String icon;
    private Float temp;
    private Float latitude;
    private Float longtitude;

    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getMain() {
        return this.main;
    }
    public void setMain(String main) {
        this.main = main;
    }
    public String GetDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = "http://openweathermap.org/img/wn/%s@2x.png".formatted(icon);
    }
    public Float getTemp() {
        return this.temp;
    }
    public void setTemp(Float temp) {
        this.temp = temp;
    }
    public Float getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
    public Float getLongtitude() {
        return this.longtitude;
    }
    public void setLongtitude(Float longtitude) {
        this.longtitude = longtitude;
    }

    public static WeatherModel create(JsonObject obj) {
        WeatherModel w = new WeatherModel();
        w.setCityName(obj.getString("name"));
        JsonArray arr = obj.getJsonArray("weather");
        /* w.setDescription(obj.getString("description")); */
        if(!arr.isEmpty()) {
            JsonObject wo = arr.getJsonObject(0);
            w.setMain(wo.getString("main"));
            w.setDescription(wo.getString("description"));
            w.setIcon(wo.getString("icon"));
        }
        w.setTemp((float)obj.getJsonObject("main").getJsonNumber("temp").doubleValue());
        return (w);
    }
    public static WeatherModel create(String jsonString) {
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            return create(reader.readObject());            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WeatherModel();
    }

    public JsonObject toJson() {       // adding parameters add converting to Json object
        return Json.createObjectBuilder()
                .add("cityName", cityName)
                .add("main", main)
                .add("description", description)
                .add("icon", icon)
                .add("temperature", temp)
                .build();
    }

    @Override
    public String toString() {
        return this.toJson().toString();    // to convert Json objwct to String
    }
}

