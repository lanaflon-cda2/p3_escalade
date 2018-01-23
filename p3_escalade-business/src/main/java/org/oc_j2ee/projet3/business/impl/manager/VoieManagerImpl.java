package org.oc_j2ee.projet3.business.impl.manager;

import org.oc_j2ee.projet3.business.contrat.manager.VoieManager;
import org.oc_j2ee.projet3.model.Longueur;
import org.oc_j2ee.projet3.model.Secteur;
import org.oc_j2ee.projet3.model.Site;
import org.oc_j2ee.projet3.model.Voie;

import java.util.Iterator;
import java.util.List;

public class VoieManagerImpl extends AbstractManagerImpl implements VoieManager {
    @Override
    public void deleteWay(Voie voie) {
        getDaoFactory().getVoieDAO().delete(voie);
    }

    @Override
    public void updateWay(Voie voie) {
        getDaoFactory().getVoieDAO().update(voie);

    }

    @Override
    public void addWay(Voie voie) {
        getDaoFactory().getVoieDAO().create(voie);

    }

    @Override
    public Voie getVoie(int id) {
        Voie voie = getDaoFactory().getVoieDAO().getById(id);
        Secteur secteur = getDaoFactory().getSecteurDAO().getById(voie.getSecteur_id());

        voie.setSecteur(secteur);

        return voie;
    }

    @Override
    public List<Voie> getVoieBySecteur(Secteur secteur) {
        return getDaoFactory().getVoieDAO().getAllBySector(secteur);
    }

    @Override
    public List<Voie> getAllBySite(Site site) {
        return getDaoFactory().getVoieDAO().getAllBySite(site);
    }

    @Override
    public List<Voie> getAllVoie() {

        List<Voie> vList = getDaoFactory().getVoieDAO().getListVoie();
        Iterator<Voie> iterator = vList.iterator();
        while(iterator.hasNext()){
            Voie voie = iterator.next();
            List<Longueur> longueurs = getDaoFactory().getLongueurDAO().getByWay(voie);
            voie.setLongueurs(longueurs);
        }
        return vList;


    }

    @Override
    public List<Voie> getVoieByName(String nom) {

        List<Voie> voies = getDaoFactory().getVoieDAO().getByName(nom);

        Iterator<Voie> iterator = voies.iterator();
        while (iterator.hasNext()){
            Voie voie = iterator.next();
            Secteur secteur = getDaoFactory().getSecteurDAO().getById(voie.getSecteur_id());
            voie.setSecteur(secteur);
            List<Longueur> longueurs = getDaoFactory().getLongueurDAO().getByWay(voie);
            voie.setLongueurs(longueurs);
        }

        return voies;
    }

    @Override
    public String getNameFromId(Integer voieId) {
        String name = getDaoFactory().getVoieDAO().getNameFromId(voieId);
        return name;
    }


}
