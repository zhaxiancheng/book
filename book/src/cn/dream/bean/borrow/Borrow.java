package cn.dream.bean.borrow;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.dream.bean.book.BookInfo;
import cn.dream.bean.user.User;

@Entity @Table(name="borrow")
public class Borrow implements Serializable{
    /**实体id **/
    private Long id; 
    /**所属用户**/
    private User user ;
    /**图书的id**/
    private BookInfo book;
    /**借阅时间**/
    private Date borrowtime;
    /**归还时间**/
    private Date backTime;
    /**实际归还时间**/
    private Date realbacktime;
    /**备注**/
    private String remark;
    /**借阅数量**/
    private Integer amount;
    /**借阅状态**/
    private State state=State.NORMAL;
    /**操作员**/
    private  String operator;
    
    @Id @Column(length=14)
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="userid")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "bookid")
    public BookInfo getBook() {
        return book;
    }
    public void setBook(BookInfo book) {
        this.book = book;
    }
    
    @Column(nullable = false)
    public Date getBorrowtime() {
        return borrowtime;
    }
    
    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }
    @Column(nullable = false)
    public Date getBackTime() {
        return backTime;
    }
    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }
    @Column(nullable = true)
    public Date getRealbacktime() {
        return realbacktime;
    }
    public void setRealbacktime(Date realbacktime) {
        this.realbacktime = realbacktime;
    }
    @Column(length=100)
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(length=8,nullable = false)
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    @Enumerated(EnumType.STRING) @Column(length=20,nullable=false)
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    @Column(length=8)
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    
}
