package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class EIATypeTab extends GHATab implements EIATypeSelectionListener,
		EiaTypeSelectionProducer {

	/**
	 * The ID of the Tab
	 */
	public static final String ID = "eiatype";
	private static final String TITLE = GHAStrings.get("eiatypes");
	private List<EIATypeSelectionListener> listeners = new ArrayList<EIATypeSelectionListener>();
	private EIATypeTopForm topForm;
	private EIATypeAddForm addForm;
	private EIATypeInternalTabset internalTabSet;
	private EiaTypeResultSet resultSet;

	/**
	 * @param token
	 */
	public EIATypeTab(String token) {
		super(token);
		header = new GHATabHeader(this, TITLE);

		resultSet = new EiaTypeResultSet();
		addGHAHideableHandler(resultSet);
		addGHAClosableHandler(resultSet);
		resultSet.addEiaTypeSelectionListener(this);

		topForm = new EIATypeTopForm(resultSet);
		topForm.activate();
		addGHAHideableHandler(topForm);
		addGHAClosableHandler(topForm);
		addEiaTypeSelectionListener(topForm);

		internalTabSet = new EIATypeInternalTabset(this);
		addGHAHideableHandler(internalTabSet);
		addGHAClosableHandler(internalTabSet);
		addEiaTypeSelectionListener(internalTabSet);

		addForm = new EIATypeAddForm();
		addGHAHideableHandler(addForm);
		addGHAClosableHandler(addForm);
		addForm.addEiaTypeSelectionListener(this);

		header.addSearchOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		header.addAddOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				add();

			}
		});

		verticalPanel.addMember(topForm);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabSet);
		verticalPanel.addMember(resultSet);
		addMember(verticalPanel);
	}

	protected void search() {
		if (topForm.isActivate())
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden())
				internalTabSet.hide();
			else
				return;
		if (addForm.isVisible())
			addForm.hide();
		if (resultSet.isVisible())
			resultSet.hide();
		topForm.activate();
		// GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// informacion para indicar que se ha actividado el modo de busqueda
	}

	protected void add() {
		if (addForm.isVisible())
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden())
				internalTabSet.hide();
			else
				return;
		if (topForm.isActivate())
			topForm.deactivate();
		if (resultSet.isVisible())
			resultSet.hide();
		addForm.open();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void select(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.add(eIATypeSelectionListener);
	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);
	}
}