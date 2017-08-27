package net.kaushik.example.angularrestspringsecurity.dao.user;

import net.kaushik.example.angularrestspringsecurity.dao.Dao;
import net.kaushik.example.angularrestspringsecurity.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDao extends Dao<User, Long>
{
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByName(String name);
}
