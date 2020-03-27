package ru.itis.springbootdemo.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.springbootdemo.dto.RegistrationDto;
import ru.itis.springbootdemo.models.Role;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExecutorService threadPool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Configuration configuration;

    @Autowired
    private SMSService smsService;


    @Override
    public void registration(RegistrationDto form) {

        String rawPassword = form.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);


        User user = User.builder()
                .email(form.getEmail())
                .password(hashPassword)
                .name(form.getName())
                .createdAt(LocalDateTime.now())
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .role(Role.USER)
                .phoneNumber(form.getPhoneNumber())
                .build();

        usersRepository.save(user);

        String link = "http://localhost:8080/confirm/" + user.getConfirmCode();

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("link", link);

        try {
            stringBuilder.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(configuration.getTemplate("index.txt"), model));
            System.out.println("привет я в registration" + stringBuilder.toString());

        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }

        threadPool.submit(() -> {
            emailService.sendMail("Регистрация"
                    , stringBuilder.toString()
                    , user.getEmail());

            String text = "Вы зарегестрированы!!!!! Congratulations , ваш e-mail: " + user.getEmail();

            smsService.sendSms(user, text);
        });


    }
}
