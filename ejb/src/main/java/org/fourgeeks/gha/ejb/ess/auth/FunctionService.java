package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.auth.Function;
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
public class FunctionService extends GHAEJBExceptionService implements
		FunctionServiceRemote {

	/**
	 * 
	 */
	private static final String APPVIEW_GET_BY_BPU = "select ui.appview.* from auth.permissionbpu left join ui.viewpermission on permissionbpu.permissionFk = viewpermission.permissionFk right join ui.appview on appview.viewFk = viewpermission.viewFk where permissionbpu.bpuFk =";

	private final static Logger logger = Logger.getLogger(FunctionService.class
			.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(Function function) throws GHAEJBException {
		try {
			final Function localFunction = em.find(Function.class,
					function.getCode());
			em.remove(localFunction);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: delete function", e);
			throw super.generateGHAEJBException("function-delete-fail", em);
		}
	}

	@Override
	public List<AppView> getAppViewsByBpu(Bpu bpu) throws GHAEJBException {
		try {
			@SuppressWarnings("unchecked")
			final List<AppView> resultList = em.createNativeQuery(
					APPVIEW_GET_BY_BPU + bpu.getId(), AppView.class)
					.getResultList();
			return resultList;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: delete function", e);
			throw super.generateGHAEJBException("function-delete-fail", em);
		}
	}

	@Override
	public Function save(Function function) throws GHAEJBException {
		try {
			em.persist(function);
			em.flush();
			return em.find(Function.class, function.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving function ", e);
			throw super.generateGHAEJBException("function-save-fail", em);
		}
	}

}