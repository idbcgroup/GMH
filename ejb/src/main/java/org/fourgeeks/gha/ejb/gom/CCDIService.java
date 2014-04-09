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
			CCDIDefinition result = em.find(CCDIDefinition.class,
					definition.getCode());
			return result;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Definition", e);
			throw super.generateGHAEJBException("ccdi-create-fail", em);
		}
	}

	@Override
	public CCDILevelDefinition createLevelDefinition(
			CCDILevelDefinition levelDefinition) throws GHAEJBException {
		try {
			em.persist(levelDefinition);
			return em.find(CCDILevelDefinition.class, levelDefinition.getId());

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-create-fail", em);
		}
	}

	@Override
	public CCDILevelValue createLevelValue(CCDILevelValue levelValue)
			throws GHAEJBException {
		try {
			final CCDILevelDefinition levelDefinition = levelValue
					.getLevelDefinition();
			final CCDILevelValue parentValue = levelValue.getParentValue();

			if (levelDefinition.getLevel() >= levelDefinition.getDefinition()
					.getLevels()) {
				// ABORT CATEGORIES ARE NOT ALLOWED IN THIS LEVEL
				throw new Exception("categories-add-failed-last-level");
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
			levelValue = em.find(CCDILevelValue.class, levelValue.getCode());
			return levelValue;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: create CCDI Level Value failed", e);
			throw super.generateGHAEJBException("ccdi-level-value-create-fail",
					em);
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
			throw super.generateGHAEJBException("ccdi-delete-fail", em);
		}

	}

	@Override
	public CCDIDefinition findCCDIDefinitionByCode(String code)
			throws GHAEJBException {
		try {
			return em.find(CCDIDefinition.class, code);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete CCDIDefinition failed", e);
			throw super.generateGHAEJBException("ccdi-find-fail", em);
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
					"ccdi-level-definition-find-fail", em);
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
		parentValue = em.find(CCDILevelValue.class, parentValue.getCode());

		// TODO SYNCRONIZATION
		int next = parentValue.getNextValue();
		parentValue.setNextValue(next + 1);
		em.merge(parentValue);

		return next;
	}

	@Override
	public String getNextElementCode(String code) throws GHAEJBException {
		try {
			CCDILevelValue levelValue = em.find(CCDILevelValue.class, code);

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

			if (padding > 0) {
				// The category holding the new element is not from the last
				// level, throwing exception
				throw new GHAEJBException();
			}

			StringBuilder builder = new StringBuilder(levelValue.getCode());
			builder.append(formatCode(lastLevelDefinition.getLength(),
					nextElement));
			return builder.toString();

		} catch (GHAEJBException e1) {
			logger.log(Level.INFO,
					"Error: adding element in an invalid category");
			throw super.generateGHAEJBException(
					"ccdi-invalid-category-for-element", em);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: adding element to ccdilevelvalue", e);
			throw super.generateGHAEJBException(
					"ccdi-level-value-next-element-fail", em);
		}
	}
}
