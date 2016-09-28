package com.company;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    static Scanner stringScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);

//week6 geocoding

    //import buffread for key.txt
    public static void main(String[] args) {
        String key = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("key.txt"));
            key = reader.readLine();
            System.out.println(key);
        }
        catch (Exception ioe){
            System.out.println("No key file found, or could not be read.");
            System.exit(-1);
        }


        GeoApiContext context = new GeoApiContext().setApiKey(key);
        GeocodingApiRequest locationRequest = new GeocodingApiRequest(context);
        System.out.println("Enter a location to see LatLng.");
        String location = stringScanner.nextLine();
        String baseURL = String.format("https://maps.googleapi.com/maps/api/geocode/json?address=%s&key=%s", location, key);

        GeocodingApiRequest jsonResult = locationRequest.address(baseURL);


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