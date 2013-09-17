package org.fourgeeks.gha.ejb.gar;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret BpuFunction service
 */
@Stateless(name = "gar.BpuFunctionService")
public class BpuFunctionService implements BpuFunctionServiceRemote {

	private final static Logger logger = Logger
			.getLogger(BpuFunctionService.class.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<String> getFunctionsAsStringListByBpu(Bpu bpu)
			throws EJBException {

		TypedQuery<BpuFunction> query;
		try {
			query = em.createNamedQuery("BpuFunction.findByBpu",
					BpuFunction.class).setParameter("bpu", bpu);
			List<BpuFunction> resultList = query.getResultList();
			List<String> resultAsString = new ArrayList<String>();
			for (BpuFunction bpuFunction : resultList)
				resultAsString.add(bpuFunction.getFunction().getCode());
			return resultAsString;
		} catch (Exception e) {
			logger.log(Level.INFO, "error retriving bpufunctions", e);
		}
		return null;
	}
}
