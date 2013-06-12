package org.fourgeeks.gha.webclient.client.eia.caracteristicas;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tab.Tab;

public class EIAEquiposSubTab extends Tab implements EIATypeSelectionListener {

	private EIAEquiposGridPanel eiaEquiposGridPanel = new EIAEquiposGridPanel();

	public EIAEquiposSubTab() {
		setTitle("Equipos");
		setPaneMargin(0);
		setPane(eiaEquiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> eias) {

				ListGridRecord[] array = EIAUtil.toGridRecords(eias).toArray(
						new EIATypeRecord[] {});
				eiaEquiposGridPanel.setData(array);

			}
		});

	}

}
