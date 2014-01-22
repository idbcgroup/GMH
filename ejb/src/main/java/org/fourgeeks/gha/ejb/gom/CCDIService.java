package org.fourgeeks.gha.ejb.gom;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gom.Concept;

/**
 * @author emiliot
 * 
 */
@Stateless
public class CCDIService implements CCDIServiceRemote {
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
			boolean addVerify, String verificationMethod) {
		// TODO Auto-generated method stub
		return null;
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
			String valueAtEndAction) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gom.CCDIServiceRemote#CCDIGetNextValue(java.lang
	 * .String)
	 */
	@Override
	public String CCDIGetNextValue(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
