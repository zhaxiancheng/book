package cn.dream.service.book;

import cn.dream.bean.book.BookStyle;
import cn.dream.service.base.DAO;


public interface BookStyleService extends DAO<BookStyle> {
    /**
     * 设置该样式的产品是否上架
     * @param productstyleids 产品id数组
     * @param statu true为上架,false为下架
     */
    public void setVisibleStatu(Integer[] productstyleids, boolean statu);
}
