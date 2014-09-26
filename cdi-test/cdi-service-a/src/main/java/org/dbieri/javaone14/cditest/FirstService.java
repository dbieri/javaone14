package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.service.ServiceInterface;

public class FirstService implements ServiceInterface {

	public String getLabel() {
		return "unmocked";
	}
}
