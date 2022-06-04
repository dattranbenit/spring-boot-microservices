package accountservice.client.notification;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class NotificationFeignClientConfiguration {

    @Value("${server.oauth2.token.url}")
    private String tokenUrl;

    @Value("${client.notification.client.id}")
    private String clientId;

    @Value("${client.notification.client.secret}")
    private String clientSecret;

    @Value("${client.notification.client.scopes}")
    private List<String> scopes;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resourceDetails());
    }

    private OAuth2ProtectedResourceDetails resourceDetails() {
        final ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();//force client(server) to identify itself when connect to another server
        details.setAccessTokenUri(tokenUrl);//get token from url
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(scopes);//register scopes to access other servers methods
        return details;
    }
}
