package cn.dream.service.borrow.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.book.BookInfo;
import cn.dream.bean.borrow.Borrow;
import cn.dream.bean.borrow.State;
import cn.dream.bean.user.User;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.borrow.BorrowBookService;
@Service
@Transactional
public class BorrowBookServiceImpl extends DaoSupport<Borrow> implements BorrowBookService {

    @Override
    public Borrow findBorrowBook(String barcode, String librarycard) {
        Borrow result=null;
        User user=findUser(librarycard);
        BookInfo book=findBook(barcode);
        if(user!=null&&book!=null){
         try {
            result = (Borrow) em.createQuery("select o from Borrow o where o.book=?1 and o.user=?2").setParameter(1, book).setParameter(2, user)
                        .getSingleResult();
        } catch (Exception e) {
            System.out.println("第一次借阅图书");
         }
        }
        return result;
    }
    @Override
    public User findUser(String librarycard) {
        User user=(User) em.createQuery("select o from User o where o.librarycard=?1").setParameter(1, librarycard).getSingleResult();
        return user;
    }
    
    @Override
    public BookInfo findBook(String barcode) {
        BookInfo book=(BookInfo) em.createQuery("select o from BookInfo o where o.barcode=?1").setParameter(1, barcode).getSingleResult();
        return book;
    }
    @Override
    public List<Borrow> findBorrowBooks(User user,State state) {
        List<Borrow> result = em.createQuery("select o from Borrow o where o.user=?1 and o.state=?2").setParameter(1,user).setParameter(2, state).getResultList();
        return result;
    }
    @Override
    public List<Borrow> findBorrowBooks(User user) {
        List<Borrow> result = em.createQuery("select o from Borrow o where o.user=?1").setParameter(1,user).getResultList();
        return result;
    }
    @Override
    public List<Borrow> findNotBackBooks(User user, State state) {
        List<Borrow> result = em.createQuery("select o from Borrow o where o.user=?1 and o.state !=?2").setParameter(1,user).setParameter(2, state).getResultList();
        return result;
    }
    
    
   
    
    
}
