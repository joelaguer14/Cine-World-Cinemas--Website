/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.filter;

/**
 *
 * @author felig
 */
import CineWorldCinemas.logic.User;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class RestfulFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        // @DenyAll on the method takes precedence over @RolesAllowed and @PermitAll
        if (method.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return;
        };
        // @RolesAllowed on the method takes precedence over @PermitAll
        RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
            return;
        };
        // @PermitAll on the method takes precedence over @RolesAllowed on the class
        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }
        // @DenyAll can't be attached to classes
        // @RolesAllowed on the class takes precedence over @PermitAll on the class
        rolesAllowed = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
            return;
        };
        // @PermitAll on the class
        if (resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class)) {
            return;
        }
        // Authentication is required for non-annotated methods
        if (!isAuthenticated()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        };
    }

    private void performAuthorization(String[] rolesAllowed, ContainerRequestContext requestContext) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (rolesAllowed.length > 0 && user == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        for (final String role : rolesAllowed) {
            if (String.valueOf(user.getIsAdmin()).equals(role)) {
                return;
            }
        }
        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
    }

    private boolean isAuthenticated() {
        return request.getSession().getAttribute("user") != null;
    }
}
