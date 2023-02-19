package io.bankbridge.handler;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.model.BankModel;
import io.bankbridge.model.BankModelList;
import spark.Request;
import spark.Response;

public class BanksCacheBased {


    public static CacheManager cacheManager;
    public static ArrayList<BankModel> banks1 = new ArrayList<>(); //list of objects of all banks in banks-v1.json

    public static void init() throws Exception {

        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().withCache("banks", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();
        Cache cache = cacheManager.getCache("banks", String.class, String.class);
        try {
            BankModelList models = new ObjectMapper().readValue(
                    Thread.currentThread().getContextClassLoader().getResource("banks-v1.json"), BankModelList.class);
            for (BankModel model : models.banks) {
                banks1.add(model); //adding bank by bank to list of objects
                cache.put(model.bic, model.name);
            }
        } catch (Exception e) {
            throw e;
        }


    }

    public static String handle(Request request, Response response) {

        List<Map> result = new ArrayList<>();

        cacheManager.getCache("banks", String.class, String.class).forEach(entry -> {
            Map map = new HashMap<>();
            map.put("id", entry.getKey());
            map.put("name", entry.getValue());
            result.add(map);

        });
        try {
            String resultAsString = new ObjectMapper().writeValueAsString(banks1); //showing data of all banks on localhost
            return resultAsString;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while processing request");
        }

    }

}
