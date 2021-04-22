/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Entities;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class disponibilite {
     private Integer id;
    private String jours;
    private String region;

    public disponibilite() {
    }

    @Override
    public String toString() {
        return "disponibilite{" + "id=" + id + ", jours=" + jours + ", region=" + region + ", societes_id=" + societes_id + '}';
    }

    public disponibilite(Integer id, String jours, String region, societelivraison societes_id) {
        this.id = id;
        this.jours = jours;
        this.region = region;
        this.societes_id = societes_id;
    }

    public Integer getId() {
        return id;
    }

    public disponibilite(String jours, String region, societelivraison societes_id) {
        this.jours = jours;
        this.region = region;
        this.societes_id = societes_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public societelivraison getSocietes_id() {
        return societes_id;
    }

    public void setSocietes_id(societelivraison societes_id) {
        this.societes_id = societes_id;
    }
    private societelivraison societes_id;
    
    
}
