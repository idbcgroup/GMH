package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eia.EIASearchForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAComponentGridPanel extends VLayout implements GHAClosable,
		GHAHideable, EIASelectionListener {

	private EIAComponentGrid eiaComponentGrid;
	private Eia eia;
	private EIASearchForm eIASearchForm;

	{
		eiaComponentGrid = new EIAComponentGrid();
		eiaComponentGrid.getComponentobsField().addCellSavedHandler(
				new CellSavedHandler() {

					@Override
					public void onCellSaved(CellSavedEvent event) {
						// TODO Auto-generated method stub
						EiaComponent eiaComponent = ((EIAComponentRecord) event
								.getRecord()).toEntity();
						eiaComponent.setComponentObs((String) event
								.getNewValue());
						EIAComponentModel.update(eiaComponent,
								new GHAAsyncCallback<EiaComponent>() {

									@Override
									public void onSuccess(EiaComponent result) {
									}
								});

					}
				});

		eIASearchForm = new EIASearchForm();
		eIASearchForm.addEiaSelectionListener(new EIASelectionListener() {

			@Override
			public void select(Eia eia) {
				// TODO Auto-generated method stub
				final EiaComponent eiaComponent = new EiaComponent();
				eiaComponent.setParentEia(EIAComponentGridPanel.this.eia);
				eiaComponent.setEia(eia);
				eiaComponent.setComponentObs("");
				EIAComponentModel.save(eiaComponent,
						new GHAAsyncCallback<EiaComponent>() {

							@Override
							public void onSuccess(EiaComponent result) {
								// TODO Auto-generated method stub
								loadData();

							}
						});

			}
		});
	}

	public EIAComponentGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		GHALabel title = new GHALabel("Componentes de Equipos");

		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						// form.animateShow(AnimationEffect.FLY);
						eIASearchForm.open();
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						// EiaComponent eiaComponent = ((EIAComponentRecord)
						// eiaComponentGrid.getSelectedRecord()).toEntity();
						EiaComponent eiaComponent = eiaComponentGrid
								.getSelectedEntity();
						EIAComponentModel.delete(eiaComponent.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loadData();
									}
								});

					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaComponentGrid, sideButtons);

		addMembers(title, mainPanel);

	}

	@Override
	public void close() {
		eIASearchForm.close();

	}

	@Override
	public void hide() {
		eIASearchForm.hide();

	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		this.eia = eia;
		loadData();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		EIAComponentModel.findByEiaId(eia,
				new GHAAsyncCallback<List<EiaComponent>>() {

					@Override
					public void onSuccess(List<EiaComponent> eiaComponents) {
						ListGridRecord[] array = EIAComponentUtil
								.toGridRecords(eiaComponents).toArray(
										new EIAComponentRecord[] {});
						eiaComponentGrid.setData(array);
					}

				});

	}
}
