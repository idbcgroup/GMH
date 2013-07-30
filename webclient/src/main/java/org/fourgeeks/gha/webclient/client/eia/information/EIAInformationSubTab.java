package org.fourgeeks.gha.webclient.client.eia.information;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHANotification;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author alacret Equipments sub tab
 */
public class EIAInformationSubTab extends Tab implements
		EIATypeSelectionListener, EIASelectionListener, GHAClosable {

	private EIAInformationFormPanel eiaInformationFormPanel = null;
	private EiaType eiaType;

	/**
	 * 
	 */
	public EIAInformationSubTab() {
		setTitle("Equipos");
		setPaneMargin(0);
		eiaInformationFormPanel = new EIAInformationFormPanel(this);
		setPane(eiaInformationFormPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		//loadData();

	}

	/**
	 * @param eiaType
	 */
	private void loadData() {
		if (eiaType == null) {
			GHANotification.alert("No existe un eia Type seleccionado");
			return;
		}
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> eias) {

				ListGridRecord[] array = EIAUtil.toGridRecords(eias).toArray(
						new EIATypeRecord[] {});
				eiaInformationFormPanel.setData(array);

			}
		});
	}

	@Override
	public void select(Eia eia) {
		//loadData();
		//eiaEquiposGridPanel.select(eiaType);
	}

	@Override
	public void close() {
		eiaInformationFormPanel.close();		
	}

}
