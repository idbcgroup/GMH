package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

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

public class GHASectionForm extends HLayout {

	private VLayout options;
	private VLayout mainSection;
	private List<Option> optionList;
	// private List<Layout> sectionList;
	{
		optionList = new ArrayList<Option>();
		// sectionList = new ArrayList<Layout>();
		options = new VLayout();
		options.setWidth(150);
		options.setMembersMargin(3);
		options.addStyleName("margin-right");
		mainSection = new VLayout();
	}

	public GHASectionForm() {
		addStyleName("padding-top");
		setWidth100();
		addMember(options);
		addMember(GHAUiHelper.horizontalGraySeparator("10px"));
		addMember(mainSection);
	}

	public void addSection(String name, final Canvas sect, boolean open) {
		HLayout section = new HLayout();
		section.addMembers(sect,new LayoutSpacer());
		
		mainSection.addMembers(section);
		section.setVisibility(Visibility.HIDDEN);
		
		final Option option = new Option(name, section);
		option.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {

			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				for (Option option : optionList)
					option.deactivate();
				option.activate();
				// for (Layout section : sectionList)
				// section.setVisibility(Visibility.HIDDEN);
				// section.setVisibility(Visibility.VISIBLE);

			}
		});
		options.addMember(option);

		if(open)
			option.activate();
		
		optionList.add(option);
		// sectionList.add(section);
	}
	
	public void addSectionSeparator() {
		options.addMember(GHAUiHelper.verticalGraySeparator("2px"));
	}

	public void deactivate() {
		for (Option option : optionList)
			option.deactivate();
	}
	
//	public void activateById(int id) {
//			optionList.get(id).activate();
//	}

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
}
