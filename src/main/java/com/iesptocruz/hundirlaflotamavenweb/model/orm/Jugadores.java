/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model.orm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
@Entity
@Table(name = "jugadores", catalog = "hundir_flota", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugadores.findAll", query = "SELECT j FROM Jugadores j"),
    @NamedQuery(name = "Jugadores.findByIdJugador", query = "SELECT j FROM Jugadores j WHERE j.idJugador = :idJugador"),
    @NamedQuery(name = "Jugadores.findByPuntuacion", query = "SELECT j FROM Jugadores j WHERE j.puntuacion = :puntuacion"),
    @NamedQuery(name = "Jugadores.findByNombre", query = "SELECT j FROM Jugadores j WHERE j.nombre = :nombre")})
public class Jugadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jugador", nullable = false)
    private Integer idJugador;
    @Column(name = "puntuacion")
    private Integer puntuacion;
    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jugadores", fetch = FetchType.LAZY)
    private Tableros tableros;
    @OneToMany(mappedBy = "idJugador", fetch = FetchType.LAZY)
    private Collection<Partidas> partidasCollection;

    public Jugadores() {
    }

    public Jugadores(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tableros getTableros() {
        return tableros;
    }

    public void setTableros(Tableros tableros) {
        this.tableros = tableros;
    }

    @XmlTransient
    public Collection<Partidas> getPartidasCollection() {
        return partidasCollection;
    }

    public void setPartidasCollection(Collection<Partidas> partidasCollection) {
        this.partidasCollection = partidasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJugador != null ? idJugador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugadores)) {
            return false;
        }
        Jugadores other = (Jugadores) object;
        if ((this.idJugador == null && other.idJugador != null) || (this.idJugador != null && !this.idJugador.equals(other.idJugador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesptocruz.hundirlaflotamavenweb.model.orm.Jugadores[ idJugador=" + idJugador + " ]";
    }
    
}
