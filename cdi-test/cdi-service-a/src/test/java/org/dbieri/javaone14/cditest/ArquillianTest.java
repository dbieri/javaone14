package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.FirstService;
import org.dbieri.javaone14.cditest.SecondService;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class ArquillianTest {

	@Inject
	private SecondService secondService;

	@Deployment
	public static Archive<?> createDeployment() {
		return
			ShrinkWrap.create(JavaArchive.class, "test.jar")
			.addClass(FirstService.class)
			.addClass(SecondService.class)
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testLabel() {
		Assert.assertEquals("unmocked", secondService.passthroughLabel());
	}
}
