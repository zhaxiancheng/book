package cn.dream.web.action.book;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.dream.bean.book.BookInfo;
import cn.dream.bean.book.BookStyle;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.book.BookStyleService;
import cn.dream.utils.SiteUrl;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookForm;
import cn.dream.web.formbean.uploadfile.UploadfileForm;

@Controller("/control/book/style/manage")
public class BookStyleManageAction extends DispatchAction {
    @Resource(name="bookInfoServiceBean")
    private BookInfoService bookInfoService;
    
    @Resource(name="bookStyleServiceBean")
    private BookStyleService bookStyleService;
    
    /**
     * 设置图书样式上架
     */
    @Permission(module="book", privilege="visible")
    public ActionForward visible(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookForm formbean = (BookForm) form;
        bookStyleService.setVisibleStatu(formbean.getStylesids(), true);
        request.setAttribute("message", "设置成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.style.list")+"?bookid="+ formbean.getBookid());
        return mapping.findForward("message");
    }
    
    /**
     * 设置图书样式下架
     */
    @Permission(module="book", privilege="visible")
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookForm formbean = (BookForm) form;
        bookStyleService.setVisibleStatu(formbean.getStylesids(), false);
        request.setAttribute("message", "设置成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.style.list")+"?bookid="+ formbean.getBookid());
        return mapping.findForward("message");
    }
    /**
     * 图书样式修改界面
     */
    @Permission(module="book", privilege="update")
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookForm formbean = (BookForm)form;
        BookStyle bookstyle = bookStyleService.find(formbean.getBookstyleid());
        formbean.setStylename(bookstyle.getName());
        formbean.setISBN(bookstyle.getISBN());
        formbean.setRevision(bookstyle.getRevision());
        request.setAttribute("imagepath", bookstyle.getImageFullPath());
        return mapping.findForward("edit");
    }

    /**
     * 图书样式修改
     */
    @Permission(module="book", privilege="update")
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookForm formbean = (BookForm)form;
        BookStyle bookstyle = bookStyleService.find(formbean.getBookstyleid());
        bookstyle.setName(formbean.getStylename());
        if(formbean.getImagefile()!=null && formbean.getImagefile().getFileSize()>0){
            if(!BookForm.validateImageFileType(formbean.getImagefile())){
                request.setAttribute("message", "文件格式不正确,只允许上传gif/jpg/png/bmp图片");
                return mapping.findForward("message");
            }
            if(formbean.getImagefile().getFileSize()>204800){
                request.setAttribute("message", "图片不能大于200K");
                return mapping.findForward("message");
            }
            String ext = UploadfileForm.getExt(formbean.getImagefile());
            String filename = UUID.randomUUID().toString()+ "."+ext;//构建文件名称    
            BookForm.saveBookImageFile(request, formbean.getImagefile(),bookstyle.getBook().getType().getTypeid(),
                    bookstyle.getBook().getBookid(), filename);
            bookstyle.setImagename(filename);
        }
        bookStyleService.update(bookstyle);
        request.setAttribute("message", "修改成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.style.list")+"?bookid="+ bookstyle.getBook().getBookid());
        return mapping.findForward("message");
    }
    /**
     * 产品图片添加界面
     */
    @Permission(module="book", privilege="insert")
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("add");
    }
    
    /**
     * 图书样式添加
     */
    @Permission(module="book", privilege="insert")
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookForm formbean = (BookForm)form;
        if(!BookForm.validateImageFileType(formbean.getImagefile())){
            request.setAttribute("message", "文件格式不正确,只允许上传gif/jpg/png/bmp图片");
            return mapping.findForward("message");
        }
        if(formbean.getImagefile().getFileSize()>204800){
            request.setAttribute("message", "图片不能大于200K");
            return mapping.findForward("message");
        }
        BookInfo book = bookInfoService.find(formbean.getBookid());
        String ext = UploadfileForm.getExt(formbean.getImagefile());
        String filename = UUID.randomUUID().toString()+ "."+ext;//构建文件名称    
        BookForm.saveBookImageFile(request, formbean.getImagefile(),book.getType().getTypeid(),
                book.getBookid(),filename);
        BookStyle bookStyle = new BookStyle(formbean.getStylename(), filename);
        bookStyle.setBook(book);
        bookStyle.setISBN(formbean.getISBN());
        bookStyle.setRevision(formbean.getRevision());
        bookStyleService.save(bookStyle);
        request.setAttribute("message", "添加成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.style.list")+"?bookid="+ book.getBookid());
        return mapping.findForward("message");
    }
}
