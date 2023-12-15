/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.JbsUsuario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Janaína B da Silva
 */
public class UsuarioDAO extends DaoAbstract{

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
        Criteria criteria = session.createCriteria(JbsUsuario.class);
        criteria.add(Restrictions.eq("jbsIdUsuario", id)); //método estático - eq = equals = igual
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    
    }

    @Override
    public List listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsUsuario.class); //SELECT *FROM USUARIOS (PRA FAZER ISSO)
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    
    }
    
    public List listNome(String nome){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsUsuario.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsNome", nome, MatchMode.ANYWHERE));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;        
    }
    
    public List listcpf(String cpf){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsUsuario.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsCpf", cpf, MatchMode.ANYWHERE));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;        
    }
    public List listCpfNome(String cpf, String nome){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsUsuario.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsCpf", cpf, MatchMode.ANYWHERE));
        criteria.add(Restrictions.like("jbsNome", nome, MatchMode.ANYWHERE));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;        
    }
    
//    public List listSenha(String senha){
//        session.beginTransaction();
//        Criteria criteria = session.createCriteria(JbsUsuario.class);
//        criteria.add(Restrictions.like("jbsSenha", senha, MatchMode.ANYWHERE));
//        List lista = criteria.list();
//        session.getTransaction().commit();
//        return lista;        
//    }
//    public List listApelido(String apelido){
//        session.beginTransaction();
//        Criteria criteria = session.createCriteria(JbsUsuario.class);
//        criteria.add(Restrictions.like("jbsApelido", apelido, MatchMode.ANYWHERE));
//        List lista = criteria.list();
//        session.getTransaction().commit();
//        return lista;        
//    }
    
    public JbsUsuario Loginzinho (String apelido, String senha){
        JbsUsuario jbsUsuario = null;
        
        session.beginTransaction();
        
        try{
                Criteria criteria = session.createCriteria(JbsUsuario.class);
                criteria.add(Restrictions.eq("jbsApelido", apelido));
                criteria.add(Restrictions.eq("jbsSenha", senha));
                
                jbsUsuario = (JbsUsuario) criteria.uniqueResult();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
            session.getTransaction().commit();
        }
        return jbsUsuario;
    }
    
}
