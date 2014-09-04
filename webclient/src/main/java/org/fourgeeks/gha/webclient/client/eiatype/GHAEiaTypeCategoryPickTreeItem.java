/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAPickTreeItem;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * @author emiliot
 * 
 */
public class GHAEiaTypeCategoryPickTreeItem extends GHAPickTreeItem {
	private final TreeNode root = new TreeNode("root");

	/**
	 * 
	 */
	public GHAEiaTypeCategoryPickTreeItem() {
		super();
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
		this(name);
		this.setTitle(title);
	}

	/**
	 * 
	 */
	private void fill() {
		final Tree tree = new Tree();
		GHACache.INSTANCE
		.getEiaTypeCategories(new GHAAsyncCallback<List<ServiceResourceCategory>>() {

			@Override
			public void onSuccess(List<ServiceResourceCategory> result) {
				Collections.sort(result);

				final String codes[] = new String[result.size()];
				final TreeNode nodes[] = new TreeNode[result.size()];

				for (int i = 0; i < result.size(); ++i) {
					codes[i] = result.get(i).getCode();

					nodes[i] = new TreeNode(result.get(i).getName());
					nodes[i].setAttribute("categoryCode", result.get(i)
							.getCode());
					nodes[i].setAttribute("categoryName", result.get(i)
							.getName());

				}

				final int parent[] = GHAUtil.buildParentsByCode(codes, 0);

				for (int i = 0; i < result.size(); ++i) {
					final List<Integer> children = new ArrayList<Integer>();

					// get the children of i
					for (int j = 0; j < result.size(); ++j) {
						if (parent[j] == i)
							children.add(j);
					}

					final TreeNode theChildren[] = new TreeNode[children
					                                            .size()];
					for (int j = 0; j < children.size(); ++j) {
						theChildren[j] = nodes[children.get(j)];
					}

					if (children.size() > 0)
						nodes[i].setChildren(theChildren);
				}
				root.setChildren(new TreeNode[] { nodes[0] });
				tree.setRoot(root);
				GHAEiaTypeCategoryPickTreeItem.this.setValueTree(tree);
			}
		});
	}
}
