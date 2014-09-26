package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.service.ServiceInterface;
import org.dbieri.javaone14.cditest.SecondService;

import javax.inject.Inject;

public class ThirdService implements ServiceInterface {

	@Inject
	private SecondService secondService;

	public String passthroughLabelAgain() {
		return secondService.passthroughLabel();
	}

}
