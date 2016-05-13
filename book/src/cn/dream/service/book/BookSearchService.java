package cn.dream.service.book;

import cn.dream.bean.QueryResult;
import cn.dream.bean.book.BookInfo;

public interface BookSearchService {
    /**
     * 搜索商品
     * @param keyword 关键字
     * @param firstResult 开始索引
     * @param maxResult 每页获取的记录数
     * @return
     */
    public QueryResult<BookInfo> query(String keyword, int firstResult, int maxResult);

}