package com.felipeleme.Dashboard.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "meme")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Meme extends AbstractEntity<Long> {

    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cor", nullable = true)
    private String cor;
    @Column(name = "resultado", nullable = true)
    private String resultado;
    
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @return the usuarioId
     */
    public Long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }
   
}
