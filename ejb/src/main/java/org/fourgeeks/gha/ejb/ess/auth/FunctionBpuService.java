package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 *         BpuFunction service
 */
@Stateless
public class FunctionBpuService extends GHAEJBExceptionService implements
		FunctionBpuServiceRemote {

	private final static Logger logger = Logger
			.getLogger(FunctionBpuService.class.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(final FunctionBpu functionBpu) throws GHAEJBException {
		try {
			em.remove(em.find(FunctionBpu.class, functionBpu.getCode()));
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: delete BpuFunction ", e);
			throw super.generateGHAEJBException("bpufunction-delete-fail", em);
		}
	}

	@Override
	public List<FunctionBpu> getFunctionByBpu(final Bpu bpu)
			throws GHAEJBException {
		TypedQuery<FunctionBpu> query;
		try {
			query = em.createNamedQuery("FunctionBpu.findByBpu",
					FunctionBpu.class).setParameter("bpu", bpu);
			final List<FunctionBpu> resultList = query.getResultList();
			// final List<Function> returnList = new ArrayList<Function>();
			// for (FunctionBpu functionBpu : resultList)
			// returnList.add(functionBpu.getFunction());
			return resultList;
		} catch (final Exception e) {
			logger.log(Level.INFO, "error retriving bpufunction", e);
			throw super.generateGHAEJBException(
					"bpufunction-getfunctionbybpu-fail", em);
		}
	}

	@Override
	public FunctionBpu save(final FunctionBpu functionBpu)
			throws GHAEJBException {
		try {
			em.persist(functionBpu);
			em.flush();
			return em.find(FunctionBpu.class, functionBpu.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving BpuFunction ", e);
			throw super.generateGHAEJBException("bpufunction-save-fail", em);
		}
	}
}
