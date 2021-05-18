package com.example.flights.controller;

import com.example.flights.domain.Role;
import com.example.flights.domain.User;
import com.example.flights.security.JwtProvider;
import com.example.flights.service.UserService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private LoginDto loginDto = new LoginDto("username", "password", "firstname", "lastname", "country");
    private User user = new User(loginDto.getUsername(), loginDto.getPassword(), new Role(),
            loginDto.getFirstName(), loginDto.getLastName(), loginDto.getCountry());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtProvider jwtProvider;

    @MockBean
    private UserService service;

    @Test
    public void signin() {
        restTemplate.postForEntity("/users/signin", new LoginDto("admin", "mypass"), Void.class);
        verify(this.service).signin("admin", "mypass");
    }

    @Test
    public void signup() {
        when(service.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName(), loginDto.getCountry())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = restTemplate.exchange("/users/signup", POST,
                new HttpEntity(loginDto, withRole("ROLE_ADMIN")), User.class);

        MatcherAssert.assertThat(response.getStatusCode().value(), is(201));
        MatcherAssert.assertThat(response.getBody().getUsername(), is(user.getUsername()));
        MatcherAssert.assertThat(response.getBody().getFirstName(), is(user.getFirstName()));
        MatcherAssert.assertThat(response.getBody().getLastName(), is(user.getLastName()));
        MatcherAssert.assertThat(response.getBody().getRoles().size(), is(user.getRoles().size()));
    }

    @Test
    public void getAllUsers() {
        when(service.getAll()).thenReturn(Arrays.asList(user));

        ResponseEntity<List<User>> response =restTemplate.exchange("/users", GET,
                new HttpEntity(withRole("ROLE_ADMIN")),
                new ParameterizedTypeReference<List<User>>() {});

        MatcherAssert.assertThat(response.getStatusCode().value(), is(200));
        MatcherAssert.assertThat(response.getBody().get(0).getUsername(), is(user.getUsername()));
    }

    private HttpHeaders withRole(String roleName) {
        HttpHeaders headers = new HttpHeaders();
        Role role = new Role();
        role.setRoleName(roleName);
        String token = jwtProvider.createToken("anonymous", Arrays.asList(role));
        headers.setContentType(APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}