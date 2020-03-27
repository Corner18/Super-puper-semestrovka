package ru.itis.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.springbootdemo.models.User;

import java.util.concurrent.ExecutorService;

@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    private ExecutorService threadPool;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${sms.aero.email}")
    private String smsAeroEmail;

    @Value("${sms.aero.api-key}")
    private String smsAeroApiKey;

    @Value("${sms.aero.from}")
    private String smsAeroFrom;

    @Value("${sms.aero.type}")
    private String smsAeroType;

    @Value("${sms.aero.url}")
    private String smsAeroUrl;

    @Override
    public void sendSms(User user, String text) {

        threadPool.submit(() -> {
            String request = smsAeroUrl + "?user="
                    + smsAeroEmail + "&password="
                    + smsAeroApiKey + "&to="
                    + user.getPhoneNumber() +
                    "&text=" + text
                    + "&from="
                    + smsAeroFrom + "&type="
                    + smsAeroType;
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
            if (responseEntity.getBody().contains("accepted")) {
                return true;
            } else {
                throw new IllegalArgumentException("Incorrect phone number");
            }
        });
    }
}




