package org.fourgeeks.gha.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author naramirez
 */
public class GHAUtil {

	/**
	 * @param duration
	 * @param pot
	 * @return the time in seconds
	 */
	public static int getDurationInSeconds(int duration, TimePeriodEnum pot) {

		if (pot == TimePeriodEnum.HOURS)
			return (int) Math.ceil(duration * 3600);
		if (pot == TimePeriodEnum.DAYS)
			return (int) Math.ceil(duration * 86400);
		if (pot == TimePeriodEnum.WEEKS)
			return (int) Math.ceil(duration * 604800);
		if (pot == TimePeriodEnum.MONTHS)
			return (int) Math.ceil(duration * 2629743.83);
		if (pot == TimePeriodEnum.SEMESTERS)
			return (int) Math.ceil(duration * 15778463);
		if (pot == TimePeriodEnum.YEARS)
			return (int) Math.ceil(duration * 31556926);

		return -1;
	}

	/**
	 * Get the estimated duration of a plan based on its activities in a period
	 * of days
	 * 
	 * @param protocol
	 *            the list with the maintenance activities of the plan (the
	 *            protocol)
	 * @return the ceil value of the estimated duration
	 */

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
