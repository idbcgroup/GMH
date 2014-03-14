/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHAEmailTextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.textitems.GHANameTextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class CitizenSearchForm extends GHASearchForm<Citizen> implements
		CitizenSelectionProducer {

	private GHATextItem idItem, firstNameItem, secondNameItem, lastNameItem,
			secondLastNameItem, nationalityItem, legalEntityIdentifierItem;
	private GHASelectItem typeidSelectItem, genderSelectItem;
	private GHAEmailTextItem primaryEmailItem;

	// private CitizenGrid grid;
	// private List<CitizenSelectionListener> listeners;
	// private CitizenTopForm citizenTopForm;
	private GHADynamicForm form;

	protected final CitizenResultSet resultSet = new CitizenResultSet(
			ResultSetContainerType.SEARCH_FORM);

	{
		firstNameItem = new GHANameTextItem(GHAStrings.get("first-name"));
		secondNameItem = new GHANameTextItem(GHAStrings.get("second-name"));
		lastNameItem = new GHANameTextItem(GHAStrings.get("first-lastname"));
		lastNameItem.setTooltip(GHAStrings.get("user-tooltip-last-name"));
		secondLastNameItem = new GHANameTextItem(
				GHAStrings.get("second-lastname"));
		primaryEmailItem = new GHAEmailTextItem("Email Primario");
		typeidSelectItem = new GHASelectItem("Tipo ID");
		idItem = new GHATextItem("No. Identificiación");
		genderSelectItem = new GHASelectItem("Género");
		nationalityItem = new GHATextItem("Nacionalidad");
		legalEntityIdentifierItem = new GHATextItem("R.I.F.");

		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());

		form = new GHADynamicForm(5, FormType.NORMAL_FORM);

		// grid = new CitizenGrid();
		// listeners = new ArrayList<CitizenSelectionListener>();

		resultSet.addCitizenSelectionListener(new CitizenSelectionListener() {
			@Override
			public void onCitizenSelect(Citizen citizen) {
				hide();
			}
		});
	}

	/**
	 * @param title
	 * 
	 */
	public CitizenSearchForm(String title) {
		super(title);
		setTop(GHAUiHelper.getTopSpace()
				- GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ GHAUiHelper.DEFAULT_PATIENT_TOP_HEIGHT);
		form.setItems(typeidSelectItem, idItem, firstNameItem, secondNameItem,
				lastNameItem, secondLastNameItem, genderSelectItem,
				nationalityItem, legalEntityIdentifierItem, primaryEmailItem);
		form.setAutoFocus(true);
		// Event Handlers
		final ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};

		idItem.addKeyUpHandler(searchKeyUpHandler);
		firstNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondNameItem.addKeyUpHandler(searchKeyUpHandler);
		lastNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		nationalityItem.addKeyUpHandler(searchKeyUpHandler);
		legalEntityIdentifierItem.addKeyUpHandler(searchKeyUpHandler);
		typeidSelectItem.addKeyUpHandler(searchKeyUpHandler);
		genderSelectItem.addKeyUpHandler(searchKeyUpHandler);
		primaryEmailItem.addKeyUpHandler(searchKeyUpHandler);

		final VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clean();
			}
		}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
				clean();
			}
		}));

		final HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"), resultSet);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * addUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addCitizenSelectionListener(
			CitizenSelectionListener userSelectionListener) {
		resultSet.addCitizenSelectionListener(userSelectionListener);
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void notifyCitizen(Citizen citizen) {
		resultSet.notifyCitizen(citizen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * removeUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeCitizenSelectionListener(
			CitizenSelectionListener userSelectionListener) {
		resultSet.removeCitizenSelectionListener(userSelectionListener);
	}

	@Override
	public void search() {
		final Citizen citizen = new Citizen();
		final LegalEntity legalEntity = new LegalEntity();
		if (firstNameItem.getValue() != null) {
			citizen.setFirstName(firstNameItem.getValueAsString());
		}
		if (secondNameItem.getValue() != null) {
			citizen.setSecondName(secondNameItem.getValueAsString());
		}
		if (lastNameItem.getValue() != null) {
			citizen.setFirstLastName(lastNameItem.getValueAsString());
		}
		if (secondLastNameItem.getValue() != null) {
			citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		}
		if (primaryEmailItem.getValue() != null) {
			citizen.setPrimaryEmail(primaryEmailItem.getValueAsString());
		}
		if (typeidSelectItem.getValue() != null) {
			citizen.setIdType(DocumentTypeEnum.valueOf(typeidSelectItem
					.getValueAsString()));
		}
		if (idItem.getValue() != null) {
			citizen.setIdNumber(idItem.getValueAsString());
		}
		if (genderSelectItem.getValue() != null) {
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		}
		if (nationalityItem.getValue() != null) {
			citizen.setNationality(nationalityItem.getValueAsString());
		}
		if (legalEntityIdentifierItem.getValue() != null) {
			legalEntity.setIdentifier(legalEntityIdentifierItem
					.getValueAsString());
			citizen.setLegalEntity(legalEntity);
		}
		search(citizen);
	}

	private void search(Citizen citizen) {
		CitizenModel.find(citizen, new GHAAsyncCallback<List<Citizen>>() {

			@Override
			public void onSuccess(List<Citizen> results) {
				List<Citizen> newList = null;
				if (blackList != null) {
					final List<AbstractEntity> tmpList = GHAUtil
							.binarySearchFilterEntity(results, blackList);
					final List<Citizen> newTmpList = new ArrayList<Citizen>();
					for (final AbstractEntity abstractCodeEntity : tmpList)
						newTmpList.add((Citizen) abstractCodeEntity);
					newList = newTmpList;
				} else
					newList = results;

				resultSet.setRecords(newList, false);
			}
		});
	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		// grid.setRecords(new CitizenRecord[] {});
		resultSet.clean();
	}
}
