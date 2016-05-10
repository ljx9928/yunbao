package org.m4.platform.svc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;

public class AdvancedParellelService extends AdvancedService {

	public List<DomainModel> execute(Map<?, ?> param) throws Exception {
		int total = (services == null ? 0 : services.size()) + (advancedServices == null ? 0 : advancedServices.size());
		ExecutorService executor = Executors.newFixedThreadPool(total);

		// TODO key?
		Map<String, FutureTask<List<DomainModel>>> map = new HashMap<String, FutureTask<List<DomainModel>>>();

		List<DomainModel> dms = new ArrayList<DomainModel>();

		// process base services
		for (final BaseService svc : services) {
			svc.setParam(param);
			FutureTask<List<DomainModel>> futureTask1 = new FutureTask<List<DomainModel>>(svc);
			executor.execute(futureTask1);
			map.put(svc.toString(), futureTask1);
		}

		// process AdvancedService

		for (final AdvancedService advSvc : advancedServices) {
			advSvc.setParam(param);
			FutureTask<List<DomainModel>> futureTask1 = new FutureTask<List<DomainModel>>(advSvc);
			executor.execute(futureTask1);
			map.put(advSvc.toString(), futureTask1);
		}

		// get result(domain model) from future
		for (Entry<String, FutureTask<List<DomainModel>>> future : map.entrySet()) {
			// TODO blocking?
			List<DomainModel> response = future.getValue().get();
			// future.getKey().processResponse(response);
			if (response != null) {
				dms.addAll(response);
			}
		}

		if (domainModel != null) {
			List<DataModel> dataModels = new ArrayList<DataModel>();
			for (final BaseService svc : services) {
				dataModels.addAll(svc.getDataModels());
			}
			domainModel.build(dataModels);
			dms.add(domainModel);
		}

		return dms;
	}

	public Object call() throws Exception {
		return execute(param);
	}

}
