package org.fourgeeks.gha.webclient.client.material;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

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
public class MaterialSearchForm extends GHASlideInWindow implements
		MaterialSelectionListener {

	private List<MaterialSelectionListener> selectionListeners;
	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	private GHASelectItem typeSelectItem, externalProviderSelectItem;
	private MaterialGrid grid;
	private MaterialAddForm addForm;

	{
		selectionListeners = new LinkedList<MaterialSelectionListener>();
		codeTextItem = new GHATextItem("Código");
		nameTextItem = new GHATextItem("Nombre");
		modelTextItem = new GHATextItem("Modelo");
		extCodeTextItem = new GHATextItem("Código externo");
		descriptionTextItem = new GHATextItem("Descipción");
		// selects
		externalProviderSelectItem = new GHASelectItem("Proveedor");
		typeSelectItem = new GHASelectItem("Tipo");
		
		addForm = new MaterialAddForm();
		addForm.addMaterialSelectionListener(this);
	}

	/**
	 * 
	 */
	public MaterialSearchForm() {
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeTextItem, nameTextItem, modelTextItem,
				extCodeTextItem, descriptionTextItem,
				externalProviderSelectItem, typeSelectItem);

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
		externalProviderSelectItem.addKeyUpHandler(searchKeyUpHandler);
		typeSelectItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png"),
				new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								hide();
							}
						}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(formLayout);
		addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));

		grid = new MaterialGrid();
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectMaterial(grid.getSelectedEntity());
						hide();
					}
				}),
				GHAUiHelper.verticalGraySeparator("2px"),
				new GHAImgButton("../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
					}
				})
		);

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);
		fillSelects();
	}

	private void fillSelects() {
		// types
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaterialTypeEnum e : MaterialTypeEnum.values())
			valueMap.put(e.ordinal() + "", e.toString());
		typeSelectItem.setValueMap(valueMap);

		GHACache.INSTANCE
				.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {

					@Override
					public void onSuccess(List<ExternalProvider> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ExternalProvider manufacturer : result)
							valueMap.put(manufacturer.getId() + "",
									manufacturer.getInstitution().getName());
						externalProviderSelectItem.setValueMap(valueMap);

					}
				});
	}

	private void selectMaterial(Material material) {
		for (MaterialSelectionListener listener : selectionListeners)
			listener.select(material);
	}

	/**
	 * @param selecionListener
	 */
	public void AddMaterialSelectionListener(
			MaterialSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}
	
	@Override
	public void select(Material material) {
		search(material);
	}

	private void search() {
		Material material = new Material();
		material.setCode(codeTextItem.getValueAsString());
		material.setName(nameTextItem.getValueAsString());
		material.setDescription(descriptionTextItem.getValueAsString());
		material.setModel(modelTextItem.getValueAsString());
		material.setExtCode(extCodeTextItem.getValueAsString());

		if (typeSelectItem.getValue() != null)
			material.setType(MaterialTypeEnum.valueOf(typeSelectItem
					.getValueAsString()));

		if (externalProviderSelectItem.getValue() != null)
			material.setExternalProvider(new ExternalProvider(Long
					.valueOf(externalProviderSelectItem.getValueAsString())));
		
		search(material);
	}
	
	private void search(final Material material) {
		MaterialModel.find(material, new GHAAsyncCallback<List<Material>>() {

			@Override
			public void onSuccess(List<Material> materials) {
				ListGridRecord[] array = MaterialUtil.toGridRecords(materials)
						.toArray(new MaterialRecord[] {});
				grid.setData(array);
				
				if (material != null && material.getId() != 0l)
					for (ListGridRecord listGridRecord : grid.getRecords())
						if (((MaterialRecord) listGridRecord).toEntity().getId() == material.getId())
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
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}
}
