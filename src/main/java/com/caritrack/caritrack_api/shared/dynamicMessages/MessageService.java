package com.caritrack.caritrack_api.shared.dynamicMessages;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(String code, Object... args) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            // Agregamos un valor por defecto (el mismo código) para evitar el 500
            return messageSource.getMessage(code, args, code, locale);
        } catch (Exception e) {
            return "Message not found: " + code;
        }
    }
}