package cn.dream.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.dream.bean.privilege.Employee;
import cn.dream.bean.privilege.IDCard;
import cn.dream.bean.privilege.PrivilegeGroup;
import cn.dream.bean.privilege.SystemPrivilege;
import cn.dream.bean.user.Gender;
import cn.dream.service.privilege.EmployeeService;
import cn.dream.service.privilege.PrivilegeGroupService;
import cn.dream.service.privilege.SystemPrivilegeService;
/**
 * 初始化 (此action是在系统安装完后就执行)
 */
@Controller("/system/init")
public class SystemInitAction extends Action {
    @Resource SystemPrivilegeService privilegeService;
    @Resource PrivilegeGroupService groupService;
    @Resource EmployeeService employeeService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        initSystemPrivilege();
        initPrivilegeGroup();
        initAdmin();
        request.setAttribute("message", "初始化完成");
        request.setAttribute("urladdress", "/employee/logon.do");
        return mapping.findForward("message");
    }
    /**
     * 初始化管理员账号
     */
    private void initAdmin() {
        if(employeeService.getCount()==0){
            Employee employee = new Employee();
            employee.setUsername("admin");
            employee.setPassword("admin");
            employee.setRealname("系统管理员");
            employee.setGender(Gender.MAN);
            employee.setIdCard(new IDCard("213213","北京", new Date()));
            employee.getGroups().addAll(groupService.getScrollData().getResultlist());//赋予权限
            employeeService.save(employee);
        }       
    }
    /**
     * 初始化系统权限组
     */
    private void initPrivilegeGroup() {
        if(groupService.getCount()==0){
            PrivilegeGroup group = new PrivilegeGroup();
            group.setName("系统权限组");
            group.getPrivileges().addAll(privilegeService.getScrollData().getResultlist());
            groupService.save(group);
        }       
    }
    /**
     * 初始化权限
     */
    private void initSystemPrivilege() {
        if(privilegeService.getCount()==0){
            List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
            privileges.add(new SystemPrivilege("department", "view", "部门查看"));
            privileges.add(new SystemPrivilege("department", "insert", "部门添加"));
            privileges.add(new SystemPrivilege("department", "update", "部门修改"));
            privileges.add(new SystemPrivilege("department", "delete", "部门删除"));
            
            privileges.add(new SystemPrivilege("borrow","back","图书归还"));
            privileges.add(new SystemPrivilege("borrow","renew","图书续借"));
            privileges.add(new SystemPrivilege("borrow","borrow","借出图书"));
            
            
            privileges.add(new SystemPrivilege("employee","leave","员工离职设置"));
            privileges.add(new SystemPrivilege("employee","insert","员工添加"));
            privileges.add(new SystemPrivilege("employee","update","员工信息修改"));
            privileges.add(new SystemPrivilege("employee","view","员工查看"));
            privileges.add(new SystemPrivilege("employee","privilegeGroupSet","员工权限设置"));
            
            privileges.add(new SystemPrivilege("privilegegroup","list","权限组管理"));
            
            privileges.add(new SystemPrivilege("shelf","insert","书架添加"));
            privileges.add(new SystemPrivilege("shelf","update","书架信息修改"));
            privileges.add(new SystemPrivilege("shelf","view","书架查看"));
            
            privileges.add(new SystemPrivilege("book","insert","图书添加"));
            privileges.add(new SystemPrivilege("book","update","图书信息修改"));
            privileges.add(new SystemPrivilege("book","view","图书查看"));
            privileges.add(new SystemPrivilege("book","visible","图书上/下架"));
            privileges.add(new SystemPrivilege("book","commend","图书推荐"));
            
            privileges.add(new SystemPrivilege("bookType","insert","图书类别添加"));
            privileges.add(new SystemPrivilege("bookType","update","图书类别修改"));
            privileges.add(new SystemPrivilege("bookType","view","图书类别查看"));
            
            privileges.add(new SystemPrivilege("user","enable","读者启用"));
            privileges.add(new SystemPrivilege("user","delete","读者禁用"));
            privileges.add(new SystemPrivilege("user","view","读者查看"));
            
            privilegeService.batchSave(privileges);
        }       
    }

}
