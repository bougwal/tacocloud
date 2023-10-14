package tacos.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.data.UserRepository;
import tacos.security.RegistrationForm;

@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository; 
    private PasswordEncoder passwordEncoder; 

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository; 
        this.passwordEncoder = passwordEncoder; 
    }

    @GetMapping
    public String RegisterForm() { 
        return "registration"; 
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        userRepository.save(registrationForm.toUser(passwordEncoder)); 
        return "redirect:/login"; 
    }
}
