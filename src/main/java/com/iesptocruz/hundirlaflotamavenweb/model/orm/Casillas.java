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
@Table(name = "casillas", catalog = "hundir_flota", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Casillas.findAll", query = "SELECT c FROM Casillas c"),
    @NamedQuery(name = "Casillas.findByIdCasilla", query = "SELECT c FROM Casillas c WHERE c.idCasilla = :idCasilla"),
    @NamedQuery(name = "Casillas.findByNumero", query = "SELECT c FROM Casillas c WHERE c.numero = :numero"),
    @NamedQuery(name = "Casillas.findByLetra", query = "SELECT c FROM Casillas c WHERE c.letra = :letra")})
public class Casillas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_casilla", nullable = false)
    private Integer idCasilla;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "letra")
    private Character letra;
    @JoinColumn(name = "id_barco", referencedColumnName = "id_barco")
    @ManyToOne(fetch = FetchType.LAZY)
    private Barcos idBarco;
    @JoinColumn(name = "id_tablero", referencedColumnName = "id_tablero")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tableros idTablero;

    public Casillas() {
    }

    public Casillas(Integer idCasilla) {
        this.idCasilla = idCasilla;
    }

    public Integer getIdCasilla() {
        return idCasilla;
    }

    public void setIdCasilla(Integer idCasilla) {
        this.idCasilla = idCasilla;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Character getLetra() {
        return letra;
    }

    public void setLetra(Character letra) {
        this.letra = letra;
    }

    public Barcos getIdBarco() {
        return idBarco;
    }

    public void setIdBarco(Barcos idBarco) {
        this.idBarco = idBarco;
    }

    public Tableros getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Tableros idTablero) {
        this.idTablero = idTablero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCasilla != null ? idCasilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Casillas)) {
            return false;
        }
        Casillas other = (Casillas) object;
        if ((this.idCasilla == null && other.idCasilla != null) || (this.idCasilla != null && !this.idCasilla.equals(other.idCasilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesptocruz.hundirlaflotamavenweb.model.orm.Casillas[ idCasilla=" + idCasilla + " ]";
    }
    
}
