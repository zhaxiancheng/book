package cn.dream.service.book.impl;

import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;
import org.compass.core.CompassQuery.SortDirection;
import org.compass.core.CompassQuery.SortPropertyType;

import cn.dream.bean.QueryResult;
import cn.dream.bean.book.BookInfo;

public class QueryCallback implements CompassCallback<QueryResult<BookInfo>> {
    private String keyword;
    private int firstResult;
    private int maxResult;

    public QueryCallback(String keyword, int firstResult, int maxResult) {
        this.firstResult = firstResult;
        this.maxResult = maxResult;
        this.keyword = keyword;
    }

    public QueryResult<BookInfo> doInCompass(CompassSession session) throws CompassException {
        /*查询指定类别的匹配记录，并按position降序排序
         CompassQueryBuilder queryBuilder = session.queryBuilder();
         CompassHits hits = queryBuilder.bool()
            .addMust(queryBuilder.spanEq("typeid", typeid))
            .addMust(queryBuilder.queryString(keyword).toQuery())
            .toQuery().addSort("position", SortPropertyType.FLOAT, SortDirection.REVERSE)
            .hits();//sql: typeid=1 and (xxxx like ?) order by positoin desc
         */
        CompassHits hits = session.find(keyword);//5
        QueryResult<BookInfo> qr = new QueryResult<BookInfo>();
        qr.setTotalrecord(hits.length());//获取匹配记录的总数
        int length = firstResult + maxResult;
        if(length>hits.length()) length = hits.length();
        List<BookInfo> products = new ArrayList<BookInfo>();
        for(int i = firstResult ; i < length ; i++){
            BookInfo book = (BookInfo)hits.data(i);
            if(hits.highlighter(i).fragment("bookName")!=null)
                book.setName(hits.highlighter(i).fragment("bookName"));
            if(hits.highlighter(i).fragment("description")!=null)
                book.setDescription(hits.highlighter(i).fragment("description"));
            products.add(book);
        }
        qr.setResultlist(products);
        return qr;
    }

}
