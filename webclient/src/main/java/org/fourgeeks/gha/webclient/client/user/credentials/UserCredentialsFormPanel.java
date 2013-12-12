package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.user.UserPanel;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserCredentialsFormPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener {

	private GHACheckboxItem physicianItem, nurseItem, payerItem, patientItem,
			admisionItem;
	private EiaType eiaType, orginalEiaType;
	private UserPanel tab;

	{
		physicianItem = new GHACheckboxItem(
				CredentialTypeEnum.PHYSICIAN.toString());
		nurseItem = new GHACheckboxItem(CredentialTypeEnum.NURSE.toString());
		payerItem = new GHACheckboxItem(CredentialTypeEnum.PAYER.toString());
		patientItem = new GHACheckboxItem(CredentialTypeEnum.PATIENT.toString());
		admisionItem = new GHACheckboxItem(
				CredentialTypeEnum.ADMISSION_AGENT.toString());
	}

	public UserCredentialsFormPanel(UserPanel tab) {
		activateForm(false);
		this.tab = tab;

		tab.addClosableListener(this);

		GHALabel title = new GHALabel("Credenciales del Usuario");
		addMember(title);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(1);
		form.setItems(physicianItem, nurseItem, payerItem, patientItem,
				admisionItem);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/undo.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						undo();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(gridPanel);
	}

	public void activateForm(boolean activate) {
		physicianItem.setDisabled(!activate);
		nurseItem.setDisabled(!activate);
		payerItem.setDisabled(!activate);
		patientItem.setDisabled(!activate);
		admisionItem.setDisabled(!activate);
	}

	protected void undo() {
		// reload the original eiatype
		save();
	}

	private void save() {

	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}
}
