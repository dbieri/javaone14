package org.dbieri.javaone14.cditest;

import javax.enterprise.inject.Alternative;

@Alternative
public class SecondServiceMock extends SecondService {
	@Override
	public String passthroughLabel() {
        return "Hello, World!";
    }
}
