package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeEquipmentSubTab extends Tab implements
		EIATypeSelectionListener,EIASelectionListener, GHAClosable {

	private EIATypeEquipmentGridPanel equiposGridPanel;

	public EIATypeEquipmentSubTab(EIATypeTab tab) {
		equiposGridPanel = new EIATypeEquipmentGridPanel(this);
		tab.addClosableHandler(this);
		setTitle("Equipos");
		setPaneMargin(0);
		setPane(equiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
	}

	@Override
	public void close() {
		equiposGridPanel.close();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		equiposGridPanel.select(eia);
		
	}
}