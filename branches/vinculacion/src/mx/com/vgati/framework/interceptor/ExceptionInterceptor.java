/*
 * ExceptionInterceptor.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.interceptor;

import mx.com.vgati.framework.exception.ExceptionLogger;
import mx.com.vgati.framework.exception.ExceptionMessage;
import mx.com.vgati.framework.exception.ExceptionMessageHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

/**
 * 
 * @author Getsemani Correa
 * 
 */
public class ExceptionInterceptor extends ExceptionMappingInterceptor {

	private static final long serialVersionUID = -539777024542822400L;
	private Log log = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor#intercept
	 * (com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		log.debug("intercept.init");
		return super.intercept(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor#
	 * publishException(com.opensymphony.xwork2.ActionInvocation,
	 * com.opensymphony.xwork2.interceptor.ExceptionHolder)
	 */
	@Override
	public void publishException(ActionInvocation invocation,
			ExceptionHolder exceptionHolder) {
		log.debug("publishException.begin");
		Exception exception = exceptionHolder.getException();
		ExceptionMessage exceptionMessage = ExceptionMessageHandler
				.getExceptionMessage(exception);

		ExceptionLogger exceptionLogger = new ExceptionLogger(exceptionMessage,
				exception);
		exceptionLogger.log();
		log.debug("publishException.getStack.push");
		invocation.getStack().push(exceptionMessage);

	}
}
