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
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.hibernate.exception.ConstraintViolationException;

@Stateless(name = "gmh.EiaTypeMaterialService")
public class EiaTypeMaterialService implements EiaTypeMaterialServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaTypeMaterialService.class
			.getName());
	
	@Override
	public List<EiaTypeMaterial> find(EiaType eiaType) throws EJBException {
		TypedQuery<EiaTypeMaterial> query = em.createNamedQuery(
				"EiaTypeMaterial.findByEiaType", EiaTypeMaterial.class);
		query.setParameter("eiaType", eiaType);
		return query.getResultList();
	}

	@Override
	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial)
			throws EJBException {
		try {
			em.persist(eiaTypeMaterial);
			em.flush();
			return em.find(EiaTypeMaterial.class, eiaTypeMaterial.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaTypeMaterial", e);
			String message = null;
			if (e.getCause() instanceof ConstraintViolationException) {
				message = "Error: Ya se ha agregado ese Material a este Tipo de Equipo";
			}
			if (message == null)
				message = "Error guardando EiaTypeMaterial: "
						+ e.getCause().getMessage();
			throw new EJBException(message);
		}
	}

	@Override
	public void delete(long id) throws EJBException {
		try {
			EiaTypeMaterial entity = em.find(EiaTypeMaterial.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypematerial",
					e);
			throw new EJBException("Error eliminando EiaTypeMaterial por id "
					+ e.getCause().getMessage());
		}
	}

}
