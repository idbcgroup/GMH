package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACloseButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
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
public class EIAAddForm extends GHASlideInWindow implements
		EIATypeSelectionListener, EiaSelectionProducer, EIASelectionListener {
	private EIAForm form;

	{
		form = new EIAForm();
	}

	/**
	 * 
	 */
	public EIAAddForm() {
		super();
		initComponent();
	}

	/**
	 * @param eiaType
	 * 
	 */
	public EIAAddForm(EiaType eiaType) {
		super();
		form = new EIAForm(eiaType);
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
		setWidth100();
		setHeight(GHAUiHelper.getTabHeight());
		addMember(new GHALabel(GHAStrings.get("new-eia")));

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

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

		// register as listener to the eiaform
		form.addEiaSelectionListener(this);
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

	/**
	 * 
	 */
	private void save() {
		form.save();
		hide();
	}

	@Override
	public void select(Eia eia) {

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
