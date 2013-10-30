package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

public abstract class GHASearchForm extends GHASlideInWindow {
	private GHALabel label;
	protected KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getKeyName().equals("Enter")) {
				search();
			}
		}
	};

	protected ClickHandler searchClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			search();
		}
	};

	public GHASearchForm(String title) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getTabHeight());
		setTop(GHAUiHelper.getTopSpace());
		label = new GHALabel(title);
		addMember(label);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	public abstract void search();
}
