package org.fourgeeks.gha.webclient.client.UI.superclasses;

import javax.validation.Validator;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAForm<T> extends GHAVerticalLayout {

	protected boolean hasUnCommittedChanges = false;
	protected ChangedHandler changedHandler = new ChangedHandler() {

		@Override
		public void onChanged(ChangedEvent event) {
			hasUnCommittedChanges = true;
		}
	};
	protected Validator validator = Validation.buildDefaultValidatorFactory()
			.getValidator();

	/**
	 * 
	 */
	public GHAForm() {
		super();
	}

	/**
	 * activate the form
	 */
	public abstract void activate();

	/**
	 * deactivate the form
	 */
	public abstract void deactivate();

	/**
	 * clear the form fields
	 */
	public abstract void clear();

	/**
	 * @return wheter the form has uncommited changes
	 */
	public boolean hasUnCommittedChanges() {
		return hasUnCommittedChanges;
	}

	/**
	 * save the entity
	 */
	public void save() {
		save(null);
	}

	/**
	 * Save the form providing a callback
	 * 
	 * @param callback
	 */
	public abstract void save(final GHAAsyncCallback<T> callback);

	/**
	 * undo the changes to the entity
	 */
	public abstract void undo();

	/**
	 * Update the entity providing a callback
	 * 
	 * @param callback
	 */
	public abstract void update(final GHAAsyncCallback<T> callback);

	/**
	 * Update the entity
	 */
	public void update() {
		update(null);
	}

}
