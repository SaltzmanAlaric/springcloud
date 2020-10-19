package com.study.common.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * swagger配置.
 */
@Getter
@Setter
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class DefaultSwagger2Config {

    private String title;
    private String description;
    private String version;
    private String basePackage;
    private String publishDate;
    private String active;
    private String termsOfServiceUrl;

    @Bean
    public Docket defaultDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                //.globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                //.globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .forCodeGeneration(true)
                //.groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                //.paths(PathSelectors.regex(pathRegex))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo() {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (StringUtils.isEmpty(publishDate)) {
            publishDate = now;
        }
        if (StringUtils.isEmpty(active)) {
            active = "default";
        }
        return (new ApiInfoBuilder()).title(title).description(description + "<br/>部署时间：" + publishDate + "<br/>启动时间：" + now + "<br/>部署模式：" + active).termsOfServiceUrl(termsOfServiceUrl).version(version).build();
    }
}