package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;

public class GHADynamicForm extends DynamicForm implements ResizeHandler{
	
	public GHADynamicForm(int formWidth, int numCols) {
		initComponents(formWidth, numCols);
	}

	public GHADynamicForm(JavaScriptObject jsObj, int formWidth, int numCols) {
		super(jsObj);
		initComponents(formWidth, numCols);
	}
	
	public void initComponents(int fWidth, int numCols){
		setWidth(fWidth);
		setNumCols(numCols);
		setMinColWidth(50);
		
		int itemW = fWidth/numCols;
		
//		int widths[] = new int[numCols];
//		Window.alert(widths.length+"");
//		for(int i=0;i<widths.length; i++){
//			widths[i]=itemW;
//			Window.alert(widths[i]+"");
//		}
		
		
		setColWidths(itemW);
		setTitleOrientation(TitleOrientation.TOP);
		setWrapItemTitles(false);

//		setCanDragResize(true);
//		setShowEdges(true);
	
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		
	}	
}
