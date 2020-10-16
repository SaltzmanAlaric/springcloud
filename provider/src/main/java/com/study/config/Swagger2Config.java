package com.study.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * swagger配置.
 */
@Getter
@Setter
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
@ConfigurationProperties(prefix = "swagger")
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String version;
    private String publishDate;
    private String active;

    /*@Bean
    public Docket createRestApi() {
        return docket("ALL", ".+");
    }
*/

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.study.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private Docket docket(String groupName, String pathRegex) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                //.globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                //.globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .forCodeGeneration(true)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.controller"))
                .paths(PathSelectors.regex(pathRegex))
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