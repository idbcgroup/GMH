package org.fourgeeks.gha.ejb.ess.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.ui.ViewPermission;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret
 * 
 */
@Stateless
public class ViewPermissionService extends GHAEJBExceptionService implements
		ViewPermissionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ViewPermissionService.class.getName());

	@Override
	public List<ViewPermission> getAll() throws GHAEJBException {
		try {
			final TypedQuery<ViewPermission> query = em.createNamedQuery(
					"ViewPermission.getAll", ViewPermission.class);
			return query.getResultList();
		} catch (final Exception e) {
			logger.log(Level.SEVERE, "Error obteniendo todos los permissions",
					e);
			throw super.generateGHAEJBException("permission-getall-fail", em);
		}
	}

}
