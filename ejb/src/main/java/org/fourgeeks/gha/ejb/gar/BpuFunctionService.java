package org.fourgeeks.gha.ejb.gar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 *         BpuFunction service
 */
@Stateless(name = "gar.BpuFunctionService")
public class BpuFunctionService extends GHAEJBExceptionImpl implements
		BpuFunctionServiceRemote {

	private final static Logger logger = Logger
			.getLogger(BpuFunctionService.class.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BpuFunction> getFunctionsByBpu(Bpu bpu) throws GHAEJBException {
		TypedQuery<BpuFunction> query;
		try {
			query = em.createNamedQuery("BpuFunction.findByBpu",
					BpuFunction.class).setParameter("bpu", bpu);
			return query.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "error retriving bpufunctions", e);
			throw super.generateGHAEJBException(
					"bpuFunction-getFunctionsByBpu-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
