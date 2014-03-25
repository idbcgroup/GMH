/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.msg.GHAMessage;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(name = "UILog", schema = "log")
@NamedQueries(value = { @NamedQuery(name = "UILog.getAll", query = "SELECT e from UILog e order by e.id")

}

)
public class UILog extends GHALog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;

	/**
	 * 
	 */
	public UILog() {
	}

	/**
	 * @param bpu
	 * @param message
	 * 
	 */
	public UILog(final Bpu bpu, final GHAMessage message) {
		this.bpu = bpu;
		this.message = message;
	}

	/**
	 * @return the bpu
	 */
	public Bpu getBpu() {
		return bpu;
	}

	/**
	 * @param bpu
	 */
	public void setBpu(final Bpu bpu) {
		this.bpu = bpu;
	}

}