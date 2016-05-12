package com.yunbao.m4.platform.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.yunbao.m4.platform.domainmodel.DomainModel;

public class BaseParellelServiceImpl implements BaseService {
	private DomainModel domainModel;
	// private List<DataModel> dataModels;

//	<ref bean="baseCarService" />
//	<ref bean="baseShopService" />
	private List<BaseService> services;
	private List<BasePipelineServiceImpl> basePipelineServices;

	public <T, V> DomainModel makeWSCall(Map<T, V> param) throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(basePipelineServices.size());

		Map<BasePipelineServiceImpl, Future> map = new HashMap<BasePipelineServiceImpl, Future>();

		for (BasePipelineServiceImpl pipelineService : basePipelineServices) {
			//FutureTask futureTask1 = new FutureTask(pipelineService);
			FutureTask futureTask1 = new FutureTask(null);
			Future future = executor.submit(futureTask1);
			map.put(pipelineService, future);
		}

		for (Entry<BasePipelineServiceImpl, Future> future : map.entrySet()) {
			BasePipelineServiceImpl svc = future.getKey();

			BasePipelineServiceImpl pipelineSvc = future.getKey();
			//pipelineSvc.getDomainModel().build(domain);
		}

		return domainModel;
	}

	private <T, V> void pipelineCall(Map<T, V> param) throws Exception {
		for (BaseService baseSvc : services) {
			baseSvc.makeWSCall(param);
		}
	}

	public DomainModel getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(DomainModel domainModel) {
		this.domainModel = domainModel;
	}

	public List<BasePipelineServiceImpl> getBasePipelineServices() {
		return basePipelineServices;
	}

	public void setBasePipelineServices(List<BasePipelineServiceImpl> basePipelineServices) {
		this.basePipelineServices = basePipelineServices;
	}

}
