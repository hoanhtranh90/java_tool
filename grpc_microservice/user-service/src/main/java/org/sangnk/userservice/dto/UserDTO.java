package org.sangnk.userservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author sangnk
 * @Created 17/09/2024 - 11:08 SA
 * @project = grpc_test_microservice
 * @_ Mô tả:
 */
@Data
@Builder
public class UserDTO {
    private Integer id;
    private String name;

    public UserDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO() {
    }
}
