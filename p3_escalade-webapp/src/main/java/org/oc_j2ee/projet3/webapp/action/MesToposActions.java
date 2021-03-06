package org.oc_j2ee.projet3.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.oc_j2ee.projet3.business.contrat.manager.TopoManager;
import org.oc_j2ee.projet3.business.contrat.manager.UtilisateurManager;
import org.oc_j2ee.projet3.model.Topo;
import org.oc_j2ee.projet3.model.Utilisateur;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MesToposActions extends ActionSupport implements SessionAware {


    private Topo topo;
    private TopoManager topoManager;
    private Map<String, Object> session;
    private List<Topo> mesTopos = new ArrayList<>();

    public Map<String, Object> getSession() {
        return session;
    }

    public List<Topo> getMesTopos() {
        return mesTopos;
    }

    public void setMesTopos(List<Topo> mesTopos) {
        this.mesTopos = mesTopos;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public TopoManager getTopoManager() {
        return topoManager;
    }

    public void setTopoManager(TopoManager topoManager) {
        this.topoManager = topoManager;
    }



    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;

    }


    public String execute(){

        if(session.containsKey("sessionUtilisateur")) {
            Utilisateur utilisateur = (Utilisateur) session.get("sessionUtilisateur");
            mesTopos = topoManager.getUserTopo(utilisateur);

            return "success";
        }
        else{
            return "login";
        }
    }
}
