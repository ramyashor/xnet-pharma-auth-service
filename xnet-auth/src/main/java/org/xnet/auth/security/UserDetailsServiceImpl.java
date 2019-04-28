package org.xnet.auth.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.xnet.auth.entity.AppUser;
import org.xnet.auth.repository.AppUserRepository;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        AppUser u = new AppUser(1, "ramy", encoder.encode("123456"), "Ramy", "Ashour", "ramyashor@gmail.com", "00201013143717", "ADMIN");
//        save(u);
//        // hard coding the users. All passwords must be encoded.
//        final List<AppUser> users = Arrays.asList(
//                new AppUser(1, "omar", encoder.encode("12345"), "USER"),
//                new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
//        );
//
//        for (AppUser appUser : users) {
//            if (appUser.getUsername().equals(username)) {
//
//                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
//                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
//                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
//
//                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
//                // And used by auth manager to verify and check user authentication.
//                return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
//            }
//        }
        //find user in db by username
        AppUser appUser = appUserRepository.findByUsername(username);
        // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
        // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
        // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
        // And used by auth manager to verify and check user authentication.
        return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);

    }

    public AppUser save(AppUser appUser) {
        if (appUser.getEmail() == null
                || appUser.getEmail().isEmpty()
                || appUser.getUsername() == null
                || appUser.getUsername().isEmpty()
                || appUser.getPassword() == null
                || appUser.getPassword().isEmpty()
                || appUser.getMobile() == null
                || appUser.getMobile().isEmpty()) {
            return null;
        }
        return appUserRepository.save(appUser);
    }
}
