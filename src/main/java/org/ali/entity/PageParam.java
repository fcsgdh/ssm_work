package org.ali.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageParam<T> {
    private int limit;
    private int currentPage;
    private List<T> pageList;
    private int total;
    private int pages;

    public PageParam(int currentPage, int limit) {
        this.limit = limit;
        this.currentPage = currentPage;
    }
}
