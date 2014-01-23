package org.fourgeeks.gha.webclient.client.UI.alerts.modal;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;



/**
 * @author jfuentes
 *
 */
public class GHAAskDialog extends GHADialog {

	/**
	 * @param ghaMessage
	 * @param buttons 
	 */
	public GHAAskDialog(GHAMessage ghaMessage, Button... buttons){
		//TODO: decodificador por el GHAMessage
		super(DialogType.ASKYESNO,false,false,buttons);
	}

	/**
	 * @param message 
	 * @param buttons 
	 */
	public GHAAskDialog(String message, Button... buttons) {
		super(DialogType.ASKYESNO,false,false,buttons);
		setMessage(message);
	}

	/**
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAAskDialog(String title, String message, Button... buttons){
		this(message, buttons);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeParams() {
		// TODO Auto-generated method stub

	}
}
