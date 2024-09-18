package org.sangnk.partyservice.service;


import com.google.protobuf.Empty;
import com.sangnk.grpc.user.UserId;
import com.sangnk.grpc.user.UserInput;
import com.sangnk.grpc.user.UserMessage;
import com.sangnk.grpc.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.sangnk.partyservice.config.GrpcChannelManager;
import org.sangnk.partyservice.dto.output.UserDTO;
import org.sangnk.partyservice.observer.DeletedUsersStreamObserver;
import org.sangnk.partyservice.observer.GetAllUsersStreamObserver;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.*;


@Slf4j
@Service
public class UserServiceClient extends UserServiceGrpc.UserServiceImplBase {
    ManagedChannel channel;
    public static final int ONE_SECOND = 1_000;
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;
    private UserServiceGrpc.UserServiceFutureStub futureStub;
    private UserServiceGrpc.UserServiceStub streamingStub;
    private RestTemplate restTemplate;


    private final ExecutorService executorService = Executors.newCachedThreadPool();


    public UserServiceClient(GrpcChannelManager grpcChannelManager) {
        this.blockingStub = UserServiceGrpc.newBlockingStub(grpcChannelManager.getServerChannel());
        this.futureStub = UserServiceGrpc.newFutureStub(grpcChannelManager.getServerChannel());
        this.streamingStub = UserServiceGrpc.newStub(grpcChannelManager.getServerChannel());
        this.restTemplate = new RestTemplate();
    }


    public Integer create(String name) {
        return blockingStub.create(UserInput.newBuilder()
                                            .setName(name)
                                            .build())
                           .getId();
    }


    public UserDTO getById(Integer id) {
        UserMessage response = blockingStub.getById(UserId.newBuilder()
                                                          .setId(id)
                                                          .build());
        return new UserDTO(response.getId(), response.getName());
    }



    @SneakyThrows
    public List<UserDTO> getAll() {
        CompletableFuture<List<UserDTO>> allUsersResponse = new CompletableFuture<>();
        streamingStub.getAll(Empty.newBuilder().build(), new GetAllUsersStreamObserver(allUsersResponse));
        try {
            return allUsersResponse.get();
        } catch (InterruptedException | ExecutionException e) {
            throw e.getCause();
        }
    }

    @SneakyThrows
    public void deleteMultiple(List<Integer> ids) {
        CompletableFuture<List<Integer>> future = new CompletableFuture<>();

        StreamObserver<UserId> stream = streamingStub.deleteMultiple(new DeletedUsersStreamObserver(future));

        for (Integer userId : ids) {
            stream.onNext(UserId.newBuilder()
                                .setId(userId)
                                .build());
            log.info("Removing user: " + userId);
        }
        stream.onCompleted();
        log.info("All users sent for removal");

        try {
            List<Integer> deletedUsersIdList = future.get();
            log.info("All users removed: " + deletedUsersIdList.toString());
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.toString());
            throw e.getCause();
        }
    }



}
