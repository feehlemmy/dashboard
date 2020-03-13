package com.felipeleme.Dashboard.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "leitura")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Leitura extends AbstractEntity<Long> {


    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "total_paginas", nullable = true)
    private Double totalPaginas;
    
    @Column(name = "paginas_lidas", nullable = true)
    private Double paginasLidas;
    
    @Column(name = "horas", nullable = true)
    private Double horas;
   
    @Column(name = "carreira_id", nullable = false)
    private Long carreiraId;
    @Column(name = "usuario_id", nullable = false)
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
     * @return the totalPaginas
     */
    public Double getTotalPaginas() {
        return totalPaginas;
    }
    /**
     * @param totalPaginas the totalPaginas to set
     */
    public void setTotalPaginas(Double totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
    /**
     * @return the paginasLidas
     */
    public Double getPaginasLidas() {
        return paginasLidas;
    }
    /**
     * @param paginasLidas the paginasLidas to set
     */
    public void setPaginasLidas(Double paginasLidas) {
        this.paginasLidas = paginasLidas;
    }
    /**
     * @return the horas
     */
    public Double getHoras() {
        return horas;
    }
    /**
     * @param horas the horas to set
     */
    public void setHoras(Double horas) {
        this.horas = horas;
    }
    /**
     * @return the carreiraId
     */
    public Long getCarreiraId() {
        return carreiraId;
    }
    /**
     * @param carreiraId the carreiraId to set
     */
    public void setCarreiraId(Long carreiraId) {
        this.carreiraId = carreiraId;
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
