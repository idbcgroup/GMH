/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author emiliot
 *
 */
@Stateless(name = "gmh.EiaComponentService")
public class EiaComponentService implements EiaComponentServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(EiaComponentService.class.getName());
	
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try{
			EiaComponent entity = em.find(EiaComponent.class, Id);
			em.remove(entity);
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: unable to delete eiaComponent", e);
			throw new EJBException("Error eliminando eiaComponent "
					+ e.getCause().getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaComponent> find(Eia eia) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia, int, int)
	 */
	@Override
	public List<EiaComponent> find(Eia eia, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(long)
	 */
	@Override
	public EiaComponent find(long Id) throws EJBException {
		try {
			return em.find(EiaComponent.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiaComponent", e);
			throw new EJBException("Error buscando EiaComponent"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#findByEiaId(long)
	 */
	@Override
	public List<EiaComponent> findByEiaId(long Id) throws EJBException {
		List <EiaComponent> res = null;
		String query = "SELECT e from EiaComponent e " +
				"WHERE parentEiaFk = :eiaId";
		try{
			res = em.createQuery(query, EiaComponent.class)
					.setParameter("eiaId", Id)
					.getResultList();
		}catch(NoResultException e){
			logger.log(Level.INFO, "No results", e);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all EiaComponents", ex);
			throw new EJBException("Error obteniendo todos los eiaComponents "
					+ ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaComponent> getAll() throws EJBException {
		String query = "SELECT e from EiaComponent e order by eia";
		List<EiaComponent> res = null;
		try{
			res = em.createQuery(query, EiaComponent.class).getResultList();
		}catch(NoResultException e){
			logger.log(Level.INFO, "Get All: no results", e);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retriving all EiaComponents", ex);
			throw new EJBException("Error obteniendo todos los eiaComponents"
					+ex.getCause().getMessage());
		}
		return res;
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaComponent> getAll(int offset, int size) throws EJBException {
		String query = "SELECT e from EiaComponent e order by eiaparentfk";
		List<EiaComponent> res = null;
		try{
			res = em.createQuery(query, EiaComponent.class)
					.setFirstResult(offset).setMaxResults(size)
					.getResultList();
		}catch(NoResultException e){
			logger.log(Level.INFO, "Get All: no results", e);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retriving all EiaComponents", ex);
			throw new EJBException("Error obteniendo todos los eiaComponents "
					+ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#save(org.fourgeeks.gha.ejb.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent save(EiaComponent eiaComponent) throws EJBException {
		try{
			em.persist(eiaComponent);
			em.flush();
			return em.find(EiaComponent.class, eiaComponent.getId());
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: saving eiaComponent", e);
			throw new EJBException("Error guardando EiaComponent: "
					+ e.getCause().getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#update(org.fourgeeks.gha.ejb.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent update(EiaComponent eiaComponent) throws EJBException {
		try{
			EiaComponent res = em.merge(eiaComponent);
			em.flush();
			return res;
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: unable to update eiacomponent", e);
			throw new EJBException("Error actualizando EiaComponent " +e.getCause().getMessage());
		}
	}

}
