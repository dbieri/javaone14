package org.dbieri.javaone14.firsttest.arquillian;


import javax.inject.Inject;

import org.dbieri.javaone14.firsttest.entity.FirstEntity;
import org.dbieri.javaone14.firsttest.service.FirstService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by dbieri on 22.09.2014.
 */
@RunWith(Arquillian.class)
public class FirstTest {

    @Inject
    private FirstService firstService;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(FirstEntity.class)
                .addClass(FirstService.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testCreateFirstEntiy() {
        FirstEntity firstEntity = new FirstEntity();
        firstEntity.setName("junit");
        firstEntity = firstService.createFirstEntity(firstEntity);
        Assert.assertNotNull(firstEntity.getId());
    }
}
