package app.auth.social;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;

@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

    private Facebook facebook;
    private Google google;
    private ConnectionRepository connectionRepository;

    public BaseProvider(ConnectionRepository connectionRepository, Facebook facebook, Google google) {
        this.connectionRepository = connectionRepository;
        this.facebook = facebook;
        this.google = google;
    }

    public ConnectionRepository getConnectionRepository() {
        return connectionRepository;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public Google getGoogle() {
        return google;
    }

    public void setConnectionRepository(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public void setGoogle(Google google) {
        this.google = google;
    }
}
