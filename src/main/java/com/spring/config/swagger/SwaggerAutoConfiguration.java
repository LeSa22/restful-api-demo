/*
 * Copyright 2016-2019 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spring.config.swagger;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;

import com.spring.config.Properties;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springfox Swagger configuration.
 * <p>
 * Warning! When having a lot of REST endpoints, Springfox can become a performance issue.
 * In that case, you can use the "no-swagger" Spring profile, so that this bean is ignored.
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({
    ApiInfo.class,
    BeanValidatorPluginsConfiguration.class,
    Servlet.class,
    DispatcherServlet.class
})
@AutoConfigureAfter(Properties.class)
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerAutoConfiguration {

    static final String STARTING_MESSAGE = "Starting Swagger";
    static final String STARTED_MESSAGE = "Started Swagger in {} ms";
    static final String MANAGEMENT_TITLE_SUFFIX = "Management API";
    static final String MANAGEMENT_GROUP_NAME = "management";
    static final String MANAGEMENT_DESCRIPTION = "Management endpoints documentation";

    private final Logger log = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);

    private final Properties.Swagger properties;
	
    public SwaggerAutoConfiguration(Properties properties) {
	    this.properties = properties.getSwagger();
	}

    /**
     * Springfox configuration for the API Swagger docs.
     *
     * @param swaggerCustomizers Swagger customizers
     * @param alternateTypeRules alternate type rules
     * @return the Swagger Springfox configuration
     */
    @Bean
    @ConditionalOnMissingBean(name = "swaggerSpringfoxApiDocket")
    public Docket swaggerSpringfoxApiDocket(List<SwaggerCustomizer> swaggerCustomizers,
        ObjectProvider<AlternateTypeRule[]> alternateTypeRules) {
        log.debug(STARTING_MESSAGE);
        StopWatch watch = new StopWatch();
        watch.start();

        Docket docket = createDocket();

        // Apply all SwaggerCustomizers orderly.
        swaggerCustomizers.forEach(customizer -> customizer.customize(docket));

        // Add all AlternateTypeRules if available in spring bean factory.
        // Also you can add your rules in a customizer bean above.
        Optional.ofNullable(alternateTypeRules.getIfAvailable()).ifPresent(docket::alternateTypeRules);

        watch.stop();
        log.debug(STARTED_MESSAGE, watch.getTotalTimeMillis());
        return docket;
    }

    /**
     * Swagger Customizer
     *
     * @return the Swagger Customizer
     */
    @Bean
    public AppSwaggerCustomizer testSwaggerCustomizer() {
        return new AppSwaggerCustomizer(properties);
    }

    /**
     * Springfox configuration for the management endpoints (actuator) Swagger docs.
     *
     * @param appName               the application name
     * @param managementContextPath the path to access management endpoints
     * @return the Swagger Springfox configuration
     */
    @Bean
    @ConditionalOnProperty("management.endpoints.web.base-path")
    @ConditionalOnExpression("'${management.endpoints.web.base-path}'.length() > 0")
    @ConditionalOnMissingBean(name = "swaggerSpringfoxManagementDocket")
    public Docket swaggerSpringfoxManagementDocket(@Value("${spring.application.name:application}") String appName,
        @Value("${management.endpoints.web.base-path}") String managementContextPath) {
    
        ApiInfo apiInfo = new ApiInfo(
            StringUtils.capitalize(appName) + " " + MANAGEMENT_TITLE_SUFFIX,
            MANAGEMENT_DESCRIPTION,
            properties.getVersion(),
            "",
            ApiInfo.DEFAULT_CONTACT,
            "",
            "",
            new ArrayList<>()
        );
    
        return createDocket()
        	//.securitySchemes(Lists.newArrayList(apiKey()))
            //.securityContexts(Collections.singletonList(securityContext()))
            .apiInfo(apiInfo)
            .useDefaultResponseMessages(properties.isUseDefaultResponseMessages())
            .groupName(MANAGEMENT_GROUP_NAME)
            .host(properties.getHost())
            .protocols(new HashSet<>(Arrays.asList(properties.getProtocols())))
            .forCodeGeneration(true)
            .directModelSubstitute(ByteBuffer.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(Pageable.class)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.spring.controller"))
            .paths(PathSelectors.any())
            .build();
    }
    
    protected Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
