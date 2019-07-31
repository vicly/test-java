package vic.test.springboot.app.jersey;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;

/**
 * @author Vic Liu
 */
@ConfigurationProperties(prefix = "example")
@Validated // DEMO: config - Bean Validation
public class ExampleConfiguration {

    // DEMO: config - boolean (int is similar)
    private boolean enabled;

    // DEMO: config - nest obj
    @Valid
    private Security security;

    private String greeting;

    // DEMO: config - array
    private Map<String, ClientConfiguration> services;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Map<String, ClientConfiguration> getServices() {
        return services;
    }

    public void setServices(Map<String, ClientConfiguration> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enabled", enabled)
                .add("security", security)
                .add("greeting", greeting)
                .add("services", services)
                .toString();
    }

    public static class Security {
        @NotEmpty
        private String username;
        private String password;
        // DEMO: config - array
        private Set<String> roles = Sets.newHashSet();

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Set<String> getRoles() {
            return roles;
        }

        public void setRoles(Set<String> roles) {
            this.roles = roles;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("username", username)
                    .add("password", password)
                    .add("roles", roles)
                    .toString();
        }
    }
}
