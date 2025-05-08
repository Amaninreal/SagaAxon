package com.progressivecoder.ecommerce.config;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class XStreamConfig {

    /**
     * Configure XStream with permissive settings
     */
    @Bean
    @Primary
    public XStream xStream() {
        XStream xStream = new XStream();

        // Clear existing permissions and set safe defaults
        XStream.setupDefaultSecurity(xStream);

        // Allow types from your packages
        xStream.allowTypesByWildcard(new String[] {
                "com.progressivecoder.ecommerce.**",
                "java.util.**",
                "java.lang.**",
                "java.time.**"
        });

        // Add specific classes you need to serialize/deserialize
        Class<?>[] allowedClasses = new Class<?>[] {
                com.progressivecoder.ecommerce.commands.CreateOrderCommand.class,
                // Add other command classes here if needed
        };
        xStream.allowTypes(allowedClasses);

        return xStream;
    }

    /**
     * Create primary serializer using our configured XStream
     */
    @Bean
    @Primary
    public XStreamSerializer xStreamSerializer(XStream xStream) {
        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}