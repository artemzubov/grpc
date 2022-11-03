package ru.spb.zubov;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.spb.zubov.grpc.GreetingServiceGrpc;
import ru.spb.zubov.grpc.GreetingServiceOuterClass;

import java.util.Iterator;

import static ru.spb.zubov.grpc.GreetingServiceGrpc.newBlockingStub;

public class Client {
    public static void main( String[] args ) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forTarget("localhost:8080")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = newBlockingStub(managedChannel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Artem")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> greetings = stub.greetings(request);
        while (greetings.hasNext()) {
            System.out.println(greetings.next());
        }

        managedChannel.shutdownNow();
    }
}
