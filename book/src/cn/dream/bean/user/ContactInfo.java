package cn.dream.bean.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ContactInfo implements Serializable{
    private static final long serialVersionUID = -4336182674133849896L;
    private Integer contactid;
    /** 地址 **/
    private String address;
    /** 邮编 **/
    private String postalcode;
    /** 手机 **/
    private String mobile;
    /** 所属用户 **/
    private User user;
    @Id @GeneratedValue
    public Integer getContactid() {
        return contactid;
    }
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }
    @Column(length=100,nullable=false)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Column(length=6)
    public String getPostalcode() {
        return postalcode;
    }
    public void setPostalcode(String postcode) {
        this.postalcode = postcode;
    }
    @Column(length=11)
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @OneToOne(mappedBy="contactInfo",cascade=CascadeType.REFRESH)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((contactid == null) ? super.hashCode() : contactid.hashCode());
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
        final ContactInfo other = (ContactInfo) obj;
        if (contactid == null) {
            if (other.contactid != null)
                return false;
        } else if (!contactid.equals(other.contactid))
            return false;
        return true;
    }
    
}
