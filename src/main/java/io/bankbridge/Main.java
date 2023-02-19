package io.bankbridge;

import static spark.Spark.get;
import static spark.Spark.port;


import io.bankbridge.handler.BanksCacheBased;
import io.bankbridge.handler.BanksRemoteCalls;
import spark.Spark;

public class Main {

    public static void main(String[] args) throws Exception {

        port(8080);
        Spark.options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });


        BanksCacheBased.init();
        BanksRemoteCalls.init();

        get("/v1/banks/all", (request, response) -> BanksCacheBased.handle(request, response));

        get("/v2/banks/all", (request, response) -> BanksRemoteCalls.handle(request, response));
    }
}