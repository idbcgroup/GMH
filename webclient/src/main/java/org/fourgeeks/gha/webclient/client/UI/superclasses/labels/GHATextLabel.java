package org.fourgeeks.gha.webclient.client.UI.superclasses.labels;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

/**
 * @author alacret
 * 
 */
public class GHATextLabel extends GHALabel {
	private String style = "text-label";

	/**
	 * 
	 */
	public GHATextLabel() {
		super();
		setAutoFit(true);
		setStyleName(style);
	}
	/**
	 * @param title
	 */
	public GHATextLabel(String title) {
		this();
		setContents(title);
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabel bold() {
		setStyleName(style+" bold");
		return this;
	}

	/**
	 * add the text style for bold
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabel small() {
		style = "small-text-label";
		setStyleName(style);
		return this;
	}

	/**
	 * add the text style for small titles
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabel smallTitle() {
		style = "small-title-label";
		setStyleName(style);
		return this;
	}

	/**
	 * add the text style for titles
	 * 
	 * @return the label with the style bold added
	 */
	public GHATextLabel title() {
		style = "title-label";
		setStyleName(style);
		return this;
	}
}