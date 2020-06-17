/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model.orm;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
@Entity
@Table(name = "partidas", catalog = "hundir_flota", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partidas.findAll", query = "SELECT p FROM Partidas p"),
    @NamedQuery(name = "Partidas.findByIdPartida", query = "SELECT p FROM Partidas p WHERE p.idPartida = :idPartida")})
public class Partidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_partida", nullable = false)
    private Integer idPartida;
    @JoinColumn(name = "id_jugador", referencedColumnName = "id_jugador")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jugadores idJugador;

    public Partidas() {
    }

    public Partidas(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Jugadores getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugadores idJugador) {
        this.idJugador = idJugador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partidas)) {
            return false;
        }
        Partidas other = (Partidas) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesptocruz.hundirlaflotamavenweb.model.orm.Partidas[ idPartida=" + idPartida + " ]";
    }
    
}
