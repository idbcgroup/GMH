package org.fourgeeks.gha.ejb.gom;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.CodeTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.gom.Concept;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot
 * 
 */
@Stateless
public class CCDIService extends GHAEJBExceptionService implements
		CCDIServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(CCDIService.class
			.getName());

	@Override
	public String createCCDIDefinition(String code, String name, int length,
			int levels, String status, Concept concept, String type,
			boolean addVerify, String verificationMethod)
			throws GHAEJBException {
		try {
			CCDIDefinition definition = new CCDIDefinition(code, name, length,
					levels, CCDIStatusEnum.getByString(status), null,
					CodeTypeEnum.getByString(type), addVerify,
					verificationMethod);
			em.persist(definition);
			CCDIDefinition result = em
					.createNamedQuery("CCDIDefinition.findByCode",
							CCDIDefinition.class).setParameter("code", code)
					.getSingleResult();

			return result.getCode();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Definition", e);
			throw super.generateGHAEJBException("ccdi-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public String createCCDILevelDefinition(String definition, int level,
			String name, int length, String valueType, String fixedValue,
			int initialValue, int incValue, String separator,
			String valueAtEndAction) throws GHAEJBException {
		try {
			CCDIDefinition ccdiDefinition = findCCDIDefinitionByCode(definition);
			CCDILevelDefinition levelDefinition = new CCDILevelDefinition(
					ccdiDefinition, level, name, length,
					CCDIValueTypeEnum.getByString(valueType), fixedValue,
					initialValue, incValue, separator,
					CCDIEndValueActionEnum.getByString(valueAtEndAction));

			em.persist(levelDefinition);
			levelDefinition = em
					.createNamedQuery("CCDILevelDefinition.findByLevel",
							CCDILevelDefinition.class)
					.setParameter("level", level)
					.setParameter("definition", ccdiDefinition)
					.getSingleResult();

			return createCCDILevelValue(ccdiDefinition, levelDefinition);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/**
	 * @param ccdiDefinition
	 * @param levelDefinition
	 * @return the code of the level value created for the level definition
	 */
	private String createCCDILevelValue(CCDIDefinition ccdiDefinition,
			CCDILevelDefinition levelDefinition) {
		CCDILevelValue value = new CCDILevelValue();
		if (levelDefinition.getLevel() <= 0) {

		}
		return null;
	}

	@Override
	public void delete(String code) throws GHAEJBException {

		try {
			CCDIDefinition definition = em
					.createNamedQuery("CCDIDefinition.findByCode",
							CCDIDefinition.class).setParameter("code", code)
					.getSingleResult();
			em.remove(definition);

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete CCDIDefinition failed", e);
			throw super.generateGHAEJBException("ccdi-delete-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	@Override
	public CCDIDefinition findCCDIDefinitionByCode(String code)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("CCDIDefinition.findByCode",
							CCDIDefinition.class).setParameter("code", code)
					.getSingleResult();

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete CCDIDefinition failed", e);
			throw super.generateGHAEJBException("ccdi-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public String getNextCCDILevelValue(String code) throws GHAEJBException {
		try {
			// TODO
			return null;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-value-next-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
