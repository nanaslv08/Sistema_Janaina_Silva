/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.JbsVenda;
import bean.JbsVendaProduto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Janaína B da Silva
 */
public class VendasProdutoDAO extends DaoAbstract{

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
        Criteria criteria = session.createCriteria(JbsVendaProduto.class);
        criteria.add(Restrictions.eq("idcliente", id)); //método estático - eq = equals = igual
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    
    }

    @Override
    public List listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsVendaProduto.class); //SELECT *FROM USUARIOS (PRA FAZER ISSO)
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    
    }
    
    public List listQuantidade(String quantidade){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsVendaProduto.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.like("jbsQuantidade", quantidade, MatchMode.ANYWHERE));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;        
    }
    
    public List listVUnitario(double valorUnitario){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsVendaProduto.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.gt("jbsValorUnitario", valorUnitario));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;        
    }
    public List listValorUniQuantidade(double valorUnitario, String quantidade){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsVendaProduto.class);
//        criteria.add(Restrictions.like("jbsNome", "%" + nome + "%"));
        criteria.add(Restrictions.gt("jbsValorUnitario", valorUnitario));
        criteria.add(Restrictions.like("jbsQuantidade", quantidade, MatchMode.ANYWHERE));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;        
    }
    
    public List listaProdutos(JbsVenda jbsVenda){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(JbsVendaProduto.class);
        criteria.add(Restrictions.eq("jbsVenda", jbsVenda));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    
}
