package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String email;

    public void ticketBook() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setFrom(email);
            helper.setTo("sanchitrocks2903@gmail.com");
//            helper.setCc("sanchit2903@gmail.com");
            helper.setSubject("Ticket Status");
            helper.setText("Your ticket has been booked successfully\nThank you\n");
//            String attachment = "C:\\Users\\MEGATRON2903\\Downloads\\download.png";
//            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
//            helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String token = "";

    public void updatePasscode() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            Random rand = new Random();
            int intresRandom = rand.nextInt((9999 - 100) + 1) + 10;
            token = String.valueOf(intresRandom);
            System.out.println(token+"\n\n\n\n\n\n");
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setFrom(email);
            helper.setTo("sanchitrocks2903@gmail.com");
            helper.setSubject("OTP");
            helper.setText("Hello, this is the OTP = " + intresRandom);
            token = passwordEncoder.encode(token);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int cnt = 0;

    public String resetPasscode(String incomOtp, User user) {
        if(passwordEncoder.matches(incomOtp,token)){
            token = "";
            cnt=0;
            Optional<User> optionalUser = userRepository.findById(user.getUserId());

            if(optionalUser.isEmpty()){
                return "No such user found";
            }
            optionalUser.get().setPassword(user.getPassword());
            userRepository.save(optionalUser.get());
            return "Passcode updated successfully";

        } else if (cnt == 3) {
            token = "";
            cnt = 0;
            return "Session expired please try again";
        } else {
            cnt++;
            return "invalid otp";
        }
    }
}
