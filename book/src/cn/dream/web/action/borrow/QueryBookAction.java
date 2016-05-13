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
import cn.dream.web.formbean.book.BookForm;
import cn.dream.web.formbean.borrow.BorrowForm;

/**
 * 图书归还
 */

@Controller("/control/book/query")
public class QueryBookAction extends DispatchAction {
    @Resource(name="borrowBookServiceImpl")
    BorrowBookService borrowBookService;
    
    /** 图书借阅查询界面 */
    public ActionForward queryborrowBookUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("query");
    }
    /** 查询借阅图书 */
    public ActionForward queryborrowBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BorrowForm formBean = (BorrowForm) form;
        if (formBean.getLibrarycard() != null & !"".equals(formBean.getLibrarycard())) {
            String librarycard = formBean.getLibrarycard();
            User user = borrowBookService.findUser(librarycard);
            request.setAttribute("user", user);
            if(user!=null){
                List<Borrow> borrowBooks = borrowBookService.findBorrowBooks(user);
                request.setAttribute("borrowBooks", borrowBooks);
            }else{
                request.setAttribute("message", "用户不存在");
            }
            return mapping.findForward("query");
        } else {
            request.setAttribute("message", "无效的借阅卡号");
            return mapping.findForward("query");
        }
    }

}