package org.consumer.domainmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.consumer.datamodel.CarDataModel;
import org.consumer.datamodel.ShopDataModel;
import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;

public class ProdDomainModel implements DomainModel {
	final String prodDomainModel = "ProdDomainModel";

	private Map<String, Map> map = new HashMap<String, Map>();

	@Override
	public void build(List<DataModel> dataModels) {
		Map localmap = new HashMap();
		for (DataModel dm : dataModels) {
			if (dm instanceof ShopDataModel) {
				localmap.put("shop", ((ShopDataModel) dm).getShop());
			} else if (dm instanceof CarDataModel) {
				localmap.put("prod", ((CarDataModel) dm).getProd());
			}
		}
		map.put(prodDomainModel, localmap);
	}

	@Override
	public Map<String, Map> getDataMap() {
		return map;
	}
}
