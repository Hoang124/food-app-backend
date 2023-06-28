package com.example.datn.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomPageable implements Pageable{
	
	private int pageNumber;
	private int pageSize;
	private Sort sort;
	
    public CustomPageable(int pageNumber, int pageSize, Sort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return (long) pageNumber * (long) pageSize;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new CustomPageable(pageNumber + 1, pageSize, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return new CustomPageable(pageNumber > 0 ? pageNumber - 1 : 0, pageSize, sort);
    }

    @Override
    public Pageable first() {
        return new CustomPageable(0, pageSize, sort);
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 0;
    }

	@Override
	public Pageable withPage(int pageNumber) {
		return new CustomPageable(pageNumber, pageSize, sort);
	}

}
