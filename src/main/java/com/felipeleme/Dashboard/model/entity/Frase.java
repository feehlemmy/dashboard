package com.felipeleme.Dashboard.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "frases")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class Frase  extends AbstractEntity<Long>{
    
@Column(name = "frase", nullable = false)  
String frase;

/**
 * @return the frase
 */
public String getFrase() {
    return frase;
}

/**
 * @param frase the frase to set
 */
public void setFrase(String frase) {
    this.frase = frase;
}
}
