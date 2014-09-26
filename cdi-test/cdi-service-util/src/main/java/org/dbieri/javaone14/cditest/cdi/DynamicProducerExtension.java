package org.dbieri.javaone14.cditest.cdi;

import org.dbieri.javaone14.cditest.service.ServiceInterface;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import java.util.Set;

/**
 * http://docs.jboss.org/weld/reference/latest/en-US/html/extend.html#d0e4800
 */
@SuppressWarnings("all")
public abstract class DynamicProducerExtension implements Extension {

	public abstract Set<Class<? extends ServiceInterface>> getMocks();

	/**
	 * Adds the DynamicProducer for each mock
	 *
	 * @param  afterBeanDiscovery
	 * @param  bm
	 */
	public void afterBeanDiscovery(@Observes AfterBeanDiscovery afterBeanDiscovery, BeanManager bm) {
		for (Class<? extends ServiceInterface> mock : getMocks()) {
			Class<?> service = mock.getSuperclass();
			afterBeanDiscovery.addBean(new DynamicProducer(service, mock));
		}
	}

	/**
	 * Annotates the superclass of the mocks with @Alternative, to make sure we get no ambiguous dependencies.
	 *
	 * @param  pat
	 * @param  <T>
	 */
	public <T extends ServiceInterface> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
		boolean annotateWithAlternative = false;

		//test, if the current type is a superclass of a mock
		Class<?> serviceClass = pat.getAnnotatedType().getJavaClass();
		for (Class<? extends ServiceInterface> mock : getMocks()) {
			if (serviceClass.isAssignableFrom(mock)) {
				annotateWithAlternative = true;
			}
		}

		if (annotateWithAlternative) {
			final AnnotatedType<T> type = pat.getAnnotatedType();
			AlternativeWrapperAnnotatedType<T> wrapperAnnotatedType = new AlternativeWrapperAnnotatedType<T>(type);
			pat.setAnnotatedType(wrapperAnnotatedType);
		}
	}

}
