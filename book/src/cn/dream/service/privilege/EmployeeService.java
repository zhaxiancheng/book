package cn.dream.service.privilege;

import cn.dream.bean.privilege.Employee;
import cn.dream.service.base.DAO;

public interface EmployeeService extends DAO<Employee> {
    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return
     */
    public boolean exist(String username);
    /**
     * 判断用户名及密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean validate(String username, String password);
}
