package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */
public abstract class GHAUtil {

	/**
	 * This class is used in the bfs algoritm in the method buildParentsByCode
	 * function.
	 * 
	 * @author emiliot
	 * @see GHAUtil#buildParentsByCode(String[], int)
	 */
	private static class Node {
		public int code;
		public int parent;

		public Node() {
			this.code = -1;
			this.parent = -1;
		}

		/**
		 * @param code
		 * @param parent
		 */
		public Node(int code, int parent) {
			super();
			this.code = code;
			this.parent = parent;
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean binarySearch(Comparable[] all, Comparable key) {
		int low = 0, high = all.length;
		while (low < high) {
			int mid = (low + high) / 2;
			int cmp = key.compareTo(all[mid]);
			if (cmp == 0)
				return true;
			else if (cmp > 0)
				low = mid + 1;
			else
				high = mid;
		}
		return false;
	}

	/**
	 * @param all
	 * @param blackList
	 * @return the filtered list
	 */
	public static List<AbstractCodeEntity> binarySearchFilterCodeEntity(
			List<? extends AbstractCodeEntity> all,
			List<? extends AbstractCodeEntity> blackList) {
		Collections.sort(blackList, new Comparator<AbstractCodeEntity>() {

			@Override
			public int compare(AbstractCodeEntity o1, AbstractCodeEntity o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});

		List<AbstractCodeEntity> newList = new ArrayList<AbstractCodeEntity>();
		String[] keyArray = stringArrayFrom(blackList);

		for (AbstractCodeEntity allItem : all) {
			if (!(binarySearch(keyArray, allItem.getCode()))) {
				newList.add(allItem);
			}
		}

		return newList;
	}

	/**
	 * @param all
	 * @param blackList
	 * @return the filtered list
	 */
	public static List<AbstractEntity> binarySearchFilterEntity(
			List<? extends AbstractEntity> all,
			List<? extends AbstractEntity> blackList) {
		Collections.sort(blackList, new Comparator<AbstractEntity>() {

			@Override
			public int compare(AbstractEntity o1, AbstractEntity o2) {
				return Long.valueOf(o1.getId()).compareTo(o2.getId());
			}
		});
		List<AbstractEntity> newList = new ArrayList<AbstractEntity>();
		Long[] keyArray = longArrayFrom(blackList);
		for (AbstractEntity allItem : all) {
			if (!(binarySearch(keyArray, Long.valueOf(allItem.getId()))))
				newList.add(allItem);
		}

		return newList;
	}

	/**
	 * @author emiliot
	 * @param codes
	 *            the list of codes
	 * @param root
	 *            the position of the root in the list of codes
	 * @return the parents for everyNode using a bfs algorithm
	 */
	public static int[] buildParentsByCode(String codes[], int root) {
		int parents[] = new int[codes.length];
		parents[root] = -1;
		for (int i = 0; i < parents.length; ++i) {
			parents[i] = -1;
		}

		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(root, -1));
		while (!q.isEmpty()) {
			Node next = q.poll();
			String parentCode = codes[next.code];

			for (int i = 0; i < codes.length; ++i) {
				if (i == root || i == next.code)
					continue;
				String nextCode = codes[i];
				if (nextCode.startsWith(parentCode)
						&& (parents[i] == -1 || parentCode.length() > codes[parents[i]]
								.length())) {
					// first parent or more suitable one
					Node child = new Node(i, next.code);
					parents[i] = next.code;
					q.add(child);
				}
			}
		}

		return parents;
	}

	private static Long[] longArrayFrom(List<? extends AbstractEntity> all) {
		Long[] keyArray = new Long[all.size()];
		for (int i = 0; i < all.size(); i++)
			keyArray[i] = all.get(i).getId();
		return keyArray;
	}

	private static String[] stringArrayFrom(
			List<? extends AbstractCodeEntity> all) {
		String[] keyArray = new String[all.size()];
		for (int i = 0; i < all.size(); i++)
			keyArray[i] = all.get(i).getCode();
		return keyArray;
	}
}