/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Cliente;
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
    
    public Cliente getBean(int row){
        return (Cliente) lista.get(row);
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
        Cliente cliente = (Cliente) lista.get(rowIndex);
        if(columnIndex == 0){
            return cliente.getIdcliente();
        }
        if(columnIndex == 1){
            return cliente.getNome();
        }
        if(columnIndex == 2){
            return cliente.getCpf();
        }
        if(columnIndex == 3){
            return cliente.getCelular();
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
            return "celular";
        }
        return "";
    }
}
