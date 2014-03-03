package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 *         permission service
 */
@Stateless
public class AppViewService extends GHAEJBExceptionService implements
		AppViewServiceRemote {

	/**
	 * 
	 */
	private static final String APPVIEW_GET_BY_BPU = "select av.* from auth.functionbpu as fb left join ui.viewfunction as vp on fb.functionFk = vp.functionFk right join ui.appview as av on av.viewFk = vp.viewFk where fb.bpuFk =";
	private final static Logger logger = Logger.getLogger(AppViewService.class
			.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AppView> getAppViewsByBpu(Bpu bpu) throws GHAEJBException {
		try {
			// TODO : unit test
			@SuppressWarnings("unchecked")
			final List<AppView> resultList = em.createNativeQuery(
					APPVIEW_GET_BY_BPU + bpu.getId(), AppView.class)
					.getResultList();
			return resultList;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: getting appviews by bpu function", e);
			throw super.generateGHAEJBException("getappviewbybpu-fail", em);
		}
	}

}