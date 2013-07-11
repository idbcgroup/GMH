/**
 * 
 */
package org.fourgeeks.gha.gar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Obu;

/**
 * @author alacret
 * 
 */
@Stateless(name = "mix.ObuService")
public class ObuService implements ObuServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(ObuService.class
			.getName());

	@Override
	public void delete(long Id) throws EJBException {
		try {
			Obu entity = em.find(Obu.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Obu", e);
			throw new EJBException("ERROR: unable to delete Obu "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<Obu> find(Obu brand) throws EJBException {
		List<Obu> res = null;
		String query = "SELECT e from Obu e where name like :name ";

		try {
			res = em.createQuery(query, Obu.class)
					.setParameter("name", brand.getName()).getResultList();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Obu by Obu", e);
			throw new EJBException("Error buscando Obu por Obu "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public Obu find(long Id) throws EJBException {
		try {
			return em.find(Obu.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Obu", e);
			throw new EJBException("ERROR: finding Obu "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<Obu> getAll() throws EJBException {
		String query = "SELECT e from Obu e order by name";
		List<Obu> res = null;
		try {
			res = em.createQuery(query, Obu.class).getResultList();
		} catch (NoResultException ex) {
			logger.log(Level.INFO, "Get all Obus, No results", ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Obu", ex);
			throw new EJBException("Error obteniendo todas las Obus"
					+ ex.getCause().getMessage());
		}
		return res;
	}

	@Override
	public Obu save(Obu entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Obu.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Obu " + entity.toString(), e);
			throw new EJBException("ERROR: saving Obu "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public Obu update(Obu entity) throws EJBException {
		try {
			Obu res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update Obu " + entity.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el Obu "
					+ e.getCause().getMessage());
		}
	}

}