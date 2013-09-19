/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.msg.Message;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(name = "LogonLog", schema = "GHALog")
@NamedQueries(value = { @NamedQuery(name = "LogonLog.getByBpu", query = "SELECT e from LogonLog e WHERE e.bpu = :bpu") })
public class LogonLog extends GHALog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Bpu bpu;
	private String ipAdd;

	/**
	 * 
	 */
	public LogonLog() {
	}

	/**
	 * @param bpu
	 * @param message
	 * @param ipAdd
	 * 
	 */
	public LogonLog(Bpu bpu, Message message, String ipAdd) {
		this.bpu = bpu;
		this.message = message;
		this.ipAdd = ipAdd;
	}

	/**
	 * @return the ip address
	 */
	public String getIpAdd() {
		return ipAdd;
	}

}
