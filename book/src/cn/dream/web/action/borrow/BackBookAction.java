package cn.dream.web.action.borrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import cn.dream.bean.PageView;
import cn.dream.bean.book.BookInfo;
import cn.dream.bean.borrow.Borrow;
import cn.dream.bean.borrow.State;
import cn.dream.bean.user.User;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.borrow.BorrowBookService;
import cn.dream.service.user.UserService;
import cn.dream.utils.SiteUrl;
import cn.dream.utils.WebUtil;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookForm;
import cn.dream.web.formbean.borrow.BorrowForm;

/**
 * 图书归还
 */

@Controller("/control/book/back")
public class BackBookAction extends DispatchAction {
    @Resource(name="borrowBookServiceImpl")
    BorrowBookService borrowBookService;
    @Resource(name = "userServiceBean")
    UserService userService;
    

    /** 图书归还界面 */
    public ActionForward backBookUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("back");
    }
    /** 图书归还*/
    @Permission(module = "borrow", privilege = "back")
    public ActionForward backBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        BorrowForm formBean = (BorrowForm) form;
        if(id!=null){
            Borrow borrow = borrowBookService.find(Long.parseLong(id));
            borrow.setState(State.BACK);
            borrow.setRealbacktime(new Date());
            borrow.getBook().setRemaincount(borrow.getBook().getRemaincount()+1);
            borrowBookService.update(borrow);
        }
        return queryborrowBook(mapping, form, request, response);
    }
    /** 图书续借界面 */
    public ActionForward renewBookUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("renew");
    }
      /** 图书续借*/
    @Permission(module = "borrow", privilege = "renew")
     public ActionForward renewBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        BorrowForm formBean = (BorrowForm) form;
        if(id!=null){
            Borrow borrow = borrowBookService.find(Long.parseLong(id));
            borrow.setState(State.RENEW);
            Date date = WebUtil.getBackDate(borrow.getBackTime());
            borrow.setBackTime(date);
            borrowBookService.update(borrow);
            User user = borrowBookService.findUser(formBean.getLibrarycard());
            request.setAttribute("user", user);
            if(user!=null){
                List<Borrow> borrowBooks = borrowBookService.findBorrowBooks(user,State.NORMAL);
                request.setAttribute("borrowBooks", borrowBooks);
            }
        }
        return mapping.findForward("renew");
    }

    /** 查询借出未归还的图书 */
    public ActionForward queryborrowBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BorrowForm formBean = (BorrowForm) form;
        if (formBean.getLibrarycard() != null & !"".equals(formBean.getLibrarycard())) {
            String librarycard = formBean.getLibrarycard();
            User user = borrowBookService.findUser(librarycard);
            request.setAttribute("user", user);
            if(user!=null){
                List<Borrow> borrowBooks = borrowBookService.findNotBackBooks(user,State.BACK);
                request.setAttribute("borrowBooks", borrowBooks);
            }else{
                request.setAttribute("message", "用户不存在");
            }
            return mapping.findForward("back");
        } else {
            request.setAttribute("message", "无效的借阅卡号");
            return mapping.findForward("back");
        }
    }
    
    /** 查询正常状态的图书 */
    public ActionForward renewborrowBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BorrowForm formBean = (BorrowForm) form;
        if (formBean.getLibrarycard() != null & !"".equals(formBean.getLibrarycard())) {
            String librarycard = formBean.getLibrarycard();
            User user = borrowBookService.findUser(librarycard);
            request.setAttribute("user", user);
            if(user!=null){
                List<Borrow> borrowBooks = borrowBookService.findBorrowBooks(user,State.NORMAL);
                request.setAttribute("borrowBooks", borrowBooks);
            }else{
                request.setAttribute("message", "用户不存在");
            }
            return mapping.findForward("renew");
        } else {
            request.setAttribute("message", "无效的借阅卡号");
            return mapping.findForward("renew");
        }
    }
}