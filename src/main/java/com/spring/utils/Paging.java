package com.spring.utils;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class Paging<T> {
    /**
     * total pages.
     */
    private long totalPages;

    /**
     * total elements.
     */
    private long totalElements;

    /**
     * page size.
     */
    private long pageSize;

    /**
     * page number.
     */
    private long pageNumber;

    /**
     * content.
     */
    private List<T> content;

    /**
     * Instantiates a new paging.
     * @param dtos the dtos
     */
    Paging(List<T> dtos) {
        this.content = dtos;
    }

    /**
     * Instantiates a new paging.
     * @param page the page
     */
    Paging(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.content = page.getContent();
    }

    /**
     * Instantiates a new paging.
     * @param entities the entities
     * @param dtos     the dtos
     */
    Paging(Page<?> entities, List<T> dtos) {
        this.totalPages = entities.getTotalPages();
        this.totalElements = entities.getTotalElements();
        this.pageSize = entities.getPageable().getPageSize();
        this.pageNumber = entities.getPageable().getPageNumber();
        this.content = dtos;
    }

    /**
     * @param <T>  the generic type
     * @param dtos the dtos
     * @return the paging
     */
    public static <T> Paging<T> of(List<T> dtos) {
        return new Paging<>(dtos);
    }

    /**
     * @param <T>      the generic type
     * @param entities the entities
     * @return the paging
     */
    public static <T> Paging<T> of(Page<T> entities) {
        return new Paging<>(entities);
    }

    /**
     * @param <T>      the generic type
     * @param entities the entities
     * @param dtos     the dtos
     * @return the paging
     */
    public static <T> Paging<T> of(Page<?> entities, List<T> dtos) {
        return new Paging<>(entities, dtos);
    }
}
