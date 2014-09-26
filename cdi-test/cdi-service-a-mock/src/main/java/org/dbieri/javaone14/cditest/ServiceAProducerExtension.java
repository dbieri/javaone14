package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.cdi.DynamicProducerExtension;
import org.dbieri.javaone14.cditest.service.ServiceInterface;

import java.util.HashSet;
import java.util.Set;

/**
 * http://docs.jboss.org/weld/reference/latest/en-US/html/extend.html#d0e4800
 */
public class ServiceAProducerExtension extends DynamicProducerExtension {
	private Set<Class<? extends ServiceInterface>> mocks = new HashSet<Class<? extends ServiceInterface>>();

	public ServiceAProducerExtension() {
		//add all mocks here
		mocks.add(FirstServiceMock.class);
		//mocks.add(SecondServiceMock.class);
	}

	@Override
	public Set<Class<? extends ServiceInterface>> getMocks() {
		return mocks;
	}
}
