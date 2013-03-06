package mx.com.vgati.framework.exception;

import java.io.Serializable;
import java.util.Map;

import mx.com.vgati.framework.dto.AbstractBaseDTO;
import mx.com.vgati.framework.uuid.UUIDProvider;

/**
 * Clase que representa un mensaje 'leíble' al usuario.
 * 
 * @version 0.1
 * @author Getsemani Correa
 * 
 */
@SuppressWarnings("serial")
public class ExceptionMessage implements Serializable {
	private String uuid;
	private String cause;
	private Map<String, Object> parameter;
	private String solution;

	public ExceptionMessage(String cause) {
		setCause(cause);
		setUuid(UUIDProvider.getInstance().getUUID());
	}

	public ExceptionMessage(String cause, Map<String, Object> params) {
		this(cause);
		setParameter(params);
	}

	public ExceptionMessage(String cause, AbstractBaseDTO baseDto) {
		this(cause);
		// Conversion de DTO a parametros
	}

	public ExceptionMessage(String cause, String solution) {
		this(cause);
		setSolution(solution);

	}

	public ExceptionMessage(String cause, Map<String, Object> params,
			String solution) {
		this(cause, params);
		setSolution(solution);
	}

	public ExceptionMessage(String cause, AbstractBaseDTO baseDto,
			String solution) {
		this(cause, baseDto);
		setSolution(solution);
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	private void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	private void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @return the parameter
	 */
	public Map<String, Object> getParameter() {
		return parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	private void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution
	 *            the solution to set
	 */
	private void setSolution(String solution) {
		this.solution = solution;
	}

}
