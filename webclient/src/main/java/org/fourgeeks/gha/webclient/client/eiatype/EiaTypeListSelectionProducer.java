package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * 
 * @author caparicio
 * 
 */
public interface EiaTypeListSelectionProducer {
	/**
	 * 
	 * @param eiaTypeListSelectionListener
	 */
	public void addEiaTypeListSelectionListener(
			EiaTypeListSelectionListener eiaTypeListSelectionListener);

	/**
	 * 
	 * @param eiaTypeListSelectionListener
	 */
	public void removeEiaTypeListSelectionListener(
			EiaTypeListSelectionListener eiaTypeListSelectionListener);

	/**
	 * 
	 * @param eiaTypes
	 */
	public void notifyEiaTypeList(List<EiaType> eiaTypes);
}