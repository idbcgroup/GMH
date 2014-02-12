/**
 * 
 */
package org.fourgeeks.gha.domain.msg;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author jfuentes
 *
 */
@Entity
@Table(schema = "msg")
public class GHAMessageType extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String typeName;

	private boolean isModal;

	private boolean isTimed;

	/**
	 * 
	 */
	public GHAMessageType() {
		super();
	}

	/**
	 * @param type
	 * @param timed
	 * @param modal
	 */
	public GHAMessageType(String type, boolean timed, boolean modal) {
		super();
		this.typeName = type;
		this.isModal = modal;
		this.isTimed = timed;
	}

	/**
	 * @return the name of the message type
	 */
	public String getTypeName() {
		return typeName;
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
		return isTimed;
	}

	/**
	 * @param isModal
	 */
	public void setModal(boolean isModal) {
		this.isModal = isModal;
	}

	/**
	 * @param isTimed
	 */
	public void setTimed(boolean isTimed) {
		this.isTimed = isTimed;
	}

	/**
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
