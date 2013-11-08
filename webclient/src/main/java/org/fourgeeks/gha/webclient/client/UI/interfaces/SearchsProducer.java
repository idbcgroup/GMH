package org.fourgeeks.gha.webclient.client.UI.interfaces;

/**
 * @author alacret a component that can do searchs
 */
public interface SearchsProducer {
	/**
	 * @param listener
	 */
	public void addSearchListener(SearchListener listener);

	/**
	 * @param listener
	 */
	public void removeSearchListener(SearchListener listener);

	/**
	 * 
	 */
	public void search();
}
