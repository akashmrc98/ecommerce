package com.ecommerce.app.bootstrap;

import com.ecommerce.app.domain.User;
import com.ecommerce.app.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.ecommerce.app.repository.UserRepository;


@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User akash = new User();

        akash.setVendor("akash");
        akash.setUsername("akash");
        akash.setEmail("akashmadduru@gmail.com");
        akash.setPhone("9494535327");
        akash.setPassword(passwordEncoder.encode("akash"));
        akash.setUserRole(UserRole.ADMIN.name());
        akash.setAccountNonExpired(true);
        akash.setAccountNonLocked(true);
        akash.setEnabled(true);
        akash.setCredentialsNonExpired(true);

        User avinash = new User();

        avinash.setVendor("avinash");
        avinash.setUsername("avinash");
        avinash.setEmail("avinash@gmail.com");
        avinash.setPhone("9440575890");
        avinash.setPassword(passwordEncoder.encode("avinash"));
        avinash.setUserRole(UserRole.USER.name());
        avinash.setAccountNonExpired(true);
        avinash.setAccountNonLocked(true);
        avinash.setEnabled(true);
        avinash.setCredentialsNonExpired(true);

        User guest = new User();

        avinash.setVendor("guest");
        avinash.setUsername("guest");
        avinash.setEmail("guest@gmail.com");
        avinash.setPhone("000100010");
        avinash.setPassword(passwordEncoder.encode("guest"));
        avinash.setUserRole(UserRole.GUEST.name());
        avinash.setAccountNonExpired(true);
        avinash.setAccountNonLocked(true);
        avinash.setEnabled(true);
        avinash.setCredentialsNonExpired(true);

        userRepository.save(akash);
        userRepository.save(avinash);
        userRepository.save(guest);


    }
}
