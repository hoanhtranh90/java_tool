package org.sangnk.partyservice.observer;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.sangnk.partyservice.dto.output.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *  Receive all users from server
 */
@Slf4j
public class GetAllUsersStreamObserver implements StreamObserver<com.sangnk.grpc.user.UserMessage> {

    private final CompletableFuture<List<UserDTO>> future;
    private final List<UserDTO> users;


    public GetAllUsersStreamObserver(CompletableFuture<List<UserDTO>> allUsersResponse) {
        this.future = allUsersResponse;
        this.users = new ArrayList<>();
    }

    /**
     * <p> Called when received a next user from server
     * <p> Add each incoming user id to {@link #users} list
     */
    @Override
    public void onNext(com.sangnk.grpc.user.UserMessage value) {
        users.add(new UserDTO(value.getId(), value.getName()));
        log.info("User received");
    }

    @Override
    public void onError(Throwable t) {
        future.completeExceptionally(t);
        log.error("Exception: " + t);
    }

    /**
     * Called when server finished to send users. After last sent
     */
    @Override
    public void onCompleted() {
        future.complete(users);
        log.info("All users received");
    }
}
