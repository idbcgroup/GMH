package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret, jfuentes
 * 
 */
public class GHATabHeader extends HLayout implements ResizeHandler{
 
	private OptionButton titulo;
	private List<OptionButton> selectables = new LinkedList<OptionButton>();

	// private GHATab tab;

	/**
	 * @param tab
	 */
	public GHATabHeader(GHATab tab) {
		// this.tab = tab;
		GHAUiHelper.addGHAResizeHandler(this);
		
		setWidth(Window.getClientWidth()-35);
		setHeight(30);
		setDefaultLayoutAlign(VerticalAlignment.TOP);
		setMembersMargin(6);
		
		setStyleName("sides-padding tab-header");
		
		titulo = new OptionButton(this,150,false, false,"","");
		addMember(titulo);

		addMember(new LayoutSpacer());
		//selectables
		OptionButton buscar = new OptionButton(this, "Buscar...",90, true, true, "../resources/img/buscarButton.png","../resources/img/buscarButtonOver.png");
		selectables.add(buscar);
		OptionButton agregar = new OptionButton(this, "Agregar...",90, true, true, "../resources/img/agregarButton.png","../resources/img/agregarButtonOver.png");
		selectables.add(agregar);
		
		//Not selectables
		OptionButton limpiar = new OptionButton(this, "Limpiar",90, true, false, "../resources/img/limpiarButton.png","../resources/img/limpiarButtonOver.png");
		OptionButton cerrar = new OptionButton(this, "Cerrar",90, true, false, "../resources/img/cerrarButton.png","../resources/img/cerrarButtonOver.png");
				
		addMembers(buscar,agregar,limpiar,cerrar);
	}

	public void setTitle(String title) {
		titulo.setContents(title);
	}

	@Override
	public String getTitle() {
		return titulo.getContents();
	}

	private static class OptionButton extends Label {
		
		private boolean selected;
		private GHATabHeader parent;
		private String bgImgSrc;
		private String bgImgSrcOver;
		
		public OptionButton(GHATabHeader p, int width, boolean hoverable, boolean selectable, final String bgSrc, final String bgSrcOver) {
			super();
			this.parent=p;
			bgImgSrc=bgSrc;
			bgImgSrcOver=bgSrcOver;
			setStyleName("tab-header-title");
			setWidth(width+"px");
			setHeight("30px");
			
			if (selectable){
				addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//Selected Style
						for(OptionButton button :parent.selectables){
							button.selectButton(false, button.bgImgSrc, button.bgImgSrcOver);
						}
						selectButton(true, bgImgSrc, bgImgSrcOver);
						//
						//TODO: Funcionalidad de cada boton
					}
				});
			}
			
			if (hoverable) {
				selectButton(false, bgImgSrc, bgImgSrcOver);
				setStyleName(getStyleName()+" button-pointer");
				setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);
				
				addMouseOverHandler(new MouseOverHandler() {
					@Override
					public void onMouseOver(MouseOverEvent event) {
						if(!isSelectedButton())
							setBackgroundImage(bgImgSrcOver);
					}
				});
				addMouseOutHandler(new MouseOutHandler() {

					@Override
					public void onMouseOut(MouseOutEvent event) {
						if(!isSelectedButton())
							setBackgroundImage(bgImgSrc);
					}
				});
			}
		}

		public OptionButton(GHATabHeader p, String text, int width, boolean hoverable, boolean selectable, String bg, String bgOver) {
			this(p,width,hoverable,selectable,bg,bgOver);
			setContents(text);
		}
		
		public void selectButton(boolean sel, String bgSrc, String bgSrcOver){
			selected = sel;
			if(sel){
				setBackgroundImage(bgImgSrcOver);
			}else{
				setBackgroundImage(bgImgSrc);
			}
		}
		
		public boolean isSelectedButton(){
			return selected;
		}
	}

	@Override
	public void onResize(ResizeEvent event) {
		setWidth(Window.getClientWidth()-35);
	}
}
