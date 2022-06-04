package statisticservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,//enable @PreAuthorize & @PostAuthorize
        securedEnabled = true,//enable @Secured
        jsr250Enabled = true//enable @RolesAllowed
)
public class ServerSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}

