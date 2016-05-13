package cn.dream.web.action.book;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.dream.bean.book.BookInfo;
import cn.dream.bean.book.BookType;
import cn.dream.service.book.BookInfoService;
import cn.dream.utils.WebUtil;
import cn.dream.web.formbean.book.FrontBookForm;

/**
 * 浏览商品
 */
@Controller("/book/view")
public class ViewBookAction extends Action {
    @Resource(name = "bookInfoServiceBean")
    private BookInfoService bookInfoService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FrontBookForm formbean = (FrontBookForm) form;
        BookInfo book = bookInfoService.find(formbean.getBookid());
        System.out.println("ha");
        if (book == null) {
            request.setAttribute("message", "获取不到你需要浏览的图书");
            request.setAttribute("urladdress", "/");
            return mapping.findForward("message");
        }
        WebUtil.addCookie(response, "bookViewHistory",
                buildViewHistory(request, formbean.getBookid()),
                30 * 24 * 60 * 60);
        List<BookType> stypes = new ArrayList<BookType>();
        BookType parent = book.getType();
        while (parent != null) {
            stypes.add(parent);
            parent = parent.getParent();
        }
        request.setAttribute("book", book);
        request.setAttribute("stypes", stypes);
        return mapping.findForward("book");
    }

    public String buildViewHistory(HttpServletRequest request,
            Integer currentProductId) {
        // 23-2-6-5
        // 1.如果当前浏览的id已经在浏览历史里了,我们要把移到最前面
        // 2.如果浏览历史里已经达到了10个产品了,我们需要把最选进入的元素删除
        String cookieValue = WebUtil.getCookieByName(request,
                "productViewHistory");
        LinkedList<Integer> productids = new LinkedList<Integer>();
        if (cookieValue != null && !"".equals(cookieValue.trim())) {
            String[] ids = cookieValue.split("-");
            for (String id : ids) {
                productids.offer(new Integer(id.trim()));
            }
            if (productids.contains(currentProductId))
                productids.remove(currentProductId);
            if (productids.size() >= 10)
                productids.poll();
        }
        productids.offer(currentProductId);
        StringBuffer out = new StringBuffer();
        for (Integer id : productids) {
            out.append(id).append('-');
        }
        out.deleteCharAt(out.length() - 1);
        return out.toString();
    }
}
