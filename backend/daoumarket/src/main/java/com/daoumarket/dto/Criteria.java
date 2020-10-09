package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {
    
    private int page;
    private int perPageNum;
    
    public int getPageStart() {
        return (this.page-1) * perPageNum;
    }
    
    public void setPage(int page) {
        if(page <= 0) {
        	this.page = 1;
        }
        else {
        	this.page = page;
        }
    }
    
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }

    public Criteria() {
        this.page = 1;
        this.perPageNum = 8;
    }
    
	public Criteria(int page) {
		this.page = page;
		this.perPageNum = 8;
	}

	@Builder
	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
}