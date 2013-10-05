/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;


/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaTypeComponentService")
public class EiaTypeComponentService implements EiaTypeComponentServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeComponentService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaTypeComponent entity = em.find(EiaTypeComponent.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypecomponent",
					e);
			throw new GHAEJBException("Error eliminando EiaTypeComponent por id "
					+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(long)
	 */
	@Override
	public EiaTypeComponent find(long Id) throws GHAEJBException {
		try {
			return em.find(EiaTypeComponent.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiatypeComponent por Id ", e);
			throw new GHAEJBException("Error buscando Eiatypecomponent por Id "
					+ e.getCause().getMessage());
		}
	}



	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#findByParentEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeComponent> findByParentEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeComponent.findByParentEiaType",
							EiaTypeComponent.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeComponents",
					ex);
			throw new GHAEJBException(
					"Error obteniendo todos los eiaTypeComponents "
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeComponent> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaTypeComponent.getAll",
					EiaTypeComponent.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EiaTypeComponents",
					ex);
			throw new GHAEJBException(
					"Error obteniendo todos los EiaTypeComponents"
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<EiaTypeComponent> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaTypeComponent.getAll",
					EiaTypeComponent.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EiaTypeComponents",
					ex);
			throw new GHAEJBException(
					"Error obteniendo todos los eiaTypeComponents "
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#save(org.fourgeeks
	 * .gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeComponent);
			em.flush();
			return em.find(EiaTypeComponent.class, eiaTypeComponent.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaTypeComponent", e);
			String message = e.getMessage();//null;
//			if (e.getCause() instanceof ConstraintViolationException) {
//				message = "Error: Ya se ha agregado ese Componente a este Tipo de Equipo";
//			}
//			if (message == null)
//				message = "Error guardando EiaTypeComponent: "
//						+ e.getCause().getMessage();
			throw new GHAEJBException(message);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#update(org.fourgeeks
	 * .gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException {
		try {
			EiaTypeComponent res = em.merge(eiaTypeComponent);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiatypecomponent",
					e);
			throw new GHAEJBException("Error actualizando EiaTypeComponent "
					+ e.getCause().getMessage());
		}

	}

}
