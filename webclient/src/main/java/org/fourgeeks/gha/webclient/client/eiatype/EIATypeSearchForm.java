package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
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
 * @author alacret, emiliot
 * 
 */
public class EIATypeSearchForm extends GHASearchForm<EiaType> implements EIATypeSelectionListener,
		EiaTypeSelectionProducer {

	private List<EIATypeSelectionListener> selectionListeners;
	private GHATextItem codeEIAItem, nameEIAItem, modelItem, umdnsCodeItem;
	private EIATypeGrid grid;
	private GHASelectItem brandItem, mobilityItem, typeItem, subTypeItem;
	private EIATypeAddForm addForm;
	private GHALabel searchResultsLabel;

	{
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
		codeEIAItem = new GHATextItem("Código");
		codeEIAItem.setLength(20);
		codeEIAItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		nameEIAItem = new GHATextItem("Nombre");
		nameEIAItem.setLength(255);
		brandItem = new GHASelectItem("Marca");
		modelItem = new GHATextItem("Modelo");
		modelItem.setLength(20);
		umdnsCodeItem = new GHATextItem("EIAUMDNS");
		umdnsCodeItem.setLength(16);
		umdnsCodeItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		mobilityItem = new GHASelectItem("Movilidad");
		typeItem = new GHASelectItem("Tipo de Equipo");
		subTypeItem = new GHASelectItem("Subtipo");

		grid = new EIATypeGrid();

		addForm = new EIATypeAddForm();
	}

	/**
	 * 
	 */
	public EIATypeSearchForm(String title) {
		super(title);
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeEIAItem, nameEIAItem, brandItem, modelItem, umdnsCodeItem, mobilityItem,
				typeItem, subTypeItem);

		codeEIAItem.addKeyUpHandler(searchKeyUpHandler);
		nameEIAItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		modelItem.addKeyUpHandler(searchKeyUpHandler);
		umdnsCodeItem.addKeyUpHandler(searchKeyUpHandler);
		mobilityItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(searchClickHandler),
				new GHACleanButton(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						form.clearValues();
						grid.setData(new ListGridRecord[0]);
					}
				}), new GHACancelButton(new ClickHandler() {
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
				GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));

		grid.setHeight(GHAUiHelper.getSubtabGridSize(30));
		HLayout gridLayout = new HLayout();

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHACheckButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectEiaType();
			}
		}));

		gridLayout.addMembers(grid, sideGridButtons);

		searchResultsLabel = new GHALabel(GHAStrings.get("search-results"));

		addMembers(GHAUiHelper.verticalSeparator("10px"), searchResultsLabel, gridLayout);
		fill();

		// register as listener to the addform producer
		addForm.addEiaTypeSelectionListener(this);
	}

	private void fill() {
		typeItem.setValueMap(EiaTypeEnum.toValueMap());
		subTypeItem.setValueMap(EiaSubTypeEnum.toValueMap());
		mobilityItem.setValueMap(EiaMobilityEnum.toValueMap());

		fillBrands(false);
	}

	private void fillBrands(boolean forceFromServer) {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				brandItem.setValueMap(valueMap);

			}
		}, forceFromServer);
	}

	private void selectEiaType() {
		EiaType selectedEntity = grid.getSelectedEntity();
		if (selectedEntity == null) {
			GHANotification.alert("record-not-selected");
			return;
		}
		notifyEiaType(selectedEntity);
		hide();
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : selectionListeners)
			listener.select(eiaType);
	}

	@Override
	public void select(EiaType eiaType) {
		search(eiaType);

		// Reload the Brand Select field, to prevent outdated cached list of
		// brands
		fillBrands(true);
	}

	public void search() {
		EiaType eiaType = new EiaType();
		eiaType.setCode(codeEIAItem.getValueAsString());
		eiaType.setName(nameEIAItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem.getValueAsString()));
		if (typeItem.getValue() != null)
			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem.getValueAsString()));

		search(eiaType);
	}

	private void search(final EiaType eiaType) {
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> eiaTypes) {
				if (blackList != null)
					eiaTypes.removeAll(blackList);

				ListGridRecord[] array = EIATypeUtil.toGridRecords(eiaTypes).toArray(
						new EIATypeRecord[] {});

				mostrarCantResults(eiaTypes);

				grid.setData(array);
				if (eiaType != null && eiaType.getCode() != "")
					for (ListGridRecord listGridRecord : grid.getRecords())
						if (((EIATypeRecord) listGridRecord).toEntity().getCode() == eiaType
								.getCode())
							grid.selectRecord(listGridRecord);
			}
		});
	}

	private void mostrarCantResults(List<?> datos) {
		String tituloSearchResults = GHAStrings.get("search-results");
		searchResultsLabel.setContents(tituloSearchResults + ": " + datos.size() + " resultados");
		searchResultsLabel.redraw();
	}

	@Override
	public void close() {
		destroy();
		addForm.close();
	}

	@Override
	public void hide() {
		super.hide();
		addForm.hide();
	}

	@Override
	public void addEiaTypeSelectionListener(EIATypeSelectionListener eIATypeSelectionListener) {
		selectionListeners.add(eIATypeSelectionListener);

	}

	@Override
	public void removeEiaTypeSelectionListener(EIATypeSelectionListener eIATypeSelectionListener) {
		selectionListeners.remove(eIATypeSelectionListener);

	}

	/**
	 * 
	 */
	public void clean() {
		grid.setData(new EIATypeRecord[] {});
	}
}
