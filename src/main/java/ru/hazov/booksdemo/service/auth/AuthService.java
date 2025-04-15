package ru.hazov.booksdemo.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.exception.entity_exceptions.person.PersonNotFoundException;
import ru.hazov.booksdemo.repository.PersonRepository;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;

    public AuthService(AuthenticationManager authenticationManager, PersonRepository personRepository) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
    }

    public Person authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        return personRepository.findByEmail(email).orElseThrow(PersonNotFoundException::new);
    }
}
