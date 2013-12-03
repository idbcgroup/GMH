package org.fourgeeks.gha.ejb.log;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;

/**
 * @author alacret
 * 
 */
@Stateless
public class LogonLogService implements LogonLogServiceRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void log(LogonLog log) {
		em.persist(log);
	}

	@Override
	public List<LogonLog> getLogsByBpu(Bpu bpu) {
		List<LogonLog> resultList = em
				.createNamedQuery("LogonLog.getByBpu", LogonLog.class)
				.setParameter("bpu", bpu).getResultList();
		return resultList;
	}
}