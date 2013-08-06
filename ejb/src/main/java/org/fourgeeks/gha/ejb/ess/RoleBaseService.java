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

import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author alacret
 * 
 */
@Stateless(name = "ess.RoleBaseService")
public class RoleBaseService implements RoleBaseServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(RoleBaseService.class
			.getName());

	@Override
	public void delete(long Id) throws EJBException {
		try {
			RoleBase entity = em.find(RoleBase.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete RoleBase", e);
			throw new EJBException("ERROR: unable to delete RoleBase "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<RoleBase> find(RoleBase roleBase) throws EJBException {
		try {
			return em.createNamedQuery("RoleBase.findByName", RoleBase.class)
					.setParameter("name", roleBase.getName().toLowerCase()).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding RoleBase by RoleBase", e);
			throw new EJBException("Error buscando RoleBase por RoleBase "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public RoleBase find(long Id) throws EJBException {
		try {
			return em.find(RoleBase.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding RoleBase", e);
			throw new EJBException("ERROR: finding RoleBase "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<RoleBase> getAll() throws EJBException {
		try {
			return em.createNamedQuery("RoleBase.getAll", RoleBase.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all RoleBase", ex);
			throw new EJBException("Error obteniendo todas las RoleBase"
					+ ex.getCause().getMessage());
		}
	}

	@Override
	public RoleBase save(RoleBase entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(RoleBase.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving RoleBase " + entity.toString(), e);
			throw new EJBException("ERROR: saving RoleBase "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public RoleBase update(RoleBase entity) throws EJBException {
		try {
			RoleBase res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update RoleBase " + entity.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el RoleBase "
					+ e.getCause().getMessage());
		}
	}

}