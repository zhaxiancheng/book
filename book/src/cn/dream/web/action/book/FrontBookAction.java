package cn.dream.web.action.book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
import cn.dream.bean.book.BookStyle;
import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Sex;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.book.BookTypeService;
import cn.dream.utils.WebUtil;
import cn.dream.web.formbean.book.FrontBookForm;


@Controller("/book/list/display")
public class FrontBookAction extends Action {
    @Resource(name="bookInfoServiceBean")
    private BookInfoService bookInfoService;
    
    @Resource(name="bookTypeServiceBean")
    private BookTypeService bookTypeService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        FrontBookForm formbean = (FrontBookForm) form;
        PageView<BookInfo> pageView = new PageView<BookInfo>(9, formbean.getPage());
        pageView.setPagecode(20);
        int firstindex = (pageView.getCurrentpage()-1)* pageView.getMaxresult();
        LinkedHashMap<String, String> orderby = buildOrder(formbean.getSort());
        StringBuffer jpql = new StringBuffer("o.visible=?1");
        List<Object> params = new ArrayList<Object>();
        params.add(true);   
        List<Integer> typeids = new ArrayList<Integer>();
        typeids.add(formbean.getTypeid());
        getTypeids(typeids, new Integer[]{formbean.getTypeid()});
        StringBuffer n = new StringBuffer();
        for(int i=0; i<typeids.size();i++){
            n.append('?').append((i+2)).append(',');
        }
        n.deleteCharAt(n.length()-1);
        jpql.append(" and o.type.typeid in("+ n.toString()+ ")");
        params.addAll(typeids);
        
        if(formbean.getIsbn()!=null && !"".equals(formbean.getIsbn().trim())){
            jpql.append(" and o.shelf.shelfid=?").append((params.size()+1));
            params.add(formbean.getIsbn());
        }           
        if(formbean.getSex()!=null ){
            String sex = formbean.getSex().trim();  
            if("NONE".equalsIgnoreCase(sex) || "MAN".equalsIgnoreCase(sex) || "WOMEN".equalsIgnoreCase(sex)){
                jpql.append(" and o.sexrequest=?").append((params.size()+1));
                params.add(Sex.valueOf(formbean.getSex()));
            }
        }       
        pageView.setQueryResult(bookInfoService.getScrollData(firstindex, 
                    pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby));
        for(BookInfo book : pageView.getRecords()){
            Set<BookStyle> styles = new HashSet<BookStyle>();
            for(BookStyle style : book.getStyles()){
                if(style.getVisible()){
                    styles.add(style);
                    break;
                }
            }
            book.setStyles(styles);
            //注意:执行此句代码会把修改后的数据同步回数据库,如果不想把数据同步回数据库,请在其后调用bookInfoService.clear();
            book.setDescription(WebUtil.HtmltoText(book.getDescription()));
        }
        bookInfoService.clear();//让托管状态的实体成为游离状态
        request.setAttribute("pageView", pageView);
        Integer[] ids = new Integer[typeids.size()];
        for(int i=0;i<typeids.size();i++){
            ids[i]=typeids.get(i);
        }
//      request.setAttribute("booktypeids", bookInfoService.getBookTypesByBookTypeid(ids));
        request.setAttribute("bookmap", bookInfoService.getBookTypes());
        if(formbean.getTypeid()!=null && formbean.getTypeid()>0){
            BookType type = bookTypeService.find(formbean.getTypeid());
            if(type!=null){
                List<BookType> types = new ArrayList<BookType>();
                types.add(type);
                BookType parent = type.getParent();
                while(parent!=null){
                    types.add(parent);
                    parent = parent.getParent();
                }
                request.setAttribute("booktype", type);
                request.setAttribute("types", types);
            }
        }
        return mapping.findForward("list_image");
    }
    
    /**
     * 获取类别下所有子类的id(注:子类级其子类的id都会获取到)
     * @param outtypeids
     * @param typeids
     */
    public void getTypeids(List<Integer> outtypeids, Integer[] typeids){
        List<Integer> subtypeids =bookTypeService.getSubTypeid(typeids);
        if(subtypeids!=null && subtypeids.size()>0){
            outtypeids.addAll(subtypeids);
            Integer[] ids = new Integer[subtypeids.size()];
            for(int i=0;i<subtypeids.size();i++){
                ids[i]=subtypeids.get(i);
            }
            getTypeids(outtypeids, ids);
        }
    }
    /**
     * 组拼排序
     * @param orderfied
     * @return
     */
    private LinkedHashMap<String, String> buildOrder(String orderfied){
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if("borrowcountdesc".equals(orderfied)){
            orderby.put("borrowcount", "desc");
        }else if("borrowcountasc".equals(orderfied)){
            orderby.put("borrowcount", "asc");
        }else if("intimeasc".equals(orderfied)){
            orderby.put("intime", "asc");
        }else{
            orderby.put("intime", "desc");
        }
        return orderby;
    }
}
