package org.fourgeeks.gha.webclient.client.eia.reports;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIAReportsFormPanel extends VLayout implements GHAClosable, GHAHideable,
		EIASelectionListener {

	public EIAReportsFormPanel() {
		super();
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
