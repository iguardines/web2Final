package ar.edu.uces.progweb2.booksmov.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MessageUtils {
 
    public static String getMessage(String key) {
 
        try {
        	Locale locale = LocaleContextHolder.getLocale();
            MessageSource bean = ApplicationContextProvider.getContext().getBean(MessageSource.class);
            return bean.getMessage(key, null, locale);
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
 
    }
}