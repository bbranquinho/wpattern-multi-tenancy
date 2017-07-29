package br.com.wpattern.multi.tenancy

import br.com.wpattern.multi.tenancy.utils.TenantHandle
import br.com.zup.spring.tenant.TenantConfig
import br.com.zup.spring.tenant.TenantContextHolder
import org.apache.log4j.LogManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.*
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.net.InetAddress

@Configuration
@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration
@Import(TenantConfig::class)
@PropertySource(value = "classpath:tenant-postgresql.properties")
@ComponentScan(basePackageClasses = arrayOf(Application::class))
open class Application {

    companion object {
        val LOGGER = LogManager.getLogger(Application::class.java)

        @JvmStatic fun main(args: Array<String>) {
            TenantContextHolder.set(TenantHandle.DEFAULT_TENANT)
            val app = SpringApplication.run(Application::class.java, *args)

            val applicationName = app.environment.getProperty("spring.application.name")
            val contextPath = app.environment.getProperty("server.contextPath") ?: ""
            val port = app.environment.getProperty("server.port")
            val hostAddress = InetAddress.getLocalHost().hostAddress

            LOGGER.info("""|
                   |------------------------------------------------------------
                   |Application '$applicationName' is running! Access URLs:
                   |   Local:      http://127.0.0.1:$port$contextPath
                   |   External:   http://$hostAddress:$port$contextPath
                   |------------------------------------------------------------""".trimMargin())
        }
    }

    @Bean
    open fun newsApi() =
            Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.regex("/.*"))
                    .build()

    private fun apiInfo() =
            ApiInfoBuilder()
                    .title("WPattern Multi-Tenancy")
                    .description("WPattern Multi-Tenancy")
                    .version("1.0.0-SNAPSHOT")
                    .build()

}
