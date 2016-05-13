package cn.dream.web.formbean.borrow;

import cn.dream.web.formbean.BaseForm;

public class BorrowForm extends BaseForm {
    /** 借阅卡号 **/
    private String  librarycard;
    /** 用户名 **/
    private String username;//只允许字母/数字/下划线
    /**图书的条形码**/
    private String barcode;
    /**图书的名称**/
    private String name;
    /**借阅数量**/
    private Integer amount;
    /**借阅图书id*/
    private Integer bookid;
    public String getLibrarycard() {
        return librarycard;
    }
    public void setLibrarycard(String librarycard) {
        this.librarycard = librarycard;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBookid() {
        return bookid;
    }
    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }
    
    
}
