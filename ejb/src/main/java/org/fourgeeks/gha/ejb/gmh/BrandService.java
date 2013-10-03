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
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 * 
 */
@Stateless(name = "gmh.BrandService")
public class BrandService implements BrandServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(BrandService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Brand entity = em.find(Brand.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete brand", e);
			throw new GHAEJBException("ERROR: unable to delete brand "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#find(org.fourgeeks.gha.domain
	 * .gmh.Brand)
	 */
	@Override
	public List<Brand> find(Brand brand) throws GHAEJBException {
		try {
			return em.createNamedQuery("Brand.findByName", Brand.class)
					.setParameter("name", brand.getName()).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by brand brand", e);
			throw new GHAEJBException("Error buscando brand por brand "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#find(long)
	 */
	@Override
	public Brand find(long Id) throws GHAEJBException {
		try {
			return em.find(Brand.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding brand", e);
			throw new GHAEJBException("ERROR: finding brand "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#getAll()
	 */
	@Override
	public List<Brand> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Brand.getAll", Brand.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all brands", ex);
			throw new GHAEJBException("Error obteniendo todas las brands"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#save(org.fourgeeks.gha.domain
	 * .gmh.Brand)
	 */
	@Override
	public Brand save(Brand brand) throws GHAEJBException {
		try {
			em.persist(brand);
			em.flush();
			return em.find(Brand.class, brand.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving brand ", e);
			throw new GHAEJBException("ERROR: saving brand "
					+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#update(org.fourgeeks.gha
	 * .domain.gmh.Brand)
	 */
	@Override
	public Brand update(Brand brand) throws GHAEJBException {
		try {
			Brand res = em.merge(brand);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update brand ", e);
			throw new GHAEJBException("ERROR: no se puede actualizar el brand "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#findByManufacturer(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public List<Brand> findByManufacturer(Manufacturer manufacturer)
			throws GHAEJBException {
		try {
			return em.createNamedQuery("Brand.findByManufacturer", Brand.class)
					.setParameter("manufacturer", manufacturer).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all brands by manufacturer", ex);
			throw new GHAEJBException("Error obteniendo todas las brands by manufacturer"
					+ ex.getCause().getMessage());
		}
	}

}
