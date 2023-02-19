package io.bankbridge.handler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.model.BankModel;

import spark.Request;
import spark.Response;

public class BanksRemoteCalls {

    private static Map config;


    public static void init() throws Exception {
        config = new ObjectMapper()
                .readValue(Thread.currentThread().getContextClassLoader().getResource("banks-v2.json"), Map.class);
    }

    private String uri = "http://localhost:1234";

    public static String handle(Request request, Response response) {
        List<Map> result = new ArrayList<>();
        ArrayList<BankModel> listOfBanks = new ArrayList<>();
        try {

            Collection<String> https = config.values();
            Object[] arr = https.toArray();
            System.out.println(config.values());
            for (int i = 0; i < arr.length; i++) {
                URL url = new URL(arr[i].toString());
                String path = url.getPath();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    BankModel bank = new BankModel();
                    String[] last = new String[9];
                    int index = 0;
                    //parsing the string that was picked up from the localhost:1234
                    while ((inputLine = in.readLine()) != null) {

                        String[] res2 = inputLine.split(":");
                        for (String m : res2) {
                            System.out.println(m);
                            last[index] = m;
                        }
                        index++;
                    }
                    in.close();

                    //taking the results that are stored in array last and putting them to the attributes of the bank
                    bank.bic = last[1].substring(0, last[1].length() - 1);
                    bank.name = last[2].substring(0, last[2].length() - 1);
                    bank.countryCode = last[3].substring(0, last[3].length() - 1);
                    bank.auth = last[4];
                    listOfBanks.add(bank);

                } else {
                    System.out.println("GET request did not work.");
                }
                con.disconnect();
                System.out.println("path: " + path);

            }

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String resultAsString = new ObjectMapper().writeValueAsString(listOfBanks); //result
            return resultAsString;  //result will display products as null because bankmodel has products because of the banks-v1.json
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while processing request");
        }
    }
}
