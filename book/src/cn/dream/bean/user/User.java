package cn.dream.bean.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.type.IntegerType;

import cn.dream.bean.borrow.Borrow;

@Entity
public class User implements Serializable{
    private static final long serialVersionUID = 8394979715028899027L;
    /** 借阅卡号 **/
    private String  librarycard;
    /** 用户名 **/
    private String username;//只允许字母/数字/下划线
    /** 密码 **/
    private String password;//采用MD5加密
    /** 电子邮箱 **/
    private String email;
    /** 性别 **/
    private Gender gender=Gender.MAN;
    /** 联系信息 **/
    private ContactInfo contactInfo;
    /** 是否启用 **/
    private Boolean visible=true;
    /** 注册时间 **/
    /**罚金**/
    private Float fine;
    private Integer totalamount;
    private Date regTime = new Date();
    private Set<Borrow> borrowrecords=new HashSet<Borrow>();
    
    @Temporal(TemporalType.TIMESTAMP) @Column()
    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public User(){}
    
    public User(String username){
        this.username = username;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public User(String librarycard, String username, String password,
            String email) {
        this.librarycard = librarycard;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id @Column(nullable=false,length=18)
    public String getLibrarycard() {
        return librarycard;
    }

    public void setLibrarycard(String librarycard) {
        this.librarycard = librarycard;
    }

    @Column(nullable=false,length=18)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(length=32)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(length=50)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Enumerated(EnumType.STRING) @Column(length=5)
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="contactid")
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    @Column(nullable=false)
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    @Column(length=8)
    public Float getFine() {
        return fine;
    }
    public void setFine(Float fine) {
        this.fine = fine;
    }
    @Column(length=4)
    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    public Set<Borrow> getBorrowrecords() {
        return borrowrecords;
    }

    public void setBorrowrecords(Set<Borrow> borrowrecords) {
        this.borrowrecords = borrowrecords;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((librarycard == null) ? 0 : librarycard.hashCode());
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (librarycard == null) {
            if (other.librarycard != null)
                return false;
        } else if (!librarycard.equals(other.librarycard))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
