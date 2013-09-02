package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.Img;

public class GHAImg extends Img {

	public GHAImg() {
		setBorder("1px solid black");
	}

	public GHAImg(JavaScriptObject jsObj) {
		super(jsObj);
		setBorder("1px solid black");
	}

	public GHAImg(String src) {
		super(src);
		setBorder("1px solid black");
	}

	public GHAImg(String src, int width, int height) {
		super(src, width, height);
		setBorder("1px solid black");
	}

}
