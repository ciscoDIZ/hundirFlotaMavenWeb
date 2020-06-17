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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
@Entity
@Table(name = "tableros", catalog = "hundir_flota", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tableros.findAll", query = "SELECT t FROM Tableros t"),
    @NamedQuery(name = "Tableros.findByIdTablero", query = "SELECT t FROM Tableros t WHERE t.idTablero = :idTablero")})
public class Tableros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tablero", nullable = false)
    private Integer idTablero;
    @JoinColumn(name = "id_tablero", referencedColumnName = "id_jugador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Jugadores jugadores;
    @OneToMany(mappedBy = "idTablero", fetch = FetchType.LAZY)
    private Collection<Casillas> casillasCollection;

    public Tableros() {
    }

    public Tableros(Integer idTablero) {
        this.idTablero = idTablero;
    }

    public Integer getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Integer idTablero) {
        this.idTablero = idTablero;
    }

    public Jugadores getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugadores jugadores) {
        this.jugadores = jugadores;
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
        hash += (idTablero != null ? idTablero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tableros)) {
            return false;
        }
        Tableros other = (Tableros) object;
        if ((this.idTablero == null && other.idTablero != null) || (this.idTablero != null && !this.idTablero.equals(other.idTablero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesptocruz.hundirlaflotamavenweb.model.orm.Tableros[ idTablero=" + idTablero + " ]";
    }
    
}
