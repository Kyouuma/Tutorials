
/*
 * MIT License
 *
 * Copyright (@Kyouuma) [2020] [oussamahafsi.official@gmail.com]
 */

package config;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

/**
 * MicroProfile Health allows applications to provide information about their state to external
 * viewers which is typically useful in cloud environments where automated processes
 * must be able to determine whether the application should be discarded or restarted.
 *
 * @Liveness - the liveness check accessible at /health/live
 */
@Liveness
@ApplicationScoped
/**
 HealthCheck is a functional interface whose single method call
 returns a HealthCheckResponse object which can be easily constructed by the fluent builder API
 */
public class LivenessHealthCheck implements HealthCheck {

    /**
     * The general status of the health check is computed as a logical AND of all the declared health check procedures.
     *
     * @return JSON object
     * status — the overall result of all the health check procedures
     * checks — an array of individual checks
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("Liveness Probe");
    }
}
