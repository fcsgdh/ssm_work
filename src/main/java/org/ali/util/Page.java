package org.ali.util;

import org.ali.entity.PageParam;

public class Page<T> {

    public PageParam<T> setPages(PageParam<T> pageParam) {
        int total = pageParam.getTotal();
        int limit = pageParam.getLimit();
        pageParam.setPages(total % limit == 0 ? total/limit:total/limit+1);
        return pageParam;
    }
}
