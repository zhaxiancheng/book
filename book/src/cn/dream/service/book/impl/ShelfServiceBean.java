package cn.dream.service.book.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.book.Shelf;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.book.ShelfService;


@Service
@Transactional
public class ShelfServiceBean extends DaoSupport<Shelf> implements ShelfService {

	@Override
	public void save(Shelf entity) {
		entity.setShelfid(UUID.randomUUID().toString());
		super.save(entity);
	}
	
}
