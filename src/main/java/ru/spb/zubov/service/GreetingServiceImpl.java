package ru.spb.zubov.service;

import io.grpc.stub.StreamObserver;
import ru.spb.zubov.grpc.GreetingServiceGrpc;

import java.util.UUID;

import static ru.spb.zubov.grpc.GreetingServiceOuterClass.HelloRequest;
import static ru.spb.zubov.grpc.GreetingServiceOuterClass.HelloResponse;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greetings(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HelloResponse helloResponse = HelloResponse.newBuilder()
                    .setGreeting("Hello from " + request.getName() + UUID.randomUUID())
                    .build();
            responseObserver.onNext(helloResponse);
        }
        responseObserver.onCompleted();
    }
}
