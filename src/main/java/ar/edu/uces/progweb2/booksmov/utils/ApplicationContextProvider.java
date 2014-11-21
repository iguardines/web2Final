package ar.edu.uces.progweb2.booksmov.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;
 
    public static ApplicationContext getContext() {
 
        if (context != null) {
            return context;
        }
       return null;
 
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)  {
 
        if (context == null) {
            ApplicationContextProvider.context = applicationContext;
        }
 
    }

}