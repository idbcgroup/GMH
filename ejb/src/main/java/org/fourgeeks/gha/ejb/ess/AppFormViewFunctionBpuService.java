package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 *         BpuFunction service
 */
@Stateless(name = "ess.AppFormViewFunctionBpuService")
public class AppFormViewFunctionBpuService extends GHAEJBExceptionImpl
		implements AppFormViewFunctionBpuServiceRemote {

	private final static Logger logger = Logger
			.getLogger(AppFormViewFunctionBpuService.class.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AppFormViewFunctionBpu> getFunctionsByBpu(Bpu bpu)
			throws GHAEJBException {
		TypedQuery<AppFormViewFunctionBpu> query;
		try {
			query = em.createNamedQuery("AppFormViewFunctionBpu.findByBpu",
					AppFormViewFunctionBpu.class).setParameter("bpu", bpu);
			List<AppFormViewFunctionBpu> resultList = query.getResultList();
			return resultList;
		} catch (Exception e) {
			logger.log(Level.INFO, "error retriving bpufunctions", e);
			throw super.generateGHAEJBException(
					"bpuFunction-getFunctionsByBpu-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public AppFormViewFunctionBpu save(AppFormViewFunctionBpu bpuFunction)
			throws GHAEJBException {
		try {
			em.persist(bpuFunction);
			em.flush();
			return em.find(AppFormViewFunctionBpu.class, bpuFunction.getCode());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving BpuFunction ", e);
			throw super.generateGHAEJBException("bpuFunction-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public void delete(AppFormViewFunctionBpu bpuFunction)
			throws GHAEJBException {
		try {
			Query query = em.createNamedQuery("BpuFunction.delete")
					.setParameter("bpu", bpuFunction.getBpu())
					.setParameter("function", bpuFunction.getFunction());
			query.executeUpdate();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete BpuFunction ", e);
			throw super.generateGHAEJBException("bpuFunction-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
