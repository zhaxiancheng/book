package cn.dream.service.book.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.book.BookType;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.book.BookTypeService;


@Service
@Transactional
public class BookTypeServiceBean extends DaoSupport<BookType> implements BookTypeService {

	@Override
	public void delete(Serializable ... entityids) {
		if(entityids!=null && entityids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0 ;i<entityids.length;i++){
				jpql.append("?").append(i+2).append(",");
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update BookType o set o.visible=?1 where o.typeid in("+ jpql.toString()+")")
			.setParameter(1, false);
			for(int i=0 ;i<entityids.length;i++){
				query.setParameter(i+2, entityids[i]);
			}
			query.executeUpdate();
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<Integer> getSubTypeid(Integer[] parentids){
		if(parentids!=null && parentids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<parentids.length; i++){
				jpql.append('?').append((i+1)).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("select o.typeid from BookType o where o.parent.typeid in("+ jpql.toString()+ ")");
			for(int i=0; i<parentids.length; i++){
				query.setParameter(i+1, parentids[i]);
			}
			return query.getResultList();
		}
		return null;
	}
	
}
