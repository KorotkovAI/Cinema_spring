package cinema.hib.service;

import org.springframework.context.support.StaticApplicationContext;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

public class MethodValidationProxyFactory {
    private static final StaticApplicationContext ctx = new StaticApplicationContext();

    static {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.afterPropertiesSet();
        ctx.getBeanFactory().addBeanPostProcessor(processor);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T instance) {

        return (T) ctx.getAutowireCapableBeanFactory()
                .applyBeanPostProcessorsAfterInitialization(instance, instance.getClass().getName());
    }
}
