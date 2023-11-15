/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.JbsVenda;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Janaína B da Silva
 */
public class VendasControle extends AbstractTableModel{

    List lista;
    
    public void setList(List lista){
        this.lista = lista;
    }
    
    public JbsVenda getBean(int row){
        return (JbsVenda) lista.get(row);
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
    JbsVenda venda = (JbsVenda) lista.get(rowIndex);
        if(columnIndex == 0){
            return venda.getJbsIdVenda();
        }
        if(columnIndex == 1){
            return venda.getJbsTotal();
        }
        if(columnIndex == 2){
            return venda.getJbsVendedor();
        }
        if(columnIndex == 3){
            return venda.getJbsCliente();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column){
        if(column == 0){
            return "Id";
        }
        if(column == 1){
            return "Total";
        }
        if(column == 2){
            return "Vendedor";
        }
        if(column == 3){
            return "Cliente";
        }
        return "";
    }
    
}