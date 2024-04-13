package net.skhu.tastyinventory_be.oauth2;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryOAuth2UserDetailsRepository {
    private final Map<String, UserDetails> userDetailsMap = new HashMap<>();

    public void saveUserDetails(String state, UserDetails userDetails) {
        userDetailsMap.put(state, userDetails);
    }

    public UserDetails getUserDetails(String state) {
        return userDetailsMap.get(state);
    }

    public UserDetails deleteUserDetails(String state) {
        return userDetailsMap.remove(state);
    }
}
