package org.sangnk.userservice.service;

import com.google.rpc.Code;
import com.google.rpc.Status;
import com.sangnk.grpc.user.UserId;
import com.sangnk.grpc.user.UserInput;
import com.sangnk.grpc.user.UserMessage;
import com.sangnk.grpc.user.UserServiceGrpc;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.sangnk.userservice.dto.CreateUserRequest;
import org.sangnk.userservice.dto.CreateUserResponse;
import org.sangnk.userservice.dto.UserDTO;
import org.sangnk.userservice.exception.AlreadyExistsException;
import org.sangnk.userservice.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author sangnk
 * @Created 17/09/2024 - 10:56 SA
 * @project = grpc_test_microservice
 * @_ Mô tả:
 */
@Slf4j
@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    public List<UserDTO> users = List.of(
            UserDTO.builder().id(1).name("sang").build(),
            UserDTO.builder().id(2).name("nguyen").build()
    );
    @Override
    public void create(UserInput request, StreamObserver<UserId> responseObserver) {
        log.info("Server - create user");
        UserId response;
        try {
            int id = createUser(request.getName());
            responseObserver.onNext(UserId.newBuilder()
                    .setId(id)
                    .build());
            responseObserver.onCompleted();
            log.info("Server - user ID: " + id);
        } catch (AlreadyExistsException e) {
            Status status = Status.newBuilder()
                    .setCode(Code.ALREADY_EXISTS.getNumber())
                    .setMessage("User with name: " + request.getName() + " already exists")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void getById(UserId request, StreamObserver<UserMessage> responseObserver) {
        responseObserver.onNext(getUserById(request.getId())
                .map(this::toUser)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + request.getId() + " not exists")));
        log.info("User found");
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(com.google.protobuf.Empty request, StreamObserver<UserMessage> responseObserver) {
        users.stream()
                .map(this::toUser)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    public int createUser(String name) {
        if (existsByName(name))
            throw new AlreadyExistsException("User with name: " + name + " already exists");
        UserDTO user = new UserDTO();
        user.setId(new Random().nextInt(Integer.MAX_VALUE));
        user.setName(name);
        save(user);
        return user.getId();
    }

    private boolean existsByName(String name) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }

    private void save(UserDTO user) {
        users.add(user);
    }

    public Optional<UserDTO> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    private UserMessage toUser(UserDTO foundUser) {
        return UserMessage.newBuilder()
                .setId(foundUser.getId())
                .setName(foundUser.getName())
                .build();
    }
}