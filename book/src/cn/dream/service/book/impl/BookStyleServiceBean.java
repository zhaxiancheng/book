package cn.dream.service.book.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.book.BookStyle;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.book.BookStyleService;


@Service
@Transactional
public class BookStyleServiceBean extends DaoSupport<BookStyle> implements BookStyleService {

	public void setVisibleStatu(Integer[] productstyleids, boolean statu) {
		if(productstyleids!=null && productstyleids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0;i<productstyleids.length;i++){
				jpql.append('?').append((i+2)).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update BookStyle o set o.visible=?1 where o.id in("+ jpql.toString()+ ")");
			query.setParameter(1, statu);
			for(int i=0;i<productstyleids.length;i++){
				query.setParameter(i+2, productstyleids[i]);
			}
			query.executeUpdate();
		}
	}
}
