package cn.dream.web.action.book;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.dream.bean.book.BookType;
import cn.dream.service.book.BookTypeService;
import cn.dream.utils.SiteUrl;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookTypeForm;


@Controller("/control/book/type/manage")
public class BookTypeManageAction extends DispatchAction {
    @Resource(name="bookTypeServiceBean")
    private BookTypeService bookTypeService;
    
    /**
     * 查询界面
     */
    @Permission(module="bookType", privilege="view")
    public ActionForward queryUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("query");
    }
    
    /**
     * 类别添加界面
     */
    @Permission(module="bookType", privilege="insert")
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("add");
    }
    
    /**
     * 类别添加
     */
    @Permission(module="bookType", privilege="insert")
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookTypeForm formbean = (BookTypeForm) form;
        BookType type = new BookType(formbean.getName(),formbean.getNote());
        if(formbean.getParentid()!=null && formbean.getParentid()>0) type.setParent(new BookType(formbean.getParentid()));
        bookTypeService.save(type);
        request.setAttribute("message", "添加类别成功");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.type.list"));
        return mapping.findForward("message");
    }
    
    /**
     * 类别修改界面
     */
    @Permission(module="bookType", privilege="update")
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookTypeForm formbean = (BookTypeForm) form;
        BookType type = bookTypeService.find(formbean.getTypeid());
        formbean.setName(type.getName());
        formbean.setNote(type.getNote());
        return mapping.findForward("edit");
    }
    
    /**
     * 类别修改
     */
    @Permission(module="bookType", privilege="update")
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookTypeForm formbean = (BookTypeForm) form;
        BookType type = bookTypeService.find(formbean.getTypeid());
        type.setName(formbean.getName());
        type.setNote(formbean.getNote());
        bookTypeService.update(type);
        request.setAttribute("message", "修改类别成功");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.book.type.list"));
        return mapping.findForward("message");
    }
}
