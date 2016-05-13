package cn.dream.web.action.book;


import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Shelf;
import cn.dream.service.book.BookTypeService;
import cn.dream.service.book.ShelfService;
import cn.dream.utils.SiteUrl;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.ShelfForm;


/**
 * 书架管理
 * 
 */
@Controller("/control/shelf/manage")
public class ShelfManageAction extends DispatchAction {
    @Resource(name = "shelfServiceBean")
    private ShelfService shelfService;
    @Resource(name = "bookTypeServiceBean")
    private BookTypeService bookTypeService;

    /**
     * 书架查询界面
     */
    @Permission(module = "shelf", privilege = "view")
    public ActionForward queryUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("query");
    }

    /**
     * 书架修改界面
     */
    @Permission(module = "shelf", privilege = "update")
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShelfForm formbean = (ShelfForm) form;
        Shelf shelf = shelfService.find(formbean.getShelfid());
        formbean.setName(shelf.getName());
        formbean.setLocation(shelf.getLocation());
        formbean.setTypeid(shelf.getType().getTypeid());
        return mapping.findForward("edit");
    }

    /**
     * 书架修改
     */
    @Permission(module = "shelf", privilege = "update")
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShelfForm formbean = (ShelfForm) form;
        Shelf shelf = shelfService.find(formbean.getShelfid());
        shelf.setName(formbean.getName());
        shelf.setlocation(formbean.getLocation());
        shelf.setType(bookTypeService.find(formbean.getTypeid()));
        shelfService.update(shelf);
        request.setAttribute("message", "书架修改成功");
        request.setAttribute("urladdress",
                SiteUrl.readUrl("control.shelf.list"));
        return mapping.findForward("message");
    }

    /**
     * 书架添加界面
     */
    @Permission(module = "shelf", privilege = "insert")
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("add");
    }

    /**
     * 书架添加
     */
    @Permission(module = "shelf", privilege = "insert")
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShelfForm formbean = (ShelfForm) form;
        Shelf shelf = new Shelf();
        shelf.setType(bookTypeService.find(formbean.getTypeid()));
        shelf.setName(formbean.getName());
        shelf.setlocation(formbean.getLocation());
        shelfService.save(shelf);
        request.setAttribute("message", "书架添加成功");
        request.setAttribute("urladdress",
                SiteUrl.readUrl("control.shelf.list"));
        return mapping.findForward("message");
    }
    /**
     * 选择类别
     */
    @Permission(module = "book", privilege = "view")
    public ActionForward selectUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ShelfForm formbean = (ShelfForm) form;
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
}
