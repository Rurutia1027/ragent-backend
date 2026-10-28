/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nageoffer.ai.ragent.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI 文档入口。
 * 接口仍按现有 Controller 暴露，这里按业务模块分组。
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ragentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ragent API")
                        .version("0.0.1")
                        .description("HTTP API for the Ragent backend. Responses keep the existing Result envelope."));
    }

    @Bean
    public GroupedOpenApi userApi() {
        return group("user", "com.nageoffer.ai.ragent.user.controller");
    }

    @Bean
    public GroupedOpenApi ragApi() {
        return group("rag", "com.nageoffer.ai.ragent.rag");
    }

    @Bean
    public GroupedOpenApi knowledgeApi() {
        return group("knowledge", "com.nageoffer.ai.ragent.knowledge.controller");
    }

    @Bean
    public GroupedOpenApi ingestionApi() {
        return group("ingestion", "com.nageoffer.ai.ragent.ingestion.controller");
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return group("admin", "com.nageoffer.ai.ragent.admin.controller");
    }

    private static GroupedOpenApi group(String name, String basePackage) {
        return GroupedOpenApi.builder()
                .group(name)
                .packagesToScan(basePackage)
                .build();
    }
}
