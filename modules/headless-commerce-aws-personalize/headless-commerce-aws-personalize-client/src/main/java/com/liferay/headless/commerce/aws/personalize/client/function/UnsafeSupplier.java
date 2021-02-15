package com.liferay.headless.commerce.aws.personalize.client.function;

import javax.annotation.Generated;

/**
 * @author riccardoferrari
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}