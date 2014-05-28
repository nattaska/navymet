package com.ss.sq.util.configurer;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import javax.validation.MessageInterpolator;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageSourceResourceBundleMessageInterpolator implements MessageInterpolator {
	private MessageSource messageSource;

	public MessageSourceResourceBundleMessageInterpolator(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public String interpolate(String message, Context context) {
		return interpolateMessage(message, context.getConstraintDescriptor().getAttributes(), LocaleContextHolder.getLocale());
	}

	@Override
	public String interpolate(String message, Context context, Locale locale) {
		return interpolateMessage(message, context.getConstraintDescriptor().getAttributes(), locale);
	}

	public String interpolateMessage(String message, Map<String, Object> annotationParameters, Locale locale) {
		String code = null;
		String[] arguments = null;

		if (message.indexOf(",") != -1) {
			String[] splited = message.split("\\s*\\,\\s*");
			code = resolveMessageCode(splited[0]);
			arguments = Arrays.copyOfRange(splited, 1, splited.length);
			for (int i = 0; i < arguments.length; i++) {
				arguments[i] = resolveMessageCode(arguments[i]);
			}
		} else {
			code = resolveMessageCode(message);
		}

		return getMessage(code, arguments, locale);
	}

	public String resolveMessageCode(String code) {
		return code.replace("{", "").replace("}", "");
	}

	public String getMessage(String code, String[] arguments, Locale locale) {
		String[] lookedUpArguments = null;
		if (arguments != null && arguments.length > 0) {
			lookedUpArguments = new String[arguments.length];
			for (int i = 0; i < arguments.length; i++) {
				String argument = arguments[i];
				try {
					String lookedUpArgument = messageSource.getMessage(argument, null, locale);
					lookedUpArguments[i] = lookedUpArgument;
				} catch (NoSuchMessageException e) {
					lookedUpArguments[i] = argument;
				}
			}
		}

		String message = null;
		try {
			message = messageSource.getMessage(code, lookedUpArguments, locale);
		} catch (NoSuchMessageException e) {
			message = code;
		}

		return message;
	}
}
