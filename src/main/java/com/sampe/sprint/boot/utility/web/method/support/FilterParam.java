/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sampe.sprint.boot.utility.web.method.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ValueConstants;

/**
 * Annotation which indicates that a method parameter should be bound to a URI
 * template variable filter. Supported for {@link RequestMapping} annotated
 * handler methods.
 * 
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 28/10/2018 22:18:38
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FilterParam {

	/**
	 * Alias for {@link #name}.
	 */
	@AliasFor("name")
	String value() default "";

	/**
	 * The name of the path variable to bind to.
	 * 
	 * @since 4.3.3
	 */
	@AliasFor("value")
	String name() default "";

	/**
	 * Whether the path variable perform the trim
	 */
	boolean trim() default true;

	/**
	 * Whether the path variable is required
	 */
	boolean required() default false;

	/**
	 * The default value to use as a fallback when the request parameter is not
	 * provided or has an empty value.
	 * <p>
	 * Supplying a default value implicitly sets {@link #required} to {@code false}.
	 */
	String defaultValue() default ValueConstants.DEFAULT_NONE;

}
