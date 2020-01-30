package com.guames.grpc.service;

import com.guames.grpc.proto.greet.GreetRequest;
import com.guames.grpc.proto.greet.GreetResponse;
import com.guames.grpc.proto.greet.GreetServiceGrpc;
import com.guames.grpc.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();

        String result = "Hello" + firstName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
