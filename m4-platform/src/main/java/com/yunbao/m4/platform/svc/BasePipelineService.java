package com.yunbao.m4.platform.svc;

import java.util.List;
import java.util.Map;

import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.datamodel.DataModel;
import com.yunbao.m4.platform.domainmodel.DomainModel;
import com.yunbao.m4.platform.util.LogUtil;

public class BasePipelineService extends BaseService {

	private DomainModel domainModel;
	private List<DataModel> dataModels;

	@Override
	public <T, V> DomainModel execute(Map<T, V> param) throws Exception {
		for (final DataModel dm : dataModels) {
			AbstractDAO<T> dao = dm.getDao();
			dao.setParams(param);
			T response = dao.call();
			if (response != null) {
				LogUtil.debug(dm.getClass(),"process " + response.toString());
			}
			dm.processResponse(response);

			// prepare param for next dao
			if (dm.getParam() != null && !dm.getParam().isEmpty()) {
				param.putAll(dm.getParam());
			}
		}

		if (domainModel != null) {
			domainModel.build(dataModels);
		}
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

	public Object call() throws Exception {
		return this.execute(param);
	}
}
