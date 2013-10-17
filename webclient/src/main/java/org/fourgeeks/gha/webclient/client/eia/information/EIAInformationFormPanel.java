package org.fourgeeks.gha.webclient.client.eia.information;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eia.EIAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIAInformationFormPanel extends VLayout implements GHAClosable, GHAHideable,
		EiaSelectionProducer, EIASelectionListener {

	/**
	 * @param eiaEquipmentSubTab
	 * 
	 */
	private EIAForm eiaForm = new EIAForm();
	private Eia firstEia;
	private List<EIASelectionListener> listeners = new ArrayList<EIASelectionListener>();

	/**
	 * 
	 */
	public EIAInformationFormPanel() {
		super();
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		eiaForm.addEiaSelectionListener(this);
		GHALabel title = new GHALabel("Caracteristicas del Equipos");
		
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton("../resources/icons/save.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/undo.png", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// undo();
			}
		}));

		HLayout formPanel = new HLayout();
		formPanel.addMembers(eiaForm, sideButtons);

		addMembers(title, formPanel);

	}

	protected void save() {
		eiaForm.update();
	}

	/**
	 * @param eia
	 */
	public void setEia(Eia eia) {
		this.firstEia = eia;
		eiaForm.setEia(eia);

	}

	@Override
	public void close() {

	}

	@Override
	public void addEiaSelectionListener(EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);

	}

	@Override
	public void removeEiaSelectionListener(EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	@Override
	public void select(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}
}