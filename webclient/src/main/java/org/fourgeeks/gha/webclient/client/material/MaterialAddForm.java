package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaterialAddForm extends GHASlideInWindow implements
		MaterialSelectionProducer, MaterialSelectionListener {

	private MaterialForm materialForm;
	{
		materialForm = new MaterialForm();
	}

	/**
	 * 
	 */
	public MaterialAddForm() {
		super();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		GHALabel title = new GHALabel("Agregar un Material");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						materialForm.save();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(materialForm, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

		// register as listener to the addForm
		materialForm.addMaterialSelectionListener(this);
	}

	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		materialForm.addMaterialSelectionListener(materialSelectionListener);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		materialForm.addMaterialSelectionListener(materialSelectionListener);

	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener
	 * #select(org.fourgeeks.gha.domain.glm.Material)
	 */
	@Override
	public void select(Material material) {
		materialForm.cancel();
		hide();
	}

}
