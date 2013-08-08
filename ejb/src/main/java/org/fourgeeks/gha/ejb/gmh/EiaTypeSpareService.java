package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.hibernate.exception.ConstraintViolationException;

@Stateless(name = "gmh.EiaTypeSpareService")
public class EiaTypeSpareService implements EiaTypeSpareServiceRemote {

	@PersistenceContext
	private EntityManager em;
	
	private final static Logger logger = Logger.getLogger(EiaTypeSpareService.class
			.getName());

	@Override
	public EiaTypeSpare save(EiaTypeSpare eiaTypeSpare) throws EJBException {
		try {
			em.persist(eiaTypeSpare);
			em.flush();
			return em.find(EiaTypeSpare.class, eiaTypeSpare.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaTypeSpare", e);
			String message = null;
			if (e.getCause() instanceof ConstraintViolationException) {
				message = "Error: Ya se ha agregado ese Repuesto a este Tipo de Equipo";
			}
			if (message == null)
				message = "Error guardando EiaTypeSpare: "
						+ e.getCause().getMessage();
			throw new EJBException(message);
		}
	}

	@Override
	public List<EiaTypeSpare> find(EiaType eiaType) throws EJBException {
		TypedQuery<EiaTypeSpare> query = em.createNamedQuery(
				"EiaTypeSpare.findByEiaType", EiaTypeSpare.class);
		query.setParameter("eiaType", eiaType);
		return query.getResultList();
	}

	@Override
	public void delete(long id) throws EJBException {
		try {
			EiaTypeSpare entity = em.find(EiaTypeSpare.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypespare",
					e);
			throw new EJBException("Error eliminando EiaTypeSpare por id "
					+ e.getCause().getMessage());
		}
	}
	
}
