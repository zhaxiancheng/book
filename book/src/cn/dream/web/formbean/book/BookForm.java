package cn.dream.web.formbean.book;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;

import cn.dream.bean.book.BookStyle;
import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Shelf;
import cn.dream.utils.ImageSizer;
import cn.dream.web.formbean.BaseForm;

public class BookForm extends BaseForm {

    private static final long serialVersionUID = 1L;
    /** 图书id **/
    private Integer bookid;
    private Integer[] bookids;
    /** 图书条形码 **/
    private String barcode;
    /** 图书名称 **/
    private String name;
    /** 作者 **/
    private String author;
    /** 译者 **/
    private String translator;
    /** 图书价格 **/
    private Float price;
    /** 图书类型 **/
    private Integer typeid;
    /** 书架 **/
    private String shelfid;
    /** 图书样式 **/
    private Set<BookStyle> styles = new HashSet<BookStyle>();
    /** 图书出版社 **/
    private String ISBN;
    /** 图书版次 **/
    private String revision;
    /** 借阅次数 **/
    private Integer borrowcount = 0;
    /** 图书简介 **/
    private String description;
    /** 是否可见 **/
    private Boolean visible = true;
    /** 图书总量 **/
    private Integer sumcount = 0;
    /** 库存量 **/
    private Integer remaincount = 0;
    /** 是否推荐 **/
    private Boolean recommend = false;
    
    private Integer[] stylesids;
    private String stylename;
    private FormFile imagefile;
    private Integer bookstyleid;
    private String word;
    /** 性别要求 **/
    private String sex;

    

    public Integer getBookid() {
        return bookid;
    }



    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }



    public Integer[] getBookids() {
        return bookids;
    }



    public void setBookids(Integer[] bookids) {
        this.bookids = bookids;
    }



    public String getBarcode() {
        return barcode;
    }



    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getAuthor() {
        return author;
    }



    public void setAuthor(String author) {
        this.author = author;
    }



    public String getTranslator() {
        return translator;
    }



    public void setTranslator(String translator) {
        this.translator = translator;
    }



    public Float getPrice() {
        return price;
    }



    public void setPrice(Float price) {
        this.price = price;
    }



    public Integer getTypeid() {
        return typeid;
    }



    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }



    public String getShelfid() {
        return shelfid;
    }



    public void setShelfid(String shelfid) {
        this.shelfid = shelfid;
    }



    public Set<BookStyle> getStyles() {
        return styles;
    }



    public void setStyles(Set<BookStyle> styles) {
        this.styles = styles;
    }



    public Integer getBorrowcount() {
        return borrowcount;
    }



    public void setBorrowcount(Integer borrowcount) {
        this.borrowcount = borrowcount;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public Boolean getVisible() {
        return visible;
    }



    public void setVisible(Boolean visible) {
        this.visible = visible;
    }



    public Integer getSumcount() {
        return sumcount;
    }



    public void setSumcount(Integer sumcount) {
        this.sumcount = sumcount;
    }



    public Integer getRemaincount() {
        return remaincount;
    }



    public void setRemaincount(Integer remaincount) {
        this.remaincount = remaincount;
    }



    public Boolean getRecommend() {
        return recommend;
    }



    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }



    public Integer[] getStylesids() {
        return stylesids;
    }



    public void setStylesids(Integer[] stylesids) {
        this.stylesids = stylesids;
    }



    public String getStylename() {
        return stylename;
    }



    public void setStylename(String stylename) {
        this.stylename = stylename;
    }



    public FormFile getImagefile() {
        return imagefile;
    }



    public void setImagefile(FormFile imagefile) {
        this.imagefile = imagefile;
    }



    public Integer getBookstyleid() {
        return bookstyleid;
    }



    public void setBookstyleid(Integer bookstyleid) {
        this.bookstyleid = bookstyleid;
    }



    public String getWord() {
        return word;
    }



    public void setWord(String word) {
        this.word = word;
    }



    public String getSex() {
        return sex;
    }



    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getISBN() {
        return ISBN;
    }



    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }



    public String getRevision() {
        return revision;
    }



    public void setRevision(String revision) {
        this.revision = revision;
    }


    /**
     * 保存图书图片
     * 
     * @param request
     * @param imagefile
     *            上传的产品图片
     * @param bookTypeId
     *            图书类别id
     * @param bookId
     *            图书id
     * @return
     * @throws Exception
     */
    public static void saveBookImageFile(HttpServletRequest request,
            FormFile imagefile, Integer bookTypeId, Integer bookId,
            String filename) throws Exception {
        String ext = BaseForm.getExt(imagefile);
        String pathdir = "/images/book/" + bookTypeId + "/" + bookId
                + "/prototype";// 构建文件保存的目录
        // 得到图片保存目录的真实路径
        String realpathdir = request.getSession().getServletContext()
                .getRealPath(pathdir);
        File savedir = new File(realpathdir);
        File file = saveFile(savedir, filename, imagefile.getFileData());
        String pathdir140 = "/images/book/" + bookTypeId + "/" + bookId
                + "/140x";// 140宽度的图片保存目录
        String realpathdir140 = request.getSession().getServletContext()
                .getRealPath(pathdir140);
        File savedir140 = new File(realpathdir140);
        if (!savedir140.exists())
            savedir140.mkdirs();// 如果目录不存在就创建
        File file140 = new File(realpathdir140, filename);
        ImageSizer.resize(file, file140, 140, ext);
    }

}
