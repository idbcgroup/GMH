package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

public abstract class GHASearchForm<T> extends GHASlideInWindow {
	private GHALabel label;
	protected List<T> blackList;

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
		setHeight(GHAUiHelper.getTabHeight()-5);
		setTop(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT);
		label = new GHALabel(title);
		addMember(label);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight()-5);
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

	public void filterBy(List<T> blackList) {
		this.blackList = blackList;
	}
}
