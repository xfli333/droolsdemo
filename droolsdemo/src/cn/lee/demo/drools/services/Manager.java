package cn.lee.demo.drools.services;

import cn.lee.demo.drools.model.Model;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


/**
 * 基本的对象操作接口
 */
public interface Manager {
    /**
     * 新增对象或更新对象
     *
     * @param model 需要持久化的对象
     * @return 新增或更新后的对象,如果是新增的对象返回的对象回自动生产ID
     */
    @Transactional(readOnly = false)
    public <T extends Model> T store(T model);

    /**
     * 批量新增或修改对象
     *
     * @param newModels 需要持久化的对象集合
     * @return 新增或更新后的对象集合,如果是新增的对象返回的对象回自动生产ID
     */
    @Transactional(readOnly = false)
    public <T extends Model> Collection<T> storeAll(Collection<T> newModels);

    /**
     * 根据映射,从数据库中取出主键为id的记录,构造成clazz的实例对象返回
     *
     * @param clazz 已经映射了的类
     * @param id    对应的数据库主键
     * @return clazz的实例对象,如果主键为id的记录不存在返回null
     */
    public <T extends Model> T load(Class<T> clazz, Long id);

    /**
     * 由对象的id和对象的类从数据库中取出主键为id的记录,构造成clazz的实例对象返回
     *
     * @param dbModel 游离的持久化对象
     * @return 持久化对象,如果主键为id的记录不存在返回null,如果dbModel的id为null抛出异常
     */
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

    /**
     * 批量从数据库中获取持久化对象
     *
     * @param modelList 持久化对象集合的来源
     * @return 持久化对象集合
     */
    public <T extends Model> List<T> loadAll(List<T> modelList);

    /**
     * 删除一个持久化对象,如果对象不是一个持久化对象那么抛出异常
     *
     * @param object 需要删除的持久化对象
     */
    @Transactional(readOnly = false)
    public void delete(Object object);

    /**
     * 删除一个集合中的所有对象,此对象集合中的所有对象都必须是持久化对象
     *
     * @param objects 需要删除的持久化对象集合
     */
    @Transactional(readOnly = false)
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
    public void joinSession(Object object);

    /**
     * 强制hibernate加载对象的子集合,此方法必须在manager中调用且集合中元素的父必须在session管理中
     *
     * @param children 需要强制加载的对象的子集合
     */
    public void initializeChildren(Collection children);

    /**
     * 强制hibernate清一次session,即将后台生成的SQL语句执行一次
     * 应用场景:
     * 某些对象被删除之后需要立即执行删除对象的sql语句,保证对象被删除
     * 以进行下一步的工作
     */
    public void flushSession();

    /**
     * 执行Session.clear()
     */
    public void clearSession();

    /**
     * 获得Hibernate Session,不建议直接操作Hibernate Session
     *
     * @return Hibernate Session
     */
    public Session getHibernateSession();

    /**
     * 由hql语句及传入的查询参数名,参数值做查询,如果没有查询到记录返回空集合
     * 此查询函数要求hql语句的格式为: FROM Orders o where o.serialNO like :serialNO AND o.deliveryTime = :deliveryTime
     *
     * @param hql        查询hql语句
     * @param paramNames 参数的名称
     * @param values     参数的值
     * @return 查询结果
     */
    public List findByQuery(String hql, String[] paramNames, Object[] values);

    /**
     * 由hql语句及传入的查询参数值做查询,无需查询参数名.如果没有查询到记录返回空集合
     * 此查询函数要求hql语句的格式为: FROM Orders o where o.serialNO like ? AND o.deliveryTime = ?
     * 注意:用此查询函数可以写比较简洁的hql语句,但是这种形式的查询的参数值不能为集合,即不能写有IN的hql语句,如:
     * FROM Orders o where o.company.id in (?)
     *
     * @param hql    查询hql语句
     * @param values 参数的值
     * @return 查询结果
     */
    public List findByQuery(String hql, Object... values);

    /**
     * 由hql语句及传入的查询参数值做查询,同时需要指定flushMode.如果没有查询到记录返回空集合
     *
     * @param hql       查询hql语句
     * @param values    参数的值
     * @param flushMode 由flushMode决定是否在查询以前先执行此Session已经生产的SQL语句
     * @return 查询结果
     */
    public List findByQuery(final String hql, final Object[] values, final FlushMode flushMode);

    /**
     * 由hql语句及传入的查询参数值做查询,同时在查询以前不执行此Session已经生产的SQL语句.如果没有查询到记录返回空集合
     *
     * @param hql    查询hql语句
     * @param values 参数的值
     * @return 查询结果
     */
    public Object findByQueryUniqueResultNoFlush(String hql, Object... values);

    /**
     * 由hql语句及传入的查询参数名,参数值做查询,返回唯一的一个对象,如果查询有多个对象则抛出异常,如果没有找到记录返回null
     * 此查询函数要求hql语句的格式为: FROM Orders o where o.serialNO like :serialNO AND o.deliveryTime = :deliveryTime
     *
     * @param hql        查询hql语句
     * @param paramNames 参数的名称
     * @param values     参数的值
     * @return 唯一的查询结果
     */
    public Object findByQueryUniqueResult(String hql, String[] paramNames, Object... values);

    /**
     * 由hql语句及传入的查参数值做查询,返回唯一的一个对象,如果查询有多个对象则抛出异常,如果没有找到记录返回null
     * 此查询函数要求hql语句的格式为: FROM Orders o where o.serialNO like ? AND o.deliveryTime = ?
     *
     * @param hql    查询hql语句
     * @param values 参数的值
     * @return 唯一的查询结果
     */
    public Object findByQueryUniqueResult(String hql, Object... values);

    /**
     * 使用NamedQuery做查询
     *
     * @param namedQueryName NamedQuery的名称
     * @param paramNames     参数名称
     * @param values         参数值
     * @return 查询结果
     */
//    public List findByNamedQuery(String namedQueryName, String[] paramNames, Object[] values);

    /**
     * 使用NamedQuery做唯一结果查询
     *
     * @param namedQueryName NamedQuery的名称
     * @param paramNames     参数名称
     * @param values         参数值
     * @return 查询结果
     */
//    public Object findByNamedQueryUniqueResult(String namedQueryName, String[] paramNames, Object[] values);

    /**
     * 翻页查询
     *
     * @param hql           查询hql
     * @param values        参数值
     * @param currentPage   当前页面
     * @param numberPerPage 每页的记录数
     * @return 包含翻译信息和查询结果的包装对象
     */
//    public ValueList pagingQuery(String hql, Object[] values, int currentPage, int numberPerPage);
//
//    public ValueList pagingQuery(String hql, Object[] values, int currentPage, int numberPerPage, boolean calNumberCount);

    /**
     * 将对象从当前的session中踢出
     *
     * @param o 需要从当前session中踢出的对象
     */
    public void evict(Object o);

    /**
     * 批量执行hql语句,如需要将门店号以C开头的门店(ChainStore)删除时可以执行下面的代码
     * String deleteHql = "delete ChainStore c where c.code like ?";
     * hibernateDao.execute(deleteHql,"C%");
     * 而不必将所有需要删除的门店先查询出来再调用hibernateDao.deleteAll(Collection)方法
     *
     * @param hql    需要执行的hql语句
     * @param values hql语句的参数值
     * @return 更新或删除的对象的数量
     */
    public int execute(String hql, Object... values);

}
