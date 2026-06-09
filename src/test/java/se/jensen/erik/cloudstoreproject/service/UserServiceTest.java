package se.jensen.erik.cloudstoreproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.jensen.erik.cloudstoreproject.model.user.AppUser;
import se.jensen.erik.cloudstoreproject.model.user.dto.RegisterRequest;
import se.jensen.erik.cloudstoreproject.repository.user.AppUserRepository;
import se.jensen.erik.cloudstoreproject.service.user.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUser() {

        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setName("John Doe");
        request.setEmail("john@example.com");
        request.setPassword("password123");

        when(passwordEncoder.encode("password123"))
                .thenReturn("encodedPassword");

        // Act
        userService.register(request);


        // Assert
        verify(passwordEncoder).encode("password123");

        // Capture the AppUser object that was passed to the repository's save method,
        // so we can inspect the content with assertEquals
        ArgumentCaptor<AppUser> userCaptor =
                ArgumentCaptor.forClass(AppUser.class);

        verify(appUserRepository).save(userCaptor.capture());

        AppUser savedUser = userCaptor.getValue();

        assertEquals("John Doe", savedUser.getName());
        assertEquals("john@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());

    }












}
