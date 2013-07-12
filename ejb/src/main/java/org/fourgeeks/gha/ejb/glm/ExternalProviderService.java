/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author alacret
 * 
 */
@Stateless(name = "glm.ExternalProviderService")
public class ExternalProviderService implements ExternalProviderServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ExternalProviderService.class.getName());

	@Override
	public void delete(long Id) throws EJBException {
		try {
			ExternalProvider entity = em.find(ExternalProvider.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete ExternalProvider",
					e);
			throw new EJBException("ERROR: unable to delete ExternalProvider "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<ExternalProvider> find(ExternalProvider brand)
			throws EJBException {
		// List<ExternalProvider> res = null;
		// String query =
		// "SELECT e from ExternalProvider e where name like :name ";
		//
		// try {
		// res = em.createQuery(query, Obu.class)
		// .setParameter("name", brand.getName()).getResultList();
		// return res;
		// } catch (Exception e) {
		// logger.log(Level.INFO, "Error: finding Obu by Obu", e);
		// throw new EJBException("Error buscando Obu por Obu "
		// + e.getCause().getMessage());
		// }//TODO
		return getAll();
	}

	@Override
	public ExternalProvider find(long Id) throws EJBException {
		try {
			return em.find(ExternalProvider.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding ExternalProvider", e);
			throw new EJBException("ERROR: finding ExternalProvider "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<ExternalProvider> getAll() throws EJBException {
		String query = "SELECT e from ExternalProvider e"; // TODO: Order
		List<ExternalProvider> res = null;
		try {
			res = em.createQuery(query, ExternalProvider.class).getResultList();
		} catch (NoResultException ex) {
			logger.log(Level.INFO, "Get all ExternalProviders, No results", ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all ExternalProviders",
					ex);
			throw new EJBException(
					"Error obteniendo todas las ExternalProvider"
							+ ex.getCause().getMessage());
		}
		return res;
	}

	@Override
	public ExternalProvider save(ExternalProvider entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(ExternalProvider.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving ExternalProvider " + entity.toString(), e);
			throw new EJBException("ERROR: saving ExternalProvider "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public ExternalProvider update(ExternalProvider entity) throws EJBException {
		try {
			ExternalProvider res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update ExternalProvider "
					+ entity.toString(), e);
			throw new EJBException(
					"ERROR: no se puede eliminar el ExternalProvider "
							+ e.getCause().getMessage());
		}
	}

}