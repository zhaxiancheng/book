package cn.dream.service.book;

import java.util.List;

import cn.dream.bean.book.BookType;
import cn.dream.service.base.DAO;


public interface BookTypeService extends DAO<BookType> {
    /**
     * 获取下类别的id
     * @param parentids 父类id数组
     * @return
     */
    public List<Integer> getSubTypeid(Integer[] parentids);
}