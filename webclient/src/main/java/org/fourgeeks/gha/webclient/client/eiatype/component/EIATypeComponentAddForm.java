package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;

public class EIATypeComponentAddForm extends GHASlideInWindow {

	private List<EIATypeComponentSelectionListener> listeners;
	
	{
		listeners = new ArrayList<EIATypeComponentSelectionListener>();
	}
	
	protected void cancel() {
		animateHide(AnimationEffect.SLIDE);
	}
	
	private void save() {
		final EiaTypeComponent eiaTypeComponent = new EiaTypeComponent();
		EIATypeComponentModel.save(eiaTypeComponent, new GHAAsyncCallback<EiaTypeComponent>() {
			
			@Override
			public void onSuccess(EiaTypeComponent result) {
				select(result);
				cancel();
			}
		});
	}
	
	protected void select(EiaTypeComponent eiaTypeComponent) {
		for (EIATypeComponentSelectionListener listener : listeners)
			listener.select(eiaTypeComponent);
	}
	
	public void addEiaTypeComponentSelectionListener(
			EIATypeComponentSelectionListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void close() {
		destroy();
	}

}
