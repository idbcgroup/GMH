package org.fourgeeks.gha.ejb.log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.logs.LogonLog;

/**
 * @author alacret
 * 
 */
@Stateless(name = "log.LogService")
public class LogService implements LogServiceRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void log(LogonLog log) {
		em.persist(log);
	}
}