package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
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
	private EIATypeInternalTabset internatlTabSet;
	private EiaTypeResultSet resultSet;

	/**
	 * @param token
	 */
	public EIATypeTab(String token) {
		super(token);
		header = new GHATabHeader(this, TITLE);
		resultSet = new EiaTypeResultSet();
		resultSet.addEiaTypeSelectionListener(this);
		topForm = new EIATypeTopForm(resultSet);
		topForm.activate();
		addEiaTypeSelectionListener(topForm);
		internatlTabSet = new EIATypeInternalTabset(this);
		addForm = new EIATypeAddForm();
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
		verticalPanel.addMember(internatlTabSet);
		verticalPanel.addMember(resultSet);
		addMember(verticalPanel);
	}

	protected void search() {
		if (topForm.isActivate())
			return;
		if (addForm.isVisible())
			addForm.hide();
		if (resultSet.isVisible())
			resultSet.hide();

		topForm.activate();
	}

	protected void add() {
		if (addForm.isVisible())
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
	public void addGHAClosableHandler(GHAClosable closable) {
		// TODO Auto-generated method stub
		super.addGHAClosableHandler(closable);
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