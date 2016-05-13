package cn.dream.web.action.book;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.dream.bean.PageView;
import cn.dream.bean.book.BookInfo;
import cn.dream.service.book.BookSearchService;
import cn.dream.web.formbean.book.BookQueryForm;
/**
 * 图书搜索
 */
@Controller("/book/query")
public class BookSearchAction extends Action {
    @Resource BookSearchService bookSearchService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookQueryForm formbean = (BookQueryForm)form;
        PageView<BookInfo> pageView = new PageView<BookInfo>(18, formbean.getPage());
        pageView.setQueryResult(bookSearchService.query(formbean.getWord(), pageView.getFirstResult(), pageView.getMaxresult()));
        request.setAttribute("pageView", pageView);
        return mapping.findForward("list");
    }

}
