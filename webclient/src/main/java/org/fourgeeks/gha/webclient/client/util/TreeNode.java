/**
 * 
 */
package org.fourgeeks.gha.webclient.client.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author alacret
 * @param <T>
 * @param <E>
 * 
 */
public class TreeNode<T extends Comparable<T>, E> {
	private T code;
	private E object;
	private TreeNode<T, E> parent;
	private Set<TreeNode<T, E>> childs;

	/**
	 * create a empty Node
	 */
	public TreeNode() {

	}

	/**
	 * create a Node with the given object
	 * 
	 * @param code
	 * @param object
	 */
	public TreeNode(final T code, final E object) {
		this.code = code;
		this.object = object;
	}

	/**
	 * @param node
	 */
	public void addChild(final TreeNode<T, E> node) {
		if (childs == null)
			childs = new HashSet<TreeNode<T, E>>();
		childs.add(node);
	}

	@Override
	public boolean equals(final Object obj) {
		return code.equals(code);
	}

	/**
	 * Get the set of childs of this node, or null if a child has never been
	 * added
	 * 
	 * @return the childs
	 */
	public Set<TreeNode<T, E>> getChilds() {
		return childs == null ? new HashSet<TreeNode<T, E>>() : childs;
	}

	/**
	 * @return the code
	 */
	public T getCode() {
		return code;
	}

	/**
	 * @return the Object E
	 */
	public E getObject() {
		return object;
	}

	/**
	 * @return the parent
	 */
	public TreeNode<T, E> getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
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
	public void setChilds(final Set<TreeNode<T, E>> childs) {
		this.childs = childs;
	}

	/**
	 * @param code
	 */
	public void setCode(final T code) {
		this.code = code;
	}

	/**
	 * @param object
	 */
	public void setObject(final E object) {
		this.object = object;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(final TreeNode<T, E> parent) {
		this.parent = parent;
	}
}