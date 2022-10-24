package com.genspark.martintorrey.aicmbackend.email;

public interface EmailSender {
    void send(String to,String email,String subject);
}
