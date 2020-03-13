package com.felipeleme.Dashboard.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "carreira")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Carreira extends AbstractEntity<Long> {

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "usuario_id")
    private Long usuarioId;

    /**
     * @return the nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
	this.nome = nome;
    }

    /**
     * @return the usuarioId
     */
    public Long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
