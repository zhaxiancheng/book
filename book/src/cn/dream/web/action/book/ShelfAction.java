package cn.dream.web.action.book;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.dream.bean.PageView;
import cn.dream.bean.book.Shelf;
import cn.dream.service.book.ShelfService;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.ShelfForm;


@Controller("/control/shelf/list")
public class ShelfAction extends Action {
	@Resource(name="shelfServiceBean")
	private ShelfService shelfService;

	@Override 
	@Permission(module="shelf", privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShelfForm formbean = (ShelfForm) form;
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if("true".equals(formbean.getQuery())){
			jpql.append(" and o.name like ?"+ (params.size()+1));
			params.add("%"+ formbean.getName()+ "%");
		}
		PageView<Shelf> pageView = new PageView<Shelf>(12, formbean.getPage());
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("shelfid", "desc");
		pageView.setQueryResult(shelfService.getScrollData(pageView.getFirstResult(), 
				pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby));
		request.setAttribute("pageView", pageView);		
		return mapping.findForward("list");
	}

}
