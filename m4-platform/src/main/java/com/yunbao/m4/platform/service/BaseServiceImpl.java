package com.yunbao.m4.platform.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.datamodel.DataModel;
import com.yunbao.m4.platform.domainmodel.DomainModel;

public class BaseServiceImpl<V> implements BaseService {
	private DomainModel domainModel;
	private List<DataModel> dataModels;
	
	public <T, V> DomainModel makeWSCall(Map<T, V> param) throws Exception {

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

	
	public List<DataModel> getDataModels() {
		return dataModels;
	}

	public void setDataModels(List<DataModel> dataModels) {
		this.dataModels = dataModels;
	}

	public DomainModel getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(DomainModel domainModel) {
		this.domainModel = domainModel;
	}


}
