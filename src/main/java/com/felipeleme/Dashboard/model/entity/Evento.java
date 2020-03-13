package com.felipeleme.Dashboard.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Evento extends AbstractEntity<Long> {

    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "cor", nullable = true)
    private String cor;
    @Column(name = "data", nullable = true)
    private String data;
    
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the usuarioId
     */
    public Long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
