package org.fourgeeks.gha.webclient.client.user.uilog;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.user.loginlog.LogonLogGrid;

import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class UserUILogGridPanel extends GHAFormLayout implements
		ClosableListener, HideableListener {

	private LogonLogGrid grid;
	private EiaType eiaType;
	{
		grid = new LogonLogGrid();
	}

	/**
	 * @param eIATypeEquipmentSubTab
	 */
	public UserUILogGridPanel(UserUILogSubTab eIATypeEquipmentSubTab) {
		super();
		GHATopTitleLabel title = new GHATopTitleLabel("Log de Mensajes UI");
		addMember(title);

		// //////Botones laterales
		// VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
		// "../resources/icons/new.png", new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// }
		// }), new GHAImgButton("../resources/icons/edit.png",
		// new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// }
		// }), new GHAImgButton("../resources/icons/delete.png",
		// new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// }
		//
		// }));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid/* , sideButtons */);
		addMember(mainLayout);
	}

	/**
	 * @param eiaType
	 */
	private void loadData(EiaType eiaType) {
		// EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {
		//
		// @Override
		// public void onSuccess(List<Eia> result) {
		// ListGridRecord[] array = (ListGridRecord[]) EIAUtil
		// .toGridRecords(result).toArray(new EIARecord[] {});
		// grid.setData(array);
		//
		// }
		// });
	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {
		// super.hide();
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
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
