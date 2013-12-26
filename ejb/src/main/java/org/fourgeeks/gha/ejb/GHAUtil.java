package org.fourgeeks.gha.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author naramirez
 */
public class GHAUtil {
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

	private static Long[] longArrayFrom(List<? extends AbstractEntity> all) {
		Long[] keyArray = new Long[all.size()];
		for (int i = 0; i < all.size(); i++)
			keyArray[i] = all.get(i).getId();
		return keyArray;
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
}
