package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.FirstService;

import javax.enterprise.inject.Alternative;

@Alternative
public class FirstServiceMock extends FirstService {
	@Override
	public String getLabel() {
		return "mocked";
	}
}
