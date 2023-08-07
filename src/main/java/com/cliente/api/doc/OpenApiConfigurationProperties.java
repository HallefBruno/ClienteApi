package com.cliente.api.doc;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.cliente.api")
public class OpenApiConfigurationProperties {

    private OpenAPI openApi = new OpenAPI();

    @Data
    public class OpenAPI {

        private String title;
        private String description;
        private String apiVersion;
        private Contact contact = new Contact();

        @Data
        public class Contact {

            private String email;
            private String name;
            private String url;
            private String portifio;
            private String usuario;
            private String password;
        }

    }

}
