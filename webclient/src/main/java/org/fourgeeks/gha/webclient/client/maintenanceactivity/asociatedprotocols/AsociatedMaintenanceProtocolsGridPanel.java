package org.fourgeeks.gha.webclient.client.maintenanceactivity.asociatedprotocols;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenancePlanMaintenanceProtocolGrid;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class AsociatedMaintenanceProtocolsGridPanel extends VLayout implements
EIATypeSelectionListener,/* EiaSelectionProducer, */
EIASelectionListener, GHAClosable, GHAHideable {

	private MaintenancePlanMaintenanceProtocolGrid grid;
	private EiaType eiaType;
	{
		grid = new MaintenancePlanMaintenanceProtocolGrid();
	}

	/**
	 * @param subTab
	 */
	public AsociatedMaintenanceProtocolsGridPanel(
			AsociatedMaintenanceProtocolsSubTab subTab) {
		super();


		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		Label title = new Label("<h3>Protocolos de Mantenimiento contienen esta actividad</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//TODO: EIA Search(select) form
					}
				}), new GHAImgButton("../resources/icons/delete.png",
						new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//TODO: Remove this plan for the selected equipment
					}

				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);
	}

	@Override
	public void close() {
		//Close the search/select form
	}

	@Override
	public void hide() {
		//Hide the search/select form
		// super.hide();
	}

	/**
	 * @param eiaType
	 */
	private void loadData(EiaType eiaType) {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {
			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = EIAUtil
						.toGridRecords(result).toArray(new EIARecord[] {});
				grid.setData(array);

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 * 
	 * @Override public void hide() {
	 * eiaAddForm.animateHide(AnimationEffect.FLY); }
	 * 
	 * /* (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		loadData(eiaType);
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData(eiaType);
	}

	// @Override
	// public void addEiaSelectionListener(
	// EIASelectionListener eiaSelectionListener) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void removeEiaSelectionListener(
	// EIASelectionListener eiaSelectionListener) {
	// // TODO Auto-generated method stub
	//
	// }

}
