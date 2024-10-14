package utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    String message;

    @Default
    int statusCode = 0;

    Object object;

    Object pagination;

    List<Object> errors;

    public static <T, D> List<D> convertPageToDto(Page<T> page, Class<D> dtoClass) {
        List<D> dtoList = new ArrayList<>();

        for (T entity : page.getContent()) {
            D dto = convertEntityToDto(entity, dtoClass);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public static <T, D> List<D> convertListToDto(List<T> results, Class<D> dtoClass) {
        List<D> dtoList = new ArrayList<>();

        for (T entity : results) {
            D dto = convertEntityToDto(entity, dtoClass);
            dtoList.add(dto);
        }

        return dtoList;
    }

    private static <T, D> D convertEntityToDto(T entity, Class<D> dtoClass) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(entity, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert entity to DTO.", e);
        }
    }

    public static <T, D> OperationResponse withPagination(Page<T> results, Class<D> clazz) {
        return OperationResponse
                .builder()
                .object(convertPageToDto(results, clazz))
                .pagination(
                        Pagination.builder()
                                .pageable(results.getPageable())
                                .sort(results.getSort())
                                .last(results.isLast())
                                .totalElements(results.getTotalElements())
                                .totalPages(results.getTotalPages())
                                .size(results.getSize())
                                .number(results.getNumber())
                                .first(results.isFirst())
                                .numberOfElements(results.getNumberOfElements())
                                .empty(results.isEmpty())
                                .build()
                ).build();
    }

    public static <T, D> OperationResponse withList(List<T> results, Class<D> clazz) {
        return OperationResponse
                .builder()
                .object(convertListToDto(results, clazz))
                .build();
    }

    public static ResponseEntity<OperationResponse> notFoundMessage(String message) {
        List<Object> errors = new ArrayList<>();
        errors.add(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(OperationResponse.builder()
                        .statusCode(1)
                        .message("Error")
                        .errors(errors)
                        .build()
                );
    }

    public static ResponseEntity<OperationResponse> BadRequestMessage(String message) {
        List<Object> errors = new ArrayList<>();
        errors.add(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OperationResponse.builder()
                        .statusCode(1)
                        .message(message)
                        .errors(errors)
                        .build()
                );
    }

    public static ResponseEntity<OperationResponse> tenantNullMessage() {
        List<Object> errors = new ArrayList<>();
        errors.add("Tenant should not be null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OperationResponse.builder()
                        .statusCode(1)
                        .message("Error")
                        .errors(errors)
                        .build()
                );
    }

    public static ResponseEntity<OperationResponse> duplicateMessage(String s) {
        List<Object> errors = new ArrayList<>();
        errors.add(s);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OperationResponse.builder()
                        .statusCode(1)
                        .message("Error")
                        .errors(errors)
                        .build()
                );
    }
}
