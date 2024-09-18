package org.sangnk.partyservice.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
//import org.sangnk.partyservice.interceptor.GrpcClientInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

@Slf4j
@Component
public class GrpcChannelManager {

    @Getter
    private final ManagedChannel serverChannel;


    public GrpcChannelManager(@Value("${grpc-server.url}") String url,
                              @Value("${grpc-server.port}") int port) {
        serverChannel = ManagedChannelBuilder.forAddress(url, port).usePlaintext().build();
        log.info("GrpcChannelManager created");
    }




}
