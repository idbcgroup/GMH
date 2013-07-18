package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;

/**
 * @author alacret
 * 
 */
@Stateless(name = "gmh.BuildingLocationService")
public class BuildingLocationService implements BuildingLocationServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(BuildingLocationService.class.getName());

	@Override
	public void delete(long Id) throws EJBException {
		try {
			BuildingLocation entity = em.find(BuildingLocation.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete BuildingLocation",
					e);
			throw new EJBException("ERROR: unable to delete BuildingLocation "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<BuildingLocation> find(BuildingLocation entity)
			throws EJBException {
		// TODO : busqueda por cada campo
		List<BuildingLocation> res = null;
		String query = "SELECT e from BuildingLocation e where name like :name ";

		try {
			res = em.createQuery(query, BuildingLocation.class)
					.setParameter("name", entity.getName()).getResultList();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding BuildingLocation by BuildingLocation", e);
			throw new EJBException(
					"Error finding BuildingLocation by BuildingLocation "
							+ e.getCause().getMessage());
		}
	}

	@Override
	public BuildingLocation find(long Id) throws EJBException {
		try {
			return em.find(BuildingLocation.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding BuildingLocation", e);
			throw new EJBException("ERROR: finding BuildingLocation "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<BuildingLocation> getAll() throws EJBException {
		String query = "SELECT e from BuildingLocation e order by name";
		List<BuildingLocation> res = null;
		try {
			res = em.createQuery(query, BuildingLocation.class).getResultList();
		} catch (NoResultException ex) {
			logger.log(Level.INFO, "Get all BuildingLocations, No results", ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all BuildingLocation",
					ex);
			throw new EJBException("Error obteniendo todas las brands"
					+ ex.getCause().getMessage());
		}
		return res;
	}

	@Override
	public BuildingLocation save(BuildingLocation entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(BuildingLocation.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving BuildingLocation " + entity.toString(), e);
			throw new EJBException("ERROR: saving BuildingLocation "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public BuildingLocation update(BuildingLocation entity) throws EJBException {
		try {
			BuildingLocation res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update BuildingLocation "
					+ entity.toString(), e);
			throw new EJBException(
					"ERROR: no se puede eliminar el BuildingLocation "
							+ e.getCause().getMessage());
		}
	}

}
