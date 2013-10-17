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
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

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
 * @author alacret
 * 
 */
public class EIATypeSearchForm extends GHASlideInWindow implements
		EIATypeSelectionListener, EiaTypeSelectionProducer {

	private List<EIATypeSelectionListener> selectionListeners;
	private GHATextItem codeEIAItem, nameEIAItem, modelItem, umdnsCodeItem;
	private EIATypeGrid eiaTypeGrid;
	private GHASelectItem brandItem, mobilityItem, typeItem, subTypeItem;
	private EIATypeAddForm addForm;

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

		eiaTypeGrid = new EIATypeGrid();

		addForm = new EIATypeAddForm();
	}

	/**
	 * 
	 */
	public EIATypeSearchForm() {
		super(1);
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight()-4 + "px");

		GHALabel title = new GHALabel("Buscar un Tipo de Equipo");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeEIAItem, nameEIAItem, brandItem, modelItem,
				umdnsCodeItem, mobilityItem, typeItem, subTypeItem);

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

		codeEIAItem.addKeyUpHandler(searchKeyUpHandler);
		nameEIAItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		modelItem.addKeyUpHandler(searchKeyUpHandler);
		umdnsCodeItem.addKeyUpHandler(searchKeyUpHandler);
		mobilityItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								form.clearValues();
								eiaTypeGrid.setData(new ListGridRecord[0]);
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
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(title, formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		eiaTypeGrid.setHeight(GHAUiHelper.getSubtabGridSize(30));
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectEiaType();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
					}
				}));

		gridLayout.addMembers(eiaTypeGrid, sideGridButtons);

		addMember(gridLayout);
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
		GHAGridRecord<EiaType> selectedRecord = eiaTypeGrid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyEiaType(((EIATypeRecord) selectedRecord).toEntity());
		hide();
	}

	/**
	 * @param eiaType
	 */
	private void notifyEiaType(EiaType eiaType) {
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

	private void search() {
		EiaType eiaType = new EiaType();
		eiaType.setCode(codeEIAItem.getValueAsString());
		eiaType.setName(nameEIAItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (mobilityItem.getValue() != null)
			eiaType.setMobility(EiaMobilityEnum.valueOf(mobilityItem
					.getValueAsString()));
		if (typeItem.getValue() != null)
			eiaType.setType(EiaTypeEnum.valueOf(typeItem.getValueAsString()));
		if (subTypeItem.getValue() != null)
			eiaType.setSubtype(EiaSubTypeEnum.valueOf(subTypeItem
					.getValueAsString()));

		search(eiaType);
	}

	private void search(final EiaType eiaType) {
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> eiaTypes) {
				ListGridRecord[] array = EIATypeUtil.toGridRecords(eiaTypes)
						.toArray(new EIATypeRecord[] {});
				eiaTypeGrid.setData(array);
				if (eiaType != null && eiaType.getCode() != "")
					for (ListGridRecord listGridRecord : eiaTypeGrid
							.getRecords())
						if (((EIATypeRecord) listGridRecord).toEntity()
								.getCode() == eiaType.getCode())
							eiaTypeGrid.selectRecord(listGridRecord);
			}

		});
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
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight()-4 + "px");
	}

	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		selectionListeners.add(eIATypeSelectionListener);

	}

	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		selectionListeners.remove(eIATypeSelectionListener);

	}
}
