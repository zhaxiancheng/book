package cn.dream.web.action.book;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Sex;
import cn.dream.service.book.ShelfService;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.book.BookTypeService;
import cn.dream.utils.SiteUrl;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookForm;
import cn.dream.web.formbean.uploadfile.UploadfileForm;

@Controller("/control/book/manage")
public class BookManageAction extends DispatchAction {
    @Resource(name = "bookInfoServiceBean")
    private BookInfoService bookInfoService;
    @Resource(name = "shelfServiceBean")
    private ShelfService shelfService;
    @Resource(name = "bookTypeServiceBean")
    private BookTypeService bookTypeService;

    /**
     * 设置图书上架
     */
    @Permission(module = "book", privilege = "visible")
    public ActionForward visible(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        bookInfoService.setVisibleStatu(formbean.getBookids(), true);
        request.setAttribute("message", "设置成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.list"));
        return mapping.findForward("message");
    }

    /**
     * 设置图书下架
     */
    @Permission(module = "book", privilege = "visible")
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        bookInfoService.setVisibleStatu(formbean.getBookids(), false);
        request.setAttribute("message", "设置成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.list"));
        return mapping.findForward("message");
    }

    /**
     * 设置图书为推荐
     */
    @Permission(module = "book", privilege = "commend")
    public ActionForward commend(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        bookInfoService.setCommendStatu(formbean.getBookids(), true);
        request.setAttribute("message", "设置成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.list"));
        return mapping.findForward("message");
    }

    /**
     * 设置图书为不推荐
     */
    @Permission(module = "book", privilege = "commend")
    public ActionForward uncommend(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        bookInfoService.setCommendStatu(formbean.getBookids(), false);
        request.setAttribute("message", "设置成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.list"));
        return mapping.findForward("message");
    }

    /**
     * 图书查询界面
     */
    @Permission(module = "book", privilege = "view")
    public ActionForward queryUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("shelfids", shelfService.getScrollData()
                .getResultlist());
        return mapping.findForward("query");
    }

    /**
     * 选择类别
     */
    @Permission(module = "book", privilege = "view")
    public ActionForward selectUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        String jpql = "o.parent is null and o.visible=true";
        Object[] params = new Object[0];
        if (formbean.getTypeid() != null && formbean.getTypeid() > 0) {
            jpql = "o.parent.id=?1";
            params = new Object[] { formbean.getTypeid() };
            BookType type = bookTypeService.find(formbean.getTypeid());
            BookType parent = type.getParent();
            List<BookType> types = new ArrayList<BookType>();
            types.add(type);
            while (parent != null) {
                types.add(parent);
                parent = parent.getParent();
            }
            request.setAttribute("menutypes", types);
        }
        request.setAttribute("types",
                bookTypeService.getScrollData(-1, -1, jpql, params)
                        .getResultlist());
        return mapping.findForward("typeselect");
    }

    /**
     * 图书添加界面
     */
    @Permission(module = "book", privilege = "insert")
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("typeid", shelfService.getScrollData()
                .getResultlist());
        return mapping.findForward("add");
    }

    /**
     * 图书添加
     */
    @Permission(module = "book", privilege = "insert")
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        BookInfo book = new BookInfo();
        book.setName(formbean.getName());
        book.setBarcode(formbean.getBarcode());
        book.setType(bookTypeService.find(formbean.getTypeid()));
        if (formbean.getShelfid() != null
                && !"".equals(formbean.getShelfid().trim())) {
            book.setShelf(shelfService.find(formbean.getShelfid()));
        }
        book.setDescription(formbean.getDescription());
        book.setPrice(formbean.getPrice());
        book.setAuthor(formbean.getAuthor());
        book.setTranslator(formbean.getTranslator());
        book.setSexrequest(Sex.valueOf(formbean.getSex()));
        book.setSumcount(formbean.getSumcount());
        book.setRemaincount(formbean.getSumcount());
        if (!BookForm.validateImageFileType(formbean.getImagefile())) {
            request.setAttribute("message", "文件格式不正确,只允许上传gif/jpg/png/bmp图片");
            request.setAttribute("urladdress",
                    SiteUrl.readUrl("control.book.add"));
            return mapping.findForward("message");
        }
        if (formbean.getImagefile().getFileSize() > 204800) {
            request.setAttribute("message", "图片不能大于200K");
            request.setAttribute("urladdress",
                    SiteUrl.readUrl("control.book.add"));
            return mapping.findForward("message");
        }
        String ext = UploadfileForm.getExt(formbean.getImagefile());
        String filename = UUID.randomUUID().toString() + "." + ext;// 构建文件名称
        book.addBookStyle(new BookStyle(formbean.getStylename(), formbean
                .getISBN(), formbean.getRevision(), filename));
        bookInfoService.save(book);

        BookForm.saveBookImageFile(request, formbean.getImagefile(),
                formbean.getTypeid(), book.getBookid(), filename);
        request.setAttribute("message", "添加成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.list"));
        // 生成的文件存放在网站根目录的html/product/类别id/
        File saveDir = new File(request.getSession().getServletContext()
                .getRealPath("/html/book/" + book.getType().getTypeid()));
        BuildHtmlFile.createProductHtml(book, saveDir);
        return mapping.findForward("message");
    }

    /**
     * 图书修改界面
     */
    @Permission(module = "book", privilege = "update")
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        BookInfo book = bookInfoService.find(formbean.getBookid());
        formbean.setName(book.getName());
        formbean.setBarcode(book.getBarcode());
        formbean.setPrice(book.getPrice());
        if (book.getShelf() != null)
            formbean.setShelfid(book.getShelf().getShelfid());
        formbean.setDescription(book.getDescription());
        formbean.setAuthor(book.getAuthor());
        formbean.setTranslator(book.getTranslator());
        formbean.setSumcount(book.getSumcount());
        formbean.setSex(book.getSexrequest().toString());
        formbean.setTypeid(book.getType().getTypeid());
        request.setAttribute("typename", book.getType().getName());
        request.setAttribute("typeid", shelfService.getScrollData()
                .getResultlist());
        return mapping.findForward("edit");
    }

    /**
     * 图书修改
     */
    @Permission(module = "book", privilege = "update")
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookForm formbean = (BookForm) form;
        BookInfo book = bookInfoService.find(formbean.getBookid());
        book.setName(formbean.getName());
        book.setBarcode(formbean.getBarcode());
        book.setType(bookTypeService.find(formbean.getTypeid()));
        if (formbean.getShelfid() != null
                && !"".equals(formbean.getShelfid().trim())) {
            book.setShelf(shelfService.find(formbean.getShelfid()));
        }
        book.setDescription(formbean.getDescription());
        book.setPrice(formbean.getPrice());
        book.setAuthor(formbean.getAuthor());
        book.setTranslator(formbean.getTranslator());
        book.setSexrequest(Sex.valueOf(formbean.getSex()));
        book.setSumcount(formbean.getSumcount());
        bookInfoService.update(book);
        request.setAttribute("message", "修改成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.list"));

        File saveDir = new File(request.getSession().getServletContext()
                .getRealPath("/html/book/" + book.getType().getTypeid()));
        BuildHtmlFile.createProductHtml(book, saveDir);

        return mapping.findForward("message");
    }

}
