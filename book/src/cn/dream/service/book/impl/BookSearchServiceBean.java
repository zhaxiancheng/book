package cn.dream.service.book.impl;

import javax.annotation.Resource;

import org.compass.core.Compass;
import org.compass.core.CompassTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.QueryResult;
import cn.dream.bean.book.BookInfo;
import cn.dream.service.book.BookSearchService;

@Service @Transactional
public class BookSearchServiceBean implements BookSearchService {
	private CompassTemplate compassTemplate;
	
	@Resource
	public void setCompass(Compass compass){
		this.compassTemplate = new CompassTemplate(compass);
	}
	
	public QueryResult<BookInfo> query(String keyword, int firstResult, int maxResult){
		return compassTemplate.execute(new QueryCallback(keyword, firstResult, maxResult));
	}
}
