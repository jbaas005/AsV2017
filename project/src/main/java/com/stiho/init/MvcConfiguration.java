package com.stiho.init;

import com.stiho.service.AttachmentUploadService;
import com.stiho.service.AttachmentUploadServiceImpl;
import java.util.Locale;
import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The configuration class for the application
 *
 * @see
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html
 * @author IS204_1
 */
@Configuration
@ComponentScan("com.stiho")
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:application.properties")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    private static final String PROPERTY_NAME_MAIL_HOST = "mail.host";
    private static final String PROPERTY_NAME_MAIL_PORT = "mail.port";
    private static final String PROPERTY_NAME_MAIL_USERNAME = "mail.username";
    private static final String PROPERTY_NAME_MAIL_PASSWORD = "mail.password";

    private static final String PROPERTY_NAME_UPLOAD_PATH = "upload.path";

    @Resource
    private Environment env;

    /**
     * Set up the data source
     *
     * @return data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    /**
     * Set up the session factory
     *
     * @return session factory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    /**
     * Hibernate properties
     *
     * @return
     */
    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        return properties;
    }

    /**
     * Set up Hibernate transaction manager bean
     *
     * @return hibernate transaction manager
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    /**
     * Set up the view resolver
     *
     * @return view resolver
     */
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Add resource handlers
     *
     * @see http://fruzenshtein.com/spring-mvc-resources/
     * @see
     * http://www.mytechnotes.biz/2012/11/serving-static-resources-with-spring-mvc.html
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    /**
     * @see https://gist.github.com/marcaurele/3665696
     * @return multipartresolver
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
        return multipartResolver;
    }

    /**
     * Set up the mail sender instance
     *
     * @see
     * http://stackoverflow.com/questions/22483407/send-emails-with-spring-by-using-java-annotations
     * @return the mailsender instance
     */
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(env.getRequiredProperty(PROPERTY_NAME_MAIL_HOST));
        javaMailSender.setPort(Integer.parseInt(env.getRequiredProperty(PROPERTY_NAME_MAIL_PORT)));
        javaMailSender.setUsername(env.getRequiredProperty(PROPERTY_NAME_MAIL_USERNAME));
        javaMailSender.setPassword(env.getRequiredProperty(PROPERTY_NAME_MAIL_PASSWORD));

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    /**
     * Get a properties object with mail settings
     *
     * @return properties object
     */
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        return properties;
    }

    /**
     * Configure the attachment upload service for the dependency container
     *
     * @return the configured attachment upload service
     */
    @Bean
    public AttachmentUploadService attachmentUploadService() {
        AttachmentUploadServiceImpl aus = new AttachmentUploadServiceImpl();
        aus.setUploadPath(env.getRequiredProperty(PROPERTY_NAME_UPLOAD_PATH));

        return aus;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages","i18n/complaint","i18n/index","i18n/faq");
        messageSource.setCacheSeconds(1);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * For the language interceptor on the request parameter.
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * To save the locale in a cookie on the browser, and not in the session.
     *
     * @return
     */
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("locale");
        localeResolver.setDefaultLocale(new Locale("nl"));
        return localeResolver;
    }

}
