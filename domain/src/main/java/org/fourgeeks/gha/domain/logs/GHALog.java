/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import java.sql.Timestamp;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;

/**
 * @author emiliot
 * 
 */

@MappedSuperclass
public abstract class GHALog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Timestamp timestamp = new Timestamp(
			new java.util.Date().getTime());

	@ManyToOne
	@JoinColumn(nullable = false)
	protected GHAMessage message;

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the message
	 */
	public GHAMessage getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(GHAMessage message) {
		this.message = message;
	}
}
