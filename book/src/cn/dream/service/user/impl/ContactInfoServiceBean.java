package cn.dream.service.user.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.user.ContactInfo;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.user.ContactInfoService;


@Service @Transactional
public class ContactInfoServiceBean extends DaoSupport<ContactInfo> implements ContactInfoService{

}
