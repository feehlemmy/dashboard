package com.felipeleme.Dashboard.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Usuario extends AbstractEntity<Long> {

	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "senha", nullable = false)
	private String senha;
	@Column(name = "nome", nullable = true)
	private String nome;
	@Column(name = "email", nullable = true)
	private String email;
	@Column(name = "foto", nullable = true)
	private String foto;
	
	@Column(name = "visitante", nullable = true)
	private boolean visitante;
	
	@Column(name = "usuario_criador", nullable = true)
	private String usuarioCriador;
	
	private byte[] uploadImagem;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public byte[] getUploadImagem() {
		return uploadImagem;
	}

	public void setUploadImagem(byte[] uploadImagem) {
		this.uploadImagem = uploadImagem;
	}

	public void setFoto(String bs) {
		this.foto = bs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the visitante
	 */
	public boolean isVisitante() {
	    return visitante;
	}

	/**
	 * @return the visitante
	 */
	public boolean getVisitante() {
		return visitante;
	}
	/**
	 * @return the usuarioCriador
	 */
	public String getUsuarioCriador() {
	    return usuarioCriador;
	}

	/**
	 * @param visitante the visitante to set
	 */
	public void setVisitante(boolean visitante) {
	    this.visitante = visitante;
	}

	/**
	 * @param usuarioCriador the usuarioCriador to set
	 */
	public void setUsuarioCriador(String usuarioCriador) {
	    this.usuarioCriador = usuarioCriador;
	}
}
