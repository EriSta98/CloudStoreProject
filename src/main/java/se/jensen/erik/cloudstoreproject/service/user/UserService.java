package se.jensen.erik.cloudstoreproject.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.jensen.erik.cloudstoreproject.model.user.AppUser;
import se.jensen.erik.cloudstoreproject.model.user.dto.RegisterRequest;
import se.jensen.erik.cloudstoreproject.repository.user.AppUserRepository;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){

        AppUser user = new AppUser();

        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        appUserRepository.save(user);
    }
}
