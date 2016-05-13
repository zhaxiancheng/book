package cn.dream.service.book;

import java.util.List;
import java.util.Map;

import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Shelf;
import cn.dream.bean.book.BookInfo;
import cn.dream.service.base.DAO;


public interface BookInfoService extends DAO<BookInfo> {
    /**
     * 设置图书是否上架
     * @param productids 产品id数组
     * @param statu true为上架,false为下架
     */
    public void setVisibleStatu(Integer[] productids, boolean statu);
    
    /**
     * 设置的推图书荐状态
     * @param productids 产品id数组
     * @param statu true为推荐,否则不推荐
     */
    public void setCommendStatu(Integer[] productids, boolean statu);
    /**
     * 获取类别下产品所使用到的品牌
     * @param typeids 产品类别id
     * @return
     */
    public List<BookType> getBookTypesByBookTypeid(Integer[] typeids);
    public Map<String, String> getBookTypes();
    /**
     * 获取销量最多并且被推荐的图书
     * @param typeid 类别id
     * @param maxResult 获取的产品数量
     * @return
     */
    public List<BookInfo> getTopSell(Integer typeid, int maxResult);
    /**
     * 获取指定ID的图书
     * @param bookids 图书id数组
     * @param maxResult 最大获取多少条记录
     * @return
     */
    public List<BookInfo> getViewHistory(Integer[] bookids, int maxResult);
}
