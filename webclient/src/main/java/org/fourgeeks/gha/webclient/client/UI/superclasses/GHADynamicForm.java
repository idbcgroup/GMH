package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;

/**
 * @author alacret
 * 
 */
public class GHADynamicForm extends DynamicForm {

	public GHADynamicForm(int formWidth, int numCols) {
		initComponents(formWidth, numCols);
	}

	public void initComponents(int fWidth, int numCols) {
		setWidth(fWidth);
		setNumCols(numCols);
		setMinColWidth(50);

		int itemW = fWidth / numCols;

		// int widths[] = new int[numCols];
		// Window.alert(widths.length+"");
		// for(int i=0;i<widths.length; i++){
		// widths[i]=itemW;
		// Window.alert(widths[i]+"");
		// }

		setColWidths(itemW);
		setTitleOrientation(TitleOrientation.TOP);
		setWrapItemTitles(false);

		// setCanDragResize(true);
		// setShowEdges(true);

	}

	public void resize(int formWidth, int numCols) {
		setWidth(formWidth);
		int itemW = formWidth / numCols;
		setColWidths(itemW);
	}
}
