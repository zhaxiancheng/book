package cn.dream.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.dream.service.user.UserService;
import cn.dream.web.formbean.user.UserForm;


@Controller("/user/logon")
public class LogonAction extends Action {
    @Resource(name="userServiceBean") UserService userService;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        if(formbean.getLibrarycard()!=null && !"".equals(formbean.getLibrarycard().trim())
                && formbean.getPassword()!=null && !"".equals(formbean.getPassword().trim())){
            if(userService.checkUser(formbean.getLibrarycard().trim(), formbean.getPassword().trim())){
                request.getSession().setAttribute("user", userService.find(formbean.getLibrarycard().trim()));
                String url = "/book/list/display.do?typeid=1";
                if(formbean.getDirectUrl()!=null) url = formbean.getDirectUrl();
                request.setAttribute("directUrl", url);
                return mapping.findForward("directUrl");
            }
            request.setAttribute("message", "用户名或密码有误");
        }
        return mapping.findForward("logon");
    }
}
