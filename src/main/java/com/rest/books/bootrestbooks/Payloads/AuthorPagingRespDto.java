package com.rest.books.bootrestbooks.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPagingRespDto {

    private List<AuthorRespDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalRecords;
    private int totalPages;

    private boolean lastPage;
}
