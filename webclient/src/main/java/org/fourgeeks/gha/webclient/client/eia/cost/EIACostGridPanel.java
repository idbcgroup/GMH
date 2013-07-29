package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIACostGridPanel extends VLayout implements EIATypeSelectionListener, GHAClosable{
	
	public EIACostGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		Label title = new Label("<h3>Costos</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
	
		//TODO: Contenido.
		
	    addMembers(title/*,contenido*/);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
