package org.m4.platform.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.m4.platform.domainmodel.DomainModel;

public class AdvancedPipelineService extends AdvancedService {

	@Override
	public List<DomainModel> execute(Map<?, ?> param) throws Exception {
		List<DomainModel> dms = new ArrayList<DomainModel>();

		for (AdvancedService pService : advancedServices) {
			// in pipeline, can't process two list injection, because can't
			// determine the order, so only process AdvancedParellelService, for
			// baseservice, wrap it into AdvancedParellelService.
			if (pService instanceof AdvancedParellelService) {
				List<DomainModel> domainModels = pService.execute(param);
				dms.addAll(domainModels);
			} else {
				throw new Exception("only process AdvancedParellelService in AdvancedPipelineService");
			}
		}

		return dms;
	}

	public Object call() throws Exception {
		return this.execute(param);
	}
}
