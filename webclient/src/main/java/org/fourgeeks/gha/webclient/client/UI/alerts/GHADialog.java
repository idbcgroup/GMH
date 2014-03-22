package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
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
public abstract class GHADialog extends Dialog implements ResizeHandler,
Window.ScrollHandler {
	/**
	 * @author jfuentes
	 * 
	 */

	// Internal Measures
	private static final int DEFAULT_NOTIFICATION_WIDTH = 280;
	private static final int DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT = 140;
	private static final int DEFAULT_NOTIFICATION_BUTTONS_HEIGHT = 140;

	private final int HEADER_HEIGHT = 25;
	private final int FOOTER_HEIGHT = 18;
	private final int RIGHT_MARGIN = 30;
	private final int BORDER_SEPARATION = 8;

	protected boolean hasButtons;
	protected int dialogHeight;
	protected String dialogType;
	protected boolean isModal;
	protected boolean isTimed;
	protected int waitingTime = -1;

	protected int openedPosition = -1;

	private final Timer waiter = new Timer() {
		@Override
		public void run() {
			close();
		}
	};
	protected final int DEFAULT_ANIMATION_TIME = 300;

	/**
	 * public GHADialog() { } Creates a GHADialog without buttons, with the
	 * specifying parameters.
	 * 
	 * @param type
	 * @param canMinimize
	 * @param time
	 *            TODO
	 */
	public GHADialog(GHAMessageType type, boolean canMinimize, int time) {
		super();

		hasButtons = false;

		initHandlers();
		initialize();
		setOriginalStyle();
		initHeaderControls(canMinimize);
		initFooterControls();
		initByType(type, time);

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
	 * @param canMinimize
	 * @param time
	 *            TODO
	 * @param buttons
	 */
	public GHADialog(GHAMessageType type, boolean canMinimize, int time,
			Button... buttons) {
		super();
		hasButtons = true;
		initHandlers();
		initialize();
		setOriginalStyle();
		initHeaderControls(canMinimize);
		initFooterControls();
		initByType(type, time);

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

	/**
	 * Closes and destroy the window
	 */
	@Override
	public void close() {
		if (openedPosition >= 0) {
			GHAAlertManager.removeOpenMessageFromCounter();
			GHAAlertManager.toggleMessagePosition(openedPosition);
			GHAAlertManager.messageClosedActions();
		}

		animateRect(null, Window.getScrollTop() + Window.getClientHeight(),
				null, null, new AnimationCallback() {
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
			setModalMaskOpacity(15);
		} else if (!hasButtons && !dialogType.equals("PROCESSING")) {
			if (isTimed) {
				waiter.schedule(waitingTime);
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
					waiter.schedule(waitingTime - 1000);
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

	private void initByType(GHAMessageType type, int time) {
		initTypeParameters(type, time);
		confModalTimingSettings();
	}

	/**
	 * 
	 */
	private void initFooterControls() {
		// ---Foooter controls
		setShowFooter(true);

		final Label function = new Label("Función");
		function.setStyleName("windowFooterText");
		final SimpleDateFormat sdf = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss a");
		// Label date = new
		// Label(DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(new
		// Date()));
		final Label date = new Label(sdf.format(new Date()));
		date.setWidth(120);
		date.setStyleName("windowFooterText");
		setFooterControls(function, new LayoutSpacer(), date);
		setFooterHeight(FOOTER_HEIGHT);
	}

	private void initHandlers() {
		GHAUiHelper.addGHAResizeHandler(this);
		GHAUiHelper.addWindowScrollHandler(this);
	}

	/**
	 * @param canMinimize
	 */
	private void initHeaderControls(boolean canMinimize) {
		// ---Header Controls
		setShowHeaderIcon(true);
		setHeaderIcon("../resources/icons/favicon.ico");
		setShowMinimizeButton(canMinimize);
		setShowCloseButton(false);
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
		final Layout bodyAC = new Layout();

		if (hasButtons)
			dialogHeight = DEFAULT_NOTIFICATION_BUTTONS_HEIGHT;
		else
			dialogHeight = DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT;

		setHeight(dialogHeight);
		setMaxHeight(dialogHeight);
		bodyAC.setHeight(dialogHeight - (HEADER_HEIGHT + FOOTER_HEIGHT + BORDER_SEPARATION));
		bodyAC.setMaxHeight(dialogHeight - (HEADER_HEIGHT + FOOTER_HEIGHT + BORDER_SEPARATION));

		// bodyAC.setAlign(VerticalAlignment.CENTER);
		// bodyAC.setAlign(Alignment.CENTER);
		changeAutoChildDefaults("body", bodyAC);
		final Layout msgStack = new Layout();
		msgStack.setStyleName("dialogMessageStack");
		changeAutoChildDefaults("messageStack", msgStack);
	}

	private void initTypeParameters(GHAMessageType type, int time) {
		final int secsToMills = 1000;
		dialogType = type.getCode();
		isTimed = type.isTimed();
		isModal = type.isModal();
		if (time >= 0) {
			waitingTime = time * secsToMills;
		} else {
			waitingTime = type.getTime() * secsToMills;
		}
	}

	protected abstract void initTypeView();

	@Override
	public void onResize(ResizeEvent event) {
		// Window.alert("weight:"+event.getWidth()+"\nheight"+event.getHeight());
		resize(Window.getScrollTop(), Window.getScrollLeft(), event.getWidth(),
				event.getHeight());

	}

	@Override
	public void onWindowScroll(ScrollEvent arg0) {
		// Window.alert("left:"+arg0.getScrollLeft()+"\ntop"+arg0.getScrollTop());
		resize(arg0.getScrollTop(), arg0.getScrollLeft(),
				Window.getClientWidth(), Window.getClientHeight());
	}

	private void resize(int top, int left, int width, int height) {
		if (isVisible()) {
			final int windowWidth = width;
			final int windowHeight = height;

			int multp = 0;
			int rightMargin;
			if (openedPosition < 0) {
				rightMargin = (getWidth() + RIGHT_MARGIN) * 2;
				multp = dialogHeight + BORDER_SEPARATION;
			} else {
				rightMargin = getWidth() + RIGHT_MARGIN;
				multp = (openedPosition + 1) * (dialogHeight + BORDER_SEPARATION);
			}

			setLeft(left + (windowWidth - rightMargin));
			setTop(top + (windowHeight - multp));
		}
	}

	/**
	 * @param dialogType
	 *            The type of dialog to set
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

	/**
	 * 
	 */
	// public void openWindow() {
	//
	// openedPosition = GHAAlertManager.getFreeMessagePosition();
	// if (openedPosition >= 0 || isModal) {
	// if(openedPosition >= 0){
	// GHAAlertManager.toggleMessagePosition(openedPosition);
	// GHAAlertManager.addNewMessageToCounter();
	// }
	//
	// show();
	// // set the height to the available message text space
	// getMessageStack().setHeight(40);
	//
	// final int windowWidth = Window.getClientWidth();
	// final int windowHeight = Window.getClientHeight();
	// int multp=0;
	// if(openedPosition<0){
	// setLeft(Window.getScrollLeft()+(windowWidth - (getWidth() +
	// RIGHT_MARGIN)*2));
	//
	// multp = DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT + BORDER_SEPARATION;
	// if (hasButtons)
	// multp = DEFAULT_NOTIFICATION_BUTTONS_HEIGHT + BORDER_SEPARATION;
	// }else{
	// setLeft(Window.getScrollLeft()+(windowWidth - (getWidth() +
	// RIGHT_MARGIN)));
	//
	// multp = (openedPosition + 1)*(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT +
	// BORDER_SEPARATION);
	// if (hasButtons)
	// multp = (openedPosition + 1)*(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT +
	// BORDER_SEPARATION);
	// }
	// setTop(Window.getScrollTop()+Window.getClientHeight());
	//
	// animateRect(null,Window.getScrollTop()+(windowHeight- multp),null, null);
	// } else {
	// Window.alert("Error. no hay posiciones libres para mostrar alertas");
	// }
	// }

	@Override
	public void show() {
		openedPosition = GHAAlertManager.getFreeMessagePosition();
		if (openedPosition >= 0 || isModal) {
			if (openedPosition >= 0) {
				GHAAlertManager.toggleMessagePosition(openedPosition);
				GHAAlertManager.addNewMessageToCounter();
			}

			super.show();
			// set the height to the available message text space
			getMessageStack().setHeight(40);

			final int windowWidth = Window.getClientWidth();
			final int windowHeight = Window.getClientHeight();

			int multp = 0;
			int rightMargin;
			if (openedPosition < 0) {
				rightMargin = (getWidth() + RIGHT_MARGIN) * 2;
				multp = dialogHeight + BORDER_SEPARATION;
			} else {
				rightMargin = getWidth() + RIGHT_MARGIN;
				multp = (openedPosition + 1) * (dialogHeight + BORDER_SEPARATION);
			}

			setLeft(Window.getScrollLeft()	+ (windowWidth - rightMargin));
			setTop(Window.getScrollTop() + Window.getClientHeight());

			animateRect(null, Window.getScrollTop() + (windowHeight - multp),
					null, null);
		} else {
			// Window.alert("Error. no hay posiciones libres para mostrar alertas");
		}
	}
}
