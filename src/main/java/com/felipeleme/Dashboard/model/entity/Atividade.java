package com.felipeleme.Dashboard.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "atividades")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Atividade extends AbstractEntity<Long> {


    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "carga_horaria", nullable = true)
    private Double cargaHoraria;
    
    @Column(name = "carga_horaria_cumprida", nullable = true)
    private Double cargaHorariaCumprida;
    
    @Column(name = "carreira_Id", nullable = false)
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
     * @return the cargaHoraria
     */
    public Double getCargaHoraria() {
        return cargaHoraria;
    }
    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    /**
     * @return the cargaHorariaCumprida
     */
    public Double getCargaHorariaCumprida() {
        return cargaHorariaCumprida;
    }
    /**
     * @param cargaHorariaCumprida the cargaHorariaCumprida to set
     */
    public void setCargaHorariaCumprida(Double cargaHorariaCumprida) {
        this.cargaHorariaCumprida = cargaHorariaCumprida;
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
