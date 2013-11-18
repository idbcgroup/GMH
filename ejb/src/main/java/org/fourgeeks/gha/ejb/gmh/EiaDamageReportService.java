package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class EiaDamageReportService
 */
@Stateless(name = "gmh.EiaDamageReportService")
public class EiaDamageReportService extends GHAEJBExceptionImpl implements
		EiaDamageReportServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaDamageReportService.class.getName());

	@Override
	public boolean delete(long Id) throws GHAEJBException {
		try {
			EiaDamageReport entity = em.find(EiaDamageReport.class, Id);
			em.remove(entity);
			return true;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete eiaDamageReport = "
							+ EiaDamageReport.class.getName() + " with id="
							+ Long.toString(Id), e);
			throw super.generateGHAEJBException("eia-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<EiaDamageReport> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaDamageReport.findByEiaType",
							EiaDamageReport.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding eiaDamageReport by eiatype",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<EiaDamageReport> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaDamageReport.getAll",
					EiaDamageReport.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiaDamageReports",
					ex);
			throw super.generateGHAEJBException("eia-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public EiaDamageReport save(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {
		try {
			em.persist(eiaDamageReport);
			em.flush();
			return em.find(EiaDamageReport.class, eiaDamageReport.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaDamageReport ", e);
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public EiaDamageReport update(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {
		try {
			EiaDamageReport res = em.merge(eiaDamageReport);
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiaDamageReport ",
					e);
			throw super.generateGHAEJBException("eia-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
