package utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Pagination {
    Object pageable;
    Object sort;
    boolean last;
    long totalElements;
    long totalPages;
    long size;
    long number;
    boolean first;
    long numberOfElements;
    boolean empty;
}
