package org.fourgeeks.gha.webclient.client.UI.imageitems;

import com.smartgwt.client.widgets.Img;

/**
 * @author alacret
 * 
 */
public class GHAImg extends Img {

	/**
	 * 
	 */
	public GHAImg() {
		setSize("20px", "20px");
		setShowDown(false);
	}

	/**
	 * @param src
	 */
	public GHAImg(String src) {
		super(src);
		setSize("20px", "20px");
		setShowDown(false);
	}

	/**
	 * @param src
	 * @param width
	 * @param height
	 */
	public GHAImg(String src, int width, int height) {
		super(src, width, height);
		setShowDown(false);
	}

}
