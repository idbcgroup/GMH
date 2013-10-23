package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eia.EIAAddForm;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUpdateForm;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeEquipmentGridPanel extends VLayout implements
		EIATypeSelectionListener,/* EiaSelectionProducer, */
		EIASelectionListener, GHAClosable, GHAHideable {

	private EIAGrid grid;
	private EiaType eiaType;

	private EIAAddForm eiaAddForm;
	private EIAUpdateForm eiaUpdateForm;
	{
		grid = new EIAGrid();
		eiaAddForm = new EIAAddForm();
		eiaUpdateForm = new EIAUpdateForm();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public EIATypeEquipmentGridPanel() {
		super();
		eiaAddForm.addEiaSelectionListener(this);
		eiaUpdateForm.addEiaSelectionListener(this);

		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		GHALabel title = new GHALabel(
				"Equipos pertenecientes a este Tipo de Equipo");
		addMember(title);

		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						eiaAddForm.open();
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						final Eia selectedRecord = grid.getSelectedEntity();

						if (selectedRecord == null)
							return;// No record selected

						GHANotification
								.confirm(
										"Equipo",
										"Confirme si desea eliminar el equipo seleccionado",
										new BooleanCallback() {

											@Override
											public void execute(
													Boolean resultAsc) {
												if (resultAsc)
													EIAModel.delete(
															selectedRecord
																	.getId(),
															new GHAAsyncCallback<Boolean>() {

																@Override
																public void onSuccess(
																		Boolean result) {
																	loadData(eiaType);

																}

															});
											}
										});

					}

				}), new GHAImgButton("../resources/icons/edit.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						final Eia selectedRecord = grid.getSelectedEntity();

						if (selectedRecord == null)
							return;// No record selected

						eiaUpdateForm.setEia(selectedRecord);
						eiaUpdateForm.open();
					}
				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		eiaAddForm.select(eiaType);
		eiaUpdateForm.select(eiaType);
		loadData(eiaType);

	}

	/**
	 * @param eiaType
	 */
	private void loadData(EiaType eiaType) {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = EIAUtil.toGridRecords(result).toArray(
						new EIARecord[] {});
				grid.setData(array);

			}
		});
	}

	@Override
	public void close() {
		eiaAddForm.animateHide(AnimationEffect.FLY);
		eiaAddForm.close();
	}

	@Override
	public void hide() {
		eiaAddForm.animateHide(AnimationEffect.FLY);
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
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}
}
