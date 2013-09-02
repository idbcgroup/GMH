package org.fourgeeks.gha.webclient.client.UI.grids.tree;

import com.smartgwt.client.widgets.tree.TreeNode;

public abstract class GHATreeGridNode<T> extends TreeNode {

	public abstract T toEntity();
}