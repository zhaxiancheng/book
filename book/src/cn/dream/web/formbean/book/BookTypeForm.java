package cn.dream.web.formbean.book;

import cn.dream.web.formbean.BaseForm;

public class BookTypeForm extends BaseForm {
	private static final long serialVersionUID = -6146631431645768150L;
	private Integer parentid;
	private String name;
	private String note;
	private Integer typeid;

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}
