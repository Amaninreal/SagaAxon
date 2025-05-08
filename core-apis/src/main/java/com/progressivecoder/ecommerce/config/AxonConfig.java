package com.progressivecoder.ecommerce.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.Configuration;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

@org.springframework.context.annotation.Configuration
public class AxonConfig {

    @Bean
    public Serializer xstreamSerializer() {
        XStream xstream = new XStream();

        // Clear existing permissions
        xstream.addPermission(NoTypePermission.NONE);

        // Allow framework/internal classes
        xstream.allowTypesByWildcard(new String[] {
                "org.axonframework.**",
                "java.**",
                "javax.**",
                "com.thoughtworks.xstream.**"
        });

        // Allow application classes
        xstream.allowTypesByWildcard(new String[] {
                "com.progressivecoder.coreapis.**",
                "com.progressivecoder.ecommerce.**"
        });

        // Basic permissions
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);

        return XStreamSerializer.builder()
                .xStream(xstream)
                .build();
    }

    @Bean
    public Configuration axonConfiguration(Serializer serializer) {
        return DefaultConfigurer.defaultConfiguration()
                .configureSerializer(c -> serializer)
                .configureMessageSerializer(c -> serializer)
                .configureEventSerializer(c -> serializer)
                .buildConfiguration();
    }
}