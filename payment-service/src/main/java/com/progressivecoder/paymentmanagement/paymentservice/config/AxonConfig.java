package com.progressivecoder.paymentmanagement.paymentservice.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;


@org.springframework.context.annotation.Configuration
public class AxonConfig {

    @Primary
    @Bean
    public Serializer xstreamSerializer() {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[] {
                "org.axonframework.**",
                "java.**",
                "javax.**",
                "com.thoughtworks.xstream.**"
        });

        xstream.allowTypesByWildcard(new String[] {
                "com.progressivecoder.coreapis.**",
                "com.progressivecoder.ecommerce.**"
        });

        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);

        return XStreamSerializer.builder()
                .xStream(xstream)
                .build();
    }

}