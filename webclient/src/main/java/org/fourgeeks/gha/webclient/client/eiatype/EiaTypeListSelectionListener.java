package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * 
 * @author caparicio
 * 
 */
public interface EiaTypeListSelectionListener {

	/**
	 * @param eiaTypes
	 */
	public void select(List<EiaType> eiaTypes);

}