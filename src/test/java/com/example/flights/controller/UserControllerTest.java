package com.example.flights.controller;

import com.example.flights.domain.Role;
import com.example.flights.domain.User;
import com.example.flights.security.JwtProvider;
import com.example.flights.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private LoginDto loginDto = new LoginDto("username", "password", "firstname", "lastname");
    private User user = new User(loginDto.getUsername(), loginDto.getPassword(), new Role(),
            loginDto.getFirstName(), loginDto.getLastName());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtProvider jwtProvider;

    @MockBean
    private UserService service;

    @Test
    void signin() {
        restTemplate.postForEntity("/users/signin", new LoginDto("admin", "pass"),
                Void.class);
        verify(this.service).signin("admin", "pass");
    }

    @Test
    void signup() {
        when(service.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = restTemplate.exchange("/users/signup",
                POST, new HttpEntity(loginDto, withRole("ROLE_ADMIN")), User.class);

        assertThat(response.getStatusCode().value(), is(201));
        assertThat(response.getBody().getUsername(), is(user.getUsername()));
        assertThat(response.getBody().getFirstName(), is(user.getFirstName()));
        assertThat(response.getBody().getLastName(), is(user.getLastName()));
        assertThat(response.getBody().getRoles().size(), is(user.getRoles().size()));
    }

    @Test
    void getAllUsers() {
        when(service.getAll()).thenReturn(Arrays.asList(user));

        ResponseEntity<List<User>> response = restTemplate.exchange("/users",
                GET, new HttpEntity(withRole("ROLE_ADMIN")),
                new ParameterizedTypeReference<List<User>>() { });

        assertThat(response.getStatusCode().value(), is(200));
        assertThat(response.getBody().get(0).getUsername(), is(user.getUsername()));
    }

    private HttpHeaders withRole(String roleName) {
        HttpHeaders headers = new HttpHeaders();
        Role role = new Role();
        role.setRoleName(roleName);
        String token = jwtProvider.createToken("username", Arrays.asList(role));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer" + token);
        return headers;
    }
}