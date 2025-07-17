package com.example.quanlychitieuv2.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageResponse<T> {
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private T data;
}
