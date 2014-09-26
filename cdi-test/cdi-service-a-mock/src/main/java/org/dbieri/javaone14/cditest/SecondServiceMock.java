package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.SecondService;

import javax.enterprise.inject.Alternative;

@Alternative
public class SecondServiceMock extends SecondService {
	@Override
	public String passthroughLabel() {
		return "hello";
	}
}
