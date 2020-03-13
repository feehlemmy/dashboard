package com.felipeleme.Dashboard.model.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.felipeleme.Dashboard.model.DAO.UserDAO;
import com.felipeleme.Dashboard.model.entity.Usuario;

@Service
@Transactional(readOnly = false)
public class UserService {
	private static final String UPLOADED_FOLDER = "C:\\Users\\felipeleme\\Documents\\Dashboard\\src\\main\\resources\\static\\imgPerfil\\";
	// C:\\Users\\felipeleme\\Documents\\Dashboard\\src\\main\\resources\\static\\imgPerfil\\

	@Autowired
	private UserDAO dao;

	@Transactional(readOnly = true)
	public Usuario verifyLogin(String username, String senha) throws Exception {
		Usuario usuario = new Usuario();
		List<Usuario> usuarioLogin = new ArrayList<>();

		try {
			usuarioLogin = dao.readByLogin(username, senha);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (usuarioLogin.get(0) == null) {
			throw new Exception("Usuario não cadastrada.");
		} else {
			usuario = usuarioLogin.get(0);
			return usuario;
		}
	}

	public void editarAdministrador(Usuario usuario) {
		dao.updateAdminsitrador(usuario);

	}

	public String saveImagem(MultipartFile uploadImagem, Long Id) {

		Path path = Paths.get(UPLOADED_FOLDER + Id + uploadImagem.getOriginalFilename());
		try {
			Files.write(path, uploadImagem.getBytes());
			return path.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String gerarSenhaAleatoria() {
		int qtdeMaximaCaracteres = 8;
		String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < qtdeMaximaCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}
		return senha.toString();
	}

	public void cadastrar(Usuario usuario) {
		dao.save(usuario);
	}
	
	@Transactional(readOnly = true)
		public List<Usuario> findAllUsers() {
		return dao.findAll();
	}

	public Usuario getUserByUsername(String usuarioCriador) throws Exception {
		Usuario usuario = new Usuario();
		List<Usuario> usuarioLogin = new ArrayList<>();

		try {
			usuarioLogin = dao.readByUsername(usuarioCriador);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (usuarioLogin.get(0) == null) {
			throw new Exception("Usuario não cadastrada.");
		} else {
			usuario = usuarioLogin.get(0);
			return usuario;
		}	    
	}

//	public void updateUsuario(Usuario usuario) {
//		dao.updateUsuario(usuario);
//	}
}
