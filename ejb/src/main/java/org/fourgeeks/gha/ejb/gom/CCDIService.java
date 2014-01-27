package org.fourgeeks.gha.ejb.gom;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
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
		// TODO: TESTING
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
			System.out.println(result.getCode());
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
			CCDIDefinition ccdiDefinition = em
					.createNamedQuery("CCDIDefinition.findByCode",
							CCDIDefinition.class)
					.setParameter("code", definition).getSingleResult();

			CCDILevelDefinition levelDefinition = new CCDILevelDefinition(
					ccdiDefinition, level, name, length,
					CCDIValueTypeEnum.getByString(valueType), fixedValue,
					initialValue, incValue, separator,
					CCDIEndValueActionEnum.getByString(valueAtEndAction));
			em.persist(levelDefinition);
			em.flush();

			levelDefinition = em
					.createNamedQuery("CCDILevelDefinition.findByLevel",
							CCDILevelDefinition.class)
					.setParameter("level", levelDefinition.getLevel())
					.setParameter("definition", ccdiDefinition)
					.getSingleResult();

			CCDILevelValue levelValue = createCCDILevelValue(levelDefinition);

			return levelValue.getCode();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/**
	 * This method creates a new CCDILevelValue with default parameters
	 * 
	 * @param levelDefinition
	 * @return the new level Value
	 */
	private CCDILevelValue createCCDILevelValue(
			CCDILevelDefinition levelDefinition) {
		CCDILevelValue levelValue = new CCDILevelValue();

		if (levelDefinition.getLevel() <= 0) {
			levelValue.setCode("");
		} else {
			CCDILevelDefinition parentDefinition = em
					.createNamedQuery("CCDILevelDefinition.findByLevel",
							CCDILevelDefinition.class)
					.setParameter("level", levelDefinition.getLevel() - 1)
					.setParameter("definition", levelDefinition.getDefinition())
					.getSingleResult();
			CCDILevelValue parentLevelValue = em
					.createNamedQuery("CCDILevelValue.findByLevelDefinition",
							CCDILevelValue.class)
					.setParameter("levelDefinition", parentDefinition)
					.getSingleResult();
			levelValue.setCode(getNextCCDILevelValue(parentDefinition,
					parentLevelValue));
		}

		levelValue.setNextValue(levelDefinition.getInitialValue());
		levelValue.setStatus(CCDIValueStatusEnum.ACTIVE);

		return levelValue;
	}

	/**
	 * @param next
	 * @param levelDefinition
	 * @param levelValue
	 * @return
	 */
	private String formatCodeForLevel(int next,
			CCDILevelDefinition levelDefinition, CCDILevelValue levelValue) {
		String format = "%0" + Integer.toString(levelDefinition.getLength());
		String res = levelValue.getCode() + levelDefinition.getSeparator()
				+ String.format(format, next);
		return res;
	}

	/**
	 * @param levelDefinition
	 * @param levelValue
	 * @return
	 */
	private String getNextCCDILevelValue(CCDILevelDefinition levelDefinition,
			CCDILevelValue levelValue) {
		if (levelDefinition.getValueType() == CCDIValueTypeEnum.FIXED)
			return levelDefinition.getFixedValue();
		else {
			int next = levelValue.getNextValue();

			levelValue.setNextValue(next + levelDefinition.getIncValue());
			em.merge(levelValue);

			return formatCodeForLevel(next, levelDefinition, levelValue);
		}
	}

	@Override
	public String getNextCCDILevelValue(String code) throws GHAEJBException {
		try {
			CCDILevelValue levelValue = em.createNamedQuery(
					"CCDILevelValue.findByCode", CCDILevelValue.class)
					.getSingleResult();
			return getNextCCDILevelValue(levelValue.getLevelDefinition(),
					levelValue);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-value-next-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
