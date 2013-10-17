package org.fourgeeks.gha.webclient.client.user.uilog;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.user.loginlog.LogonLogGrid;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserUILogGridPanel extends VLayout implements GHAClosable, GHAHideable {

	private LogonLogGrid grid;
	private EiaType eiaType;
	{
		grid = new LogonLogGrid();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public UserUILogGridPanel(
			UserUILogSubTab eIATypeEquipmentSubTab) {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");

		GHALabel title = new GHALabel("Log de Mensajes UI");
		addMember(title);

		// //////Botones laterales
//		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
//				"../resources/icons/new.png", new ClickHandler() {
//
//					@Override
//					public void onClick(ClickEvent event) {
//					
//					}
//				}), new GHAImgButton("../resources/icons/edit.png",
//				new ClickHandler() {
//					@Override
//					public void onClick(ClickEvent event) {
//					
//					}
//				}), new GHAImgButton("../resources/icons/delete.png",
//				new ClickHandler() {
//
//					@Override
//					public void onClick(ClickEvent event) {
//					
//					}
//
//				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid/*, sideButtons*/);
		addMember(mainLayout);
	}

	/**
	 * @param eiaType
	 */
	private void loadData(EiaType eiaType) {
//		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {
//
//			@Override
//			public void onSuccess(List<Eia> result) {
//				ListGridRecord[] array = (ListGridRecord[]) EIAUtil
//						.toGridRecords(result).toArray(new EIARecord[] {});
//				grid.setData(array);
//
//			}
//		});
	}

	@Override
	public void close() {
	
	}

	@Override
	public void hide() {
		// super.hide();
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
