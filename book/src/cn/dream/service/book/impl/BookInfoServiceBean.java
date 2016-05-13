package cn.dream.service.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Shelf;
import cn.dream.bean.book.BookInfo;
import cn.dream.service.base.DaoSupport;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.book.BookTypeService;

@Service
@Transactional
public class BookInfoServiceBean extends DaoSupport<BookInfo> implements
        BookInfoService {
    @Resource(name = "bookTypeServiceBean")
    private BookTypeService bookTypeService;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<BookInfo> getViewHistory(Integer[] bookids, int maxResult) {
        StringBuffer jpql = new StringBuffer();
        for (int i = 0; i < bookids.length; i++) {
            jpql.append('?').append(i).append(',');
        }
        jpql.deleteCharAt(jpql.length() - 1);
        Query query = em
                .createQuery("select o from BookInfo o where o.bookid in("
                        + jpql.toString() + ")");
        for (int i = 0; i < bookids.length; i++) {
            query.setParameter(i, bookids[i]);
        }
        query.setFirstResult(0).setMaxResults(maxResult);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<BookInfo> getTopSell(Integer typeid, int maxResult) {
        List<Integer> typeids = new ArrayList<Integer>();
        typeids.add(typeid);
        getTypeids(typeids, new Integer[] { typeid });
        StringBuffer n = new StringBuffer();
        for (int i = 0; i < typeids.size(); i++) {
            n.append('?').append((i + 2)).append(',');
        }
        n.deleteCharAt(n.length() - 1);
        Query query = em
                .createQuery("select o from BookInfo o where o.recommend=?1 and o.type.typeid in("
                        + n.toString() + ") order by o.borrowcount desc");
        query.setParameter(1, true);
        for (int i = 0; i < typeids.size(); i++) {
            query.setParameter(i + 2, typeids.get(i));
        }
        query.setFirstResult(0).setMaxResults(maxResult);
        return query.getResultList();
    }

    private void getTypeids(List<Integer> outtypeids, Integer[] typeids) {
        List<Integer> subtypeids = bookTypeService.getSubTypeid(typeids);
        if (subtypeids != null && subtypeids.size() > 0) {
            outtypeids.addAll(subtypeids);
            Integer[] ids = new Integer[subtypeids.size()];
            for (int i = 0; i < subtypeids.size(); i++) {
                ids[i] = subtypeids.get(i);
            }
            getTypeids(outtypeids, ids);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Shelf> getShelfsByBookTypeid(Integer[] typeids) {
        if (typeids != null && typeids.length > 0) {
            StringBuffer jpql = new StringBuffer();
            for (int i = 0; i < typeids.length; i++) {
                jpql.append('?').append((i + 1)).append(',');
            }
            jpql.deleteCharAt(jpql.length() - 1);
            Query query = em
                    .createQuery("select o from Shelf o where o.shelfid in(select p.shelf.shelfid from BookInfo p where p.type.typeid in("
                            + jpql.toString() + ") group by p.shelf.shelfid)");
            for (int i = 0; i < typeids.length; i++) {
                query.setParameter(i + 1, typeids[i]);
            }
            return query.getResultList();
        }
        return null;
    }

    public void setCommendStatu(Integer[] productids, boolean statu) {
        if (productids != null && productids.length > 0) {
            StringBuffer jpql = new StringBuffer();
            for (int i = 0; i < productids.length; i++) {
                jpql.append('?').append((i + 2)).append(',');
            }
            jpql.deleteCharAt(jpql.length() - 1);
            Query query = em
                    .createQuery("update BookInfo o set o.recommend=?1 where o.id in("
                            + jpql.toString() + ")");
            query.setParameter(1, statu);
            for (int i = 0; i < productids.length; i++) {
                query.setParameter(i + 2, productids[i]);
            }
            query.executeUpdate();
        }
    }

    public void setVisibleStatu(Integer[] productids, boolean statu) {
        if (productids != null && productids.length > 0) {
            StringBuffer jpql = new StringBuffer();
            for (int i = 0; i < productids.length; i++) {
                jpql.append('?').append((i + 2)).append(',');
            }
            jpql.deleteCharAt(jpql.length() - 1);
            Query query = em
                    .createQuery("update BookInfo o set o.visible=?1 where o.bookid in("
                            + jpql.toString() + ")");
            query.setParameter(1, statu);
            for (int i = 0; i < productids.length; i++) {
                query.setParameter(i + 2, productids[i]);
            }
            query.executeUpdate();
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<BookType> getBookTypesByBookTypeid(Integer[] typeids) {
        /*
         * if(typeids!=null && typeids.length>0){ StringBuffer jpql = new
         * StringBuffer(); for(int i=0;i<typeids.length;i++){
         * jpql.append('?').append((i+1)).append(','); }
         * jpql.deleteCharAt(jpql.length()-1); Query query = em.createQuery(
         * "select o from BookType o where o.typeid in(select p.typeid from BookInfo p where p.type.typeid in("
         * + jpql.toString()+") group by p.typeid)"); for(int
         * i=0;i<typeids.length;i++){ query.setParameter(i+1, typeids[i]); }
         * return query.getResultList(); }
         */
        return null;
    }

    @Override
    public Map<String, String> getBookTypes() {
        Map<String, String> map = new HashMap<String, String>();
        // select distinct B.A from B where B.name='xx' and B.A.dept='pp'
        // from ClassName cn where cn.id in (3,4,5)
        // ”select distinct cn from ClassName cn
        // Query query =
        // em.createQuery("from BookType b where b.typeid in(select distinct o.typeid from BookInfo o))");
//      select o from BookType o where o.parentid=?1
//      from BookType b where b.parentid=?1
        Query query = em.createQuery("from BookType");
        List list = query.getResultList();
        for (Object object : list) {
            BookType book = (BookType) object;
            map.put(book.getTypeid() + "", book.getName());
        }
        return map;
    }

}
