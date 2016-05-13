package cn.dream.bean;

import java.util.List;

public class QueryResult<T> {
    private List<T> resultlist;
    /*总页数*/
    private long totalrecord;
    
    public List<T> getResultlist() {
        return resultlist;
    }
    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }
    public long getTotalrecord() {
        return totalrecord;
    }
    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
    }
}
