/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.EiaTypeService")
@Remote(EiaTypeService.class)
public class EiaTypeServiceRemote implements EiaTypeService{
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(EiaTypeServiceRemote.class.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#saveEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void save(EiaType eiaType) {		
		try{
			em.persist(eiaType);
		}catch(Exception e){
			logger.info("ERROR: saving object " + eiaType.toString());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getEiaType(long)
	 */
	@Override
	public EiaType find(long Id) {
		return em.find(EiaType.class, Id);
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public EiaType find(EiaType eiaType) {
		//TODO: TEST
		return null;//em.find(EiaType.class, eiaType);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#deleteEiaType(long)
	 */
	@Override
	public void delete(long Id) {
		try{
			EiaType entity = em.find(EiaType.class, Id);
			em.remove(entity);
			
			logger.info("Deleted: "+entity.toString());
		}catch(Exception e){
			e.printStackTrace();
			logger.info("ERROR: unable to delete object with id="+Long.toString(Id));
		}
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#updateEiaType(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void update(EiaType eiaType) {
		try{
			em.merge(eiaType);
		}catch(Exception e){
			logger.info("ERROR: unable to update object "+eiaType.toString());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAllEiaType()
	 */
	@Override
	public List<EiaType> getAll() {
		String query = "SELECT e from EiaType e order by id";
		List <EiaType> res = null;
		try {
			res = em.createQuery(query, EiaType.class).getResultList();
		} catch (NoResultException e) {
			System.out.println("No results");
			res = null;
		} catch (Exception ex){
			ex.printStackTrace();
			res = null;
		} finally {
			return res;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAll(long, long)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) {
		List <EiaType> res = null;
		String query = "SELECT e from EiaType e order by id";
		try{
			res = em.createQuery(query, EiaType.class)
					.setFirstResult(offset)
					.setMaxResults(size).getResultList();
		}catch (NoResultException e){
			logger.info("Get All: no results");
			res = null;
		}catch (Exception ex){
			ex.printStackTrace();
			res = null;
		}finally {
			return res;
		}
	}
	
}
