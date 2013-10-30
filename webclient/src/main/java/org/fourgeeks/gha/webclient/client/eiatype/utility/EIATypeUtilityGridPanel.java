package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategorySelectionListener;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeUtilityGridPanel extends GHAVerticalLayout implements
		EIATypeSelectionListener {

	// private MaterialGrid grid = new MaterialGrid();
	private EiaTypeUtilityGrid grid = new EiaTypeUtilityGrid();
	private UtilitySearchForm utilitySearchForm;
	private EiaType eiaType;

	{
		utilitySearchForm = new UtilitySearchForm(
				GHAStrings.get("search-utility-material"));
		utilitySearchForm
				.addMaterialSelectionListener(new MaterialCategorySelectionListener() {

					@Override
					public void select(MaterialCategory material) {
						// TODO Auto-generated method stub
						EiaTypeUtility eiaTypeUtility = new EiaTypeUtility();
						eiaTypeUtility
								.setEiaType(EIATypeUtilityGridPanel.this.eiaType);
						eiaTypeUtility.setMaterialCategory(material);
						EIATypeUtilityModel.save(eiaTypeUtility,
								new GHAAsyncCallback<EiaTypeUtility>() {

									@Override
									public void onSuccess(EiaTypeUtility result) {
										loadData();
									}
								});
					}
				});

	}

	/**
	 * 
	 */
	public EIATypeUtilityGridPanel() {
		super();

		GHALabel title = new GHALabel("Servicios utilitarios");

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						utilitySearchForm.open();
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EiaTypeUtility eiaTypeUtility = grid
								.getSelectedEntity();

						if (eiaTypeUtility == null) {
							GHANotification.alert("record-not-selected");
							return;
						}

						EIATypeUtilityModel.delete(eiaTypeUtility.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loadData();
									}
								});

					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(title, mainPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	@Override
	public void close() {
		utilitySearchForm.animateHide(AnimationEffect.FLY);
		utilitySearchForm.close();
	}

	@Override
	public void hide() {
		utilitySearchForm.animateHide(AnimationEffect.FLY);
	}

	private void loadData() {
		EIATypeUtilityModel.findByEiaType(eiaType,
				new AsyncCallback<List<EiaTypeUtility>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<EiaTypeUtility> result) {
						// TODO Auto-generated method stub
						ListGridRecord[] array = EIATypeUtilityUtil
								.toGridRecords(result).toArray(
										new EIATypeUtilityRecord[] {});
						grid.setData(array);

					}
				});
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

}
