package service;

import entity.User;

import javax.servlet.ServletContext;

public interface EmailService {
    void SendEmail(ServletContext context, User recipient, String type);
}
