/*
 *
 *  * MIT License
 *  *
 *  * Copyright (@Kyouuma) [2020] [oussamahafsi.official@gmail.com]
 *
 */

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

/**
 * OpenAPI definition
 */
@OpenAPIDefinition(
        info = @Info(
                title="Quarkus Keycloak API",
                version = "1.0.0",
                contact = @Contact(
                        name = "API Support",
                        url = "https://github.com/Kyouuma",
                        email = "oussamahafsi.official@gmail.com"),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"))
)
public class QuarkusKeycloakApiApplication extends Application {
}
