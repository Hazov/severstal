package ru.hazov.booksdemo.entity.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.hazov.booksdemo.entity.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class PersonDetails implements UserDetails {
    private final Person person;
    public PersonDetails(Person person){
        this.person = person;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(person.getRole().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() { return person.getPassword(); }

    @Override
    public String getUsername() { return person.getEmail(); }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() {return true; }
    @Override
    public boolean isEnabled() { return true; }
}
