package cn.lee.demo.drools.spring;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 2010-4-24
 * Time: 21:27:41
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import java.lang.reflect.Array;
import java.util.Map;

public class HbmMappingFactory implements FactoryBean, BeanFactoryAware {
    private final Class type;
    private ListableBeanFactory beanFactory;

    public HbmMappingFactory(Class type) {
        this.type = type;
    }

    public Object getObject() throws Exception {
        Object[] objects = ((Map) BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, type, false, false)).values().toArray();
        return objects;
    }


    public Class getObjectType() {
        return Array.newInstance(type, 0).getClass();
    }

    public boolean isSingleton() {
        return true;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

}
