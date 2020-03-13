package com.felipeleme.Dashboard.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.felipeleme.Dashboard.model.entity.Usuario;

@Service
public class EmailService {
    @Autowired private JavaMailSender mailSender;

    public String sendEmail(Usuario usuario) {
	 SimpleMailMessage message = new SimpleMailMessage();
	 message.setSubject("Bem vindo" +usuario.getNome());
     message.setText("Bem vindo " +usuario.getNome()+", seu cadastro no Mentor Online foi realizado com sucesso. \n"
     		+ "Utilize o usuário: " +usuario.getUsername() +"  e senha: " +usuario.getSenha() +" para acessar a plataforma.\n"
     				+ "Obrigado, Samuel Queles."
     		);
     message.setTo(usuario.getEmail());
     message.setFrom("dashboardsamuelqueles@gmail.com");

     try {
         mailSender.send(message);
         return "Email enviado com sucesso!";
     } catch (Exception e) {
         e.printStackTrace();
         return "Erro ao enviar email.";
     }
 }

    public void sendEmailCompartilhar(Usuario usuario, Usuario sessaoatual) {
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setSubject("Bem vindo" +usuario.getNome());
	     message.setText("Bem vindo " +usuario.getNome()+", seu cadastro como visitante no Mentor Online foi realizado com sucesso. \n"
	     		+ "Utilize o usuário: " +usuario.getUsername() +"  e senha: " +usuario.getSenha() +" para acessar a plataforma.\n"
	     				+ "Obrigado, Samuel Queles."
	     		);
	     message.setTo(usuario.getEmail());
	     message.setFrom("dashboardsamuelqueles@gmail.com");

	     try {
	         mailSender.send(message);
	        
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }	
    
}
