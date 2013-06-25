package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTab extends GHATab implements EIATypeSelectionListener {

	public static final String ID = "eiatype";
	private static final String TITLE = "Tipos de equipo";
	private GHATabHeader header;
	private EIATypeTopSection topSection;
	private EiaType eiaType;
	private EIATypeInternalTabset bottomTabset;
	private List<GHAClosable> closables;
	{
		closables = new ArrayList<GHAClosable>();
	}

	public EIATypeTab(EiaType eiaType) {
		super();
		this.eiaType = eiaType;
		header = new GHATabHeader(this);
		header.setTitle(TITLE);

		bottomTabset = new EIATypeInternalTabset(this);
		topSection = new EIATypeTopSection(this);

		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(bottomTabset);
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
	public GHATabHeader getHeader() {
		return header;
	}

	public void addClosableHandler(GHAClosable closable) {
		closables.add(closable);
	}

	@Override
	public void close() {
		for (GHAClosable closable : closables)
			closable.close();
		removeFromParent();
	}

	@Override
	public void select(EiaType eiaType) {
		topSection.select(eiaType);
		bottomTabset.select(eiaType);
	}
}