package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author jfuentes
 * 
 */
public abstract class GHADialog extends Dialog implements ResizeHandler, Window.ScrollHandler {
	/**
	 * @author jfuentes
	 * 
	 */

	//Internal Measures
	private static final int DEFAULT_NOTIFICATION_WIDTH = 280;
	private static final int DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT = 140;
	private static final int DEFAULT_NOTIFICATION_BUTTONS_HEIGHT = 145;

	private final int HEADER_HEIGHT = 20;
	private final int FOOTER_HEIGHT = 15;
	private final int RIGHT_MARGIN = 40;
	private final int BORDER_SEPARATION = 8;

	protected boolean hasButtons;
	protected String dialogType;
	protected boolean isModal;
	protected boolean isTimed;

	protected int openedPosition = -1;

	private final int DEFAULT_WAITING_TIME = 4000;
	private final Timer waiter = new Timer() {
		@Override
		public void run() {
			close();
		}
	};
	protected final int DEFAULT_ANIMATION_TIME = 300;

	/**
	 * Creates a GHADialog without buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param hasCloseButton
	 * @param canMinimize
	 */
	public GHADialog(String type, boolean hasCloseButton,
			boolean canMinimize) {
		super();

		hasButtons = false;

		initHandlers();
		initialize();
		setOriginalStyle();
		initHeaderControls(hasCloseButton, canMinimize);
		initFooterControls();

		// ---Handlers
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});
	}

	/**
	 * Creates a GHADialog with buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param hasCloseButton
	 * @param canMinimize
	 * @param buttons
	 */
	public GHADialog(String type, boolean hasCloseButton,
			boolean canMinimize, Button... buttons) {
		super();


		hasButtons = true;
		initHandlers();
		initialize();
		setOriginalStyle();
		initHeaderControls(hasCloseButton, canMinimize);
		initFooterControls();

		// ---Buttons
		if (hasButtons) {
			setButtons(buttons);
		}
		// ---Handlers
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});
	}

	private void initHandlers() {
		GHAUiHelper.addGHAResizeHandler(this);
		GHAUiHelper.addWindowScrollHandler(this);
	}

	/**
	 * Closes and destroy the window
	 */
	public void close() {
		GHAAlertManager.removeOpenMessageFromCounter();
		GHAAlertManager.toggleMessagePosition(openedPosition);

		animateRect(null, Window.getScrollTop()+Window.getClientHeight(), null, null,
				new AnimationCallback() {
			@Override
			public void execute(boolean earlyFinish) {
				hide();
				destroy();
			}
		}, DEFAULT_ANIMATION_TIME);
	}

	/**
	 * 
	 */
	protected void confModalTimingSettings() {
		// ---Modal & Timing Conf.
		if (isModal) {
			setIsModal(true);
			setShowModalMask(true);
			setModalMaskOpacity(40);
		} else if (!hasButtons && !dialogType.equals("PROCESSING")) {
			if (isTimed) {
				waiter.schedule(DEFAULT_WAITING_TIME);
			}
			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					waiter.cancel();
				}
			});
			addMouseOutHandler(new MouseOutHandler() {
				@Override
				public void onMouseOut(MouseOutEvent event) {
					waiter.schedule(DEFAULT_WAITING_TIME - 1000);
				}
			});
		}
	}

	/**
	 * @return The Dialog Type.
	 */
	public String getDialogType() {
		return dialogType;
	}

	/**
	 * 
	 */
	private void initFooterControls() {
		// ---Foooter controls
		setShowFooter(true);
		Label function = new Label("Función");
		function.setStyleName("windowFooterText");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		// Label date = new
		// Label(DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(new
		// Date()));
		Label date = new Label(sdf.format(new Date()));
		date.setWidth(120);
		date.setStyleName("windowFooterText");
		setFooterControls(function, new LayoutSpacer(), date);
		setFooterHeight(FOOTER_HEIGHT);
	}

	/**
	 * @param hasCloseButton
	 * @param canMinimize
	 */
	private void initHeaderControls(boolean hasCloseButton, boolean canMinimize) {
		// ---Header Controls
		setShowHeaderIcon(true);
		setHeaderIcon("../resources/icons/favicon.ico");
		setShowMinimizeButton(canMinimize);
		setShowCloseButton(hasCloseButton);
		setShowMaximizeButton(false);
	}

	/**
	 * Set the neccesary parameters for the dialog to work
	 */
	private void initialize() {
		// ---Init
		setAutoSize(false);
		setAutoCenter(false);
		setWidth(DEFAULT_NOTIFICATION_WIDTH);
		Layout bodyAC = new Layout();
		if (hasButtons) {
			setHeight(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			setMaxHeight(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			bodyAC.setHeight(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT - (HEADER_HEIGHT + FOOTER_HEIGHT));

		}else{
			setHeight(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			setMaxHeight(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			bodyAC.setHeight(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT - (HEADER_HEIGHT + FOOTER_HEIGHT));
		}
		//		bodyAC.setAlign(VerticalAlignment.CENTER);
		//		bodyAC.setAlign(Alignment.CENTER);
		changeAutoChildDefaults("body", bodyAC);
		Layout msgStack = new Layout();
		msgStack.setStyleName("dialogMessageStack");
		changeAutoChildDefaults("messageStack", msgStack);
	}

	protected abstract void initTypeParams();

	@Override
	public void onResize(ResizeEvent event) {
		//		Window.alert("weight:"+event.getWidth()+"\nheight"+event.getHeight());
		resize(Window.getScrollTop(),Window.getScrollLeft(),event.getWidth(),event.getHeight());
	}

	@Override
	public void onWindowScroll(ScrollEvent arg0) {
		//		Window.alert("left:"+arg0.getScrollLeft()+"\ntop"+arg0.getScrollTop());
		resize(arg0.getScrollTop(),arg0.getScrollLeft(),Window.getClientWidth(),Window.getClientHeight());
	}

	private void resize(int top, int left, int width, int height) {
		if (isVisible()) {
			final int windowWidth = width;
			final int windowHeight = height;
			//			final int windowWidth = Window.getClientWidth() > GHAUiHelper.MIN_WIDTH ? Window.getClientWidth() : GHAUiHelper.MIN_WIDTH;
			//			final int windowHeight = Window.getClientHeight() > GHAUiHelper.MIN_HEIGHT ? Window.getClientHeight() : GHAUiHelper.MIN_HEIGHT;


			int multp = (openedPosition + 1) * (DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT + BORDER_SEPARATION);
			if (hasButtons)
				multp = (openedPosition + 1) * (DEFAULT_NOTIFICATION_BUTTONS_HEIGHT + BORDER_SEPARATION);

			setLeft(left + (windowWidth - (getWidth() + RIGHT_MARGIN)));
			setTop(top + (windowHeight - multp));
		}
	}

	/**
	 * @param dialogType The type of dialog to set
	 */
	public void setDialogType(String dialogType) {
		this.dialogType = dialogType;
	}


	/**
	 * 
	 */
	private void setOriginalStyle() {
		setHeaderStyle("dialogHeaderStyle");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");
		setMessageStyle("dialogMessageStyle");
	}

	@Override
	public void show() {
		super.show();

		openedPosition = GHAAlertManager.getFreeMessagePosition();
		GHAAlertManager.toggleMessagePosition(openedPosition);
		GHAAlertManager.addNewMessageToCounter();
		// set the height to the available message text space
		getMessageStack().setHeight(40);

		if(openedPosition >= 0){
			final int windowWidth = Window.getClientWidth();
			final int windowHeight = Window.getClientHeight();
			//			final int windowWidth = Window.getClientWidth() > GHAUiHelper.MIN_WIDTH ? Window.getClientWidth() : GHAUiHelper.MIN_WIDTH;
			//			final int windowHeight = Window.getClientHeight() > GHAUiHelper.MIN_HEIGHT ? Window.getClientHeight() : GHAUiHelper.MIN_HEIGHT;

			setLeft(Window.getScrollLeft()+(windowWidth - (getWidth() + RIGHT_MARGIN)));
			setTop(Window.getScrollTop()+Window.getClientHeight());

			int multp = (openedPosition + 1)*(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT + BORDER_SEPARATION);
			if (hasButtons)
				multp = (openedPosition + 1)*(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT + BORDER_SEPARATION);

			animateRect(null,Window.getScrollTop()+(windowHeight- multp),null, null);
		}else{
			Window.alert("Error. no hay posiciones libres para mostrar alertas");
		}
	}
}
