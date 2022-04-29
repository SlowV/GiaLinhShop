package com.slowv.fruit.service.impl;

import com.slowv.fruit.config.ApplicationProperties;
import com.slowv.fruit.domain.Account;
import com.slowv.fruit.service.EmailService;
import com.slowv.fruit.web.errors.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;

import static com.slowv.fruit.constant.ErrorCode.EMAIL_SEND_FAIL;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String EMAIL_RESET_PASSWORD_TEMPLATE_NAME = "reset-password";
    private static final String EMAIL_INIT_PASSWORD_TEMPLATE_NAME = "init-password";
    private final ApplicationProperties properties;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendResetPassword(Account staff, String token) {
        final var link = String.format("%s?token=%s", properties.getResetPasswordLink(), token);
        final var context = new Context();
        context.setVariable("link", link);
        final var mimeMessage = mailSender.createMimeMessage();
        final var helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
        try {
            String subject = "Reset Password";
            helper.setSubject(subject);
            helper.setTo(staff.getEmail());
            String htmlContent = templateEngine.process(EMAIL_RESET_PASSWORD_TEMPLATE_NAME, context);
            helper.setText(htmlContent, true);

            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new BusinessException(EMAIL_SEND_FAIL, "Send mail error");
        }
    }

    @Override
    public void sendInitialPassword(Account staff, String password) {

    }
}
