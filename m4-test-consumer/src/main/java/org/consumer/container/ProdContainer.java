package org.consumer.container;

import java.util.Map;

import org.m4.platform.container.BaseContainer;

public class ProdContainer extends BaseContainer {

	@Override
	public void buildPageContainer(Map argMap) {
		Map dms = (Map) argMap.get("ProdDomainModel");

		
		this.addObject("ProdDomainModel", dms);
		
		this.addObject("responseType", "success");
	}

}
