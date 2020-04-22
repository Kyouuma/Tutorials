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

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String REALM_URL;
    private  URL url;
    private boolean kc_present;


    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("keycloak connection health check");
        try {
            simulateKeycloakConnectionVerification();
            responseBuilder.up();

        }catch (IllegalStateException e){
            simulateKeycloakConnectionVerification();
            responseBuilder.down();
        }


        return HealthCheckResponse.up("keycloak connection health check");
    }

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
            throw new IllegalStateException("Cannot contact keycloak realm");
        }
    }

}
