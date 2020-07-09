package com.virtusa.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.project.geocode.GeocodeResult;
import com.virtusa.project.okhttp3.OkHttpClient;

import com.virtusa.project.okhttp3.Request;
import com.virtusa.project.okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
@RestController
public class GeocodeController {
   @RequestMapping(path = "/geocode", method = RequestMethod.GET )
   public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
       OkHttpClient client = new OkHttpClient();
       String encodedAddress = URLEncoder.encode(address, "UTF-8");
       int key;
	Request request = ((Object) new Request.Builder()
               .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress))
               .get()
               .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
               .addHeader("x-rapidapi-key", {your-api-key-here}/*  Use your API Key here */)
               .build();
       ResponseBody responseBody = ((Object) client.newCall(request)).execute().body();
       ObjectMapper objectMapper = new ObjectMapper();
       GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
       return result;
   }
}