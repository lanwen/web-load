package ru.lanwen.web.load;

import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.bootstrap.HttpServer;
import org.apache.http.impl.nio.bootstrap.ServerBootstrap;
import org.apache.http.nio.protocol.BasicAsyncRequestHandler;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class PingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpProcessor httpproc = HttpProcessorBuilder.create()
                .add(new ResponseDate())
                .add(new ResponseServer("MyServer-HTTP/1.1"))
                .add(new ResponseContent())
                .add(new ResponseConnControl())
                .build();

        final HttpServer server = ServerBootstrap.bootstrap()
                .setListenerPort(8080)
                .setHttpProcessor(httpproc)
                .registerHandler("/ping", new BasicAsyncRequestHandler((httpRequest, httpResponse, httpContext) -> {
                    httpResponse.setStatusCode(HttpStatus.SC_OK);
                    httpResponse.setEntity(new StringEntity("pong"));
                }))
                .create();
        server.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown!");
                server.shutdown(5, TimeUnit.SECONDS);
            }
        });

        System.out.println("Started on 8080");
        server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }
}
