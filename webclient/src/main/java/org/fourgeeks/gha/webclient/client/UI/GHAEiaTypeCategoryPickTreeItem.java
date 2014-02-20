/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAIPickTreeItem;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * @author emiliot
 * 
 */
public class GHAEiaTypeCategoryPickTreeItem extends GHAIPickTreeItem {

	/**
	 * 
	 */
	public GHAEiaTypeCategoryPickTreeItem() {
		super();
		setCanSelectParentItems(true);
		setDisplayField("categoryName");
		setValueField("categoryCode");
		fill();
	}

	/**
	 * @param name
	 */
	public GHAEiaTypeCategoryPickTreeItem(String name) {
		this();
		this.setName(name);
	}

	/**
	 * @param name
	 * @param title
	 */
	public GHAEiaTypeCategoryPickTreeItem(String name, String title) {
		this();
		this.setName(name);
		this.setTitle(title);
	}

	/**
	 * 
	 */
	private void fill() {
		final Tree tree = new Tree();
		GHACache.INSTANCE
				.getEiaTypeCategories(new GHAAsyncCallback<List<EiaTypeCategory>>() {

					@Override
					public void onSuccess(List<EiaTypeCategory> result) {
						TreeNode dummy = new TreeNode();
						List<List<EiaTypeCategory>> list = new ArrayList<List<EiaTypeCategory>>();

						tree.setRoot(dummy);
						GHAEiaTypeCategoryPickTreeItem.this.setValueTree(tree);
					}
				});
	}
}
