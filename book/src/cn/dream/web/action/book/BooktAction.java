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
import cn.dream.bean.book.BookInfo;
import cn.dream.service.book.BookInfoService;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.book.BookForm;

/**
 * 图书列表
 *
 */
@Controller("/control/book/list")
public class BooktAction extends Action {
    @Resource(name="bookInfoServiceBean")
    private BookInfoService bookInfoService;

    @Override @Permission(module="book", privilege="view")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookForm formbean = (BookForm) form;
        PageView<BookInfo> pageView = new PageView<BookInfo>(12, formbean.getPage());
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("visible", "desc");
        orderby.put("id", "desc");
        
        if("true".equals(formbean.getQuery())){
            StringBuffer jpql = new StringBuffer("");
            List<Object> params = new ArrayList<Object>();
            //按图书名称查询
            if(formbean.getName()!=null && !"".equals(formbean.getName())){
                if(params.size()>0) jpql.append(" and ");
                jpql.append(" o.name like ?").append((params.size()+1));
                params.add("%"+ formbean.getName()+ "%");
            }
            
            //按图书类型id查询
            if(formbean.getTypeid()!=null && formbean.getTypeid()>0){
                if(params.size()>0) jpql.append(" and ");
                jpql.append(" o.type.typeid=?").append((params.size()+1));
                params.add(formbean.getTypeid());
            }
            //按图书条形码查询
            if(formbean.getBarcode()!=null && !"".equals(formbean.getBarcode())){
                if(params.size()>0) jpql.append(" and ");
                jpql.append(" o.barcode=?").append((params.size()+1));
                params.add(formbean.getBarcode());
            }
            //按所在书架名称查询
            if(formbean.getShelfid()!=null && !"".equals(formbean.getShelfid())){
                if(params.size()>0) jpql.append(" and ");
                jpql.append(" o.shelf.shelfid=?").append((params.size()+1));
                params.add(formbean.getShelfid());
            }
            pageView.setQueryResult(bookInfoService.getScrollData(pageView.getFirstResult(), 
                    pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby));
        }else{
            pageView.setQueryResult(bookInfoService.getScrollData(pageView.getFirstResult(), 
                    pageView.getMaxresult(), orderby));
        }
        request.setAttribute("pageView", pageView);     
        return mapping.findForward("list");
    }
    
    
    
}
