package com.yunbao.m4.platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.yunbao.m4.platform.domainmodel.DomainModel;

public class AdvancedPipelineServiceImpl implements AdvancedService, Callable<List<DomainModel>> {

	//private List<BaseDataServiceImpl> services;

	private List<AdvancedParellelServiceImpl> pServices;

	private Map<?, ?> param;

	public List<DomainModel> processBaseServices(Map<?, ?> param) throws Exception {
		List<DomainModel> dms = new ArrayList<DomainModel>();

		for (AdvancedParellelServiceImpl pService : pServices) {
			List<DomainModel> domainModels = pService.processBaseServices(param);
			dms.addAll(domainModels);
		}

		return dms;
	}

//	public List<DomainModel> processBaseServices(Map<?, ?> param) throws Exception {
//		List<DomainModel> dms = new ArrayList<DomainModel>();
//
//		for (BaseDataServiceImpl baseService : services) {
//			DomainModel domainModel = baseService.makeWSCall(param);
//			domainModel.build(baseService.getDataModels());
//			dms.add(domainModel);
//		}
//
//		return dms;
//	}

	public List<DomainModel> call() throws Exception {
		return processBaseServices(this.param);
	}

//	public List<BaseDataServiceImpl> getServices() {
//		return services;
//	}
//
//	public void setServices(List<BaseDataServiceImpl> services) {
//		this.services = services;
//	}

	public Map<?, ?> getParam() {
		return param;
	}

	public void setParam(Map<?, ?> param) {
		this.param = param;

	}


}
