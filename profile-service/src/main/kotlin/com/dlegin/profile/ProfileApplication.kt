package com.dlegin.profile

import com.dlegin.profile.profile.repository.ProfileRepository
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
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
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
	lateinit var profileRepository: ProfileRepository

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
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(Application::class.java, *args)
		}
	}
}