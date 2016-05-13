package cn.dream.web.action.user;

import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Controller;

import cn.dream.bean.user.User;
import cn.dream.mail.EmailSender;
import cn.dream.service.user.UserService;
import cn.dream.utils.MD5;
import cn.dream.utils.SiteUrl;
import cn.dream.web.formbean.user.UserForm;
/**
 * 找回密码
 */
@Controller("/user/post")
public class FindPasswordAction extends DispatchAction {
    @Resource UserService userService;
    /**
     * 找回密码之发送邮件
     */
    public ActionForward getpassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        System.out.println(formbean.getLibrarycard());
        if(formbean.getLibrarycard()!=null && !"".equals(formbean.getLibrarycard().trim())){
            if(userService.exsit(formbean.getLibrarycard().trim())){
                User user = userService.find(formbean.getLibrarycard().trim());
                Template template = Velocity.getTemplate("mailContent.html");
                VelocityContext context = new VelocityContext();
                context.put("username", user.getUsername());
                context.put("librarycard", user.getLibrarycard());
                context.put("validateCode", MD5.MD5Encode(user.getUsername()+ user.getLibrarycard()));
                StringWriter writer = new StringWriter(); 
                template.merge(context, writer);
                String content = writer.toString();
                EmailSender.send(user.getEmail(), "同大图书馆-找回密码邮件", content, "text/html");
                return mapping.findForward("findpassword2");
            }else{
                request.setAttribute("message", "借阅卡号不存在");
            }           
        }else{
            request.setAttribute("message", "请输入借阅卡号");
        }
        return mapping.findForward("findpassword");
    }
    
    /**
     * 找回密码之显示密码修改界面
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        if(formbean.getLibrarycard()!=null && !"".equals(formbean.getLibrarycard().trim())){
            if(userService.exsit(formbean.getLibrarycard().trim())){
                User user = userService.find(formbean.getLibrarycard().trim());
                String code = MD5.MD5Encode(user.getUsername()+ user.getLibrarycard());
                if(code.equals(formbean.getValidateCode())){//校验通过，表示来源合法                   
                    return mapping.findForward("findPassword3");
                }
            }           
        }
        return mapping.findForward("errorresult");
    }
    
    /**
     * 找回密码之修改密码
     */
    public ActionForward changepassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm formbean = (UserForm) form;
        if(formbean.getLibrarycard()!=null && !"".equals(formbean.getLibrarycard().trim())){
            if(userService.exsit(formbean.getLibrarycard().trim())){
                User user = userService.find(formbean.getLibrarycard().trim());
                String code = MD5.MD5Encode(user.getUsername()+ user.getLibrarycard());
                if(code.equals(formbean.getValidateCode())){//校验通过，表示来源合法                   
                    userService.updatePassword(user.getLibrarycard(), formbean.getPassword().trim());
                    request.setAttribute("message", "密码修改成功");
                    request.setAttribute("urladdress", "/user/logon.do");
                    return mapping.findForward("message");
                }
            }           
        }
        return mapping.findForward("errorresult");
    }
}
