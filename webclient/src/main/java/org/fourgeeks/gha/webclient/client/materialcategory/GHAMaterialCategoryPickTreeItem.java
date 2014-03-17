/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialcategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHAIPickTreeItem;

import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * @author emiliot
 * 
 */
public class GHAMaterialCategoryPickTreeItem extends GHAIPickTreeItem {
	private final TreeNode root = new TreeNode("root");

	/**
	 * @param width TODO
	 * 
	 */
	public GHAMaterialCategoryPickTreeItem(int width) {
		super(width);
		setDisplayField("categoryName");
		setValueField("categoryCode");
		fill();
	}

	/**
	 * @param name
	 * @param width TODO
	 */
	public GHAMaterialCategoryPickTreeItem(String name, int width) {
		this(width);
		this.setName(name);
	}

	/**
	 * @param name
	 * @param title
	 * @param width TODO
	 */
	public GHAMaterialCategoryPickTreeItem(String name, String title, int width) {
		this(name, width);
		this.setTitle(title);
	}

	/**
	 * 
	 */
	private void fill() {
		final Tree tree = new Tree();
		GHACache.INSTANCE
		.getMaterialCategories(new GHAAsyncCallback<List<MaterialCategory>>() {

			@Override
			public void onSuccess(List<MaterialCategory> result) {
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
				GHAMaterialCategoryPickTreeItem.this.setValueTree(tree);
			}
		});
	}

}
