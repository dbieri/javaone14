package org.dbieri.javaone14.cditest.cdi;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class AlternativeWrapperAnnotatedType<T> implements AnnotatedType<T> {

	private final AlternativeLiteral alternativeLiteral = new AlternativeLiteral();
	private AnnotatedType<T> originalType;
	public AlternativeWrapperAnnotatedType(AnnotatedType<T> originalType) {
		this.originalType = originalType;
	}

	@Override
	public Type getBaseType() {
		return originalType.getBaseType();
	}

	@Override
	public Set<Type> getTypeClosure() {
		return originalType.getTypeClosure();
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> tClass) {
		if (tClass.equals(Alternative.class)) {
			return (T) alternativeLiteral;
		}
		return originalType.getAnnotation(tClass);
	}

	@Override
	public Set<Annotation> getAnnotations() {
		Set<Annotation> annotations = new HashSet<Annotation>(originalType.getAnnotations());
		annotations.add(alternativeLiteral);
		return annotations;
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		return annotationType.equals(Alternative.class) ? true : originalType.isAnnotationPresent(annotationType);
	}

	@Override
	public Class<T> getJavaClass() {
		return originalType.getJavaClass();
	}

	@Override
	public Set<AnnotatedConstructor<T>> getConstructors() {
		return originalType.getConstructors();
	}

	@Override
	public Set<AnnotatedMethod<? super T>> getMethods() {
		return originalType.getMethods();
	}

	@Override
	public Set<AnnotatedField<? super T>> getFields() {
		return originalType.getFields();
	}

	class AlternativeLiteral extends AnnotationLiteral<Alternative> implements Alternative {
	}
}
