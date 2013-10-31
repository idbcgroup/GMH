package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASaveButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAAddForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot Add Eia Form
 * 
 */
public class EIAAddForm extends GHAAddForm implements EIATypeSelectionListener,
		EiaSelectionProducer {
	private EIAForm form;

	{
		form = new EIAForm();
	}

	/**
	 * @param title
	 * 
	 */
	public EIAAddForm(String title) {
		super(title);
		initComponent();
	}

	/**
	 * @param eiaType
	 * @param title
	 * 
	 */
	public EIAAddForm(EiaType eiaType, String title) {
		super(title);
		form = new EIAForm();
		form.select(eiaType);
		initComponent();
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		form.addEiaSelectionListener(eiaSelectionListener);

	}

	@Override
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	/**
	 * 
	 */
	private void initComponent() {
		VLayout sideButtons = GHAUiHelper.createBar(new GHASaveButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHACloseButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, new LayoutSpacer(), sideButtons);
		addMember(gridPanel);

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
		form.notifyEia(eia);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		form.removeEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	public void hide() {
		form.hide();
		super.hide();
	}

	/**
	 * 
	 */
	private void save() {
		form.save(new GHAAsyncCallback<Eia>() {

			@Override
			public void onSuccess(Eia result) {
				GHANotification.alert("eia-save-success");
				hide();
			}
		});

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
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}

}
