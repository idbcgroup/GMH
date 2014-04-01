package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAUpdateForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author vivi.torresg, emiliot Update Eia Form
 * 
 */
public class EIAUpdateForm extends GHAUpdateForm<Eia> implements
		EIATypeSelectionListener, EiaSelectionProducer, EIASelectionListener {

	/**
	 * @param eiaType
	 * @param title
	 * 
	 */
	public EIAUpdateForm(EiaType eiaType, String title) {
		super(title);
		form = new EIAForm();
		((EIATypeSelectionListener) form).select(eiaType);
		initComponent();
	}

	/**
	 * @param title
	 * 
	 */
	public EIAUpdateForm(String title) {
		super(title);
		form = new EIAForm();
		initComponent();
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		((EiaSelectionProducer) form)
				.addEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) { // TODO
		if (form.hasUnCommittedChanges()) {
			GHAAlertManager.confirm("unsaved-changes", new BooleanCallback() {

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
	public boolean canBeHidden(HideCloseAction hideAction) { // TODO
		if (form.hasUnCommittedChanges()) {
			GHAAlertManager.confirm("unsaved-changes", new BooleanCallback() {

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
			GHAAlertManager.confirm("unsaved-changes", new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					if (value) {
						form.undo();
						form.hide();
						EIAUpdateForm.super.hide();
					}
				}
			});
			return;
		}

		form.hide();
		super.hide();
	}

	/**
	 * 
	 */
	private void initComponent() {
		((EiaSelectionProducer) form).addEiaSelectionListener(this);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						update();
					}
				}), new GHACloseButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cancel();
			}
		}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMember(form);
		mainLayout.addMembers(new LayoutSpacer(), sideButtons);
		addMember(mainLayout);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer#notifyEia
	 * (org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void notifyEia(Eia eia) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow#open
	 * ()
	 */
	@Override
	public void open() {
		super.open();
		form.show();
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		((EiaSelectionProducer) form)
				.removeEiaSelectionListener(eiaSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		form.clear();
		hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		((EIATypeSelectionListener) form).select(eiaType);
	}

	/**
	 * @param eia
	 */
	public void setEia(Eia eia) {
		form.set(eia);
	}

	@Override
	protected void update() {
		form.update(new GHAAsyncCallback<Eia>() {

			@Override
			public void onSuccess(Eia result) {
				hide();
				GHAAlertManager.alert("eiatype-save-success");
			}
		});
	}
}
