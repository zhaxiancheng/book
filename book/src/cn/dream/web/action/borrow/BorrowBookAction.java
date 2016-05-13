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
 * 订单信息查看
 */

@Controller("/control/book/borrow")
public class BorrowBookAction extends DispatchAction {
    @Resource
    BorrowBookService borrowBookService;
    @Resource(name = "userServiceBean")
    UserService userService;
    @Resource(name = "bookInfoServiceBean")
    private BookInfoService bookInfoService;

    /** 图书借阅界面 */
    public ActionForward borrowBookUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("borrow");
    }

    /** 借出图书 */
    @Permission(module = "borrow", privilege = "borrow")
    public ActionForward borrowBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BorrowForm formBean = (BorrowForm) form;
        if (formBean.getBookid() != null & !"".equals(formBean.getBookid())) {
            BookInfo book = bookInfoService.find(formBean.getBookid());
            book.setBorrowcount(book.getBorrowcount() + 1);
            book.setRemaincount(book.getRemaincount() - 1);
            bookInfoService.update(book);
            String barcode = formBean.getBarcode();
            String librarycard = formBean.getLibrarycard();
            
            Borrow borrow = new Borrow();
            Date date = new Date();
            borrow.setBorrowtime(date);
            Date backDate = WebUtil.getBackDate(date);
            borrow.setBackTime(backDate);
            User user = borrowBookService.findUser(librarycard);
            BookInfo bookInfo = borrowBookService.findBook(barcode);
            borrow.setUser(user);
            borrow.setBook(bookInfo);
            borrow.setState(State.NORMAL);
            borrow.setAmount(1);
            borrowBookService.save(borrow);

            request.setAttribute("message", "借出成功了");
            request.setAttribute("urladdress",
                    SiteUrl.readUrl("control.book.borrow"));
            return mapping.findForward("borrow");
        } else {
            request.setAttribute("message", "借出失败");
            return mapping.findForward("borrow");
        }

    }

    /** 查找用户 */
    public ActionForward findUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BorrowForm formBean = (BorrowForm) form;
        if (formBean.getLibrarycard() != null
                && !"".equals(formBean.getLibrarycard())) {
            User user = userService.find(formBean.getLibrarycard());
            request.setAttribute("user", user);
            return mapping.findForward("borrow");
        } else {
            request.setAttribute("message", "用户借阅卡号不能为空");
            return mapping.findForward("borrow");
        }
    }

    /** 查询图书 */
    public ActionForward findBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        findUser(mapping, form, request, response);
        BorrowForm formbean = (BorrowForm) form;
        if (formbean.getBarcode() != null && !"".equals(formbean.getBarcode())) {
            String barcode = formbean.getBarcode();
            BookInfo bookInfo = borrowBookService.findBook(barcode);
            if (bookInfo != null) {
                request.setAttribute("bookInfo", bookInfo);
                return mapping.findForward("borrow");
            } else {
                request.setAttribute("message", "图书不存在");
            }
        } else {
            request.setAttribute("message", "用户借阅卡号不能为空");
            return mapping.findForward("borrow");
        }
        return mapping.findForward("borrow");
    }
}
