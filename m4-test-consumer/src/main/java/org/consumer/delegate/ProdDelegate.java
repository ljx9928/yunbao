package org.consumer.delegate;

import java.util.Map;

import org.m4.platform.container.BaseContainer;
import org.m4.platform.domainmodel.DomainModel;
import org.m4.platform.service.BaseService;

public class ProdDelegate {
	private BaseService service;

	public <T, V> void process(Map<T, V> param, BaseContainer container) throws Exception {
		DomainModel dms= service.makeWSCall(param);
		container.buildPageContainer(dms.getDataMap());
	}

	public BaseService getService() {
		return service;
	}

	public void setService(BaseService service) {
		this.service = service;
	}

}
