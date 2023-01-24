package com.rest.books.bootrestbooks.Payloads;

import lombok.Data;
import java.util.List;


@Data
public class CustomerPagingRespDto {

    private List<CustomerDtoResponse> content;
    private int pageNumber;
    private int pageSize;
    private long totalRecords;
    private int totalPages;
    private boolean lastPage;
}
