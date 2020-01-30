package com.guames.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051).build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Receive Shutdown Server!");
            server.shutdown();
            System.out.println("Sucessfully stopped the server!");
        }));

        server.awaitTermination();
    }
}
