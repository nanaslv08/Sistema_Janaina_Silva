/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.JbsVendaProduto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Janaína B da Silva
 */
public class VendasProdutoControle extends AbstractTableModel{

    List lista;
    
    public void setList(List lista){
        this.lista = lista;
        this.fireTableDataChanged();
    }
    
    public JbsVendaProduto getBean(int row){
        return (JbsVendaProduto) lista.get(row);
    }
    
    public void addBean(JbsVendaProduto jbsVendaProduto){
        lista.add(jbsVendaProduto);
        this.fireTableDataChanged();
    }
    
    public void removeBean(int Index){
        lista.remove(Index);
        this.fireTableDataChanged();
    }
    
    public void updateBean(int Index, JbsVendaProduto jbsVendaProduto){
        lista.set(Index, jbsVendaProduto);
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    JbsVendaProduto vProduto = (JbsVendaProduto) lista.get(rowIndex);
        if(columnIndex == 0){
            return vProduto.getJbsIdVendaProduto();
        }
        if(columnIndex == 1){
            return vProduto.getJbsVUnitario();
        }
        if(columnIndex == 2){
            return vProduto.getJbsQuantida();
        }
        if(columnIndex == 3){
            return vProduto.getJbsProduto();
        }
        if(columnIndex == 4){
            return vProduto.getJbsTotal();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column){
        if(column == 0){
            return "Id";
        }
        if(column == 1){
            return "Valor Uni.";
        }
        if(column == 2){
            return "Quantidade";
        }
        if(column == 3){
            return "Produto";
        }
        if(column == 4){
            return "Total";
        }
        return "";
    }
    
}
