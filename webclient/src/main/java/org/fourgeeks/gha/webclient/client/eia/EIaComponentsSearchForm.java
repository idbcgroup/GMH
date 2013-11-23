package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;

/**
 * 
 * @author alacret This class is use to search only for the Eia that can be
 *         components
 */
public class EIaComponentsSearchForm extends EIASearchForm {

	private EiaType eiaType;

	public EIaComponentsSearchForm(String title) {
		super(title);
	}

	@Override
	protected void search(final Eia eia) {
		EIAModel.findComponents(eia, eiaType,
				new GHAAsyncCallback<List<Eia>>() {

					@Override
					public void onSuccess(List<Eia> result) {
						List<Eia> newList = new ArrayList<Eia>();
						if (blackList != null) {
							List<AbstractEntity> tmpList = GHAUtil
									.binarySearchFilterEntity(result, blackList);
							List<Eia> newTmpList = new ArrayList<Eia>();
							for (AbstractEntity entity : tmpList)
								newTmpList.add((Eia) entity);
							newList = newTmpList;
						} else
							newList = result;
						resultSet.setRecords(newList, false);
					}
				});
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

}
