package cn.dream.bean.book;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@Entity
@Searchable(root = false)
public class BookType implements Serializable {
    private static final long serialVersionUID = 8106351120886053881L;
    /** 类别id **/
    private Integer typeid;
    /** 类别名称 **/
    private String name;
    /** 备注,用于google搜索页面描述 **/
    private String note;
    /** 是否可见 **/
    private Boolean visible = true;
    /** 子类别 **/
    private Set<BookType> childtypes = new HashSet<BookType>();
    /** 所属父类 **/
    private BookType parent;
    /** 类别下的图书 **/
    private Set<BookInfo> products = new HashSet<BookInfo>();
    /** 类别下的书架 **/
    private Set<Shelf> shelfs = new HashSet<Shelf>();

    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    public Set<Shelf> getShelfs() {
        return shelfs;
    }

    public void setShelfs(Set<Shelf> shelfs) {
        this.shelfs = shelfs;
    }

    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    public Set<BookInfo> getProducts() {
        return products;
    }

    public void setProducts(Set<BookInfo> products) {
        this.products = products;
    }

    public BookType() {
    }

    public BookType(Integer typeid) {
        this.typeid = typeid;
    }

    public BookType(String name, String note) {
        this.name = name;
        this.note = note;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "parentid")
    public BookType getParent() {
        return parent;
    }

    public void setParent(BookType parent) {
        this.parent = parent;
    }

    @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parent")
    public Set<BookType> getChildtypes() {
        return childtypes;
    }

    public void setChildtypes(Set<BookType> childtypes) {
        this.childtypes = childtypes;
    }

    @Column(length = 36, nullable = false)
    @SearchableProperty(index = Index.NOT_ANALYZED, store = Store.YES, name = "typeName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 200)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(nullable = false)
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableProperty(index = Index.NO, store = Store.YES)
    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
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
        final BookType other = (BookType) obj;
        if (typeid == null) {
            if (other.typeid != null)
                return false;
        } else if (!typeid.equals(other.typeid))
            return false;
        return true;
    }
}
