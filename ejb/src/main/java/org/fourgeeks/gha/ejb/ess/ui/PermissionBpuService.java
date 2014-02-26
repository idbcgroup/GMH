package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.ui.PermissionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 *         BpuFunction service
 */
@Stateless
public class PermissionBpuService extends GHAEJBExceptionService implements
		PermissionBpuServiceRemote {

	private final static Logger logger = Logger
			.getLogger(PermissionBpuService.class.getName());

	@PersistenceContext
	private EntityManager em;

	@Override
	public void delete(PermissionBpu permissionBpu) throws GHAEJBException {
		try {
			em.remove(em.find(PermissionBpu.class, permissionBpu.getCode()));
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: delete BpuFunction ", e);
			throw super
					.generateGHAEJBException("bpupermission-delete-fail", em);
		}
	}

	@Override
	public List<PermissionBpu> getPermissionByBpu(Bpu bpu)
			throws GHAEJBException {
		TypedQuery<PermissionBpu> query;
		try {
			query = em.createNamedQuery("PermissionBpu.findByBpu",
					PermissionBpu.class).setParameter("bpu", bpu);
			final List<PermissionBpu> resultList = query.getResultList();
			return resultList;
		} catch (final Exception e) {
			logger.log(Level.INFO, "error retriving bpupermissions", e);
			throw super.generateGHAEJBException(
					"bpupermission-getpermissionsbybpu-fail", em);
		}
	}

	@Override
	public PermissionBpu save(PermissionBpu permissionBpu)
			throws GHAEJBException {
		try {
			em.persist(permissionBpu);
			em.flush();
			return em.find(PermissionBpu.class, permissionBpu.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving BpuFunction ", e);
			throw super.generateGHAEJBException("bpupermission-save-fail", em);
		}
	}
}
