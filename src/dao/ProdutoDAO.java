/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.JbsProduto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author Janaína B da Silva
 */
public class ProdutoDAO extends DaoAbstract{

    @Override
    public void insert(Object object) {
        session.beginTransaction(); //todas as operações do banco de dados são feitas com trnsações por conta do hibernate
        session.save(object);
        session.getTransaction().commit();//commit é pra salvar a transação, goback apaga tudo
    }

    @Override
    public void update(Object object) {
        session.beginTransaction();
        session.flush();//limpar o cash do hibernate pra não enviar coisas erradas
        session.clear();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
        session.beginTransaction();
        session.flush();//limpar o cash do hibernate pra não enviar coisas erradas
        session.clear();
        session.delete(object);
        session.getTransaction().commit();
    }

    @Override
    public Object list(int id) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsProduto.class);
        criteria.add(Restrictions.eq("idproduto", id)); //método estático - eq = equals = igual
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    
    }

    @Override
    public List listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsProduto.class); //SELECT *FROM USUARIOS (PRA FAZER ISSO)
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    
    }
    
    public List listNome(String nome){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsProduto.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsNome", nome, MatchMode.ANYWHERE));
        List lista = criteria.list();;
        session.getTransaction().commit();
        return lista;        
    }
    
    public List listEspecifica(String especifica){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsProduto.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsEspecifica", especifica, MatchMode.ANYWHERE));
        List lista = criteria.list();;
        session.getTransaction().commit();
        return lista;        
    }
    public List listEspecificaNome(String especifica, String nome){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsProduto.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsEspecifica", especifica, MatchMode.ANYWHERE));
        criteria.add(Restrictions.like("jbsNome", nome, MatchMode.ANYWHERE));
        List lista = criteria.list();;
        session.getTransaction().commit();
        return lista;        
    }
    
}
