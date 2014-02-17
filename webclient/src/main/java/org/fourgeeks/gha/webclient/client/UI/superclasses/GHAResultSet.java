package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAResultSet<T> extends GHAFormLayout implements
		ResizeHandler, HideableListener, ClosableListener {
	protected GHALabel searchResultsLabel;

	/**
	 * @param label
	 * 
	 */
	public GHAResultSet(String label) {
		super();
		setStyleName("sides-padding");
		GHAUiHelper.addGHAResizeHandler(this);
		searchResultsLabel = new GHALabel(label);
		addMember(searchResultsLabel);
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

	/**
	 * this method is used to refresh the label with the amount of elements in
	 * the resultset
	 */
	protected void refreshResultsSize(int elements) {
		final String title = GHAStrings.get("search-results")
				+ ": "
				+ elements
				+ " "
				+ (elements == 1 ? GHAStrings.get("result") : GHAStrings
						.get("results"));

		searchResultsLabel.setContents(title);
		searchResultsLabel.redraw();
	}

	protected void showResultsSize(List<?> results, boolean isCleaning) {
		String title = GHAStrings.get("search-results");
		if (!isCleaning) {
			final int res = results == null ? 0 : results.size();
			title = title
					+ ": "
					+ res
					+ " "
					+ (res == 1 ? GHAStrings.get("result") : GHAStrings
							.get("results"));
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