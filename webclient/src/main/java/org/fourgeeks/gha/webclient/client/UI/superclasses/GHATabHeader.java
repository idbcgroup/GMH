package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
public class GHATabHeader extends HLayout implements ResizeHandler{
 
	private OptionButton titulo;

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
		
		titulo = new OptionButton(150,false,"","");
		addMember(titulo);

		addMember(new LayoutSpacer());
		OptionButton buscar = new OptionButton("Buscar...",90, true, "../resources/img/buscarButton.png","../resources/img/buscarButtonOver.png");
		OptionButton agregar = new OptionButton("Agregar...",90, true, "../resources/img/agregarButton.png","../resources/img/agregarButtonOver.png");
		OptionButton limpiar = new OptionButton("Limpiar",90, true, "../resources/img/limpiarButton.png","../resources/img/limpiarButtonOver.png");
		OptionButton cerrar = new OptionButton("Cerrar",90, true, "../resources/img/cerrarButton.png","../resources/img/cerrarButtonOver.png");
		
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
		public OptionButton(int width, boolean hoverable, final String bgSrc, final String bgSrcOver) {
			super();
			setStyleName("tab-header-title");
			setWidth(width+"px");
			setHeight("30px");
			if (hoverable) {
				setStyleName(getStyleName()+" button-pointer");
				setBackgroundImage(bgSrc);
				setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);
				
				addMouseOverHandler(new MouseOverHandler() {
					@Override
					public void onMouseOver(MouseOverEvent event) {
						setBackgroundImage(bgSrcOver);
					}
				});
				addMouseOutHandler(new MouseOutHandler() {
					
					@Override
					public void onMouseOut(MouseOutEvent event) {
						setBackgroundImage(bgSrc);
					}
				});
			}
		}

		public OptionButton(String text, int width, boolean hoverable, String bg, String bgOver) {
			this(width,hoverable,bg,bgOver);
			setContents(text);
		}

	}

	@Override
	public void onResize(ResizeEvent event) {
		setWidth(Window.getClientWidth()-35);
	}
}
