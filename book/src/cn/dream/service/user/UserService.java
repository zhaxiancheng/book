package cn.dream.service.user;

import java.io.Serializable;

import cn.dream.bean.user.User;
import cn.dream.service.base.DAO;


public interface UserService extends DAO<User>{
    /**
     * 启用指定用户
     * @param usernames
     */
    public void enable(Serializable ... usernames);
    /**
     * 判断借阅卡号是否存在
     * @param librarycard
     * @return
     */
    
    public boolean exsit(String librarycard);
    /**
     * 用借阅卡号和用户名来验证用户是否存在
     * @param librarycard
     * @param username
     * @return
     */
    public boolean exsit(String librarycard,String username);
    /**
     * 判断借阅卡及密码是否正确
     * @param librarycard
     * @param password
     * @return
     */
    public boolean checkUser(String librarycard, String password);
    
    /**
     * 更新密码
     * @param username 用户名
     * @param newpassword 新密码
     */
    public void updatePassword(String username, String newpassword);
    /**
     * 激活用户
     * @param user
     */
    public void updateUser(User user);
    }