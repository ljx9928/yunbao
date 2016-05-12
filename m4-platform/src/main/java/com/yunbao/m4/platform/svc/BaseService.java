package com.yunbao.m4.platform.svc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.yunbao.m4.platform.datamodel.DataModel;
import com.yunbao.m4.platform.domainmodel.DomainModel;

public abstract class BaseService implements Callable {
	public DomainModel domainModel;
	public List<DataModel> dataModels;
	public Map<?, ?> param;

	// process dataModels
	public abstract <T, V> DomainModel execute(Map<T, V> param) throws Exception;

	public DomainModel getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(DomainModel domainModel) {
		this.domainModel = domainModel;
	}

	public List<DataModel> getDataModels() {
		return dataModels;
	}

	public void setDataModels(List<DataModel> dataModels) {
		this.dataModels = dataModels;
	}

	public Map<?, ?> getParam() {
		return param;
	}

	public void setParam(Map<?, ?> param) {
		this.param = param;
	}

}
