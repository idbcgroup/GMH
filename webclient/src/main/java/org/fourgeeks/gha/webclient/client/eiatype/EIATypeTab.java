package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;

import com.smartgwt.client.widgets.layout.VLayout;

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
	// private static final String TITLE = "Tipos de equipo";
	private List<EIATypeSelectionListener> listeners = new ArrayList<EIATypeSelectionListener>();
	private EIATypeTopSection topSection;
	private EiaType eiaType;
	private EIATypeInternalTabset internatlTabSet;

	/**
	 * @param eiaType
	 */
	public EIATypeTab(EiaType eiaType) {
		super();
		this.eiaType = eiaType;
		// getHeader().setTitle(TITLE);

		topSection = new EIATypeTopSection(this);
		internatlTabSet = new EIATypeInternalTabset(this);

		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");
		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internatlTabSet);
		addMember(verticalPanel);
	}

	@Override
	protected void onDraw() {
		if (eiaType == null)
			topSection.search();
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