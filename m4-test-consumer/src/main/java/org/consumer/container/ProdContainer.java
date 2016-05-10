package org.consumer.container;

import java.util.Map;

import org.consumer.domainmodel.ProdDomainModel;
import org.m4.platform.container.BaseContainer;

public class ProdContainer extends BaseContainer {

	@Override
	public void buildPageContainer(Map argMap) {
		ProdDomainModel dms = (ProdDomainModel) argMap.get("ProdDomainModel");

		this.addObject("ProdDomainModel", dms.getCollectModel());

		this.addObject("responseType", "success");
	}

}
