package org.fourgeeks.gha.webclient.client.UI.icons;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.Img;


public class GHAImg extends Img {

	/**
	 * 
	 */
	public GHAImg() {
		setSize("20px", "20px");
		setShowDown(false);
	}

	public GHAImg(JavaScriptObject jsObj) {
		super(jsObj);
		setSize("20px", "20px");
		setShowDown(false);
	}

	public GHAImg(String src) {
		super(src);
		setSize("20px", "20px");
		setShowDown(false);
	}

	public GHAImg(String src, int width, int height) {
		super(src, width, height);
		setShowDown(false);
	}

}
