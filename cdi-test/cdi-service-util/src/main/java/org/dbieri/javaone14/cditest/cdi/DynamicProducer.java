package org.dbieri.javaone14.cditest.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DynamicProducer<S, M extends S> implements Bean<S> {

	private Class<S> serviceType;
	private Class<M> mockType;

	public DynamicProducer(Class<S> serviceType, Class<M> mockType) {
		this.serviceType = serviceType;
		this.mockType = mockType;
	}

	@Override
	public S create(CreationalContext<S> creationalContext) {
		S mock = null;
		try {
			mock = mockType.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mock;
	}

	@Override
	public void destroy(S s, CreationalContext<S> sCreationalContext) {

	}

	@Override
	public Set<Type> getTypes() {
		HashSet<Type> types = new HashSet<Type>();
		types.add(serviceType);
		return types;
	}

	@Override
	public Set<Annotation> getQualifiers() {
		Set<Annotation> qualifiers = new HashSet<Annotation>();
		qualifiers.add(new AnnotationLiteral<Default>() {
			});
		qualifiers.add(new AnnotationLiteral<Any>() {
			});

		return qualifiers;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return ApplicationScoped.class;
	}

	@Override
	public String getName() {
		return serviceType.getName();
	}

	@Override
	public Set<Class<? extends Annotation>> getStereotypes() {
		return Collections.emptySet();
	}

	@Override
	public Class<?> getBeanClass() {
		return serviceType;
	}

	@Override
	public boolean isAlternative() {
		return false;
	}

	@Override
	public boolean isNullable() {
		return false;
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return new HashSet<InjectionPoint>();
	}
}
