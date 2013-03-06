package mx.com.vgati.ccmx.vinculacion.common.exception.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import mx.com.vgati.framework.action.AbstractBaseAction;

/**
 * 
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class ExceptionAction extends AbstractBaseAction {

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Action(value = "/httpErrors/error", results = { @Result(name = "success", location = "errorCodes", type = "tiles") })
	public String procesaError() {
		log.debug("-- procesaError --");
		log.debug("code=" + code);

		return SUCCESS;
	}

}
