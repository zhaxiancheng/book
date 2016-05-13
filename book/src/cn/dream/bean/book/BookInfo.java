package cn.dream.bean.book;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@Entity
@Searchable
public class BookInfo implements Serializable {
    private static final long serialVersionUID = -8860864584425256200L;
    /** 图书id **/
    private Integer bookid;
    /** 图书条形码 **/
    private String barcode;
    /** 图书名称 **/
    private String name;
    /** 书架 **/
    private Shelf shelf;
    /** 作者 **/
    private String author;
    /** 译者 **/
    private String translator;
    /** 图书价格 **/
    private Float price;
    /** 借阅次数 **/
    private Integer borrowcount=0;
    /** 图书简介 **/
    private String description;
    /** 是否可见 **/
    private Boolean visible = true;
    /** 图书类型 **/
    private BookType type;
    /** 上架日期 **/
    private Date intime = new Date();
    /** 图书总量 **/
    private Integer sumcount = 0;
    /** 库存量 **/
    private Integer remaincount = 0;
    /** 是否推荐 **/
    private Boolean recommend = false;
    /** 性别要求 **/
    private Sex sexrequest = Sex.NONE;
    

    /** 产品样式 **/
    private Set<BookStyle> styles = new HashSet<BookStyle>();

    public BookInfo(Integer bookid) {
        this.bookid = bookid;
    }

    public BookInfo() {
    }

    @Id
    @GeneratedValue
    @SearchableId
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @Column(length = 20, nullable = false)
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Column(length = 40, nullable = false)
    @SearchableProperty(boost = 2, name = "bookName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 20)
    @SearchableProperty(index = Index.NO, store = Store.YES)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(length = 20)
    @SearchableProperty(index = Index.NO, store = Store.YES)
    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    @Column(nullable = false)
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    @Column(nullable = false)
    public Integer getBorrowcount() {
        return borrowcount;
    }

    public void setBorrowcount(Integer borrowcount) {
        this.borrowcount = borrowcount;
    }

    @Temporal(TemporalType.DATE)
    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(nullable = false)
    public Integer getSumcount() {
        return sumcount;
    }

    public void setSumcount(Integer sumcount) {
        this.sumcount = sumcount;
    }
    @Column(nullable = false)
    public Integer getRemaincount() {
        return remaincount;
    }

    public void setRemaincount(Integer remaincount) {
        this.remaincount = remaincount;
    }
    @Column(nullable = false)
    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    /*
     * @Transient public Float getSavedPrice() { return marketprice - sellprice;
     * }
     */

    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, mappedBy = "book")
    @OrderBy("visible desc, styleid asc")
    @SearchableComponent
    public Set<BookStyle> getStyles() {
        return styles;
    }

    public void setStyles(Set<BookStyle> styles) {
        this.styles = styles;
    }

    

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "shelfid")
    @SearchableComponent
    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "typeid")
    @SearchableComponent
    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }
    @Enumerated(EnumType.STRING) @Column(length=5,nullable=false)
    public Sex getSexrequest() {
        return sexrequest;
    }

    public void setSexrequest(Sex sexrequest) {
        this.sexrequest = sexrequest;
    }

    @Lob
    @SearchableProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    
    /**
     * 从样式集合中删除指定样式
     * 
     * @param style
     */
    public void removeBookStyle(BookStyle style) {
        if (this.styles.contains(style)) {
            this.styles.remove(style);
            style.setBook(null);
        }
    }

    /**
     * 添加样式到样式集合
     * 
     * @param style
     */
    public void addBookStyle(BookStyle style) {
        if (!this.styles.contains(style)) {
            this.styles.add(style);
            style.setBook(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookid == null) ? 0 : bookid.hashCode());
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
        final BookInfo other = (BookInfo) obj;
        if (bookid == null) {
            if (other.bookid != null)
                return false;
        } else if (!bookid.equals(other.bookid))
            return false;
        return true;
    }
}
