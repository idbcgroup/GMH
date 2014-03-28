/**
 * 
 */
package org.fourgeeks.gha.webclient.client.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alacret
 * @param <T>
 *            The type
 * 
 */
public class TreeNode<T> {
	private T object;
	private TreeNode<T> parent;
	private List<TreeNode<T>> childs;

	/**
	 * create a empty Node
	 */
	public TreeNode() {

	}

	/**
	 * create a Node with the given object
	 * 
	 * @param object
	 */
	public TreeNode(final T object) {
		this.object = object;
	}

	/**
	 * @param object
	 */
	public void addChild(final TreeNode<T> object) {
		if (childs == null)
			childs = new ArrayList<>();
		childs.add(object);
	}

	/**
	 * @return the childs
	 */
	public List<TreeNode<T>> getChilds() {
		return childs;
	}

	/**
	 * @return the object T
	 */
	public T getObject() {
		return object;
	}

	/**
	 * @return the parent
	 */
	public TreeNode<T> getParent() {
		return parent;
	}

	/**
	 * @return whether this is a Leaf
	 */
	public boolean isLeaf() {
		return childs == null ? true : childs.size() == 0;
	}

	/**
	 * @param childs
	 */
	public void setChilds(final List<TreeNode<T>> childs) {
		this.childs = childs;
	}

	/**
	 * @param object
	 */
	public void setObject(final T object) {
		this.object = object;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(final TreeNode<T> parent) {
		this.parent = parent;
	}
}