package com.sds.config;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author cs
 * @date 2020/8/27
 * @description /swagger-ui/index.html
 */
@Configuration
@Data
public class Swagger3Config {

  @Value("${swagger.enabled}")
  private boolean enabled;

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .enable(enabled)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("接口文档")
        .description("说点什么吧")
        .contact(new Contact("caoshuai", "#", "unloserv@126.com"))
        .version("1.0")
        .build();
  }
}
