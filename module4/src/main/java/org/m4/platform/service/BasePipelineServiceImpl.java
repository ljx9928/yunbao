package org.m4.platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;

public class BasePipelineServiceImpl implements BaseService {
	private List<BaseDataServiceImpl> services;

	private DomainModel domainModel;
	private List<DataModel> dataModels;

	public <T, V> DomainModel makeWSCall(Map<T, V> param) throws Exception {

		for (final DataModel dm : dataModels) {
			AbstractDAO<T> dao = dm.getDao();
			dao.setParams(param);
			T response = dao.call();
			dm.processResponse(response);

			// prepare param for next dao
			if (dm.getParam() != null && !dm.getParam().isEmpty()) {
				param.putAll(dm.getParam());
			}
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

	public List<BaseDataServiceImpl> getServices() {
		return services;
	}

	public void setServices(List<BaseDataServiceImpl> services) {
		this.services = services;
	}

}
