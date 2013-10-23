package org.fourgeeks.gha.webclient.client.eia;

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
public class EIASearchForm extends GHASlideInWindow implements
		EIASelectionListener, EiaSelectionProducer {

	private List<EIASelectionListener> listeners;
	private EIAGrid grid;
	private GHATextItem codeItem, fixedAssetIdentifierItem, serialNumberItem;
	private GHASelectItem responsibleRoleItem, eiaTypeItem,
			workingAreaLocationItem, facilityLocationItem, obuItem, stateItem;

	private EIAAddForm addForm;
	{
		listeners = new LinkedList<EIASelectionListener>();

		responsibleRoleItem = new GHASelectItem("Responsable");
		codeItem = new GHATextItem("Código");
		codeItem.setLength(20);
		codeItem.setMask("####################");
		eiaTypeItem = new GHASelectItem("Tipo de equipo");
		fixedAssetIdentifierItem = new GHATextItem("Identificador");
		fixedAssetIdentifierItem.setLength(20);
		workingAreaLocationItem = new GHASelectItem("Área de Trabajo");
		facilityLocationItem = new GHASelectItem("Servicio/Instalación");
		obuItem = new GHASelectItem("Organización");
		serialNumberItem = new GHATextItem("Serial");
		serialNumberItem.setLength(20);
		serialNumberItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		fixedAssetIdentifierItem.setLength(20);
		fixedAssetIdentifierItem.setMask("###################");
		stateItem = new GHASelectItem("Estado");
		grid = new EIAGrid();
		addForm = new EIAAddForm();
		addForm.addEiaSelectionListener(this);
	}

	/**
	 * 
	 */
	public EIASearchForm() {
		super();
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");

		GHAUiHelper.addGHAResizeHandler(this);

		GHALabel title = new GHALabel("Busqueda de Equipos");

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);

		form.setItems(responsibleRoleItem, codeItem, eiaTypeItem,
				fixedAssetIdentifierItem, workingAreaLocationItem,
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
		// actualCostItem.addKeyUpHandler(searchKeyUpHandler);
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
		formLayout.setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
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
						selectEia();
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
		searchForEiaTypes();
		searchForRoleBases();
		searchForLocations();
		searchForObus();
		fillExtras();

		addForm.addEiaSelectionListener(this);
	}

	private void fillExtras() {
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

	private void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	@Override
	public void select(Eia eia) {
		search(eia);
	}

	private void search() {
		Eia eia = new Eia();
		// if (actualCostItem.getValue() != null)
		// eia.setActualCost(new BigDecimal(actualCostItem.getValueAsString()));
		if (responsibleRoleItem.getValue() != null)
			eia.setResponsibleRole(new Role(Long.parseLong(responsibleRoleItem
					.getValueAsString())));
		if (codeItem.getValue() != null)
			eia.setCode(codeItem.getValueAsString());
		if (eiaTypeItem.getValue() != null)
			eia.setEiaType(new EiaType(eiaTypeItem.getValueAsString()));
		if (fixedAssetIdentifierItem.getValue() != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifierItem
					.getValueAsString());
		if (workingAreaLocationItem.getValue() != null)
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationItem.getValueAsString())));
		if (facilityLocationItem.getValue() != null)
			eia.setFacility(new Facility(Integer.valueOf(facilityLocationItem
					.getValueAsString())));
		if (obuItem.getValue() != null)
			eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
		if (serialNumberItem.getValue() != null)
			eia.setSerialNumber(serialNumberItem.getValueAsString());
		if (stateItem.getValue() != null)
			eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
		else
			eia.setState(null);
		search(eia);
	}

	private void search(final Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = EIAUtil.toGridRecords(result).toArray(
						new EIARecord[] {});
				grid.setData(array);
				// TODO: arreglar de acuerdo a usersearchform como lo hizo el
				// señor emilio
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
		GHAUiHelper.removeGHAResizeHandler(this);
		addForm.close();
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
		addForm.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	/**
	 * @param eiaType
	 */
	public void select(EiaType eiaType) {
		if (eiaType != null) {
			eiaTypeItem.setValue(eiaType.getCode());
			eiaTypeItem.disable();
		}

	}

	/**
	 * 
	 */
	private void selectEia() {
		GHAGridRecord<Eia> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.oldAlert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyEia(((EIARecord) selectedRecord).toEntity());
		hide();
	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}
}
