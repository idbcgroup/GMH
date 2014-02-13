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

	static class Option extends HTML {
		private Canvas section;

		public Option(String name, Canvas section) {
			super(name);
			this.section = section;
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
			addStyleName("side-option-selected");
			section.setVisibility(Visibility.VISIBLE);

		}

		public void deactivate() {
			removeStyleName("side-option-selected");
			section.setVisibility(Visibility.HIDDEN);
		}

		/**
		 * @return the section
		 */
		public Canvas getSection() {
			return section;
		}

	}
	private VLayout options;
	private VLayout mainSection;
	private Option selectedOption;
	private List<Option> optionList;

	{
		optionList = new ArrayList<Option>();
		options = new VLayout();
		options.setWidth(GHAUiHelper.SECTION_FORM_OPTION_WIDTH);
		options.setMembersMargin(3);
		options.setStyleName("margin-right");
		mainSection = new VLayout();
		selectedOption = null;
	}

	/**
	 * 
	 */
	public GHASectionForm() {
		addStyleName("padding-top");
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setMembersMargin(10);
		addMember(options);
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
		HLayout section = new HLayout();
		// Window.alert("2X");
		section.addMembers(sect, new LayoutSpacer());
		// Window.alert("3X");

		mainSection.addMembers(section);
		section.setVisibility(Visibility.HIDDEN);

		final Option option = new Option(name, section);
		option.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				for (Option option : optionList)
					option.deactivate();
				option.activate();
				selectedOption = option;
			}
		});
		options.addMember(option);
		optionList.add(option);
	}

	/**
	 * @param name
	 * @param sect
	 * @param open
	 */
	@Deprecated
	public void addSection(String name, final Canvas sect, boolean open) {
		HLayout section = new HLayout();
		section.addMembers(sect, new LayoutSpacer());

		mainSection.addMembers(section);
		section.setVisibility(Visibility.HIDDEN);

		final Option option = new Option(name, section);
		option.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {

			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				for (Option option : optionList)
					option.deactivate();
				option.activate();
				selectedOption = option;
			}
		});
		options.addMember(option);

		if (open)
			option.activate();

		optionList.add(option);
	}

	/**
	 * 
	 */
	public void addSectionSeparator() {
		options.addMember(GHAUiHelper.verticalGraySeparator("2px"));
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
		for (Option option : optionList)
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
		Option option = optionList.get(0);
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
}
