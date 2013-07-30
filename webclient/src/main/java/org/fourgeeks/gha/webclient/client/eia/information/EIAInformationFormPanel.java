package org.fourgeeks.gha.webclient.client.eia.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIAInformationFormPanel extends VLayout implements EIATypeSelectionListener,GHAClosable{

	/**
	 * @param eiaEquipmentSubTab
	 * 
	 */
	public EIAInformationFormPanel(EIAInformationSubTab eiaEquipmentSubTab) {
		super();
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		Label title = new Label("<h3>Caracteristicas del EIA</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);
		
		HLayout formPanel = new HLayout();
		/*gridPanel.addMembers(formulario, sideButtons);*/

		addMembers(formPanel);

	}

	/**
	 * @param array
	 */
	public void setData(ListGridRecord[] array) {
		//eiaGrid.setData(array);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}