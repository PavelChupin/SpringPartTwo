package com.geekbrains.july.market.julymarket.services;

import com.geekbrains.july.market.julymarket.entities.Role;
import com.geekbrains.july.market.julymarket.entities.User;
import com.geekbrains.july.market.julymarket.exceptions.UserIsBlockEception;
import com.geekbrains.july.market.julymarket.exceptions.UserNotFoundException;
import com.geekbrains.july.market.julymarket.repositories.RolesRepository;
import com.geekbrains.july.market.julymarket.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Optional<User> findByPhone(String phone) {
        return usersRepository.findOneByPhone(phone);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findOneByPhone(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        if (!user.getBlock()){
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));}

        throw new UserIsBlockEception("User is block!");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can't found user with id = " + id));
    }

    @Transactional
    public void blockUnblockUser(Long id, boolean block) {
        User user = usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can't found user with id = " + id));
        user.setBlock(block);
        usersRepository.save(user);
    }
}
