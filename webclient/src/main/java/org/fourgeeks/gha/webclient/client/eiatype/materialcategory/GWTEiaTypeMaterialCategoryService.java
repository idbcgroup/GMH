package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("eiaTypeMaterialCategory")
public interface GWTEiaTypeMaterialCategoryService extends RemoteService {

	public List<EiaTypeMaterialCategory> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	public EiaTypeMaterialCategory save(
			EiaTypeMaterialCategory eiaTypeMaterialCategory)
			throws GHAEJBException;

	public void delete(long id) throws GHAEJBException;

}
