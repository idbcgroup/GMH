package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class MaterialAddForm extends GHAAddForm<Material> implements
		MaterialSelectionProducer {
	protected MaterialForm form;
	{
		form = new MaterialForm();
	}

	/**
	 * @param title
	 */
	public MaterialAddForm(String title) {
		super(title);
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACancelButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));
		HLayout gridPanel = new HLayout();
		// gridPanel.setAlign(VerticalAlignment.TOP);
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionProducer
	 * #addMaterialSelectionListener
	 * (org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener)
	 */
	@Override
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		form.addMaterialSelectionListener(materialSelectionListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {// TODO
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {// TODO
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
							}
						}
					});
			return false;
		}
		return true;
	}

	@Override
	public void hide() {
		if (form.hasUnCommittedChanges()) {
			GHANotification.confirm(GHAStrings.get("information"),
					GHAStrings.get("unsaved-changes"), new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								form.undo();
								form.hide();
								MaterialAddForm.super.hide();
							}
						}
					});
			return;
		}
		form.hide();
		super.hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionProducer
	 * #notifyMaterial(org.fourgeeks.gha.domain.glm.Material)
	 */
	@Override
	public void notifyMaterial(Material material) {
		form.notifyMaterial(material);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void open() {
		super.open();
		form.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.material.MaterialSelectionProducer
	 * #removeMaterialSelectionListener
	 * (org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener)
	 */
	@Override
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener) {
		form.addMaterialSelectionListener(materialSelectionListener);

	}

	private void save() {
		form.save(new GHAAsyncCallback<Material>() {

			@Override
			public void onSuccess(Material arg0) {
				GHANotification.alert("material-save-success");
				form.cancel();
				hide();
			}

		});
	}

}
