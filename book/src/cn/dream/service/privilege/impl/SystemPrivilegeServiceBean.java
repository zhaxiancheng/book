package cn.dream.service.privilege.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.dream.bean.privilege.SystemPrivilege;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.privilege.SystemPrivilegeService;

@Service
public class SystemPrivilegeServiceBean extends DaoSupport<SystemPrivilege> implements SystemPrivilegeService {
	
	public void batchSave(List<SystemPrivilege> privileges){
		for(SystemPrivilege p : privileges){
			save(p);
		}
	}
}
