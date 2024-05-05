package com.dlegin.cl_shop

import com.dlegin.cl_shop.order.repository.OrderRepository
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.text.SimpleDateFormat
import java.util.*


@EnableAutoConfiguration(
    exclude = [
        DataSourceAutoConfiguration::class,
        WebMvcAutoConfiguration::class
    ]
)
@SpringBootApplication
@EnableMongoRepositories
@EnableSwagger2
@EnableWebMvc
class Application {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Bean
    fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner {
        return CommandLineRunner {
            println("Let's inspect the beans provided by Spring Boot:")
            val beanNames = ctx.beanDefinitionNames
            Arrays.sort(beanNames)
            for (beanName in beanNames) {
                println(beanName)
            }
        }
    }

    @Bean
    fun webMvcConfigure(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
                val builder = Jackson2ObjectMapperBuilder()
                    .indentOutput(true)
                    .dateFormat(SimpleDateFormat("yyyy-MM-dd"))
                    .modulesToInstall(ParameterNamesModule())

                converters.add(MappingJackson2HttpMessageConverter(builder.build()))

            }
        }
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.dlegin.cl_shop.controllers"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("API Documentation")
            .description("Documentation for your API")
            .version("1.0")
            .build()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}
