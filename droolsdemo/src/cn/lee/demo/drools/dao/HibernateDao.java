package cn.lee.demo.drools.dao;

import cn.lee.demo.drools.model.Model;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 通用对象存取接口
 */
public interface HibernateDao {
    /**
     * 新增对象或更新对象
     *
     * @return 新增或更新后的对象,如果是新增的对象返回的对象回自动生产ID
     */
    public <T extends Model> T store(T model);

    public <T extends Model> Collection<T> storeAll(Collection<T> newResources);

    /**
     * 根据映射,从数据库中取出主键为id的记录,构造成clazz的实例对象返回
     *
     * @param clazz 已经映射了的类
     * @param id    对应的数据库主键
     * @return clazz的实例对象,如果主键为id的记录不存在返回null
     */
    public <T extends Model> T load(Class<T> clazz, Long id);

    public <T extends Model> T load(T dbModel);

    /**
     * 取出数据库中映射类的所有对象
     *
     * @param clazz 映射类的Class
     * @return 数据库中映射类的所有对象
     */
    public List loadAll(Class clazz);

    /**
     * 根据id取出数据库中映射类的所有对象
     *
     * @param clazz 映射类的Class
     * @param ids   需要取出数组的标识数组
     * @return 数据库中映射类的所有对象
     */
    public <T extends Model> List<T> loadAll(Class<T> clazz, Long[] ids);

    public <T extends Model> List<T> loadAll(List<T> modelList);

    /**
     * 删除一个持久化对象,此对象必须是由Hibernate自动构造的
     *
     * @param object 需要删除的持久化对象
     */
    public void delete(Object object);

    /**
     * 删除一个集会中的所有对象,此对象集合中的所有对象都必须是hibernate自动构造的
     *
     * @param objects 需要删除的持久化对象集合
     */
    public void deleteAll(Collection objects);

    /**
     * 将游离的对象加挂到hibernate session中,让它接受hibernate session管理
     * 应用场景:
     * 显示的修改了游离对象的值之后需要得到游离对象的子集合
     * 注意:将对象图中的多个对象使用此方法加挂到session中可能会抛出异常,故在一个事务中
     * 此方法最多只调用一次
     *
     * @param object 需要加挂到hibernate session中的对象
     */
    public void refresh(Object object);

    /**
     * 得到spring提供的HibernateTemplate
     * HibernateTemplate提供大量简化hibernate操作代码的方法
     *
     * @return
     */
    public HibernateTemplate getHibernateTemplate();

    /**
     * 强制hibernate加载对象的子集合,此方法必须在manager中调用
     * 且集合中元素的父必须在session管理中
     *
     * @param proxy 需要强制加载的对象的子集合
     */
    public void initialize(Object proxy);

    /**
     * 强制hibernate对清一次session,即将后台生成的SQL语句执行一次
     * 应用场景:
     * 某些对象被删除之后需要立即执行删除对象的sql语句,保证对象被删除
     * 以进行下一步的工作
     */
    public void flushSession();


    public List findByQuery(String hql, String parameterKey, Object value);

    public List findByQuery(String hql, Map params);

    public List findByQuery(String hql);

    public List findByQuery(String hql, String[] paramNames, Object[] values);

    public List findByQuery(String hql, Object... values);

    public List findByQuery(final String hql, final Object[] values, final FlushMode flushMode);

    public Object findByQueryUniqueResult(String hql, String[] paramNames, Object... values);

    public Object findByQueryUniqueResult(String hql, Object... values);

    public Object findByQueryUniqueResultNoFlush(String hql, Object... values);

   
    public List loadAll(Class clazz, List<Long> ids);

    public void evict(Object o);

    public int execute(String hql, String[] paramKeys, Object... values);
	
	public int execute(String hql, Object... values);

	public Session getHibernateSession();
	/**
     * 执行Session.clear()
     */
	public void clearSession();
}
