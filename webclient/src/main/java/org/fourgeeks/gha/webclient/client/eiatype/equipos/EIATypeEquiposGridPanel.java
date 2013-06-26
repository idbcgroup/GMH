package org.fourgeeks.gha.webclient.client.eiatype.equipos;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eia.EIAAddForm;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeEquiposGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable {

	private EIAGrid grid;
	private EiaType eiaType;
	private EIAAddForm addForm;
	{
		grid = new EIAGrid();
		addForm = new EIAAddForm();
	}

	public EIATypeEquiposGridPanel() {
		super();
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

		GHAButton addButton = new GHAButton("../resources/icons/new.png");
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addForm.animateShow(AnimationEffect.FLY);
			}
		});
		GHAButton editButton = new GHAButton("../resources/icons/edit.png");
		GHAButton deleteButton = new GHAButton("../resources/icons/delete.png");
		GHAButton setsButton = new GHAButton("../resources/icons/set.png");
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
		// TODO Auto-generated method stub

	}
}
