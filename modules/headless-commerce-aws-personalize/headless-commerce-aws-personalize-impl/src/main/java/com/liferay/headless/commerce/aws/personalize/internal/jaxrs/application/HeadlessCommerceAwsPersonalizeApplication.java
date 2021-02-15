package com.liferay.headless.commerce.aws.personalize.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author riccardoferrari
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/headless-commerce-aws-personalize",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=HeadlessCommerceAwsPersonalize"
	},
	service = Application.class
)
@Generated("")
public class HeadlessCommerceAwsPersonalizeApplication extends Application {
}