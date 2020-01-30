package com.guames.grpc.client;

import com.guames.grpc.proto.dummy.DummyServiceGrpc;
import com.guames.grpc.proto.greet.GreetRequest;
import com.guames.grpc.proto.greet.GreetResponse;
import com.guames.grpc.proto.greet.GreetServiceGrpc;
import com.guames.grpc.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // create a greet service client blocking -> sync
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        // create a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Gustavo")
                .setLastName("Ames")
                .build();

        // create a greet request
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // call a RPC and get back a greetResponse
        GreetResponse greetResponse = greetClient.greet(greetRequest);

        // show response
        System.out.println(greetResponse.getResult());

        channel.shutdown();
    }
}
