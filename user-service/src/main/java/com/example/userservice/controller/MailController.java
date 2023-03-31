package com.example.userservice.controller;
import com.example.userservice.model.User;
import com.example.userservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@Slf4j
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    @GetMapping("/ticket/book")
    public String ticketBook(){
        mailService.ticketBook();
        return "Success";
    }
    @GetMapping("/update/passcode")
    public String updatePasscode(){
        mailService.updatePasscode();
        return "Otp sent successfully";
    }

    @PostMapping("/reset/passcode/{otp}")
    public String resetPasscode(@PathVariable("otp") String otp, @RequestBody User user){
        return mailService.resetPasscode(otp,user);
    }


}

