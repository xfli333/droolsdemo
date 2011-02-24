package cn.lee.demo.drools.services.impl;


import cn.lee.demo.drools.dao.HibernateDao;
import cn.lee.demo.drools.dao.impl.DefaultHibernateDao;
import cn.lee.demo.drools.model.IModel;
import cn.lee.demo.drools.model.Model;
import cn.lee.demo.drools.services.Manager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


/**
 * BaseManager所有服务的基类,提供操作Hibernate的方法
 *
 * @author Mars
 */
@Service("baseManager")
public class BaseManager implements Manager, ApplicationContextAware {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private HibernateDao hibernateDao;

    protected ApplicationContext ac;

    public BaseManager() {
    }

    public void deleteAll(Collection objects) {
        for (Object object : objects) {
            delete((IModel) object);
        }
    }

    public void joinSession(Object object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void initializeChildren(Collection children) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void flushSession() {
        hibernateDao.flushSession();
    }

    public Object findByQueryUniqueResult(String hql, String[] paramNames, Object[] values) {
        return hibernateDao.findByQueryUniqueResult(hql, paramNames, values);
    }

    public List findByQuery(String hql, String[] paramNames, Object[] values) {
        return hibernateDao.findByQuery(hql, paramNames, values);
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ac = ac;
        hibernateDao = (DefaultHibernateDao) ac.getBean("hibernateDao");
    }

    public <T extends Model> T store(T object) {
        T model = hibernateDao.store(object);
        return model;
    }

    public List findByQuery(String hql, Object... values) {
        return hibernateDao.findByQuery(hql, values);
    }

//	public ValueList pagingQuery(String hql, Object[] values, int currentPage, int pagingPage) {
//		return hibernateDao.pagingQuery(hql,values,currentPage,pagingPage);
//	}
//
//	public ValueList pagingQuery(String hql, Object[] values, int currentPage, int pagingPage,boolean calNumberCount) {
//		return hibernateDao.pagingQuery(hql,values,currentPage,pagingPage,calNumberCount);
//	}

    public List loadAll(Class clazz, List<Long> ids) {
        return this.hibernateDao.loadAll(clazz, ids.toArray(new Long[0]));
    }

    public <T extends Model> T load(Class<T> clazz, Long id) {
        return this.hibernateDao.load(clazz, id);
    }


    public void delete(Object object) {
        Model model = (Model) object;
        model = load(model.getClass(), model.getId());
        hibernateDao.delete(model);
    }

    public void evict(Object o) {
        hibernateDao.evict(o);
    }

    public int execute(String hql, Object... values) {
        return hibernateDao.execute(hql, values);
    }

    public List findByQuery(String hql, Object[] values, FlushMode flushMode) {
        return hibernateDao.findByQuery(hql, values, flushMode);
    }

    public Object findByQueryUniqueResult(String hql, Object... values) {
        return hibernateDao.findByQueryUniqueResult(hql, values);
    }

    public Object findByQueryUniqueResultNoFlush(String hql, Object... values) {
        return hibernateDao.findByQueryUniqueResultNoFlush(hql, values);
    }

    public <T extends Model> T load(T dbModel) {
        return hibernateDao.load(dbModel);
    }

    public <T extends Model> List<T> loadAll(List<T> modelList) {
        return hibernateDao.loadAll(modelList);
    }

    public <T extends Model> List<T> loadAll(Class<T> clazz, Long[] ids) {
        return hibernateDao.loadAll(clazz, ids);
    }

    public List loadAll(Class clazz) {
        return hibernateDao.loadAll(clazz);
    }

    public <T extends Model> Collection<T> storeAll(Collection<T> newModels) {
        return this.hibernateDao.storeAll(newModels);
    }

    public void clearSession() {
        hibernateDao.clearSession();
    }

    public Session getHibernateSession() {
        return hibernateDao.getHibernateSession();
    }

}
