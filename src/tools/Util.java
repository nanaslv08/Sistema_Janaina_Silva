/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Janaína B da Silva
 */
public class Util {
    public static void habilitar(boolean valor, JComponent ... vetComp){
        for (int i = 0; i < vetComp.length; i++) {
            vetComp[i].setEnabled(valor);
            }
        }
        
        public static void limparCampos(JComponent... vetComp) {
            for (JComponent componente : vetComp) {
                if (componente instanceof JTextField) {
//                    JTextField objeto = (JTextField) componente;
//                    objeto.setText("");
                ((JTextField) componente).setText(""); 
                } else if (componente instanceof JComboBox){
                ((JComboBox)componente).setSelectedIndex(-1);
                }else if (componente instanceof JCheckBox) {
                ((JCheckBox)componente).setSelected(false);
                }
            }
            
        
    }
        public static void mensagem(){
            
        }
        

    public static void mensagem(String cadeia) {
        JOptionPane.showMessageDialog(null, cadeia);
    }

    public static boolean perguntar(String cadeia) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?",
                "confirmar", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            return true;
        }else{
            return false;
        }   
    }
    
    public static int strInt(String cad){
    return Integer.valueOf(cad);
    }
    public static String intStr(int num){
    return "";
    }
    public static double strDouble(String cad){
    return Double.valueOf(cad);
    }
    public static String doubleStr(double num){
    return String.valueOf(num);
    }
    public static Date strDate(String cad){
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // ajuste o formato conforme sua necessidade
        return sdf.parse(cad);
    } catch (ParseException e) {
        // Tratamento de erro se a conversão falhar
        return null;
    }    }
    public static String dateStr(Date data){
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // ajuste o formato conforme sua necessidade
    return sdf.format(data);
    }

}
