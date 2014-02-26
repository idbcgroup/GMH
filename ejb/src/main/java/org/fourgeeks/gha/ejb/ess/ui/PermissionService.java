package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.ess.ui.Permission;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 *         permission service
 */
@Stateless
public class PermissionService extends GHAEJBExceptionService implements
		PermissionServiceRemote {

	/**
	 * 
	 */
	private static final String APPVIEW_GET_BY_BPU = "select appview.* from permissionbpu left join viewpermission on permissionbpu.permissionFk = viewpermission.permissionFk right join appview on appview.viewFk = viewpermission.viewFk where permissionbpu.bpuFk =";

	private final static Logger logger = Logger
			.getLogger(PermissionService.class.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(Permission permission) throws GHAEJBException {
		try {
			final Permission localPermission = em.find(Permission.class,
					permission.getCode());
			em.remove(localPermission);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: delete permission", e);
			throw super.generateGHAEJBException("permission-delete-fail", em);
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
			logger.log(Level.INFO, "ERROR: delete permission", e);
			throw super.generateGHAEJBException("permission-delete-fail", em);
		}
	}

	@Override
	public Permission save(Permission permission) throws GHAEJBException {
		try {
			em.persist(permission);
			em.flush();
			return em.find(Permission.class, permission.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving permission ", e);
			throw super.generateGHAEJBException("permission-save-fail", em);
		}
	}

}