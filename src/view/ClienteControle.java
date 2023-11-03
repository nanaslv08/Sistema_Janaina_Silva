/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.JbsCliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Janaína B da Silva
 */
public class ClienteControle extends AbstractTableModel{
 
    List lista;
    
    public void setList(List lista){
        this.lista = lista;
    }
    
    public JbsCliente getBean(int row){
        return (JbsCliente) lista.get(row);
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
        JbsCliente cliente = (JbsCliente) lista.get(rowIndex);
        if(columnIndex == 0){
            return cliente.getJbsIdCliente();
        }
        if(columnIndex == 1){
            return cliente.getJbsNome();
        }
        if(columnIndex == 2){
            return cliente.getJbsCpf();
        }
        if(columnIndex == 3){
            return cliente.getJbsDataNasc();
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
            return "cpf";
        }
        if(column == 3){
            return "datanasc";
        }
        return "";
    }
}
