/*
MIT License
Copyright (@Kyouuma) [2020] [oussamahafsi.official@gmail.com]
 */
package controller;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonString;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * Simple REST Resource that consumes information provided by a JWT token.
 * <p>
 * Note that {@code RequestScoped} is explicitly needed here, since Quarkus changed the default
 * scope for JAX-RS Resources to be {@code ApplicationScoped}.
 */
@Path("/api/data")
@RequestScoped
public class UserController {

    private static final JsonString ANOYNMOUS = Json.createValue("anonymous");

    @Inject
    @Claim("raw_token")
    String rawToken;

    @Inject
    @Claim(standard = Claims.sub)
    Optional<JsonString> subject;

    @Inject
    @Claim(standard = Claims.preferred_username)
    Optional<JsonString> currentUsername;

    @GET
    @Path("/user")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"user"})
    public String userData() {
        return "data for user " + currentUsername.orElse(ANOYNMOUS);
    }

    @GET
    @Path("/editor")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"editor"})
    public String editorData() {
        return "data for editor " + currentUsername.orElse(ANOYNMOUS);
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public String adminData() {
        return "data for admin " + currentUsername.orElse(ANOYNMOUS);
    }
}