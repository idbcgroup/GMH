package org.fourgeeks.gha.webclient.client.UI.superclasses;

import javax.validation.Validator;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.validation.client.impl.Validation;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * @param <T>
 * 
 */
public abstract class GHAForm<T> extends GHAFormLayout implements
		ResizeHandler {

	protected T originalEntity = null;
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
		GHAUiHelper.addGHAResizeHandler(this);
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
	public void clear() {
		originalEntity = null;
	}

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
	public void undo() {
		if (originalEntity == null)
			clear();
		else
			this.set(originalEntity);
		hasUnCommittedChanges = false;
	}

	/**
	 * Update the entity providing a callback
	 * 
	 * @param callback
	 */
	public abstract void update(final GHAAsyncCallback<T> callback);

	/**
	 * @param entity
	 */
	public abstract void set(T entity);

	/**
	 * Update the entity
	 */
	public void update() {
		update(null);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void destroy() {
		GHAUiHelper.removeGHAResizeHandler(this);
		super.destroy();
	}

}
