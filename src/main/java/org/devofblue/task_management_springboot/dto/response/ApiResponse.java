package org.devofblue.task_management_springboot.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private T data;
    private String message;
    private Instant timestamp;
    private PaginationMeta pagination;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaginationMeta {
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean isLast;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> successWithPagination(T data, String message, org.springframework.data.domain.Page<?> page) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .timestamp(Instant.now())
                .pagination(PaginationMeta.builder()
                        .pageNumber(page.getNumber())
                        .pageSize(page.getSize())
                        .totalElements(page.getTotalElements())
                        .totalPages(page.getTotalPages())
                        .isLast(page.isLast())
                        .build())
                .build();
    }

    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .timestamp(Instant.now())
                .build();
    }
}
