package org.fourgeeks.gha.webclient.client.citizen;

import java.util.List;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHADoumentTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAGenreSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAEmailTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHANameTextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.user.SSOUserModel;
import org.fourgeeks.gha.webclient.client.user.UserPanel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
public class CitizenTopForm extends GHATopForm<CitizenResultSet, Citizen>
		implements CitizenSelectionListener {

	private Citizen selected;
	private GHATextItem usernameItem;
	private GHADoumentTypeSelectItem idTypeSelectItem;
	private GHASelectItem stateItem;
	private GHANameTextItem firstNameItem, secondNameItem, firstLastNameItem,
			secondLastNameItem;
	private GHATextItem idItem;
	private GHAEmailTextItem emailItem;
	private GHASelectItem genderSelectItem;
	private GHADynamicForm form;
	{
		firstNameItem = new GHANameTextItem(GHAStrings.get("first-name"));
		secondNameItem = new GHANameTextItem(GHAStrings.get("second-name"));
		firstLastNameItem = new GHANameTextItem(
				GHAStrings.get("first-lastname"));
		secondLastNameItem = new GHANameTextItem(
				GHAStrings.get("second-lastname"));
		emailItem = new GHAEmailTextItem();
		idTypeSelectItem = new GHADoumentTypeSelectItem();
		idItem = new GHATextItem(GHAStrings.get("id-number"));
		idItem.setLength(20);
		idItem.setMask("####################");
		genderSelectItem = new GHAGenreSelectItem();

		usernameItem.addKeyUpHandler(searchKeyUpHandler);
		firstNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondNameItem.addKeyUpHandler(searchKeyUpHandler);
		firstLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		emailItem.addKeyUpHandler(searchKeyUpHandler);
		genderSelectItem.addKeyUpHandler(searchKeyUpHandler);

		form = new GHADynamicForm(5, FormType.NORMAL_FORM);
	}

	/**
	 * @param resultSet
	 * @param userTab
	 */
	public CitizenTopForm(CitizenResultSet resultSet, UserPanel userTab) {
		super(resultSet, userTab);

		form.setItems(usernameItem, firstNameItem, secondNameItem,
				firstLastNameItem, secondLastNameItem, idTypeSelectItem,
				idItem, emailItem, genderSelectItem, stateItem);

		addMembers(form, new LayoutSpacer(), sideButtons);
	}

	@Override
	public void activate() {
		toggleForm(false);
		usernameItem.focusInItem();
		super.activate();
	}

	@Override
	public void clear() {
		usernameItem.clearValue();
		stateItem.clearValue();
		idTypeSelectItem.clearValue();
		idItem.clearValue();
		firstNameItem.clearValue();
		secondNameItem.clearValue();
		firstLastNameItem.clearValue();
		secondLastNameItem.clearValue();
		emailItem.clearValue();
		genderSelectItem.clearValue();
		searchButton.setDisabled(false);
		cleanButton.setDisabled(false);
		activated = true;
		selected = null;
	}

	@Override
	public void deactivate() {
		toggleForm(true);
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
	}

	@Override
	protected void delete() {
		GHAAlertManager.confirm("ssoUser-delete-confirm",
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							SSOUserModel.delete(selected.getId(),
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											containerTab.search();
											clear();
											GHAAlertManager
													.alert("ssoUser-delete-success");
										}
									});
						}
					}
				});
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

	@Override
	public void search() {
		super.search();
		final Citizen citizen = new Citizen();
		if (firstNameItem.getValue() != null)
			citizen.setFirstName(firstNameItem.getValueAsString());
		if (secondNameItem.getValue() != null)
			citizen.setSecondName(secondNameItem.getValueAsString());
		if (firstLastNameItem.getValue() != null)
			citizen.setFirstLastName(firstLastNameItem.getValueAsString());
		if (secondLastNameItem.getValue() != null)
			citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		if (idTypeSelectItem.getValue() != null) {
			citizen.setIdType(DocumentTypeEnum.valueOf(idTypeSelectItem
					.getValueAsString()));
		}
		if (idItem.getValue() != null)
			citizen.setIdNumber(idItem.getValueAsString());
		if (genderSelectItem.getValue() != null)
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		if (emailItem.getValue() != null)
			citizen.setPrimaryEmail(emailItem.getValueAsString());

		search(citizen);
	}

	/**
	 * @param citizen
	 */
	@Override
	public void search(final Citizen citizen) {
		super.search();
		CitizenModel.find(citizen, new GHAAsyncCallback<List<Citizen>>() {

			@Override
			public void onSuccess(List<Citizen> ssoUsers) {
				resultSet.setRecords(ssoUsers, true);
			}
		});
	}

	/**
	 * @param citizen
	 */
	@Override
	public void onCitizenSelect(Citizen citizen) {
		selected = citizen;

		if (citizen.getFirstName() != null)
			firstNameItem.setValue(citizen.getFirstName());
		if (citizen.getSecondName() != null)
			secondNameItem.setValue(citizen.getSecondName());
		if (citizen.getIdType() != null)
			idTypeSelectItem.setValue(citizen.getIdType().name());
		if (citizen.getIdNumber() != null)
			idItem.setValue(citizen.getIdNumber());
		if (citizen.getFirstLastName() != null)
			firstLastNameItem.setValue(citizen.getFirstLastName());
		if (citizen.getSecondLastName() != null)
			secondLastNameItem.setValue(citizen.getSecondLastName());
		if (citizen.getPrimaryEmail() != null)
			emailItem.setValue(citizen.getPrimaryEmail());
		if (citizen.getGender() != null)
			genderSelectItem.setValue(citizen.getGender().name());

		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}

	private void toggleForm(boolean disabled) {
		usernameItem.setDisabled(disabled);
		stateItem.setDisabled(disabled);
		idTypeSelectItem.setDisabled(disabled);
		idItem.setDisabled(disabled);
		firstNameItem.setDisabled(disabled);
		secondNameItem.setDisabled(disabled);
		firstLastNameItem.setDisabled(disabled);
		secondLastNameItem.setDisabled(disabled);
		emailItem.setDisabled(disabled);
		genderSelectItem.setDisabled(disabled);
		cleanButton.setDisabled(disabled);
		activated = !disabled;
	}

}