package org.fourgeeks.gha.webclient.client.UI;

/**
 * @author alacret Ths status of the tab
 */
public enum TabStatus {
	/**
	 * the tab is just have been created, this state is the default state, never
	 * got seted
	 */
	INIT,
	/**
	 * the tab is in search mode, but whitout the result set open
	 */
	SEARCH, /**
	 * the tab is in search mode, but whit the result set open
	 */
	SEARCH_RESULTS, /**
	 * the tab is in Add mode
	 */
	ADD, /**
	 * the tab is in search mode, with a entity selected
	 */
	ENTITY_SELECTED;
}
