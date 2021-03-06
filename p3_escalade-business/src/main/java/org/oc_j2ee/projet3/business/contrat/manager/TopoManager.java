package org.oc_j2ee.projet3.business.contrat.manager;

import org.oc_j2ee.projet3.model.Topo;
import org.oc_j2ee.projet3.model.Utilisateur;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TopoManager {

    void addTopo(Topo topo);
    Topo getTopo(int id);
    List<Topo> getUserTopo(Utilisateur utilisateur);
    List<Topo> getAllTopo();
    void editTopo(Topo topo);
    void borrowTopo (Topo topo, Date startDate, Date endDate, Map<String, Object> session);
    List<Topo> getTopoByName(String nom);
}
