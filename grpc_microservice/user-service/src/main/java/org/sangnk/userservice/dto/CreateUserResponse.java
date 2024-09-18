package org.sangnk.userservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author sangnk
 * @Created 17/09/2024 - 11:06 SA
 * @project = grpc_test_microservice
 * @_ Mô tả:
 */
@Data
@Builder
public class CreateUserResponse {
    private Integer userId;
}
