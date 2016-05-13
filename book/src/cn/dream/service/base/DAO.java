package cn.dream.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

import cn.dream.bean.QueryResult;

public interface DAO<T> {
    /**
     * 获取记录总数
     * @param entityClass 实体类
     * @return
     */
    public long getCount();
    /**
     * 清除一级缓存的数据
     */
    public void clear();
    /**
     * 保存实体
     * @param entity 实体id
     */
    public void save(T entity);
    /**
     * 更新实体
     * @param entity 实体id
     */
    public void update(T entity);
    /**
     * 删除实体
     * @param entityClass 实体类
     * @param entityids 实体id数组
     */
    public void delete(Serializable ... entityids);
    /**
     * 获取实体
     * @param <T>
     * @param entityClass 实体类
     * @param entityId 实体id
     * @return
     */
    public T find(Serializable entityId);
    
    /**
     * 分页获取记录
     * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
     * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
     * @param where 条件语句,不带where关键字,条件语句只能使用位置参数,位置参数的索引值以1开始,如:o.username=?1 and o.password=?2
     * @param params 条件语句出现的位置参数值
     * @param orderby 排序,Key为排序属性,Value为asc/desc,如:
     *  LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("email", "asc");
        orderby.put("password", "desc");
     * @return
     */
    public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
    /**
     * 分页获取记录
     * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
     * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
     * @param where 条件语句,不带where关键字,条件语句只能使用位置参数,位置参数的索引值以1开始,如:o.username=?1 and o.password=?2
     * @param params 条件语句出现的位置参数值
     * @return
     */
    public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
    /**
     * 分页获取记录
     * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
     * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
     * @param orderby 排序,Key为排序属性,Value为asc/desc,如:
     *  LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("email", "asc");
        orderby.put("password", "desc");
     * @return
     */
    public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
    /**
     * 分页获取记录
     * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
     * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
     * @return
     */
    public QueryResult<T> getScrollData(int firstindex, int maxresult);
    /**
     * 分页获取所有记录
     * @return
     */
    
    public QueryResult<T> getScrollData();
}
