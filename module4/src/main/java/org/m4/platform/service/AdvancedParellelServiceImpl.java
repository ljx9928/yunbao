package org.m4.platform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.m4.platform.domainmodel.DomainModel;

public class AdvancedParellelServiceImpl implements AdvancedService, Callable<List<DomainModel>> {

	// private List<BaseService> services;
	private List<AdvancedPipelineServiceImpl> pipelineServices;
	private Map<?, ?> param;

	public List<DomainModel> processBaseServices(Map<?, ?> param) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(pipelineServices.size());

		Map<AdvancedPipelineServiceImpl, FutureTask<List<DomainModel>>> map = new HashMap<AdvancedPipelineServiceImpl, FutureTask<List<DomainModel>>>();

		List<DomainModel> dms = new ArrayList<DomainModel>();

		for (final AdvancedPipelineServiceImpl pSvc : pipelineServices) {
			pSvc.setParam(param);
			FutureTask<List<DomainModel>> futureTask1 = new FutureTask<List<DomainModel>>(pSvc);
			executor.execute(futureTask1);
			map.put(pSvc, futureTask1);
		}

		for (Entry<AdvancedPipelineServiceImpl, FutureTask<List<DomainModel>>> future : map.entrySet()) {
			// TODO blocking?
			List<DomainModel> response = future.getValue().get();
			// future.getKey().gprocessResponse(response);

			dms.addAll(response);
		}

		return dms;
	}

	public List<DomainModel> call() throws Exception {
		return processBaseServices(null);
	}

	public void setParam(Map<?, ?> param) {
		this.param = param;

	}

}
