package cn.dream.web.formbean.user;

import org.apache.commons.codec.binary.Base64;

import cn.dream.bean.user.Gender;
import cn.dream.web.formbean.BaseForm;

public class UserForm extends BaseForm {
	private String librarycard;
	private String username;
	private String password;
	private String email;
    private Gender gender;
	private String[] usernames;
	private String directUrl;
	private String validateCode;	
	
	
	public String getLibrarycard() {
		return librarycard;
	}

	public void setLibrarycard(String librarycard) {
		this.librarycard = librarycard;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		if(directUrl!=null && !"".equals(directUrl.trim())){
			this.directUrl = new String(Base64.decodeBase64(directUrl.trim().getBytes()));//获取解码后的url
		}
	}
	
	public String[] getUsernames() {
		return usernames;
	}
	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
