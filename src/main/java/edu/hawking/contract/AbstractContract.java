package edu.hawking.contract;

import org.apache.commons.beanutils.BeanMap;

import java.util.Map;

/**
 * 杜皓君 created by 2021/5/12
 * AbstractContract
 **/
public abstract class AbstractContract implements Contract{
    protected String title;
    protected String version;
    protected Map<String, String> parameterMap;

    protected abstract void checkParams(Object[] objects);

    protected Map beanToMap() {
        return new BeanMap(this);
    }
}
