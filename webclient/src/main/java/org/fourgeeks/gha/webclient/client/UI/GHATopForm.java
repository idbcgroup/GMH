package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.SearchListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.SearchsProducer;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * @param <T>
 * @param <E>
 * 
 */
public abstract class GHATopForm<T extends GHAResultSet<E>, E> extends HLayout
implements ResizeHandler, ClosableListener, HideableListener,
SearchsProducer {
	List<SearchListener> searchListeners = new ArrayList<SearchListener>();
	protected T resultSet;
	protected GHAPanel containerTab;
	protected boolean activated = false;

	protected KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getKeyName().equals("Enter")) {
				search();
			}
		}
	};
	protected final GHAImgButton searchButton, deleteButton, cleanButton;
	protected final VLayout sideButtons = GHAUiHelper.createBar();

	/**
	 * @param resultSet
	 * @param containerTab
	 */
	public GHATopForm(T resultSet, GHAPanel tab) {
		this.resultSet = resultSet;
		this.containerTab = tab;
		GHAUiHelper.addGHAResizeHandler(this);
		setStyleName("sides-padding padding-top");
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		searchButton = new GHASearchButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		cleanButton = new GHACleanButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clear();
			}
		});
		deleteButton = new GHADeleteButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				delete();

			}
		});
	}

	/**
	 * activate the form for searches, this method should set the buttons for
	 * searches, and remove the deletebutton
	 */
	public void activate() {
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
		sideButtons.addMember(cleanButton, 0);
		sideButtons.addMember(searchButton, 0);
	}

	@Override
	public void addSearchListener(SearchListener listener) {
		searchListeners.add(listener);

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
	 * clear the values of this form
	 */
	@Override
	public abstract void clear();;

	@Override
	public void close() throws UnavailableToCloseException {
		GHAUiHelper.removeGHAResizeHandler(this);
		destroy();
	}

	/**
	 * blocks the form for editing and funtionality
	 */
	public abstract void deactivate();

	/**
	 * borra la entidad seleccionada y permite regresar a la opcion por defecto
	 * al abrir alguna aplicacion, siendo lo mas comun la opcion de buscar
	 */
	protected abstract void delete();

	/**
	 * @return wheter this form is activated or not
	 * 
	 */
	public boolean isActivated() {
		return activated;
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
		setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT + "px");
	}

	@Override
	public void removeSearchListener(SearchListener listener) {
		searchListeners.remove(listener);
	}

	/**
	 * 
	 */
	@Override
	public void search() {
		for (SearchListener searchListener : searchListeners)
			searchListener.onSearch();
	}

	/**
	 * @param entity
	 */
	public abstract void search(E entity);

}
