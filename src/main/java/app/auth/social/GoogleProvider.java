package app.auth.social;

import app.entity.User;
import app.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;

public class GoogleProvider {


    @Autowired
    private BaseProvider baseProvider;

    public User getUserDataFromFacebook() {
        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Google.class) != null) {
            Google google = baseProvider.getGoogle();
            Person googleUser = google.plusOperations().getGoogleProfile();
            User user = new User();
            user.setEmail(googleUser.getAccountEmail());
            user.setName(googleUser.getGivenName() + " " + googleUser.getFamilyName());
            //    userForm.setImage(user.getCover().getSource());
            // userForm.setProvider(FACEBOOK);
            return user;
        } else {
            throw new AppException("Can't establish connection with google!");
        }
    }
}
