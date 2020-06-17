/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model.orm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
@Entity
@Table(name = "barcos", catalog = "hundir_flota", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barcos.findAll", query = "SELECT b FROM Barcos b"),
    @NamedQuery(name = "Barcos.findByIdBarco", query = "SELECT b FROM Barcos b WHERE b.idBarco = :idBarco"),
    @NamedQuery(name = "Barcos.findByVidas", query = "SELECT b FROM Barcos b WHERE b.vidas = :vidas"),
    @NamedQuery(name = "Barcos.findByHundido", query = "SELECT b FROM Barcos b WHERE b.hundido = :hundido")})
public class Barcos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_barco", nullable = false)
    private Integer idBarco;
    @Column(name = "vidas")
    private Integer vidas;
    @Column(name = "hundido")
    private Boolean hundido;
    @OneToMany(mappedBy = "idBarco", fetch = FetchType.LAZY)
    private Collection<Casillas> casillasCollection;

    public Barcos() {
    }

    public Barcos(Integer idBarco) {
        this.idBarco = idBarco;
    }

    public Integer getIdBarco() {
        return idBarco;
    }

    public void setIdBarco(Integer idBarco) {
        this.idBarco = idBarco;
    }

    public Integer getVidas() {
        return vidas;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

    public Boolean getHundido() {
        return hundido;
    }

    public void setHundido(Boolean hundido) {
        this.hundido = hundido;
    }

    @XmlTransient
    public Collection<Casillas> getCasillasCollection() {
        return casillasCollection;
    }

    public void setCasillasCollection(Collection<Casillas> casillasCollection) {
        this.casillasCollection = casillasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBarco != null ? idBarco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barcos)) {
            return false;
        }
        Barcos other = (Barcos) object;
        if ((this.idBarco == null && other.idBarco != null) || (this.idBarco != null && !this.idBarco.equals(other.idBarco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesptocruz.hundirlaflotamavenweb.model.orm.Barcos[ idBarco=" + idBarco + " ]";
    }
    
}
