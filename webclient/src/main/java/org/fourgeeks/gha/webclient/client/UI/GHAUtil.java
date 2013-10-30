package org.fourgeeks.gha.webclient.client.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.AbstractEntity;

import com.google.gwt.user.client.Window;

/**
 * @author alacret
 * 
 */
public final class GHAUtil {

	private GHAUtil() {
	}

	/**
	 * @param all
	 * @param blackList
	 * @return the filtered list
	 */
	public static List<AbstractCodeEntity> binarySearchFilterCodeEntity(
			List<? extends AbstractCodeEntity> all,
			List<? extends AbstractCodeEntity> blackList) {
		Collections.sort(all, new Comparator<AbstractCodeEntity>() {

			@Override
			public int compare(AbstractCodeEntity o1, AbstractCodeEntity o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});

		List<AbstractCodeEntity> newList = new ArrayList<AbstractCodeEntity>();
		String[] keyArray = stringArrayFrom(blackList);
		for (AbstractCodeEntity allItem : all) {
			if (!(binarySearch(keyArray, allItem.getCode())))
				newList.add(allItem);
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
		Collections.sort(all, new Comparator<AbstractEntity>() {

			@Override
			public int compare(AbstractEntity o1, AbstractEntity o2) {
				return Long.valueOf(o1.getId()).compareTo(o2.getId());
			}
		});
		List<AbstractEntity> newList = new ArrayList<AbstractEntity>();
		Long[] keyArray = longArrayFrom(blackList);
		for (AbstractEntity allItem : all) {
			if (!(binarySearch(keyArray,
					Long.valueOf(allItem.getId()))))
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

	private static String[] stringArrayFrom(
			List<? extends AbstractCodeEntity> all) {
		String[] keyArray = new String[all.size()];
		for (int i = 0; i < all.size(); i++)
			keyArray[i] = all.get(i).getCode();
		return keyArray;
	}

	private static boolean binarySearch(Comparable[] all, Comparable key) {
		int low = 0, high = all.length;
		while (low < high) {
			int mid = (low + high) / 2;
			int cmp = key.compareTo(all[mid]);
			Window.alert(all[mid] + " compare to: " + key + " : " + cmp);
			if (cmp == 0)
				return true;
			else if (cmp > 0)
				high = mid;
			else
				low = mid + 1;
		}
		return false;
	}
}