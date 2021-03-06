package org.oc_j2ee.projet3.consumer.impl.DAO;

import org.oc_j2ee.projet3.consumer.contract.DAO.TopoDAO;
import org.oc_j2ee.projet3.consumer.impl.RowMapper.TopoRM;
import org.oc_j2ee.projet3.model.Article;
import org.oc_j2ee.projet3.model.Site;
import org.oc_j2ee.projet3.model.Topo;
import org.oc_j2ee.projet3.model.Utilisateur;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.inject.Inject;
import java.sql.Types;
import java.util.Date;
import java.util.List;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDAO{

    @Inject
    private TopoRM topoRM;

    public TopoRM getTopoRM() {
        return topoRM;
    }
    public void setTopoRM(TopoRM topoRM) {
        this.topoRM = topoRM;
    }


    @Override
    public void create(Topo topo) {

        String vSQL = "INSERT INTO public.TOPO (utilisateur_id, site_id, nom, emprunt) " +
                "VALUES(:utilisateur_id, :site_id, :nom, :emprunt)";

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("utilisateur_id", topo.getUtilisateurId(), Types.INTEGER);
        vParams.addValue("site_id", topo.getSiteId(), Types.INTEGER);
        vParams.addValue("nom", topo.getNom(), Types.VARCHAR);
        vParams.addValue("emprunt", topo.isEmprunt(), Types.BOOLEAN);

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);

    }

    @Override
    public void createNewBorrow(Topo topo, Date startdate, Date enddate, Utilisateur utilisateur) {

        String sql = "insert into borrow (topo_id, utilisateur_id, startdate, enddate) " +
                "values(:topo_id, :utilisateur_id, :startdate, :enddate)";

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("topo_id", topo.getId(), Types.INTEGER);
        vParams.addValue("utilisateur_id", utilisateur.getId(), Types.INTEGER);
        vParams.addValue("startdate", startdate, Types.DATE);
        vParams.addValue("enddate", enddate, Types.DATE);


        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(sql, vParams);



    }


    @Override
    public void update(Topo topo) {

        String vSQL = "UPDATE public.TOPO " +
                "SET utilisateur_id=:utilisateur_id, site_id=:site_id, nom=:nom, emprunt=:emprunt " +
                "WHERE id=:id";

        MapSqlParameterSource vParams = new MapSqlParameterSource();

        vParams.addValue("id", topo.getId(), Types.INTEGER);
        vParams.addValue("utilisateur_id", topo.getUtilisateurId(), Types.INTEGER);
        vParams.addValue("site_id", topo.getSiteId(), Types.INTEGER);
        vParams.addValue("nom", topo.getNom(), Types.VARCHAR);
        vParams.addValue("emprunt", topo.isEmprunt(), Types.BOOLEAN);

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);

    }



    @Override
    public Topo getById(int id) {
        String vSQL = "SELECT * FROM public.TOPO WHERE id =:id";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        MapSqlParameterSource vParams = new MapSqlParameterSource("id", id);
        try {
            Topo topo = vJdbcTemplate.queryForObject(vSQL, vParams, topoRM);
            return topo;
        } catch (EmptyResultDataAccessException vEx) {
            return null;
        }

    }

    @Override
    public List<Topo> getAllTopos() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT * FROM public.topo";
        List<Topo> vList  = vJdbcTemplate.query(sql, topoRM);
        return vList;

    }

    @Override
    public List<Topo> getToposByUser(Utilisateur utilisateur) {

        String vSQL = "SELECT * FROM public.topo WHERE utilisateur_id=:id";

        SqlParameterSource vParams = new BeanPropertySqlParameterSource(utilisateur);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

        List<Topo> vList = vJdbcTemplate.query(vSQL,vParams,topoRM);
        return vList;
    }


    @Override
    public List<Topo> getByName(String nom) {

        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());

        String var = "'"+nom+"'";
        String vSQL = "SELECT * FROM topo WHERE nom = "+var;

        try {
            List<Topo> topos = vJdbcTemplate.query(vSQL,topoRM);
            return topos;
        } catch (EmptyResultDataAccessException vEx) {
            return null;
        }

    }
}
