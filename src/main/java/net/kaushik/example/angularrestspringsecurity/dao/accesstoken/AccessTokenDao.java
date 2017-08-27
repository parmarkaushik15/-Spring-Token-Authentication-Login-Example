package net.kaushik.example.angularrestspringsecurity.dao.accesstoken;

import net.kaushik.example.angularrestspringsecurity.dao.Dao;
import net.kaushik.example.angularrestspringsecurity.entity.AccessToken;

/**
 * @author Kaushik Parmar
 */
public interface AccessTokenDao extends Dao<AccessToken, Long>
{
    AccessToken findByToken(String accessTokenString);
}
