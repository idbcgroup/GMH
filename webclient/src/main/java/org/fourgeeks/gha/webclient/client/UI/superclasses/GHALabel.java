package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.Label;

public class GHALabel extends Label {

	public GHALabel(String title) {
		this();
		setContents(title);
		setHeight("25px");
		setWidth100();
		setStyleName("title-label");
	}

	public GHALabel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GHALabel(JavaScriptObject jsObj) {
		super(jsObj);
		// TODO Auto-generated constructor stub
	}

}
