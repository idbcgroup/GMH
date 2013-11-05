package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAResultSet<T> extends VLayout implements ResizeHandler,
		GHAHideable, GHAClosable {
	protected GHALabel searchResultsLabel;

	/**
	 * @param label
	 * 
	 */
	public GHAResultSet(String label) {
		super();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setStyleName("sides-padding padding-top");
		GHAUiHelper.addGHAResizeHandler(this);
		searchResultsLabel = new GHALabel(label);
		addMember(searchResultsLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.logical.shared.ResizeHandler#onResize(com.google
	 * .gwt.event.logical.shared.ResizeEvent)
	 */
	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	/**
	 * 
	 */
	public abstract void clean();

	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	/**
	 * @param records
	 */
	public abstract void setRecords(List<T> records);

	@Override
	public void close() throws UnavailableToCloseException {
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	@Override
	public void hide() {
		animateHide(AnimationEffect.FADE);
	}
}