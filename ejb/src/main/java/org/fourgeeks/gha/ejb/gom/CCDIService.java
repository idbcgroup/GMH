package org.fourgeeks.gha.ejb.gom;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot
 * 
 */
@Stateless
public class CCDIService extends GHAEJBExceptionService implements
		CCDIServiceRemote, CCDIServiceLocal {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(CCDIService.class
			.getName());

	@Override
	public CCDIDefinition createCCDIDefinition(CCDIDefinition definition)
			throws GHAEJBException {
		try {
			em.persist(definition);
			CCDIDefinition result = em
					.createNamedQuery("CCDIDefinition.findByCode",
							CCDIDefinition.class)
					.setParameter("code", definition.getCode())
					.getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Definition", e);
			throw super.generateGHAEJBException("ccdi-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public CCDILevelDefinition createCCDILevelDefinition(
			CCDIDefinition definition, CCDILevelDefinition levelDefinition)
			throws GHAEJBException {
		try {
			levelDefinition.setDefinition(definition);

			em.persist(levelDefinition);
			return em.find(CCDILevelDefinition.class, levelDefinition.getId());

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public CCDILevelValue createCCDILevelValue(
			CCDILevelDefinition levelDefinition, CCDILevelValue parentValue,
			CCDILevelValue levelValue) throws GHAEJBException {
		try {
			if (levelDefinition.getLevel() >= levelDefinition.getDefinition()
					.getLevels()) {
				// ABORT CATEGORIES ARE NOT ALLOWED IN THIS LEVEL
				throw new Exception(
						"Categories not allowed in this level of CCDI");
			}

			String valueCode = "";
			if (levelDefinition.getLevel() > 0 && parentValue != null) {
				valueCode += parentValue.getCode();
			}

			valueCode += levelDefinition.getSeparator()
					+ (levelDefinition.getValueType() == CCDIValueTypeEnum.FIXED ? levelValue
							.getFixedValue() : formatCode(
							levelDefinition.getLength(),
							getNextCode(parentValue)));

			levelValue.setCode(valueCode);

			levelValue.setNextValue(levelDefinition.getInitialValue());
			levelValue.setLevelDefinition(levelDefinition);
			levelValue.setParentValue(parentValue);
			em.persist(levelValue);
			em.flush();
			levelValue = em
					.createNamedQuery("CCDILevelValue.findByCode",
							CCDILevelValue.class)
					.setParameter("code", levelValue.getCode())
					.getSingleResult();

			return levelValue;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete CCDIDefinition failed", e);
			throw super.generateGHAEJBException("ccdi-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public void deleteByCode(String code) throws GHAEJBException {

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
	public CCDILevelDefinition findCCDILevelDefinitionByLevel(
			CCDIDefinition definition, int level) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("CCDILevelDefinition.findByLevel",
							CCDILevelDefinition.class)
					.setParameter("level", level)
					.setParameter("definition", definition).getSingleResult();

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete CCDIDefinition failed", e);
			throw super.generateGHAEJBException(
					"ccdi-level-definition-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/**
	 * @param length
	 * @param nextCode
	 * @return the nextCode with formatted with length spaces, filling with
	 *         leading zeroes
	 */
	private String formatCode(int length, int nextCode) {
		String format = "%0" + Integer.toString(length) + "d";
		return String.format(format, nextCode);
	}

	/**
	 * @param parentValue
	 * @return the next int available for a given ccdilevelvalue, this should be
	 *         formated properly by the calling method
	 */
	private int getNextCode(CCDILevelValue parentValue) {
		// TODO HANDLE FIXED
		parentValue = em
				.createNamedQuery("CCDILevelValue.findByCode",
						CCDILevelValue.class)
				.setParameter("code", parentValue.getCode()).getSingleResult();

		// TODO SYNCRONIZATION
		int next = parentValue.getNextValue();
		parentValue.setNextValue(next + 1);
		em.merge(parentValue);

		return next;
	}

	@Override
	public String getNextElementCode(String code) throws GHAEJBException {
		try {
			CCDILevelValue levelValue = em
					.createNamedQuery("CCDILevelValue.findByCode",
							CCDILevelValue.class).setParameter("code", code)
					.getSingleResult();

			// TODO SYNCRONIZATION
			int nextElement = levelValue.getNextElement();
			levelValue.setNextElement(nextElement + 1);
			em.merge(levelValue);

			CCDIDefinition definition = levelValue.getLevelDefinition()
					.getDefinition();

			CCDILevelDefinition lastLevelDefinition = em
					.createNamedQuery("CCDILevelDefinition.findByLevel",
							CCDILevelDefinition.class)
					.setParameter("level", definition.getLevels() - 1)
					.setParameter("definition", definition).getSingleResult();

			int padding = definition.getLength()
					- levelValue.getCode().length()
					- lastLevelDefinition.getLength();

			StringBuilder builder = new StringBuilder(levelValue.getCode());
			for (int i = 0; i < padding; ++i)
				builder.append('X');

			builder.append(formatCode(lastLevelDefinition.getLength(),
					nextElement));
			return builder.toString();

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: adding element to ccdilevelvalue", e);
			throw super.generateGHAEJBException(
					"ccdi-level-value-next-element-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
