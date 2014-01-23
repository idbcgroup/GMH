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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gom.CCDIServiceRemote#CCDICreateDefinition(java
	 * .lang.String, java.lang.String, int, int, java.lang.String,
	 * org.fourgeeks.gha.domain.gom.Concept, java.lang.String, boolean,
	 * java.lang.String)
	 */
	@Override
	public String CCDICreateDefinition(String code, String name, int length,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gom.CCDIServiceRemote#CCDICreateLevelDefinition
	 * (java.lang.String, int, java.lang.String, int, java.lang.String,
	 * java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public String CCDICreateLevelDefinition(String definition, int level,
			String name, int length, String valueType, String fixedValue,
			int initialValue, int incValue, String separator,
			String valueAtEndAction) throws GHAEJBException {
		try {
			CCDIDefinition ccdiDefinition = em
					.createNamedQuery("CCDIDefinition.findByCode",
							CCDIDefinition.class)
					.setParameter("code", definition).getSingleResult();
			CCDILevelDefinition levelDefinition = new CCDILevelDefinition(
					ccdiDefinition, null, level, name, length,
					CCDIValueTypeEnum.getByString(valueType), fixedValue,
					initialValue, incValue, separator,
					CCDIEndValueActionEnum.getByString(valueAtEndAction));

			if (level <= 0) {
				levelDefinition.setCode("");
			} else {
				CCDILevelDefinition parentLevel = em
						.createNamedQuery("CCDILevelDefinition.findByLevel",
								CCDILevelDefinition.class)
						.setParameter("level", level - 1)
						.setParameter("definition", ccdiDefinition)
						.getSingleResult();
				levelDefinition
						.setCode(CCDIGetNextValue(parentLevel.getCode()));
			}

			em.persist(levelDefinition);
			em.flush();

			levelDefinition = em.find(CCDILevelDefinition.class,
					levelDefinition.getId());
			return levelDefinition.getCode();

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gom.CCDIServiceRemote#CCDICreateLevelValue(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void CCDICreateLevelValue(String levelCode, String name,
			String nextValue, String status) throws GHAEJBException {
		try {
			CCDILevelDefinition levelDefiniton = em
					.createNamedQuery("CCDILevelDefinition.findByCode",
							CCDILevelDefinition.class)
					.setParameter("code", levelCode).getSingleResult();
			CCDILevelValue levelValue = new CCDILevelValue(levelDefiniton,
					name, Integer.toString(levelDefiniton.getInitialValue()),
					CCDIValueStatusEnum.getByString(status));
			em.persist(levelValue);

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-value-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gom.CCDIServiceRemote#CCDIGetNextValue(java.lang
	 * .String)
	 */
	@Override
	public String CCDIGetNextValue(String code) throws GHAEJBException {
		try {
			CCDILevelDefinition levelDefiniton = em
					.createNamedQuery("CCDILevelDefinition.findByCode",
							CCDILevelDefinition.class)
					.setParameter("code", code).getSingleResult();
			CCDILevelValue levelValue = em
					.createNamedQuery("CCDILevelValue.findByLevelDefinition",
							CCDILevelValue.class)
					.setParameter("levelDefinition", levelDefiniton)
					.getSingleResult();

			return "";

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: creating CCDI Level Definition", e);
			throw super.generateGHAEJBException("ccdi-level-value-create-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
