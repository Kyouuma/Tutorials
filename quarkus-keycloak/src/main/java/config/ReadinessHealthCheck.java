/*
 * MIT License
 *
 * Copyright (@Kyouuma) [2020] [oussamahafsi.official@gmail.com]
 */

package config;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MicroProfile Health allows applications to provide information about their state to external
 * viewers which is typically useful in cloud environments where automated processes
 * must be able to determine whether the application should be discarded or restarted.
 *
 * @Readiness - the Readiness check accessible at /health/ready
 */
@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String REALM_URL;
    private URL url;
    private boolean kc_present;


    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Readiness Probe")
                .withData("Keycloak URL", REALM_URL);
        try {
            simulateKeycloakConnectionVerification();
            responseBuilder.up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }


        return responseBuilder.build();

    }

    /**
     * Check if keycloak realm is present
     */
    private void simulateKeycloakConnectionVerification() {
        try {
            kc_present = false;
            url = new URL(REALM_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (status == 200) {
                kc_present = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!kc_present) {
            throw new IllegalStateException("Cannot contact keycloak Server");
        }
    }

}
