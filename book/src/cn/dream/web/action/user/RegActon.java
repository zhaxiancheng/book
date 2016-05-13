package cn.dream.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.dream.bean.user.User;
import cn.dream.service.user.UserService;
import cn.dream.web.formbean.user.UserForm;


@Controller("/user/reg")
public class RegActon extends DispatchAction {
    @Resource(name="userServiceBean") UserService userService;
    /**
     * 用户注册界面
     */
    public ActionForward regUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("reg");
    }
    /**
     * 用户注册
     */
    public ActionForward reg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        if(userService.exsit(formbean.getLibrarycard(), formbean.getUsername())){
            User user = new User(formbean.getLibrarycard(),formbean.getUsername(),formbean.getPassword(), formbean.getEmail());
            userService.updateUser(user);
            request.getSession().setAttribute("user", user);        
            request.setAttribute("message", "用户激活成功");
            String url = "/book/list/display.do?typeid=1";
            if(formbean.getDirectUrl()!=null) url = formbean.getDirectUrl();
            request.setAttribute("urladdress", url);
            return mapping.findForward("message");
        }else{
            request.setAttribute("message", "不存在的借阅账号和用户");
            return mapping.findForward("reg");
        }
    }
    /**
     * 判断用户名和 借阅卡号是否存在是否存在
     */
    public ActionForward isUserExsit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        request.setAttribute("exsit", userService.exsit(formbean.getLibrarycard(),formbean.getUsername()));
        return mapping.findForward("checkuser");
    }
}
