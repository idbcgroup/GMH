package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
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
 * @author alacret Material search form
 * 
 */
public class MaterialCategorySearchForm extends GHASearchForm implements
		MaterialCategorySelectionListener, MaterialCategorySelectionProducer {

	private List<MaterialCategorySelectionListener> selectionListeners;
	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	protected GHASelectItem typeSelectItem;
	private MaterialCategoryGrid grid;

	{
		selectionListeners = new LinkedList<MaterialCategorySelectionListener>();
		codeTextItem = new GHATextItem("Código");
		nameTextItem = new GHATextItem("Nombre");
		modelTextItem = new GHATextItem("Modelo");
		extCodeTextItem = new GHATextItem("Código externo");
		descriptionTextItem = new GHATextItem("Descipción");
		// selects
		typeSelectItem = new GHASelectItem("Tipo");

	}

	/**
	 * 
	 */
	public MaterialCategorySearchForm(String title) {
		super(title);
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeTextItem, nameTextItem, modelTextItem,
				extCodeTextItem, descriptionTextItem, typeSelectItem);

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

		codeTextItem.addKeyUpHandler(searchKeyUpHandler);
		nameTextItem.addKeyUpHandler(searchKeyUpHandler);
		modelTextItem.addKeyUpHandler(searchKeyUpHandler);
		extCodeTextItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionTextItem.addKeyUpHandler(searchKeyUpHandler);
		typeSelectItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

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
		formLayout.setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		grid = new MaterialCategoryGrid();
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectMaterialCategory(grid.getSelectedEntity());
						hide();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);
		fillSelects();
	}

	private void fillSelects() {
		typeSelectItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	private void selectMaterialCategory(MaterialCategory materialCategory) {
		for (MaterialCategorySelectionListener listener : selectionListeners)
			listener.select(materialCategory);
	}

	@Override
	public void select(MaterialCategory materialCategory) {
		search(materialCategory);
	}

	private void search() {
		MaterialCategory material = new MaterialCategory();
		material.setCode(codeTextItem.getValueAsString());
		material.setName(nameTextItem.getValueAsString());
		material.setDescription(descriptionTextItem.getValueAsString());
		material.setModel(modelTextItem.getValueAsString());
		material.setExternalCode(extCodeTextItem.getValueAsString());
		if (typeSelectItem.getValue() != null)
			material.setType(MaterialTypeEnum.valueOf(typeSelectItem
					.getValueAsString()));

		search(material);
	}

	/**
	 * @param materialCateogry
	 */
	protected void search(final MaterialCategory materialCateogry) {
		MaterialCategoryModel.find(materialCateogry,
				new GHAAsyncCallback<List<MaterialCategory>>() {

					@Override
					public void onSuccess(List<MaterialCategory> materials) {
						ListGridRecord[] array = MaterialCategoryUtil
								.toGridRecords(materials).toArray(
										new MaterialCategoryRecord[] {});
						grid.setData(array);
						if (materialCateogry != null
								&& materialCateogry.getCode() != null)
							for (ListGridRecord listGridRecord : grid
									.getRecords())
								if (((MaterialCategoryRecord) listGridRecord)
										.toEntity().getCode() == materialCateogry
										.getCode())
									grid.selectRecord(listGridRecord);
					}
				});
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
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		selectionListeners.add(materialSelectionListener);
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener) {
		selectionListeners.remove(materialSelectionListener);
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}
}
