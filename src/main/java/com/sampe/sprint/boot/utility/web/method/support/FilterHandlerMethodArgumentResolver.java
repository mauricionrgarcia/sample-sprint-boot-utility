/*
 * Copyright 2013-2017 the original author or authors.
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;

/**
 * Deals with text' filters, performing split and normalizing thus allows
 * injecting instances filter into controller methods
 * 
 * @author <a href='mailto:mauricionrgarcia@gmail.com'>Mauricio Garcia</a>
 * @version
 * @sinse 28/10/2018 22:07:33
 */
@Component
public class FilterHandlerMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		FilterParam ann = parameter.getParameterAnnotation(FilterParam.class);
		return (ann != null && String.class.isAssignableFrom(parameter.getParameterType())
				&& StringUtils.hasText(ann.value()));
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
		FilterParam ann = parameter.getParameterAnnotation(FilterParam.class);
		return (ann != null ? new RequestParamNamedValueInfo(ann) : new RequestParamNamedValueInfo());
	}

	@Override
	protected void handleResolvedValue(Object arg, String name, MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
		super.handleResolvedValue(arg, name, parameter, mavContainer, webRequest);
	}

	@Override
	@Nullable
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
		HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
		FilterParam ann = parameter.getParameterAnnotation(FilterParam.class);
		if (servletRequest != null) {
			Object mpArg = MultipartResolutionDelegate.resolveMultipartArgument(name, parameter, servletRequest);
			if (mpArg != MultipartResolutionDelegate.UNRESOLVABLE) {
				return mpArg;
			}
		}

		Object arg = null;
		MultipartHttpServletRequest multipartRequest = request.getNativeRequest(MultipartHttpServletRequest.class);
		if (multipartRequest != null) {
			List<MultipartFile> files = multipartRequest.getFiles(name);
			if (!files.isEmpty()) {
				arg = (files.size() == 1 ? files.get(0) : files);
			}
		}
		if (arg == null) {
			String[] paramValues = request.getParameterValues(name);
			if (paramValues != null) {
				arg = (paramValues.length == 1 ? paramValues[0] : paramValues);
			}
		}

		return applyFilter(arg, ann);
	}

	/**
	 * Apply filter
	 * 
	 * @param arg argument
	 * @param ann {@link FilterParam}
	 * @return arg
	 */
	private Object applyFilter(Object arg, FilterParam ann) {

		if (arg != null && arg instanceof String) {
			String str = (String) arg;
			return str.trim();
		}
		return null;

	}

	/**
	 * Represents the information about a named value, including name, whether it's
	 * required and a default value.
	 */
	private static class RequestParamNamedValueInfo extends NamedValueInfo {

		public RequestParamNamedValueInfo() {
			super("", false, ValueConstants.DEFAULT_NONE);
		}

		public RequestParamNamedValueInfo(FilterParam annotation) {
			super(annotation.name(), annotation.required(), annotation.defaultValue());
		}
	}

}
