package org.fourgeeks.gha.webclient.client.user.credentials;

import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.user.UserTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
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
public class UserCredentialsFormPanel extends VLayout implements GHAClosable, GHAHideable {
	
	private GHACheckboxItem physicianItem, nurseItem, payerItem, patientItem, admisionItem;
	private EiaType eiaType, orginalEiaType;
	private UserTab tab;

	{
		physicianItem = new GHACheckboxItem(CredentialTypeEnum.PHYSICIAN.toString());
		nurseItem = new GHACheckboxItem(CredentialTypeEnum.NURSE.toString());
		payerItem = new GHACheckboxItem(CredentialTypeEnum.PAYER.toString());
		patientItem = new GHACheckboxItem(CredentialTypeEnum.PATIENT.toString());
		admisionItem = new GHACheckboxItem(CredentialTypeEnum.ADMISSION_AGENT.toString());
	}

	public UserCredentialsFormPanel(UserTab tab) {
		activateForm(false);
		this.tab = tab;
		
		tab.addGHAClosableHandler(this);
		
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		Label title = new Label("<h3>Credenciales del Usuario</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);
		
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(1);
		form.setItems(physicianItem, nurseItem,payerItem,patientItem,admisionItem);
		
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
		//reload the original eiatype
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
}