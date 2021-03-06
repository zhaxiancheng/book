package cn.dream.web.formbean.privilege;

import cn.dream.bean.privilege.SystemPrivilegePK;
import cn.dream.web.formbean.BaseForm;

public class PrivilegeGroupForm extends BaseForm {
	private String name;
	private String groupid;
	private SystemPrivilegePK[] privileges;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public SystemPrivilegePK[] getPrivileges() {
		return privileges;
	}

	public void setPrivileges(SystemPrivilegePK[] privileges) {
		this.privileges = privileges;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
