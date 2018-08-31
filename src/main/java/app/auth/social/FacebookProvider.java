package app.auth.social;

import app.entity.User;
import app.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

@Service
public class FacebookProvider {

    @Autowired
    private BaseProvider baseProvider;

    public User getUserDataFromFacebook() {
        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) != null) {
            Facebook facebook = baseProvider.getFacebook();
            org.springframework.social.facebook.api.User facebookUserProfile = facebook.userOperations().getUserProfile();
            User user = new User();
            user.setEmail(facebookUserProfile.getEmail());
            user.setName(facebookUserProfile.getFirstName() + " " + facebookUserProfile.getLastName());
        //    userForm.setImage(user.getCover().getSource());
           // userForm.setProvider(FACEBOOK);
            return user;
        } else {
            throw new AppException("Can't establish connection with facebook!");
        }
    }
}
