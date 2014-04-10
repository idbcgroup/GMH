package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

/**
 * @author alacret
 * 
 * @param <T>
 */
public abstract class GHASearchForm<T> extends GHASlideInWindow {
	private GHATopTitleLabel titleLabel;
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

	/**
	 * @param title
	 */
	public GHASearchForm(String title) {
		super();
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		titleLabel = new GHATopTitleLabel(title);
		addMember(titleLabel);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	/**
	 * @param blackList
	 */
	public void filterBy(List<T> blackList) {
		this.blackList = blackList;
	}

	/**
	 * 
	 */
	public abstract void search();

}
