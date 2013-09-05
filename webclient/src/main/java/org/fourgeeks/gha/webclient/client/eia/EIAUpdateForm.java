package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author vivi.torresg Update Eia Form
 * 
 */
public class EIAUpdateForm extends GHASlideInWindow implements
		EIATypeSelectionListener, EiaSelectionProducer {
	EIAForm eiaForm;

	/**
	 * 
	 */
	public EIAUpdateForm() {
		super(1);
		eiaForm = new EIAForm();
		eiaForm.setEiaUpdateForm(this);
		initComponent();
	}

	/**
	 * 
	 */
	private void initComponent() {
		GHAUiHelper.addGHAResizeHandler(this);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		Label title = new Label("<h3>Actualizar equipo</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						eiaForm.update();
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMember(eiaForm);
		mainLayout.addMembers(new LayoutSpacer(), sideButtons);
		addMember(mainLayout);

	}

	/**
	 * @param eiaType
	 * 
	 */
	public EIAUpdateForm(EiaType eiaType) {
		super(1);
		eiaForm = new EIAForm(eiaType);
		initComponent();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
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
		eiaForm.select(eiaType);

	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		eiaForm.addEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		eiaForm.removeEiaSelectionListener(eiaSelectionListener);
	}

	@Override
	public void hide() {
		super.hide();
		this.close();
	}

	@Override
	public void close() {
		super.close();
	}

	public void setEia(Eia eia) {
		eiaForm.setEia(eia);
	}
}
