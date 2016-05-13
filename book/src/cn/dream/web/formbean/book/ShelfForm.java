package cn.dream.web.formbean.book;


import cn.dream.web.formbean.BaseForm;

public class ShelfForm extends BaseForm {
	
	private static final long serialVersionUID = -1839718567240619520L;
	private String name;
	private String shelfid;
	private String location;
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

	public String getShelfid() {
		return shelfid;
	}

	public void setShelfid(String shelfid) {
		this.shelfid = shelfid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
