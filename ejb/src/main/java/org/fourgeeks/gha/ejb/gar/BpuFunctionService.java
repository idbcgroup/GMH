package org.fourgeeks.gha.ejb.gar;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret BpuFunction service
 */
@Stateless(name = "gar.BpuFunctionService")
public class BpuFunctionService implements BpuFunctionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<String> getFunctionsAsStringListByBpu(Bpu bpu) {
		TypedQuery<BpuFunction> query = em.createNamedQuery(
				"BpuFunction.findByBpu", BpuFunction.class).setParameter("bpu",
				bpu);
		List<BpuFunction> resultList = query.getResultList();
		List<String> resultAsString = new ArrayList<String>();
		for (BpuFunction bpuFunction : resultList)
			resultAsString.add(bpuFunction.getFunction().getCode());
		return resultAsString;
	}

}
