package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * @param <T>
 * @param <E>
 * 
 */
public abstract class GHATopForm<T extends GHAResultSet<E>, E> extends HLayout
		implements ResizeHandler, ClosableListener, HideableListener {
	protected T resultSet;
	protected GHATab containerTab;
	protected boolean activated = false;

	protected KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getKeyName().equals("Enter")) {
				search();
			}
		}
	};

	public GHATopForm(T resultSet, GHATab containerTab) {
		this.resultSet = resultSet;
		this.containerTab = containerTab;

		GHAUiHelper.addGHAResizeHandler(this);
		setStyleName("sides-padding padding-top");
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
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

	@Override
	public void close() throws UnavailableToCloseException {
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	/**
	 * activate the form for searches
	 */
	public abstract void activate();

	/**
	 * clear the values of this form
	 */
	public abstract void clear();

	/**
	 * blocks the form for editing and funtionality
	 */
	public abstract void deactivate();

	/**
	 * @return wheter this form is activated or not
	 * 
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * 
	 */
	public abstract void search();

	/**
	 * @param entity
	 */
	public abstract void search(E entity);

	/**
	 * borra la entidad seleccionada y permite regresar a la opcion por defecto
	 * al abrir alguna aplicacion, siendo lo mas comun la opcion de buscar
	 */
	protected abstract void delete();

}
