package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.service.ServiceInterface;
import javax.inject.Inject;

public class SecondService implements ServiceInterface {
	@Inject
	private FirstService firstService;

	public String passthroughLabel() {
		return firstService.getLabel();
	}
}
