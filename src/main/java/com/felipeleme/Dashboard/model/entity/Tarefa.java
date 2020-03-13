package com.felipeleme.Dashboard.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "tarefa")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Tarefa extends AbstractEntity<Long> {


    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "descricao", nullable = true)
    private String descricao;
    
    @Column(name = "concluida", nullable = true)
    private Boolean concluida;
   
    @Column(name = "empreendimento_Id", nullable = false)
    private Long empreendimentoId;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the concluida
     */
    public Boolean getConcluida() {
        return concluida;
    }

    /**
     * @param concluida the concluida to set
     */
    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    /**
     * @return the empreendimentoId
     */
    public Long getEmpreendimentoId() {
        return empreendimentoId;
    }

    /**
     * @param empreendimentoId the empreendimentoId to set
     */
    public void setEmpreendimentoId(Long empreendimentoId) {
        this.empreendimentoId = empreendimentoId;
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
