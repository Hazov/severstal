package ru.hazov.booksdemo.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.entity.security.PersonDetails;
import ru.hazov.booksdemo.repository.PersonRepository;

import java.util.Optional;


@Service
public class PersonDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(username);


        return user.map(PersonDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(username+"There is not such person in REPO"));
    }
}
