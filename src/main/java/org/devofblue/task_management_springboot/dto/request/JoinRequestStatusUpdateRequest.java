package org.devofblue.task_management_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestStatusUpdateRequest {
    @NotBlank
    private String status;
}
