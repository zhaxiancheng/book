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
import cn.dream.bean.QueryResult;
import cn.dream.bean.book.BookType;
import cn.dream.service.book.BookTypeService;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookTypeForm;


@Controller("/control/book/type/list")
public class BookTypeAction extends Action {
    @Resource(name="bookTypeServiceBean")
    private BookTypeService bookTypeService;

    @Override @Permission(module="bookType", privilege="view")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookTypeForm formbean = (BookTypeForm) form;
        StringBuffer jpql = new StringBuffer("o.visible=?1");
        List<Object> params = new ArrayList<Object>();
        params.add(true);
        if("true".equals(formbean.getQuery())){//进行查询操作
            if(formbean.getName()!=null && !"".equals(formbean.getName().trim())){
                jpql.append(" and o.name like ?"+ (params.size()+1));
                params.add("%"+ formbean.getName()+ "%");
            }
        }else{
            if(formbean.getParentid()!=null && formbean.getParentid()>0){
                jpql.append(" and o.parent.typeid=?"+(params.size()+1));
                params.add(formbean.getParentid());
            }else{
                jpql.append(" and o.parent is null");
            }
        }
        PageView<BookType> pageView = new PageView<BookType>(12, formbean.getPage());
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("typeid", "desc");
        QueryResult<BookType> qr = bookTypeService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby);
        pageView.setQueryResult(qr);
        request.setAttribute("pageView", pageView);     
        return mapping.findForward("list");
    }

}
