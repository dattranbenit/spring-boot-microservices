package accountservice.client.statistic;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class StatisticFeignClientConfiguration {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${server.oauth2.token.url}")
    private String tokenUrl;

    @Value("${client.statistic.client.id}")
    private String clientId;

    @Value("${client.statistic.client.secret}")
    private String clientSecret;

    @Value("${client.statistic.client.scopes}")
    private List<String> scopes;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resourceDetails());
    }

    private OAuth2ProtectedResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();//force client(server) to identify itself when connect to another server
        details.setGrantType("client_credentials");
        details.setAccessTokenUri(tokenUrl);//get token from url
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(scopes);//register scopes to access other servers methods
        return details;
    }
}

