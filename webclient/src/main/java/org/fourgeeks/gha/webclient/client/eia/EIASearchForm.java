package org.fourgeeks.gha.webclient.client.eia;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
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
public class EIASearchForm extends GHASlideInWindow implements
		EiaSelectionProducer {

	private List<EIASelectionListener> listeners;
	private EIAGrid grid;
	private GHATextItem actualCostItem, codeItem, fixedAssetIdentifierItem,
			serialNumberItem;
	private GHASelectItem responsibleRoleItem, eiaTypeItem,
			workingAreaLocationItem, facilityLocationItem, obuItem, stateItem;

	{
		listeners = new LinkedList<EIASelectionListener>();

		actualCostItem = new GHATextItem("Costo actual");
		responsibleRoleItem = new GHASelectItem("Responsable");
		codeItem = new GHATextItem("Código");
		eiaTypeItem = new GHASelectItem("Tipo de equipo");
		fixedAssetIdentifierItem = new GHATextItem("Identificador");

		workingAreaLocationItem = new GHASelectItem("Área de Trabajo");
		facilityLocationItem = new GHASelectItem("Servicio/Instalación");

		obuItem = new GHASelectItem("Organización");
		serialNumberItem = new GHATextItem("Serial");
		stateItem = new GHASelectItem("Estado");

		grid = new EIAGrid();
	}

	/**
	 * 
	 */
	public EIASearchForm() {
		super(1);
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		Label title = new Label("<h3>Busqueda de Equipos</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);

		form.setItems(actualCostItem, responsibleRoleItem, codeItem,
				eiaTypeItem, fixedAssetIdentifierItem, workingAreaLocationItem,
				facilityLocationItem, obuItem, serialNumberItem, stateItem);

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
		actualCostItem.addKeyUpHandler(searchKeyUpHandler);
		responsibleRoleItem.addKeyUpHandler(searchKeyUpHandler);
		codeItem.addKeyUpHandler(searchKeyUpHandler);
		eiaTypeItem.addKeyUpHandler(searchKeyUpHandler);
		fixedAssetIdentifierItem.addKeyUpHandler(searchKeyUpHandler);
		workingAreaLocationItem.addKeyUpHandler(searchKeyUpHandler);
		facilityLocationItem.addKeyUpHandler(searchKeyUpHandler);
		obuItem.addKeyUpHandler(searchKeyUpHandler);
		serialNumberItem.addKeyUpHandler(searchKeyUpHandler);
		stateItem.addKeyUpHandler(searchKeyUpHandler);

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
						selectEia(((EIARecord) grid.getSelectedRecord())
								.toEntity());
						hide();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);
		searchForEiaTypes();
		searchForRoleBases();
		searchForLocations();
		searchForObus();
		fillExtras();
	}

	private void fillExtras() {
		// state
		stateItem.setValueMap(EiaStateEnum.toValueMap());
	}

	private void searchForEiaTypes() {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (EiaType eiaType : result)
					valueMap.put(eiaType.getCode(), eiaType.getName());
				eiaTypeItem.setValueMap(valueMap);
			}
		}, false);
	}

	private void searchForLocations() {
		GHACache.INSTANCE
				.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
					@Override
					public void onSuccess(List<WorkingArea> result) {
						LinkedHashMap<String, String> valueMapWorkingArea = new LinkedHashMap<String, String>();

						for (WorkingArea entity : result) {
							valueMapWorkingArea.put(entity.getId() + "",
									entity.getName());
						}

						workingAreaLocationItem
								.setValueMap(valueMapWorkingArea);
					}
				});
		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {
			@Override
			public void onSuccess(List<Facility> result) {
				LinkedHashMap<String, String> valueMapFacility = new LinkedHashMap<String, String>();

				for (Facility entity : result) {
					valueMapFacility.put(entity.getId() + "", entity.getName());
				}

				facilityLocationItem.setValueMap(valueMapFacility);
			}
		});
	}

	private void searchForObus() {
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {

			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Obu obu : result)
					valueMap.put(String.valueOf(obu.getId()), obu.getName());
				obuItem.setValueMap(valueMap);
			}
		});
	}

	private void searchForRoleBases() {
		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<Role>>() {

			@Override
			public void onSuccess(List<Role> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Role roleBase : result)
					valueMap.put(String.valueOf(roleBase.getId()),
							roleBase.getName());
				responsibleRoleItem.setValueMap(valueMap);
			}
		});
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	private void selectEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	private void search() {
		Eia eia = new Eia();
		if (actualCostItem.getValue() != null)
			eia.setActualCost(new BigDecimal(actualCostItem.getValueAsString()));
		if (responsibleRoleItem.getValue() != null)
			eia.setResponsibleRole(new Role(Long.parseLong(responsibleRoleItem
					.getValueAsString())));
		eia.setCode(codeItem.getValueAsString());
		if (eiaTypeItem.getValue() != null)
			eia.setEiaType(new EiaType(eiaTypeItem.getValueAsString()));
		eia.setFixedAssetIdentifier(fixedAssetIdentifierItem.getValueAsString());
		if (workingAreaLocationItem.getValue() != null)
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationItem.getValueAsString())));
		if (facilityLocationItem.getValue() != null)
			eia.setFacility(new Facility(Integer.valueOf(facilityLocationItem
					.getValueAsString())));
		if (obuItem.getValue() != null)
			eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
		eia.setSerialNumber(serialNumberItem.getValueAsString());
		if (stateItem.getValue() != null)
			eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
		search(eia);
	}

	private void search(final Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = EIAUtil.toGridRecords(result).toArray(
						new EIARecord[] {});
				grid.setData(array);
				if (eia != null && eia.getId() != 0l)
					for (ListGridRecord listGridRecord : grid.getRecords())
						if (((EIARecord) listGridRecord).toEntity().getId() == eia
								.getId())
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

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}
}
