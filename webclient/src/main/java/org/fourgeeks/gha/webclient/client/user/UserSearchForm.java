package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserSearchForm extends GHASlideInWindow {

	private UserGrid grid;
	private GHATextItem usernameItem, idItem, firstNameItem, secondNameItem,
			lastNameItem, secondLastNameItem, nationalityItem;
	private GHASelectItem typeidSelectItem, genderSelectItem;
	private GHADateItem birthDateItem;

	private UserAddForm addForm;

	{
		usernameItem = new GHATextItem("Nombre de Usuario",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		firstNameItem = new GHATextItem("Primer Nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondNameItem = new GHATextItem("Segundo Nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		lastNameItem = new GHATextItem("Apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondLastNameItem = new GHATextItem("Segundo Apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);

		typeidSelectItem = new GHASelectItem("Tipo ID",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		idItem = new GHATextItem("No. Identificiación",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		genderSelectItem = new GHASelectItem("Género",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		nationalityItem = new GHATextItem("Nacionalidad",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		birthDateItem = new GHADateItem("Fecha de Nac.",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);

		grid = new UserGrid();

		addForm = new UserAddForm();
	}

	/**
	 * 
	 */
	public UserSearchForm() {
		super(1);
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		Label title = new Label("<h3>Busqueda de Usuarios</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);

		form.setItems(usernameItem, new GHASpacerItem(2), firstNameItem,
				secondNameItem, lastNameItem, secondLastNameItem,
				new GHASpacerItem(), typeidSelectItem, idItem,
				genderSelectItem, nationalityItem, birthDateItem);

		// Event Handlers
		ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};
		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search();
				}
			}
		};
		usernameItem.addKeyUpHandler(searchKeyUpHandler);
		firstNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondNameItem.addKeyUpHandler(searchKeyUpHandler);
		lastNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		idItem.addKeyUpHandler(searchKeyUpHandler);
		genderSelectItem.addKeyUpHandler(searchKeyUpHandler);
		nationalityItem.addKeyUpHandler(searchKeyUpHandler);
		birthDateItem.addKeyUpHandler(searchKeyUpHandler);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								form.clearValues();
								grid.setData(new ListGridRecord[0]);
							}
						}), new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								hide();
							}
						}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(title, formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// selectEia(((EIARecord) grid.getSelectedRecord())
						// .toEntity());
						hide();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);

		fillExtras();
	}

	private void fillExtras() {
		typeidSelectItem.setValueMap(DocumentTypeEnum.toValueMap());
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
	}

	/**
	 * 
	 * @param eiaSelectionListener
	 */
	public void addEIASelectionListener(
			EIASelectionListener eiaSelectionListener) {
		// listeners.add(eiaSelectionListener);
	}

	private void selectEia(Eia eia) {
		// for (EIASelectionListener listener : listeners)
		// listener.select(eia);
	}

	private void search() {
		// Eia eia = new Eia();
		// if (actualCostItem.getValue() != null)
		// eia.setActualCost(new BigDecimal(actualCostItem.getValueAsString()));
		// if (responsibleRoleItem.getValue() != null)
		// eia.setResponsibleRole(new RoleBase(Long
		// .parseLong(responsibleRoleItem.getValueAsString())));
		// eia.setCode(codeItem.getValueAsString());
		// if (eiaTypeItem.getValue() != null)
		// eia.setEiaType(new EiaType(eiaTypeItem.getValueAsString()));
		// eia.setFixedAssetIdentifier(fixedAssetIdentifierItem.getValueAsString());
		// // if (buildingLocationItem.getValue() != null)
		// // eia.setBuildingLocation(new BuildingLocation(buildingLocationItem
		// // .getValueAsString()));
		// if (obuItem.getValue() != null)
		// eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
		// eia.setSerialNumber(serialNumberItem.getValueAsString());
		// if (stateItem.getValue() != null)
		// eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
		// search(eia);
	}

	private void search(final Eia eia) {
		// EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {
		//
		// @Override
		// public void onSuccess(List<Eia> result) {
		// ListGridRecord[] array = EIAUtil.toGridRecords(result).toArray(
		// new EIARecord[] {});
		// grid.setData(array);
		// if (eia != null && eia.getId() != 0l)
		// for (ListGridRecord listGridRecord : grid.getRecords())
		// if (((EIARecord) listGridRecord).toEntity().getId() == eia
		// .getId())
		// grid.selectRecord(listGridRecord);
		// }
		// });
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
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}

}
