package com.yunbao.m4.consumer.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yunbao.m4.platform.container.BaseContainer;
import com.yunbao.m4.platform.domainmodel.DomainModel;
import com.yunbao.m4.platform.svc.AdvancedService;

public class ProdDelegate {
	private AdvancedService service;

	public <T, V> void process(Map<T, V> param, BaseContainer container) throws Exception {
		List<DomainModel> dms= service.execute(param);
		DomainModel d = service.getDomainModel();
		Map m = new HashMap();
		m.put("dms", dms);
		container.buildPageContainer(d.getDataMap());
	}

	public AdvancedService getService() {
		return service;
	}

	public void setService(AdvancedService service) {
		this.service = service;
	}

	 

}
