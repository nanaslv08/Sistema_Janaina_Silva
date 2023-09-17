/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Janaína B da Silva
 */
public class UsuarioControle extends AbstractTableModel{

    List lista;
    
    public void setList(List lista){
        this.lista = lista;
    }
    
    public Usuario getBean(int row){
        return (Usuario) lista.get(row);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = (Usuario) lista.get(rowIndex);
        if(columnIndex == 0){
            return usuario.getIdusuario();
        }
        if(columnIndex == 1){
            return usuario.getNome();
        }
        if(columnIndex == 2){
            return usuario.getApelido();
        }
        if(columnIndex == 3){
            return usuario.getCpf();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column){
        if(column == 0){
            return "id";
        }
        if(column == 1){
            return "nome";
        }
        if(column == 2){
            return "apelido";
        }
        if(column == 3){
            return "cpf";
        }
        return "";
    }
    
}
