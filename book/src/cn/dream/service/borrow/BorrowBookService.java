package cn.dream.service.borrow;

import java.util.List;

import cn.dream.bean.book.BookInfo;
import cn.dream.bean.borrow.Borrow;
import cn.dream.bean.borrow.State;
import cn.dream.bean.user.User;
import cn.dream.service.base.DAO;

public interface BorrowBookService extends DAO<Borrow> {
    /**
     * 根据图书的id 和借阅卡号 查找图书
     * 
     * @param barcode
     * @param librarycard
     * @return
     */
    Borrow findBorrowBook(String barcode, String librarycard);

    /**
     * 根据借阅证查找用户
     * 
     * @param librarycard
     * @return
     */
    User findUser(String librarycard);
    /**
     * 根据图书条形码查找图书
     * @param barcode
     * @return
     */

    BookInfo findBook(String barcode);
/**
 * 返回读者所借图书
 * @param user
 * @param state 
 * @return
 */
    List<Borrow> findBorrowBooks(User user, State state);
/**
 * 根据用户名查找用户所借阅的所有图书
 * @param user
 * @return
 */
  List<Borrow> findBorrowBooks(User user);
/**
 * 查询未归还的图书
 * @param user
 * @param back说
 * @return
 */
List<Borrow> findNotBackBooks(User user, State state);

    

}
