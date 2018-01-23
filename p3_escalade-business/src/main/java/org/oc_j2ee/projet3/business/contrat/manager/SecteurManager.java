package org.oc_j2ee.projet3.business.contrat.manager;

import org.oc_j2ee.projet3.model.Secteur;
import org.oc_j2ee.projet3.model.Site;
import org.oc_j2ee.projet3.model.Voie;

import java.util.List;

public interface SecteurManager {

    Secteur getSecteur(int id);
    List<Secteur> getAllSecteursBySite(Site site);
    List<Secteur> getAllSecteurs();
    List<Secteur> getSectorByName(String nom);
    void deleteSecteur(Secteur secteur);
    void updateSecteur(Secteur secteur);
    void addSecteur(Secteur secteur);
    String getNameFromId(Integer secteurId);
    String getNameFromVoie(Voie voie);
}
