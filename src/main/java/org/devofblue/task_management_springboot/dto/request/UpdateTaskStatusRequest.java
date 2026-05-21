package org.devofblue.task_management_springboot.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.devofblue.task_management_springboot.enums.TaskStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStatusRequest {

    @NotNull(message = "Status is required")
    private TaskStatus status;
}
