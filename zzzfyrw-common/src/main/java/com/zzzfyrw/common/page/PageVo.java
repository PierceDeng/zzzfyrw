package com.zzzfyrw.common.page;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageVo implements IPageHandler, Serializable {

    private static final long serialVersionUID = 1L;

    //开始页
    private int pageIndex = 1;
    //记录数
    private int pageCount = 10;

    @Override
    public int getCurrentPage() {
        if(pageIndex > 0){
            return (pageIndex - 1) * pageCount;
        }
        return 1;
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }
}
