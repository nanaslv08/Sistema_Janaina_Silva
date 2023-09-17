/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.JbsUsuario;
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
    
    public JbsUsuario getBean(int row){
        return (JbsUsuario) lista.get(row);
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
        JbsUsuario usuario = (JbsUsuario) lista.get(rowIndex);
        if(columnIndex == 0){
            return usuario.getJbsIdUsuario();
        }
        if(columnIndex == 1){
            return usuario.getJbsNome();
        }
        if(columnIndex == 2){
            return usuario.getJbsApelido();
        }
        if(columnIndex == 3){
            return usuario.getJbsCpf();
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
