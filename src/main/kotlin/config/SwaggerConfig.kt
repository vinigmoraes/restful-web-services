package config

import io.swagger.models.Contact
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

/**
 * @author vinicius.moraes on 9/13/18
 */

@Configuration
@EnableSwagger2
class SwaggerConfig {


    val DEFAULT_PRODUCES_AND_CONSUMES = HashSet<String>(
            Arrays.asList(
                    "application/json",
                    "application/xml"
            ))

    val DEFAULT_CONTACT = Contact()
            .name("Vinicius")
            .url("github/vinigmoraes")
            .email("emailtest@gmail.com")


    val DEFAULT_API_INFO = ApiInfo(
            "User API", "This API is responsible for manage all users",
            "1.0", "urn:tos", DEFAULT_CONTACT.name,
            "Apache 2.0" , "http://www.apache.org/licenses/LICENSE-2.0"
    )

    @Bean
    fun api() : Docket {

        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)

    }
}