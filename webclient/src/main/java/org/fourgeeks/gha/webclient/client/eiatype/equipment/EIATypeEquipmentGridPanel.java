package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eia.EIAAddForm;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUpdateForm;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeEquipmentGridPanel extends GHAFormLayout implements
		EIATypeSelectionListener,/* EiaSelectionProducer, */
		EIASelectionListener, EiaDamageReportSelectionListener,
		HideableListener, ClosableListener {

	private EIAGrid grid;
	private EiaCountLabel eiaLabel;
	private EiaType eiaType;

	private EIAAddForm eiaAddForm;
	private EIAUpdateForm eiaUpdateForm;
	{
		grid = new EIAGrid();
		eiaAddForm = new EIAAddForm(GHAStrings.get("new-eia"));
		eiaUpdateForm = new EIAUpdateForm(GHAStrings.get("edit-eia"));
		eiaLabel = new EiaCountLabel();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public EIATypeEquipmentGridPanel() {
		super();
		eiaAddForm.addEiaSelectionListener(this);
		eiaUpdateForm.addEiaSelectionListener(this);

		final GHALabel title = new GHALabel(
				"Equipos pertenecientes a este Tipo de Equipo");
		addMember(title);

		// //////Botones laterales
		final VLayout sideButtons = GHAUiHelper.createBar(new GHANewButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						eiaAddForm
								.select(EIATypeEquipmentGridPanel.this.eiaType);
						eiaAddForm.open();
					}
				}), new GHADeleteButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				delete();
			}

		}), new GHAEditButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final Eia selectedRecord = grid.getSelectedEntity();

				if (selectedRecord == null) {
					GHAErrorMessageProcessor.alert("record-not-selected");
					return;
				}

				eiaUpdateForm.setEia(selectedRecord);
				eiaUpdateForm.open();
			}
		}));

		final VLayout gridPanel = new VLayout();
		gridPanel.setMembersMargin(10);
		gridPanel.addMembers(grid, eiaLabel);

		final HLayout mainLayout = new HLayout();
		mainLayout.addMembers(gridPanel, sideButtons);
		addMembers(mainLayout);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() {
		eiaAddForm.close();
		eiaUpdateForm.close();
	}

	private void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAErrorMessageProcessor.confirm("eias-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								List<Eia> entities = grid.getSelectedEntities();
								EIAModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												GHAErrorMessageProcessor
														.alert("eias-delete-success");
												loadData(EIATypeEquipmentGridPanel.this.eiaType);
											}
										});
							}
						}
					});
		} else {
			GHAErrorMessageProcessor.confirm("eia-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								Eia entity = grid.getSelectedEntity();
								EIAModel.delete(entity.getId(),
										new GHAAsyncCallback<Boolean>() {

											@Override
											public void onSuccess(Boolean result) {
												GHAErrorMessageProcessor
														.alert("eia-delete-success");
												loadData(EIATypeEquipmentGridPanel.this.eiaType);
											}
										});
							}
						}
					});
		}
	}

	@Override
	public void hide() {
		if (eiaAddForm.isVisible())
			eiaAddForm.animateHide(AnimationEffect.FLY);
		if (eiaUpdateForm.isVisible())
			eiaUpdateForm.animateHide(AnimationEffect.FLY);
	}

	/**
	 * @param eiaType
	 */
	private void loadData(EiaType eiaType) {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				final ListGridRecord[] array = EIAUtil.toGridRecords(result)
						.toArray(new EIARecord[] {});
				grid.setData(array);
				EIATypeEquipmentGridPanel.this.eiaLabel
						.setEiaStateTotals(result);
			}
		});
	}

	/*
	 * (non-Javadoc)
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
	public void select(EiaDamageReport eiaDamageReport) {
		loadData(eiaType);
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		eiaAddForm.select(eiaType);
		eiaUpdateForm.select(eiaType);
		loadData(eiaType);
	}
}
