/**
 * 
 */
package org.fourgeeks.gha.domain.msg;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author jfuentes
 *
 */
@Entity
@Table(schema = "msg")
public class GHAMessageType extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isModal;

	private int time;

	/**
	 * 
	 */
	public GHAMessageType() {
		super();
		this.code = "SAY";
		this.isModal = false;
		this.time = 4000;
	}

	/**
	 * @param type
	 * @param time
	 * @param modal
	 */
	public GHAMessageType(String type, int time, boolean modal) {
		super();
		this.code = type;
		this.isModal = modal;
		this.time = time;
	}

	/**
	 * @return the time of the this time of message
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return true if the message is modal.
	 */
	public boolean isModal() {
		return isModal;
	}

	/**
	 * @return true if the message is timed (closes automatically).
	 */
	public boolean isTimed() {
		return time>0;
	}

	/**
	 * @param isModal
	 */
	public void setModal(boolean isModal) {
		this.isModal = isModal;
	}

	/**
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
}
