package cn.dream.bean.book;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;
import org.compass.annotations.Store;

@Entity
@Searchable(root = false)
public class BookStyle implements Serializable {
    private static final long serialVersionUID = -4926119953511144279L;
    /** 样式id **/
    private Integer styleid;
    /** 样式的名称 **/
    private String name;
    /** 图书出版社 **/
    private String ISBN;
    /** 图书版次 **/
    private String revision;
    /** 图片 **/
    private String imagename;
    /** 是否可见 **/
    private Boolean visible = true;

    private BookInfo book;

    public BookStyle() {
    }

    public BookStyle(Integer styleid) {
        this.styleid = styleid;
    }

    public BookStyle(String name, String imagename) {
        this.name = name;
        this.imagename = imagename;
    }
    

    public BookStyle(String name, String iSBN, String revision, String imagename) {
        super();
        this.name = name;
        ISBN = iSBN;
        this.revision = revision;
        this.imagename = imagename;
    }

    @Id
    @GeneratedValue
    @SearchableProperty(index = Index.NO, store = Store.YES)
    public Integer getStyleid() {
        return styleid;
    }

    public void setStyleid(Integer styleid) {
        this.styleid = styleid;
    }
    @Column(length = 20, nullable = false)
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    @Column(length = 10,nullable = false)
    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "bookid")
    @SearchableReference
    public BookInfo getBook() {
        return book;
    }

    public void setBook(BookInfo book) {
        this.book = book;
    }
    
    @Column(length = 30, nullable = false)
    @SearchableProperty(index = Index.NO, store = Store.YES, name = "styleName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 40, nullable = false)
    @SearchableProperty(index = Index.NO, store = Store.YES)
    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Column(nullable = false)
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Transient
    public String getImageFullPath() {
        return "/images/book/" + this.getBook().getType().getTypeid()
                + "/" + this.getBook().getBookid() + "/prototype/"
                + this.imagename;
    }

    @Transient
    public String getImage140FullPath() {
        return "/images/book/" + this.getBook().getType().getTypeid()
                + "/" + this.getBook().getBookid() + "/140x/" + this.imagename;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((styleid == null) ? 0 : styleid.hashCode());
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
        final BookStyle other = (BookStyle) obj;
        if (styleid == null) {
            if (other.styleid != null)
                return false;
        } else if (!styleid.equals(other.styleid))
            return false;
        return true;
    }

    
}
