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

import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author alacret
 * 
 */
@Stateless(name = "ess.BaseRoleService")
public class BaseRoleService implements BaseRoleServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(BaseRoleService.class
			.getName());

	@Override
	public void delete(long Id) throws EJBException {
		try {
			BaseRole entity = em.find(BaseRole.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete BaseRole", e);
			throw new EJBException("ERROR: unable to delete BaseRole "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<BaseRole> find(BaseRole baseRole) throws EJBException {
		try {
			return em.createNamedQuery("BaseRole.findByName", BaseRole.class)
					.setParameter("name", baseRole.getName().toLowerCase()).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding BaseRole by BaseRole", e);
			throw new EJBException("Error buscando BaseRole por BaseRole "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public BaseRole find(long Id) throws EJBException {
		try {
			return em.find(BaseRole.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding BaseRole", e);
			throw new EJBException("ERROR: finding BaseRole "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<BaseRole> getAll() throws EJBException {
		try {
			return em.createNamedQuery("BaseRole.getAll", BaseRole.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all BaseRole", ex);
			throw new EJBException("Error obteniendo todas las BaseRoles"
					+ ex.getCause().getMessage());
		}
	}

	@Override
	public BaseRole save(BaseRole entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(BaseRole.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving BaseRole " + entity.toString(), e);
			throw new EJBException("ERROR: saving BaseRole "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public BaseRole update(BaseRole entity) throws EJBException {
		try {
			BaseRole res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update BaseRole " + entity.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el BaseRole "
					+ e.getCause().getMessage());
		}
	}

}