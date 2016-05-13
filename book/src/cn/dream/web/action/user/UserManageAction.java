package cn.dream.web.action.user;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import cn.dream.bean.borrow.Borrow;
import cn.dream.bean.user.User;
import cn.dream.service.user.UserService;
import cn.dream.utils.SiteUrl;
import cn.dream.web.action.privilege.Permission;
import cn.dream.web.formbean.user.UserForm;

/**
 * 读者管理
 *
 */
@Controller("/control/user/manage")
public class UserManageAction extends DispatchAction {
    @Resource(name="userServiceBean") UserService userService;
    /**
     * 启用
     */
    @Permission(module="user", privilege="enable")
    public ActionForward enable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        userService.enable((Serializable[])formbean.getUsernames());
        request.setAttribute("message", "启用成功");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.user.list"));
        return mapping.findForward("message");
    }
    /**
     * 禁用
     */
    @Permission(module="user", privilege="delete")
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {      
        UserForm formbean = (UserForm) form;
        userService.delete((Serializable[])formbean.getUsernames());
        request.setAttribute("message", "禁用成功");
        request.setAttribute("urladdress", SiteUrl.readUrl("control.user.list"));
        return mapping.findForward("message");
    }
    /**
     * 修改用户界面
     */
    @Permission(module="user", privilege="delete")
    public ActionForward updateUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {  
        UserForm formbean = (UserForm) form;
        System.out.println(formbean.getLibrarycard());
        User user = userService.find(formbean.getUsernames()[0]);
        request.setAttribute("user", user);
        return mapping.findForward("edit");
    }
    /**
     * 添加用户界面
     */
    @Permission(module="user", privilege="delete")
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {      
        return mapping.findForward("add");
    }
    /**
     * 用户添加
     */
    @Permission(module="user", privilege="delete")
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        if(formbean.getLibrarycard()!=null && !"".equals(formbean.getLibrarycard().trim())&&formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
            if(!userService.exsit(formbean.getLibrarycard(), formbean.getUsername())){
                User user = new User(formbean.getUsername());
                user.setLibrarycard(formbean.getLibrarycard());
                user.setGender(formbean.getGender());
                userService.save(user);
                String url = "/control/user/list.do";
                if(formbean.getDirectUrl()!=null) url = formbean.getDirectUrl();
                request.setAttribute("urladdress", url);
                request.setAttribute("message", "添加读者成功");
                return mapping.findForward("message");
            }else{
                request.setAttribute("message", "用户名或借阅卡号已经存在");
                return mapping.findForward("add");
            }
            
        }
        request.setAttribute("message", "用户名或借阅卡号不能为空");
        return mapping.findForward("add");
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
    
}
