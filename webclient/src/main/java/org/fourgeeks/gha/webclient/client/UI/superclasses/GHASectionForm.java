package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHASectionForm extends HLayout implements HideableListener,
ClosableListener {

	private VLayout sideOptions;
	private VLayout mainSection;
	private SectionFormSideLabel selectedOption;
	private List<SectionFormSideLabel> optionLabelList;

	{
		optionLabelList = new ArrayList<SectionFormSideLabel>();
		sideOptions = new VLayout();
		sideOptions.setWidth(GHAUiHelper.SECTION_FORM_OPTION_WIDTH);
		sideOptions.setMembersMargin(3);
		sideOptions.setStyleName("margin-right");
		mainSection = new VLayout();
		selectedOption = null;
	}

	/**
	 * @param sideOptionsTitle TODO
	 * 
	 */
	public GHASectionForm(String sideOptionsTitle) {
		addStyleName("padding-top");

		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setMembersMargin(10);


		sideOptions.addMember(new SectionFormSideLabel(sideOptionsTitle));
		sideOptions.addMember(GHAUiHelper.verticalGraySeparator("2px"));
		addMember(sideOptions);
		addMember(GHAUiHelper.horizontalGraySeparator("3px"));
		addMember(mainSection);
	}

	/**
	 * Adds a new section
	 * 
	 * @param name
	 * @param sect
	 */
	public void addSection(String name, final Canvas sect) {
		final HLayout section = new HLayout();
		// Window.alert("2X");
		section.addMembers(sect, new LayoutSpacer());
		// Window.alert("3X");

		mainSection.addMembers(section);
		section.setVisibility(Visibility.HIDDEN);

		final SectionFormSideLabel option = new SectionFormSideLabel(name, section);
		option.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				for (final SectionFormSideLabel option : optionLabelList)
					option.deactivate();
				option.activate();
				selectedOption = option;
			}
		});
		sideOptions.addMember(option);
		optionLabelList.add(option);
	}

	/**
	 * @param name
	 * @param sect
	 * @param open
	 */
	@Deprecated
	public void addSection(String name, final Canvas sect, boolean open) {
		final HLayout section = new HLayout();
		section.addMembers(sect, new LayoutSpacer());

		mainSection.addMembers(section);
		section.setVisibility(Visibility.HIDDEN);

		final SectionFormSideLabel option = new SectionFormSideLabel(name, section);
		option.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {

			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				for (final SectionFormSideLabel option : optionLabelList)
					option.deactivate();
				option.activate();
				selectedOption = option;
			}
		});
		sideOptions.addMember(option);

		if (open)
			option.activate();

		optionLabelList.add(option);
	}

	/**
	 * 
	 */
	public void addSectionSeparator() {
		sideOptions.addMember(GHAUiHelper.verticalGraySeparator("1px"));
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() {
		deactivate();
	}

	/**
	 * 
	 */
	public void deactivate() {
		for (final SectionFormSideLabel option : optionLabelList)
			option.deactivate();
	}

	/**
	 * @return Seccion de la opcion seleccionada o null si no se selecciono
	 *         ninguna opcion
	 */
	public Canvas getSelectedOptionForm() {
		if (selectedOption != null)
			return selectedOption.getSection().getChildren()[0];
		return null;
	}

	@Override
	public void hide() {
		deactivate();
		super.hide();
	}

	/**
	 * Open the first section
	 */
	public void openFirst() {
		final SectionFormSideLabel option = optionLabelList.get(2);
		if (option != null) {
			option.activate();
			selectedOption = option;
		}
	}

	/**
	 * 
	 */
	public void openSelectedSection() {
		if (selectedOption != null)
			selectedOption.activate();
		else
			openFirst();
	}

	@Override
	public void show() {
		openSelectedSection();
		super.show();
	}

	static class SectionFormSideLabel extends HTML {
		private Canvas section=null;
		private boolean selectable=false;

		public SectionFormSideLabel(String name){
			super(name);
			setStylePrimaryName("side-option-main");
			setHeight("30px");
		}

		public SectionFormSideLabel(String name, Canvas section) {
			super(name);
			this.section = section;
			selectable=true;

			setStylePrimaryName("side-option");
			setHeight("15px");

			addMouseOverHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(MouseOverEvent event) {
					addStyleName("side-option-over");
				}
			});
			addMouseOutHandler(new MouseOutHandler() {

				@Override
				public void onMouseOut(MouseOutEvent event) {
					removeStyleName("side-option-over");
				}
			});
		}

		public void activate() {
			if(selectable){
				addStyleName("side-option-selected");
				section.setVisibility(Visibility.VISIBLE);
			}
		}

		public void deactivate() {
			if(selectable){
				removeStyleName("side-option-selected");
				section.setVisibility(Visibility.HIDDEN);
			}
		}

		/**
		 * @return the section
		 */
		public Canvas getSection() {
			return section;
		}

	}
}
