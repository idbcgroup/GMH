/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret
 * 
 */
@Stateless
// @Remote(RoleServiceRemote.class)
// @Local(RoleServiceLocal.class)
public class RoleService extends GHAEJBExceptionImpl implements
		RoleServiceRemote/* , RoleServiceLocal */{
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(RoleService.class
			.getName());

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Role entity = em.find(Role.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Role", e);
			throw super.generateGHAEJBException("role-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Role> find(Role roleBase) throws GHAEJBException {
		try {
			return em.createNamedQuery("Role.findByName", Role.class)
					.setParameter("name", roleBase.getName().toLowerCase())
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Role by Role", e);
			throw super.generateGHAEJBException("role-findByRoleBase-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public Role find(long Id) throws GHAEJBException {
		try {
			return em.find(Role.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Role", e);
			throw super.generateGHAEJBException("role-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Role> getAll() {
		try {
			return em.createNamedQuery("Role.getAll", Role.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Role", ex);
			// throw super.generateGHAEJBException("role-getAll-fail",
			// RuntimeParameters.getLang(), em);
			return null;
		}
	}

	@Override
	public Role save(Role entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Role.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Role " + entity.toString(), e);
			throw super.generateGHAEJBException("role-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public Role update(Role entity) throws GHAEJBException {
		try {
			Role res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update Role " + entity.toString(), e);
			throw super.generateGHAEJBException("role-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}