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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}
