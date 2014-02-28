package org.fourgeeks.gha.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidad que maneja los parametros de configuracion para la ejecucion de
 * transacciones a traves del PDT
 * 
 * @author naramirez
 */
@Entity
@Table(schema = "conf")
public class TransactionParams extends AbstractCodeEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
	private String jndiProcessorName;

	/**
	 * 
	 */
	public TransactionParams() {
		super();
	}

	/**
	 * @param code
	 * @param jndiName
	 */
	public TransactionParams(String code, String jndiName) {
		this.code = code;
		this.jndiProcessorName = jndiName;
	}

	/**
	 * @return the jndiProcessorName
	 */
	public String getJndiProcessorName() {
		return jndiProcessorName;
	}

	/**
	 * @param jndiName
	 *            the jndiName to set
	 */
	public void setJndiProcessorName(String jndiName) {
		this.jndiProcessorName = jndiName;
	}

}
