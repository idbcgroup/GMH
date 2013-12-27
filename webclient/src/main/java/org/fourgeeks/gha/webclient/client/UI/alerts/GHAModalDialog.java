package org.fourgeeks.gha.webclient.client.UI.alerts;

import com.smartgwt.client.widgets.Button;



/**
 * @author jfuentes
 *
 */
public class GHAModalDialog extends GHADialog {

	private boolean isMasked;
	/**
	 * @param type 
	 * @param masked 
	 * @param buttons 
	 * 
	 */
	public GHAModalDialog(DialogType type, boolean masked, Button... buttons) {
		super(true);
		this.dialogType = type;
		this.isMasked = masked;
		setDialogIcon();
		
		setIsModal(true);
		setShowModalMask(true);
		if (isMasked){
			setModalMaskOpacity(40);
		}else{
			setModalMaskOpacity(15);

		}
		
		setButtons(buttons);
	}

	/**
	 * @param type 
	 * @param masked 
	 * @param message
	 * @param buttons 
	 */
	public GHAModalDialog(DialogType type, boolean masked, String message, Button... buttons) {
		this(type,masked,buttons);
		setMessage(message);
		show();
	}

	/**
	 * @param type 
	 * @param masked 
	 * @param title
	 * @param message
	 * @param buttons 
	 */
	public GHAModalDialog(DialogType type, boolean masked, String title, String message, Button... buttons) {
		this(type,masked,message,buttons);
		setTitle(title);
		show();
	}
}
