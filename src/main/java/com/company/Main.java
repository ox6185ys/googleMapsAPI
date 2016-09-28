package com.company;
import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.LatLng;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
//week6 geocoding
    public static void main(String[] args) {
        String key = null;
        try(BufferedReader reader = new BufferedReader(new FileReader("key.txt"))){
            key = reader.readLine();
            System.out.println(key);
        }catch (Exception ioe){
            System.out.println("No key file found, or could not be read.");
            System.exit(-1);}
            GeoApiContext context = new GeoApiContext().setApiKey(key);
//44.973074, -93.283356 MCTC LatLng
//37.7749, 122.4194 SF LatLng
            LatLng mctcLatLng = new LatLng(37.7749, -122.4197);
        ElevationResult[] results = new ElevationResult[0];
        try {
            results = ElevationApi.getByPoints(context, mctcLatLng).await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (results.length >= 1){
                ElevationResult mctcElevation = results[0];
                System.out.println("The elevation of MCTC is "+mctcElevation.elevation+" meters.");
                System.out.println(String.format("The elevation of MCTC above sea level is %.2f meters",mctcElevation.elevation));
           }
     }
}