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

package com.nageoffer.ai.ragent.mcp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MCP 进程的 OpenAPI 文档。
 * /mcp 是直接注册的 Servlet，不会被 Controller 扫描扫到，所以在这里补上。
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mcpOpenAPI() {
        Operation call = new Operation()
                .operationId("mcp")
                .summary("MCP streamable HTTP")
                .description("JSON-RPC messages for the MCP server. The body is a JSON-RPC request.")
                .requestBody(new RequestBody().content(new Content()
                        .addMediaType("application/json", new MediaType().schema(new StringSchema()))))
                .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("JSON-RPC response")));

        return new OpenAPI()
                .info(new Info()
                        .title("Ragent MCP")
                        .version("0.0.1")
                        .description("MCP server. Tools are served at /mcp."))
                .path("/mcp", new PathItem().post(call).get(new Operation()
                        .operationId("mcpStream")
                        .summary("MCP stream")
                        .responses(new ApiResponses().addApiResponse("200", new ApiResponse().description("Event stream")))));
    }
}
