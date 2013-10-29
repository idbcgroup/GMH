package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeComponentGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable, GHAHideable {

	private EIATypeComponentGrid eiaTypeComponentGrid;
	private EiaType eiaType;
	private EIATypeSearchForm searchForm;

	{
		eiaTypeComponentGrid = new EIATypeComponentGrid();
		eiaTypeComponentGrid.getRequiredField().addCellSavedHandler(
				new CellSavedHandler() {

					@Override
					public void onCellSaved(CellSavedEvent event) {
						EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event
								.getRecord()).toEntity();
						eiaTypeComponent.setComponentRequired((Boolean) event
								.getNewValue());
						EIATypeComponentModel.update(eiaTypeComponent,
								new GHAAsyncCallback<EiaTypeComponent>() {

									@Override
									public void onSuccess(
											EiaTypeComponent result) {
									}
								});
					}
				});
		eiaTypeComponentGrid.getReplaceableField().addCellSavedHandler(
				new CellSavedHandler() {

					@Override
					public void onCellSaved(CellSavedEvent event) {
						EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event
								.getRecord()).toEntity();
						eiaTypeComponent
								.setComponentReplaceable((Boolean) event
										.getNewValue());
						EIATypeComponentModel.update(eiaTypeComponent,
								new GHAAsyncCallback<EiaTypeComponent>() {

									@Override
									public void onSuccess(
											EiaTypeComponent result) {
									}
								});
					}
				});

		searchForm = new EIATypeSearchForm(GHAStrings.get("search-component"));
		searchForm.addEiaTypeSelectionListener(new EIATypeSelectionListener() {

			@Override
			public void select(EiaType eiaType) {
				// clean the search form
				EIATypeComponentGridPanel.this.searchForm.clean();

				final EiaTypeComponent eiaTypeComponent = new EiaTypeComponent();
				eiaTypeComponent
						.setParentEiaType(EIATypeComponentGridPanel.this.eiaType);
				eiaTypeComponent.setEiaType(eiaType);
				eiaTypeComponent.setComponentReplaceable(false);
				eiaTypeComponent.setComponentRequired(false);
				EIATypeComponentModel.save(eiaTypeComponent,
						new GHAAsyncCallback<EiaTypeComponent>() {

							@Override
							public void onSuccess(EiaTypeComponent result) {
								loadData();
							}
						});
			}
		});
	}

	public EIATypeComponentGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		GHALabel title = new GHALabel("Componentes");

		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						searchForm.open(EIATypeComponentGridPanel.this.eiaType);
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						Window.alert("2");
						EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) eiaTypeComponentGrid
								.getSelectedRecord()).toEntity();

						if (eiaTypeComponent == null) {
							GHANotification.info(GHAStrings
									.get("record-not-selected"));
							return;
						}

						EIATypeComponentModel.delete(eiaTypeComponent.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loadData();
									}
								});
					}
				}));

		final HLayout gridContainer = new HLayout();
		gridContainer.addMembers(eiaTypeComponentGrid, sideButtons);

		addMembers(title, gridContainer);
	}

	private void loadData() {
		EIATypeComponentModel.findByParentEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeComponent>>() {

					@Override
					public void onSuccess(
							List<EiaTypeComponent> eiaTypeComponents) {
						ListGridRecord[] array = EIATypeComponentUtil
								.toGridRecords(eiaTypeComponents).toArray(
										new EIATypeComponentRecord[] {});
						eiaTypeComponentGrid.setData(array);
					}

				});
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
	}

	@Override
	public void close() {
		searchForm.animateHide(AnimationEffect.FLY);
		searchForm.close();
	}

	@Override
	public void hide() {
		searchForm.animateHide(AnimationEffect.FLY);
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

}
