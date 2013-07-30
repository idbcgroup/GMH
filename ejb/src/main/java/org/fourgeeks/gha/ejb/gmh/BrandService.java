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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;

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
	public void delete(long Id) throws EJBException {
		try {
			Brand entity = em.find(Brand.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete brand", e);
			throw new EJBException("ERROR: unable to delete brand "
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
	public List<Brand> find(Brand brand) throws EJBException {
		String query = "SELECT e from Brand e where e.name like :brandName ";
		try {
			return em.createNamedQuery("Brand.findByName", Brand.class)
					.setParameter("name", brand.getName()).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by brand brand", e);
			throw new EJBException("Error buscando brand por brand "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#find(long)
	 */
	@Override
	public Brand find(long Id) throws EJBException {
		try {
			return em.find(Brand.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding brand", e);
			throw new EJBException("ERROR: finding brand "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#getAll()
	 */
	@Override
	public List<Brand> getAll() throws EJBException {
		String query = "SELECT e from Brand e order by e.name";
		try {
			return em.createNamedQuery("Brand.getAll", Brand.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all brands", ex);
			throw new EJBException("Error obteniendo todas las brands"
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
	public Brand save(Brand brand) throws EJBException {
		try {
			em.persist(brand);
			em.flush();
			return em.find(Brand.class, brand.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving brand " + brand.toString(), e);
			throw new EJBException("ERROR: saving brand "
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
	public Brand update(Brand brand) throws EJBException {
		try {
			Brand res = em.merge(brand);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update brand " + brand.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el brand "
					+ e.getCause().getMessage());
		}
	}

}