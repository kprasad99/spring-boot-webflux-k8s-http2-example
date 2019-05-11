package io.github.kprasad99.sb.webflux;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import io.github.kprasad99.sb.webflux.WebFluxHttpServerConfig.OnHttpsServer;

@Configuration
@Conditional(OnHttpsServer.class)
public class WebFluxHttpServerConfig {

    public static class OnHttpsServer implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Environment env = context.getEnvironment();
            Boolean httpsEnabled = env.getProperty("server.ssl.enabled", Boolean.class);
            Integer httpPort = env.getProperty("server.http.port", Integer.class);
            if (httpsEnabled != null && httpsEnabled && httpPort != null && httpPort.intValue() > 0) {
                return true;
            }
            return false;
        }

    }

    @Bean
    public UndertowReactiveWebServerFactory undertowServletWebServerFactory(@Value("${server.http.port}") int port) {
        UndertowReactiveWebServerFactory factory = new UndertowReactiveWebServerFactory();
        factory.addBuilderCustomizers((UndertowBuilderCustomizer) builder -> builder.addHttpListener(port, "0.0.0.0"));
        return factory;
    }
}
