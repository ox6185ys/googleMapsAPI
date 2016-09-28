package com.company;
import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.*;
import java.io.BufferedReader;//import Bufferedreader for key.txt
import java.io.FileReader;
import java.util.Scanner;
//week6 geocoding
public class Main {

    static Scanner stringScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);

    public static void main(String[] args)throws Exception{

        String key = null;//The following try/catch will throw an exception if my key.txt file is FUBAR.
        try{
            BufferedReader reader = new BufferedReader(new FileReader("key.txt"));
            key = reader.readLine();//This holds the contents of key.txt if it exists.
            System.out.println(key);//This prints my key if it exists.
        }
        catch (Exception ioe){
            System.out.println("No key file found, or could not be read.");
            System.exit(-1);
        }
        GeoApiContext context = new GeoApiContext().setApiKey(key);
        GeocodingApiRequest locationRequest = new GeocodingApiRequest(context);
        System.out.println("Enter a city to see its latitude and longitude.");
        String location = stringScanner.nextLine();
        //String baseURL = String.format("https://maps.googleapi.com/maps/api/geocode/json?address=%s&key=%s", location, key);

        GeocodingResult[] geocodingResultsArray = locationRequest.address(location).await();
        //System.out.println(baseURL);
        //System.out.println(geocodingResults[0]);
        Bounds bounds = new Geometry().bounds;
        System.out.println(locationRequest);

        if (geocodingResultsArray.length > 0) {
            GeocodingResult firstGeocodingResult = geocodingResultsArray[0];
            LatLng latlong = firstGeocodingResult.geometry.location;//geometry and location are tags under "GeocodingResult".

            System.out.println(latlong);

            //todo - Use the latlong object to make an elevation request.
            ElevationResult[] elevationResultsArray = ElevationApi.getByPoints(context, latlong).await();

        } else {
            System.out.println("No results found");
            //todo ask user again, or whatever
        }




//Below is the beginning project which I borrowed from to make the above.

        //        System.out.println(String.format("The elevation of MCTC above sea level is %.2f meters",mctcElevation.elevation));
/*        System.out.println("Enter a location to see LatLng.");
        String location = stringScanner.nextLine();*/
/*
import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.LatLng;
import java.io.BufferedReader;
import java.io.FileReader;
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
           }*/
    }
}