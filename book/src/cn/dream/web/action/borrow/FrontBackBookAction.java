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

@Controller("/book/back")
public class FrontBackBookAction extends DispatchAction {
    @Resource(name = "borrowBookServiceImpl")
    BorrowBookService borrowBookService;

    /** 图书续借 */
    @Permission(module = "borrow", privilege = "renew")
    public ActionForward renewBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        BorrowForm formBean = (BorrowForm) form;
        if (id != null) {
            Borrow borrow = borrowBookService.find(Long.parseLong(id));
            borrow.setState(State.RENEW);
            Date date = WebUtil.getBackDate(borrow.getBackTime());
            borrow.setBackTime(date);
            borrowBookService.update(borrow);
        }
        request.setAttribute("message", "续借成功了");
        request.setAttribute("urladdress", SiteUrl.readUrl("user.book.back"));
        return mapping.findForward("message");
    }

}