package org.dbieri.javaone14.cditest;

import org.dbieri.javaone14.cditest.ThirdService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import java.io.File;

@RunWith(Arquillian.class)
public class ArquillianBTest {

	@Inject
	private ThirdService thirdService;

	@Deployment
	public static Archive<?> createDeployment() {
		File[] files = Maven.resolver().resolve("org.dbieri.javaone14:cdi-service-b:1.0-SNAPSHOT").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class).addAsLibraries(files).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testLabel() {
		Assert.assertEquals("mocked", thirdService.passthroughLabelAgain());
	}
}
