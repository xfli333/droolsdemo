package cn.lee.demo.drools.dao.impl;

import cn.lee.demo.drools.dao.HibernateDao;
import cn.lee.demo.drools.model.Model;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;


/**
 * HibernateDao实现
 */
public class DefaultHibernateDao extends HibernateDaoSupport implements HibernateDao {
    public DefaultHibernateDao() {

    }

    public <T extends Model> T store(T model) {
        this.getHibernateTemplate().saveOrUpdate(model);
        return (T) this.load(model.getClass(), model.getId());
    }

    public <T extends Model> T load(Class<T> clazz, Long id) {
        return (T) this.getHibernateTemplate().get(clazz, id);
    }

    public List loadAll(Class clazz) {
        return this.getHibernateTemplate().loadAll(clazz);
    }

    public void delete(Object object) {
        this.getHibernateTemplate().delete(object);
    }

    public void deleteAll(Collection objects) {
        this.getHibernateTemplate().deleteAll(objects);
    }

    public void refresh(Object object) {
        this.getHibernateTemplate().lock(object, LockMode.READ);
    }

    public List loadAll(Class clazz, List<Long> ids) {
        List result = new ArrayList();
        for (Long id : ids) {
            result.add(this.load(clazz, id));
        }
        return result;
    }

    public List loadAll(Class clazz, Long[] ids) {
        return this.loadAll(clazz, Arrays.asList(ids));
    }

    public void initialize(Object proxy) {
        this.getHibernateTemplate().initialize(proxy);
    }

    public void flushSession() {
        this.getSession().flush();
    }


    public List findByQuery(String hql, String parameterKey, Object value) {
        return findByQuery(hql, new String[]{parameterKey}, new Object[]{value});
    }

    public List findByQuery(String hql) {
        return findByQuery(hql, new String[0], new Object[0]);
    }

    public List findByQuery(final String hql, final String[] paramNames, final Object[] values) {
        return (List) this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < paramNames.length; i++) {
                        query.setParameter(paramNames[i], values[i]);
                    }
                }
                return query.list();
            }
        });
    }

    public Object findByQueryUniqueResult(final String hql, final String[] paramNames, final Object[] values) {
        return (Object) this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < paramNames.length; i++) {
                        query.setParameter(paramNames[i], values[i]);
                    }
                }
                return query.uniqueResult();
            }
        });
    }

    public List findByQuery(String hql, Map params) {
        String[] paramNames = new String[params.size()];
        Object[] values = new Object[params.size()];
        int i = 0;
        for (Object obj : params.keySet()) {
            Entry entry = (Entry) obj;
            paramNames[i] = (String) entry.getKey();
            values[i] = entry.getValue();
            i++;
        }
        return findByQuery(hql, paramNames, values);
    }

    public List findByQuery(final String hql, final Object[] values) {

        return findByQuery(hql, values, FlushMode.NEVER);
    }

    public List findByQuery(final String hql, final Object[] values, final FlushMode flushMode) {

        return (List) this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                query.setFlushMode(flushMode);
                query.setCacheable(true);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.list();
            }
        });
    }

    public Object findByQueryUniqueResult(final String hql, final Object[] values) {
        return this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.uniqueResult();
            }
        });

    }

    public <T extends Model> T load(T dbModel) {
        return (T) this.load(dbModel.getClass(), dbModel.getId());
    }

    public void evict(Object o) {
        this.getSession().evict(o);
    }

    public <T extends Model> Collection<T> storeAll(Collection<T> models) {
        List<T> result = new ArrayList<T>();
        for (T model : models) {
            result.add(store(model));
        }
        return result;
    }


    public int execute(final String hql, final String[] paramKeys, final Object... values) {
        return (Integer) this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i].getClass().isArray()) {
                            query.setParameterList(paramKeys[i], (Object[]) values[i]);
                        } else if (values[i] instanceof Collection) {
                            query.setParameterList(paramKeys[i], (Collection) values[i]);
                        } else {
                            query.setParameter(i, values[i]);
                        }
                    }
                }


                return query.executeUpdate();
            }
        });
    }

    public int execute(final String hql, final Object... values) {
        return (Integer) this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.executeUpdate();
            }
        });
    }

    public void clearSession() {
        this.getSession().clear();
    }


    public Object findByQueryUniqueResultNoFlush(final String hql, final Object... values) {
        return this.getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.createQuery(hql);
                query.setFlushMode(FlushMode.NEVER);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.uniqueResult();
            }
        });
    }

    public <T extends Model> List<T> loadAll(List<T> modelList) {
        List<T> result = new ArrayList<T>();
        for (T model : modelList) {
            result.add(load(model));
        }
        return result;
    }


    public Session getHibernateSession() {
        return getSession();
    }
}
