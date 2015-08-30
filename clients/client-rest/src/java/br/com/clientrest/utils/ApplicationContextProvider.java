package br.com.clientrest.utils;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider { 

    private Logger logger = Logger.getLogger(ApplicationContextProvider.class);
    private ApplicationContext applicationContext; 
    private static final ApplicationContextProvider provider = new ApplicationContextProvider(); 

    private ApplicationContextProvider() throws ExceptionInInitializerError { 
        try {
            this.applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml"); 
        } catch (Throwable ex) { 
            logger.error("Falha ao carregar o applicationContext -- " + ex.getMessage(), ex); 
            throw new ExceptionInInitializerError(ex); 
        } 

    } 

    public synchronized static ApplicationContextProvider getInstance() throws ExceptionInInitializerError { 
        return provider; 
    } 

    public ApplicationContext getApplicationContext() { 
        return applicationContext; 
    } 

    public void setApplicationContext(ApplicationContext applicationContext) { 
        this.applicationContext = applicationContext; 
    } 
}