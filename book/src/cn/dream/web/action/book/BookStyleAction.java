package cn.dream.web.action.book;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.dream.service.book.BookStyleService;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookForm;

@Controller("/control/book/style/list")
public class BookStyleAction extends Action {
	@Resource(name="bookStyleServiceBean")
	private BookStyleService bookStyleService;

	@Override 
	@Permission(module="book", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BookForm formbean = (BookForm) form;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "desc");
		orderby.put("styleid", "asc");
		request.setAttribute("styles", bookStyleService.getScrollData(-1, -1,
				"o.book.bookid=?1", new Object[]{formbean.getBookid()},orderby).getResultlist());
		return mapping.findForward("list");
	}
	
	
}
