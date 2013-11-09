package org.fourgeeks.gha.webclient.client.material;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACodeItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class MaterialSearchForm extends GHASearchForm<Material> implements
		MaterialSelectionListener, MaterialSelectionProducer {

	private List<MaterialSelectionListener> listeners;
	private GHATextItem codeTextItem, nameTextItem, descriptionTextItem,
			modelTextItem, extCodeTextItem;
	// protected GHASelectItem typeSelectItem;
	private MaterialGrid grid;
	private GHALabel searchResultsLabel;

	private final DynamicForm form = new DynamicForm();

	{
		listeners = new LinkedList<MaterialSelectionListener>();
		//
		codeTextItem = new GHACodeItem(300);
		nameTextItem = new GHATextItem(GHAStrings.get("name"), 300);
		modelTextItem = new GHATextItem(GHAStrings.get("model"), 300);
		extCodeTextItem = new GHATextItem(GHAStrings.get("external-code"), 300);
		descriptionTextItem = new GHATextItem(GHAStrings.get("description"),
				600);
		descriptionTextItem.setColSpan(2);
		// selects
		// typeSelectItem = new GHASelectItem(GHAStrings.get("type"), 300);
	}

	/**
	 * @param title
	 */
	public MaterialSearchForm(String title) {
		super(title);

		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setItems(codeTextItem, nameTextItem, modelTextItem,
				extCodeTextItem, descriptionTextItem);

		codeTextItem.addKeyUpHandler(searchKeyUpHandler);
		nameTextItem.addKeyUpHandler(searchKeyUpHandler);
		modelTextItem.addKeyUpHandler(searchKeyUpHandler);
		extCodeTextItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionTextItem.addKeyUpHandler(searchKeyUpHandler);
		// typeSelectItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clean();
			}
		}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		grid = new MaterialGrid();
		HLayout gridLayout = new HLayout();

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHACheckButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectMaterial();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);

		searchResultsLabel = new GHALabel(GHAStrings.get("search-results"));

		addMembers(GHAUiHelper.verticalSeparator("10px"), searchResultsLabel,
				gridLayout);
		fillSelects();
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.add(materialSelectionListener);
	}

	/**
	 * 
	 */
	public void clean() {
		form.clearValues();
		grid.setData(new ListGridRecord[] {});
	}

	private void fillSelects() {
		// typeSelectItem.setValueMap(MaterialTypeEnum.toValueMap());
	}

	/**
	 * Actualiza el mensaje de resultados de la busqueda para que muestre la
	 * cantidad de elementos encontrados
	 * 
	 * @param datos
	 *            lista con los elementos encontrados
	 */
	private void mostrarCantResults(List<?> datos) { // TODO: MOVER A LA
														// SUPERCLASE
		String tituloSearchResults = GHAStrings.get("search-results");
		searchResultsLabel.setContents(tituloSearchResults + ": "
				+ datos.size() + " resultados");
		searchResultsLabel.redraw();
	}

	@Override
	public void notifyMaterial(Material material) {
		for (MaterialSelectionListener listener : listeners)
			listener.select(material);
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		listeners.remove(materialSelectionListener);
	}

	@Override
	public void search() {
		Material material = new Material();
		MaterialCategory materialCategory = new MaterialCategory();
		materialCategory.setCode(codeTextItem.getValueAsString());
		materialCategory.setName(nameTextItem.getValueAsString());
		materialCategory.setDescription(descriptionTextItem.getValueAsString());
		materialCategory.setModel(modelTextItem.getValueAsString());
		materialCategory.setExternalCode(extCodeTextItem.getValueAsString());
		// if (typeSelectItem.getValue() != null)
		// materialCategory.setType(MaterialTypeEnum.valueOf(typeSelectItem
		// .getValueAsString()));
		materialCategory.setType(MaterialTypeEnum.MATERIAL);
		material.setMaterialCategory(materialCategory);
		search(material);
	}

	/**
	 * @param materialCateogry
	 */
	protected void search(final Material material) {
		MaterialModel.find(material, new GHAAsyncCallback<List<Material>>() {

			@Override
			public void onSuccess(List<Material> results) {
				List<Material> newList = null;
				if (blackList != null) {
					List<AbstractEntity> tmpList = GHAUtil
							.binarySearchFilterEntity(results, blackList);
					List<Material> newTmpList = new ArrayList<Material>();
					for (AbstractEntity abstractEntity : tmpList)
						newTmpList.add((Material) abstractEntity);
					newList = newTmpList;
				} else {
					newList = results;
					mostrarCantResults(results);
				}

				ListGridRecord[] array = MaterialUtil.toGridRecords(newList)
						.toArray(new MaterialRecord[] {});
				grid.setData(array);
				if (material != null && material.getId() != 0)
					for (ListGridRecord listGridRecord : grid.getRecords())
						if (((MaterialRecord) listGridRecord).toEntity()
								.getId() == material.getId())
							grid.selectRecord(listGridRecord);
			}
		});
	}

	@Override
	public void select(Material material) {
		search(material);
	}

	private void selectMaterial() {
		Material selectedEntity = grid.getSelectedEntity();
		if (selectedEntity == null) {
			GHANotification.alert("record-not-selected");
			return;
		}
		notifyMaterial(selectedEntity);
		hide();
		grid.removeSelectedData();
	}

}
