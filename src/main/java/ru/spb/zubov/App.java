package ru.spb.zubov;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ru.spb.zubov.service.GreetingServiceImpl;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new GreetingServiceImpl())
                .build();

        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}
