package mx.com.vgati.framework.util;

import java.util.Map;

import mx.com.vgati.framework.util.exception.BeanDescriptionException;
import mx.com.vgati.framework.util.exception.CloneableBeanException;
import mx.com.vgati.framework.util.exception.CopyPropertiesException;
import mx.com.vgati.framework.util.exception.PopulateBeanException;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * Clase utilitaria para realizar conversiones de beans.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
public class BeanUtils {
	public static void copyProperties(Object destination, Object origin) {
		try {
			BeanUtilsBean.getInstance().copyProperties(destination, origin);
		} catch (Exception e) {
			throw new CopyPropertiesException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> describe(Object object) {
		try {
			return BeanUtilsBean.getInstance().describe(object);
		} catch (Exception e) {
			throw new BeanDescriptionException(e);
		}
	}

	public static Object cloneBean(Object bean) {
		try {
			return BeanUtilsBean.getInstance().cloneBean(bean);
		} catch (Exception e) {
			throw new CloneableBeanException(e);
		}
	}

	public static void populateBean(Map<String, Object> parameterMap,
			Object bean) {
		try {
			BeanUtilsBean.getInstance().populate(bean, parameterMap);
		} catch (Exception e) {
			throw new PopulateBeanException(e);
		}
	}
}
