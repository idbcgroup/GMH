package org.fourgeeks.gha.webclient.client.UI.alerts;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;

public class GHADialog extends Dialog {

	public GHADialog() {
		super();
		setWidth(GHAUiHelper.DEFAULT_NOTIFICATION_WIDTH);
		
		setOverflow(Overflow.AUTO);
		setAutoCenter(false);
		setShowMinimizeButton(false);
		setShowMaximizeButton(false);
		setAnimateTime(400);
		
		setBorder("1px solid #666666");
		setBackgroundColor("#666666");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");
		
		show();
		setLeft(Window.getClientWidth()-(getWidth()+20));
		setTop(Window.getClientHeight());
		
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				animateRect(null, Window.getClientHeight(), null, null, new AnimationCallback() {
					@Override
					public void execute(boolean earlyFinish) {
						hide();							
					}
				},400);
			}
		});
	}


}
