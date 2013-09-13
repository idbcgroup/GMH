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
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author alacret
 * 
 */
@Stateless(name = "ess.RoleService")
public class RoleService implements RoleServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(RoleService.class
			.getName());

	@Override
	public void delete(long Id) throws EJBException {
		try {
			Role entity = em.find(Role.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Role", e);
			throw new EJBException("ERROR: unable to delete Role "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<Role> find(Role roleBase) throws EJBException {
		try {
			return em.createNamedQuery("Role.findByName", Role.class)
					.setParameter("name", roleBase.getName().toLowerCase()).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Role by Role", e);
			throw new EJBException("Error buscando Role por Role "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public Role find(long Id) throws EJBException {
		try {
			return em.find(Role.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Role", e);
			throw new EJBException("ERROR: finding Role "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<Role> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Role.getAll", Role.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Role", ex);
			throw new EJBException("Error obteniendo todas las Role"
					+ ex.getCause().getMessage());
		}
	}

	@Override
	public Role save(Role entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Role.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving Role " + entity.toString(), e);
			throw new EJBException("ERROR: saving Role "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public Role update(Role entity) throws EJBException {
		try {
			Role res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update Role " + entity.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el Role "
					+ e.getCause().getMessage());
		}
	}

}