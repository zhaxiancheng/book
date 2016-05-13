package cn.dream.web.action.book;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.dream.service.book.BookInfoService;
import cn.dream.utils.WebUtil;
import cn.dream.web.formbean.book.FrontBookForm;


@Controller("/book/switch")
public class BookSwitchAction extends DispatchAction {
    @Resource(name="bookInfoServiceBean")
    private BookInfoService bookInfoService;
    
    /**
     * 显示产品大图片
     */
    public ActionForward showimage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("showimage");
    }
    
    /**
     * 获取10个最畅销的产品
     */
    public ActionForward topsell(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        FrontBookForm formbean = (FrontBookForm) form;
        request.setAttribute("topsellbooks", bookInfoService.getTopSell(formbean.getTypeid(), 10));
        return mapping.findForward("topsell");
    }
    
    /**
     * 获取10个用户浏览过的产品
     */
    public ActionForward getViewHistory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cookieValue =WebUtil.getCookieByName(request, "bookViewHistory");
        if(cookieValue!=null && !"".equals(cookieValue.trim())){            
            String[] ids = cookieValue.split("-");
            Integer[] bookids = new Integer[ids.length];
            for(int i=0 ;i<ids.length; i++){
                bookids[i]=new Integer(ids[i].trim());
            }
             request.setAttribute("viewHistory", bookInfoService.getViewHistory(bookids, 10));
        }
        return mapping.findForward("viewHistory");
    }
}
