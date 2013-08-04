package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHANotification;
import org.fourgeeks.gha.webclient.client.eia.EIAAddForm;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeGrid;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;



public class EIATypeEquipmentGridPanel extends VLayout implements
		EIATypeSelectionListener, EIASelectionListener, GHAClosable {


	private EIAGrid grid;
	private EiaType eiaType;
	
	private EIAAddForm eiaAddForm;
	{
		grid = new EIAGrid();
		eiaAddForm = new EIAAddForm();
	}

	private EIAGrid eiaEquipmentGrid = new EIAGrid();
	
	public EIATypeEquipmentGridPanel(EIATypeEquipmentSubTab eIATypeEquipmentSubTab) {
		super();
		eiaAddForm.addEiaSelectionListener(eIATypeEquipmentSubTab);
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");
		
		Label title = new Label("<h3>Equipos pertenecientes al EIA Type</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		// //////Botones laterales
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAImgButton addButton = new GHAImgButton("../resources/icons/new.png");
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eiaAddForm.animateShow(AnimationEffect.FLY);
			}
		});
		GHAImgButton editButton = new GHAImgButton("../resources/icons/edit.png");
		
		GHAImgButton deleteButton = new GHAImgButton("../resources/icons/delete.png");
		
		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				GHANotification.confirm("Equipo","Confirme si desea eliminar el equipo seleccionado", new BooleanCallback() {
					
					@Override
					public void execute(Boolean resultAsc) {
						if(resultAsc){
							Eia eiaEquipment = ((EIARecord) eiaEquipmentGrid.getSelectedRecord()).toEntity();
							
							EIAModel.delete(eiaEquipment.getId(), new GHAAsyncCallback<Boolean>() {
								
								@Override
								public void onSuccess(Boolean result) {
									loadDataEquipment();
									
								}
								@Override
								public void onFailure(Throwable caught){
									GHANotification.alert(caught.getMessage());
								}

							});
							
							
						}else {
							loadDataEquipment();
						}
					}
				});
				


			}
		
		});		
		
		
		
		
		GHAImgButton setsButton = new GHAImgButton("../resources/icons/set.png");
		setsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// EIATypeRecord selectedRecord = (EIATypeRecord)
				// eiaTypeEquiposGrid
				// .getSelectedRecord();
				// History.newItem("eia/" + selectedRecord.getCode());
			}
		});

		sideButtons.addMembers(addButton, editButton, deleteButton, setsButton);

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);

	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		eiaAddForm.select(eiaType);
		loadData(eiaType);

	}

	/**
	 * @param eiaType
	 */
	private void loadData(EiaType eiaType) {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = (ListGridRecord[]) EIAUtil
						.toGridRecords(result).toArray(new EIARecord[] {});
				grid.setData(array);

			}
		});
	}

	@Override
	public void close() {
		eiaAddForm.animateHide(AnimationEffect.FLY);
		eiaAddForm.destroy();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		loadData(eiaType);
	}


	private void loadDataEquipment() {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = (ListGridRecord[]) EIAUtil
						.toGridRecords(result).toArray(new EIARecord[] {});
				grid.setData(array);

			}
		});
	}	
	
}
