package org.m4.platform.svc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;

public class BaseParellelService extends BaseService {

	@Override
	public <T, V> DomainModel execute(Map<T, V> param) throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(dataModels.size());

		Map<DataModel, FutureTask<T>> map = new HashMap<DataModel, FutureTask<T>>();

		for (final DataModel dm : dataModels) {
			AbstractDAO<T> dao = dm.getDao();
			dao.setParams(param);
			FutureTask<T> futureTask1 = new FutureTask<T>(dao);
			executor.execute(futureTask1);
			map.put(dm, futureTask1);
		}

		for (Entry<DataModel, FutureTask<T>> future : map.entrySet()) {
			T response = future.getValue().get();
			if (response != null) {
				System.out.println("loop " + response.toString());
			}
			future.getKey().processResponse(response);
		}

		domainModel.build(dataModels);
		return domainModel;
	}

	public Object call() throws Exception {
		return this.execute(param);
	}
}
