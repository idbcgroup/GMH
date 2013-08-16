
package org.fourgeeks.gha.webclient.client.eiatype;


import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;

/**
 * Esta ventana muestra la imagen en tamaño real
 * @author lcampo
 */
public class PopupShowPicture extends Window {
	private Img picture;
	public PopupShowPicture(String urlPicture)
	{
		picture = new Img(urlPicture);
		picture.setImageType(ImageStyle.NORMAL);
		setAutoSize(true);
        setTitle("Imagen");  
        setShowMinimizeButton(false);  
        setIsModal(true);  
        setShowModalMask(true);  
        setAutoCenter(true);
        addCloseClickHandler(new CloseClickHandler() {  
            public void onCloseClick(CloseClickEvent event) {  
                destroy();  
            }  
        });   
        addItem(picture);  
        show();  
	}
}
