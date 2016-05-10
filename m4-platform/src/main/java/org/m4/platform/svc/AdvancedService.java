package org.m4.platform.svc;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;

public abstract class AdvancedService implements Callable {
	public DomainModel domainModel;
	public List<BaseService> services  = Collections.EMPTY_LIST;
	public List<AdvancedService> advancedServices=Collections.EMPTY_LIST;
	public Map<?, ?> param;

	public abstract List<DomainModel> execute(Map<?, ?> param) throws Exception;

	public List<BaseService> getServices() {
		return services;
	}

	public void setServices(List<BaseService> services) {
		this.services = services;
	}

	public List<AdvancedService> getAdvancedServices() {
		return advancedServices;
	}

	public void setAdvancedServices(List<AdvancedService> advancedServices) {
		this.advancedServices = advancedServices;
	}

	public Map<?, ?> getParam() {
		return param;
	}

	public void setParam(Map<?, ?> param) {
		this.param = param;
	}

	public DomainModel getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(DomainModel domainModel) {
		this.domainModel = domainModel;
	}

}
