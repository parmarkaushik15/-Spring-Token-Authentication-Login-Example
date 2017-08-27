package net.kaushik.example.angularrestspringsecurity.service;

import net.kaushik.example.angularrestspringsecurity.entity.AccessToken;
import net.kaushik.example.angularrestspringsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Kaushik Parmar
 */
public interface UserService extends UserDetailsService
{
    User findUserByAccessToken(String accessToken);

    AccessToken createAccessToken(User user);
}
