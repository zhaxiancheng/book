package cn.dream.service.user.impl;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.user.User;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.user.UserService;
import cn.dream.utils.MD5;


@Service @Transactional
public class UserServiceBean extends DaoSupport<User> implements UserService {

	public void updatePassword(String username, String newpassword){
		em.createQuery("update User o set o.password=?1 where o.username=?2")
		.setParameter(1, MD5.MD5Encode(newpassword)).setParameter(2, username).executeUpdate();
	}
	@Override
	public void updateUser(User entity) {
		entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		super.update(entity);
	}
	
	 @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit(String librarycard) {
		 long count = (Long)em.createQuery("select count(o) from User o where o.librarycard=?1").setParameter(1, librarycard)
					.getSingleResult();
				return count>0;
	}
   
	public boolean exsit(String librarycard,String username){
		long count = (Long)em.createQuery("select count(o) from User o where o.librarycard=?1 and o.username=?2").setParameter(1, librarycard).setParameter(2, username)
			.getSingleResult();
		return count>0;
	}
	
	public boolean checkUser(String librarycard, String password){
		long count = (Long)em.createQuery("select count(o) from User o where o.librarycard=?1 and o.password=?2")
		.setParameter(1, librarycard).setParameter(2,password).getSingleResult();//MD5.MD5Encode(password)
		return count>0;
	}
	
	@Override
	public void delete(Serializable ... entityIds){
		visible(false, entityIds);
	}

	@Override
	public long getCount() {
		return (Long)em.createQuery("select count(o) from User o where o.visible=?1")
		.setParameter(1, true).getSingleResult();
	}

	private void visible(boolean visible, Serializable ... usernames){
		if(usernames!=null && usernames.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<usernames.length;i++){
				jpql.append('?').append(i+2).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update User b set b.visible=?1 where b.librarycard in("+ jpql.toString() +")");
			query.setParameter(1, visible);
			for(int i=0; i<usernames.length;i++){
				query.setParameter(i+2, usernames[i]);
			}
			query.executeUpdate();
		}
	}
	
	public void enable(Serializable ... usernames) {
		visible(true, usernames);
	}
	
	
}
