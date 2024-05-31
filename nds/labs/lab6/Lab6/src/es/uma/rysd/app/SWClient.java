package es.uma.rysd.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;
public class SWClient {
    // java.util.logging
    java.util.logging.Logger logging = java.util.logging.Logger.getLogger(this.getClass().getName());

    private final String app_name = "SW QUIZ";
    private final int year = 2024;

    private final String url_api = "https://swapi.dev/api/";

    // Auxiliary methods provided
    // Gets the URL of the resource id of the type resource
    public String buildResourceUrl(String resource, Integer id) {
        return url_api + resource + "/" + id + "/";
    }

    // Given a resource URL, gets its ID
    public Integer extractIdFromUrl(String url) {
        String[] parts = url.split("/");

        return Integer.parseInt(parts[parts.length - 1]);
    }

    // Queries a resource and returns how many elements it has
    public int countResources(String resource) {
        // Create the corresponding URL: https://swapi.dev/api/{resource}/ replacing resource with the parameter
        String endpoint = url_api + resource;

        // Create the connection from the URL
        HttpURLConnection connection = null;
        URL service = null;
        try {
            service = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = (HttpURLConnection) service.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Add the headers User-Agent and Accept (see the statement)
        connection.setRequestProperty("User-Agent", String.format("%s-%d", app_name, year));
        connection.setRequestProperty("Accept", "application/json");

        // * Indicate that it is a GET request
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        // * Check that the response code received is correct
        try {
            int status = connection.getResponseCode();
            if (status != 200){
                logging.severe(" SWClient countResources() :: Error getting the resources. Status code: " + status);
                return 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Deserialize the response to ResourceCountResponse
        Gson parser = new Gson();
        InputStream in = null;
        try {
            in = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResourceCountResult c = parser.fromJson(new InputStreamReader(in), ResourceCountResult.class);

        // Return the number of elements
        return c.count;
    }

    public Person getPerson(String urlname) {
        Person p = null;

        // * Just in case it comes as http, we change it to https
        urlname = urlname.replaceAll("http:", "https:");

        // * Create the connection from the received URL
        HttpURLConnection connection = null;
        URL service = null;
        try {

            service = new URL(urlname);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = (HttpURLConnection) service.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Add the headers User-Agent and Accept (see the statement)
        connection.setRequestProperty("User-Agent", String.format("%s-%d", app_name, year));
        connection.setRequestProperty("Accept", "application/json");

        // * Indicate that it is a GET request
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        // * Check that the response code received is correct
        try {
            int status = connection.getResponseCode();
            if (status != 200){
                logging.severe("SWClient getPerson() :: Error getting the resource. Status code: " + status);
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Deserialize the response to Person
        Gson parser = new Gson();
        InputStream in = null;

        try {
            in = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        p = parser.fromJson(new InputStreamReader(in), Person.class);

        // For questions 2 and 3 (do not need to complete this for question 1)
        // From the URL in the homeworld field, get the planet data and store it in the homeplanet attribute
        p.homeplanet = getWorld(p.homeworld);

        return p;
    }

    public World getWorld(String urlname) {
        World p = null;

        // * Just in case it comes as http, we change it to https
        urlname = urlname.replaceAll("http:", "https:");

        // * Create the connection from the received URL
        HttpURLConnection connection = null;
        URL service = null;
        try {
            service = new URL(urlname);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = (HttpURLConnection) service.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Add the headers User-Agent and Accept (see the statement)
        connection.setRequestProperty("User-Agent", String.format("%s-%d", app_name, year));
        connection.setRequestProperty("Accept", "application/json");

        // * Indicate that it is a GET request
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        // * Check that the response code received is correct
        try {
            int status = connection.getResponseCode();
            if (status != 200){
                logging.severe("SWClient getWorld() :: Error getting the resource");
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Deserialize the response to World
        Gson parser = new Gson();
        InputStream in = null;
        try {
            in = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        p = parser.fromJson(new InputStreamReader(in), World.class);
        return p;
    }

    public Person searchPersonByName(String name) {
        Person p = null;

        // * Create the connection from the received URL
        HttpURLConnection connection = null;
        URL service = null;
        String endpoint = null;

        try {
            endpoint = url_api + "people/?search=" + URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            service = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = (HttpURLConnection) service.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Add the headers User-Agent and Accept (see the statement)
        connection.setRequestProperty("User-Agent", String.format("%s-%d", app_name, year));
        connection.setRequestProperty("Accept", "application/json");

        // * Indicate that it is a GET request
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        // * Check that the response code received is correct
        try {
            int status = connection.getResponseCode();
            if (status != 200){
                logging.severe("SWClient searchPersonByName() :: Error getting the resource");
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // * Deserialize the response to QueryResponse -> Use the first position of the array as the result
        Gson parser = new Gson();
        InputStream in = null;
        try {
            in = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        QueryResponse query = parser.fromJson(new InputStreamReader(in), QueryResponse.class);
        if (query.results.length == 0){
            logging.severe("SWClient searchPersonByName() :: Error: there is no character with that name");
            return null;
        }
        p = query.results[0];
        p.homeplanet = getWorld(p.homeworld);
        return p;
    }
}
