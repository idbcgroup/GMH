package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAResultSet<T> extends GHAVerticalLayout implements
		ResizeHandler, HideableListener, ClosableListener {
	protected GHALabel searchResultsLabel;

	/**
	 * @param label
	 * 
	 */
	public GHAResultSet(String label) {
		super();
		setHeight(GHAUiHelper.getBottomSectionHeight());
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
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		return true;
	}

	protected void showResultsSize(List<?> results, boolean isCleaning) {
		String title = GHAStrings.get("search-results");
		if (!isCleaning) {
			int res = results == null ? 0 : results.size();
			title = title
					+ ": "
					+ res
					+ " "
					+ (res > 1 ? GHAStrings.get("results") : GHAStrings
							.get("result"));
		}

		searchResultsLabel.setContents(title);
		searchResultsLabel.redraw();
	}

	/**
	 * @param records
	 * @param notifyIfOnlyOneResult
	 *            TODO
	 */
	public abstract void setRecords(List<T> records,
			boolean notifyIfOnlyOneResult);

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