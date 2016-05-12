package com.yunbao.m4.consumer.container;

import java.util.Map;

import com.yunbao.m4.consumer.domainmodel.ProdDomainModel;
import com.yunbao.m4.platform.container.BaseContainer;

public class ProdContainer extends BaseContainer {

	@Override
	public void buildPageContainer(Map argMap) {
		ProdDomainModel dms = (ProdDomainModel) argMap.get("ProdDomainModel");

		this.addObject("ProdDomainModel", dms.getCollectModel());

		this.addObject("responseType", "success");
	}

}
