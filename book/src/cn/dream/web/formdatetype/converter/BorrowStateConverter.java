package cn.dream.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import cn.dream.bean.borrow.State;

public class BorrowStateConverter implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object value) {
		if(clazz==String.class){
			return value.toString();
		}
		if(clazz==State.class){
			try{
				return State.valueOf((String) value);
			}catch (Exception e) {}
		}
		return null;
	}

}
