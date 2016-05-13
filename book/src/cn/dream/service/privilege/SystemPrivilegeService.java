package cn.dream.service.privilege;

import java.util.List;

import cn.dream.bean.privilege.SystemPrivilege;
import cn.dream.service.base.DAO;

public interface SystemPrivilegeService extends DAO<SystemPrivilege> {
    /**
     * 批量保存系统权限
     * @param privileges
     */
    public void batchSave(List<SystemPrivilege> privileges);
}
