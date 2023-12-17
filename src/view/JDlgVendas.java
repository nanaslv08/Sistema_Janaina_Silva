/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.JbsCliente;
import bean.JbsVenda;
import bean.JbsVendaProduto;
import bean.JbsVendedor;
import dao.ClienteDAO;
import dao.VendasDAO;
import dao.VendasProdutoDAO;
import dao.VendedorDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import tools.Util;

/**
 *
 * @author Janaína B da Silva
 */
public class JDlgVendas extends javax.swing.JDialog {

    private boolean incluindo;
    MaskFormatter mascaraData;
    MaskFormatter mascaraTotal;
    public JbsVenda jbsVenda;
    private VendasDAO vendasDAO;
    public JbsCliente jbsCliente;
    public JbsVendedor jbsVendedor;
    public VendasProdutoDAO vendasProdutoDAO;
    public JbsVendaProduto jbsVendasProduto;
    public VendasProdutoControle vendasProdutoControle;
    public JDlgVendasProduto jDlgVendasProduto;
    /**
     * Creates new form JDlgVendas
     */
    public JDlgVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Cadastro de Vendas");
        setLocationRelativeTo(null);
        vendasDAO = new VendasDAO();
//        jbsCliente = new JbsCliente();
//        jbsVendedor = new JbsVendedor();
//        jDlgVendasProduto = new JDlgVendasProduto(null, true);
//        vendasProdutoDAO = new VendasProdutoDAO();
//        vendasProdutoControle = new VendasProdutoControle();
        Util.habilitar(false,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jFmtTotal, 
                JBS_jCboCliente, JBS_jCboVendedor,JBS_jBtnCancelar, JBS_jBtnConfirmar, 
                JBS_jBtnAlterarProd, JBS_jBtnExcluirProd, JBS_jBtnIncluirProd);
        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        
        try {
            mascaraTotal = new MaskFormatter("####.##");
            mascaraData = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(JDlgUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        JBS_jFmtTotal.setFormatterFactory(new DefaultFormatterFactory (mascaraTotal));
        JBS_jFmtDataVenda.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
        
        
        ClienteDAO clienteDAO = new ClienteDAO();
        List lista1 = clienteDAO.listAll();
        for (int c = 0; c < lista1.size(); c++) {
        JBS_jCboCliente.addItem((JbsCliente)lista1.get(c));
        }
        
        VendedorDAO vendedorDAO = new VendedorDAO();
        List lista2 = vendedorDAO.listAll();
        for (int v = 0; v < lista2.size(); v++) {
        JBS_jCboVendedor.addItem((JbsVendedor)lista2.get(v));
        }
        
        List lista = new ArrayList();
        vendasProdutoControle = new VendasProdutoControle();
        vendasProdutoControle.setList(lista);
        jTable1.setModel(vendasProdutoControle);

    }
    
    public JbsVenda ViewBean(){
        jbsVenda = new JbsVenda();
        int id = Util.strInt(JBS_jTxtNumOs.getText());
        jbsVenda.setJbsIdVenda(id);
        double total = Double.parseDouble(JBS_jFmtTotal.getText());
        jbsVenda.setJbsTotal(total);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            jbsVenda.setJbsData(formato.parse(JBS_jFmtDataVenda.getText()));
        } catch (ParseException ex) {
            System.out.print("por favor funciona: " + ex.getMessage());
        }

        jbsVenda.setJbsCliente((JbsCliente) JBS_jCboCliente.getSelectedItem());
//        jbsVenda.setJbsCliente(JBS_jCboCliente.getItemAt(JBS_jCboCliente.getSelectedIndex()));
        jbsVenda.setJbsVendedor((JbsVendedor) JBS_jCboVendedor.getSelectedItem()); 
//        jbsVenda.setJbsVendedor(JBS_jCboVendedor.getItemAt(JBS_jCboVendedor.getSelectedIndex())); 
        //getItemAt passa o valor na posição do index
        return jbsVenda;
    }
    
    
    public void beanView(JbsVenda jbsVenda){
        //String valor = String.valueOf(jbsVenda.getJbsIdVenda());
        JBS_jTxtNumOs.setText(String.valueOf(jbsVenda.getJbsIdVenda()));
        JBS_jFmtTotal.setText(String.valueOf(jbsVenda.getJbsTotal()));
        JBS_jFmtDataVenda.setText(Util.dateStr(jbsVenda.getJbsData()));
        JBS_jCboCliente.setSelectedItem(jbsVenda.getJbsCliente());
        JBS_jCboVendedor.setSelectedItem(jbsVenda.getJbsVendedor());
        
        vendasProdutoDAO = new VendasProdutoDAO();
        List listaProd = (List) vendasProdutoDAO.listaProdutos(jbsVenda);
        vendasProdutoControle.setList(listaProd);
        
         
    }
    
//    public void habilitar(boolean valor) {
//        Util.habilitar(valor, JBS_jTxtNumOs, JBS_jFmtTotal, JBS_jFmtDataVenda, JBS_jCboVendedor,
//                JBS_jCboCliente, JBS_jBtnConfirmar,JBS_jBtnCancelar);
//        Util.habilitar(valor, JBS_jBtnIncluirProd, JBS_jBtnAlterarProd, JBS_jBtnExcluirProd);
//        Util.habilitar(!valor, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
//    }
    
    public int getSelectedRowProd() {
        return jTable1.getSelectedRow();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JBS_jFmtDataVenda = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        JBS_jBtnIncluirProd = new javax.swing.JButton();
        JBS_jBtnAlterarProd = new javax.swing.JButton();
        JBS_jBtnExcluirProd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JBS_jTxtNumOs = new javax.swing.JTextField();
        JBS_jCboCliente = new javax.swing.JComboBox<>();
        JBS_jCboVendedor = new javax.swing.JComboBox<JbsVendedor>();
        jLabel1 = new javax.swing.JLabel();
        JBS_jBtnConfirmar = new javax.swing.JButton();
        JBS_jBtnPesquisar = new javax.swing.JButton();
        JBS_jBtnCancelar = new javax.swing.JButton();
        JBS_jBtnIncluir = new javax.swing.JButton();
        JBS_jBtnAlterar = new javax.swing.JButton();
        JBS_jBtnExcluir = new javax.swing.JButton();
        JBS_jFmtTotal = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Cliente");

        jLabel3.setText("Vendedor");

        jLabel4.setText("Total");

        JBS_jFmtDataVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jFmtDataVendaActionPerformed(evt);
            }
        });

        jLabel5.setText("Data");

        JBS_jBtnIncluirProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/incluir.png"))); // NOI18N
        JBS_jBtnIncluirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnIncluirProdActionPerformed(evt);
            }
        });

        JBS_jBtnAlterarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        JBS_jBtnAlterarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnAlterarProdActionPerformed(evt);
            }
        });

        JBS_jBtnExcluirProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        JBS_jBtnExcluirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnExcluirProdActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        JBS_jTxtNumOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jTxtNumOsActionPerformed(evt);
            }
        });

        JBS_jCboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jCboVendedorActionPerformed(evt);
            }
        });

        jLabel1.setText("Num OS");

        JBS_jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        JBS_jBtnConfirmar.setText("Confirmar");
        JBS_jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnConfirmarActionPerformed(evt);
            }
        });

        JBS_jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        JBS_jBtnPesquisar.setText("Pesquisar");
        JBS_jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnPesquisarActionPerformed(evt);
            }
        });

        JBS_jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        JBS_jBtnCancelar.setText("Cancelar");
        JBS_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnCancelarActionPerformed(evt);
            }
        });

        JBS_jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/gravar.png"))); // NOI18N
        JBS_jBtnIncluir.setText("Incluir");
        JBS_jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnIncluirActionPerformed(evt);
            }
        });

        JBS_jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        JBS_jBtnAlterar.setText("Alterar");
        JBS_jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnAlterarActionPerformed(evt);
            }
        });

        JBS_jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Excluir.png"))); // NOI18N
        JBS_jBtnExcluir.setText("Excluir");
        JBS_jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBS_jBtnIncluir)
                        .addGap(18, 18, 18)
                        .addComponent(JBS_jBtnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBS_jBtnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnPesquisar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBS_jTxtNumOs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBS_jFmtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JBS_jCboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(JBS_jCboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(96, 96, 96)
                                        .addComponent(jLabel2)
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(JBS_jFmtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBS_jBtnExcluirProd)
                                    .addComponent(JBS_jBtnIncluirProd)
                                    .addComponent(JBS_jBtnAlterarProd))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBS_jCboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBS_jCboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBS_jTxtNumOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBS_jFmtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBS_jFmtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBS_jBtnIncluirProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnAlterarProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnExcluirProd)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBS_jBtnIncluir)
                    .addComponent(JBS_jBtnAlterar)
                    .addComponent(JBS_jBtnExcluir)
                    .addComponent(JBS_jBtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBS_jBtnPesquisar)
                    .addComponent(JBS_jBtnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBS_jFmtDataVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jFmtDataVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jFmtDataVendaActionPerformed

    private void JBS_jBtnIncluirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnIncluirProdActionPerformed
        // TODO add your handling code here:
        jDlgVendasProduto = new JDlgVendasProduto(null, true);
        jDlgVendasProduto.setTitle("Incluir Venda");
        jDlgVendasProduto.setTelaAnterior(this);
        jDlgVendasProduto.setVisible(true);

    }//GEN-LAST:event_JBS_jBtnIncluirProdActionPerformed

    private void JBS_jBtnAlterarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnAlterarProdActionPerformed
        // TODO add your handling code here:
        jDlgVendasProduto = new JDlgVendasProduto(null, true);
        jDlgVendasProduto.setTitle("Alteração de produtos");
        jDlgVendasProduto.setTelaAnterior(this);
        int linSel = jTable1.getSelectedRow();
        jbsVendasProduto = (JbsVendaProduto) vendasProdutoControle.getBean(linSel);
        jDlgVendasProduto.beanView(jbsVendasProduto);
        jDlgVendasProduto.setVisible(true);
    }//GEN-LAST:event_JBS_jBtnAlterarProdActionPerformed

    private void JBS_jBtnExcluirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnExcluirProdActionPerformed
        // TODO add your handling code here:
        jDlgVendasProduto = new JDlgVendasProduto(null, true);
        jDlgVendasProduto.setTitle("Excluir Venda");
        jDlgVendasProduto.setVisible(true);
        int linha = jTable1.getSelectedRow();
        if (linha == -1) {
            Util.mensagem("Nenhuma linha selecionada");
        } else {
            if (Util.perguntar("Confirma exclusão do produto?") == true) {
                vendasProdutoControle.removeBean(linha);
            }
        }
        
        
    }//GEN-LAST:event_JBS_jBtnExcluirProdActionPerformed

    private void JBS_jTxtNumOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jTxtNumOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jTxtNumOsActionPerformed

    private void JBS_jCboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jCboVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jCboVendedorActionPerformed

    private void JBS_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnConfirmarActionPerformed
//        if(incluindo == true){
//            vendasDAO.insert(ViewBean());//bean com bean retorna bean
//        } else {
//            vendasDAO.update(ViewBean());
//        }
//        Util.habilitar(false,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, 
//                JBS_jCboVendedor, JBS_jFmtTotal, JBS_jBtnCancelar, JBS_jBtnConfirmar, 
//                JBS_jBtnAlterarProd, JBS_jBtnExcluirProd, JBS_jBtnIncluirProd);
//        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        
       
            
        if (incluindo == true) {
            vendasDAO.insert(ViewBean());
            vendasProdutoDAO = new VendasProdutoDAO();
            
            for (int linha = 0; linha < jTable1.getRowCount(); linha++) {
                jbsVendasProduto = vendasProdutoControle.getBean(linha);
                jbsVendasProduto.setJbsVenda(jbsVenda);
                vendasProdutoDAO.insert(jbsVendasProduto);
            }
        } else {
            vendasDAO.update(ViewBean());
            //remover todos os pedidos produtos deste pedido
            vendasProdutoDAO = new VendasProdutoDAO();

            //incluir todos os pedidosProduto que estao no jtable
            for (int linha = 0; linha < jTable1.getRowCount(); linha++) {
                jbsVendasProduto = vendasProdutoControle.getBean(linha);
                jbsVendasProduto.setJbsVenda(jbsVenda);
                vendasProdutoDAO.insert(jbsVendasProduto);
            }
        }
//        habilitar(false);
        Util.limparCampos(JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtTotal); 
        vendasProdutoControle.setList(new ArrayList());

        jbsVenda = null;
        Util.habilitar(false,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, 
                JBS_jCboVendedor, JBS_jFmtTotal, JBS_jBtnCancelar, JBS_jBtnConfirmar, 
                JBS_jBtnAlterarProd, JBS_jBtnExcluirProd, JBS_jBtnIncluirProd);
        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
    }//GEN-LAST:event_JBS_jBtnConfirmarActionPerformed

    private void JBS_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnPesquisarActionPerformed

//        Util.habilitar(true, JBS_jBtnAlterar, JBS_jBtnPesquisar, JBS_jBtnCancelar, 
//                JBS_jBtnExcluir);
//        Util.habilitar(false, JBS_jBtnConfirmar, JBS_jBtnAlterarProd, JBS_jBtnExcluirProd, 
//                JBS_jBtnIncluirProd);  
        Util.limparCampos(JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtDataVenda, JBS_jFmtTotal,
                JBS_jTxtNumOs);
        JDlgVendasPesquisa jDlgVendasPesquisa = new JDlgVendasPesquisa(null, true);
        jDlgVendasPesquisa.setTelaAnterior(this);
        jDlgVendasPesquisa.setVisible(true);
    }//GEN-LAST:event_JBS_jBtnPesquisarActionPerformed

    private void JBS_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnCancelarActionPerformed

//        Util.habilitar(false,JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtDataVenda, JBS_jFmtTotal,
//                JBS_jTxtNumOs, JBS_jBtnCancelar, JBS_jBtnConfirmar);
//        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
//        Util.mensagem("Operação Cancelada");
        Util.limparCampos(JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtDataVenda, JBS_jFmtTotal,
                JBS_jTxtNumOs);
        vendasProdutoControle.setList(new ArrayList());
        jbsVenda = null;
//        habilitar(false);
//        Util.limparCampos(JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtDataVenda, JBS_jFmtTotal,
//                JBS_jTxtNumOs);        
        Util.habilitar(false,JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtDataVenda, JBS_jFmtTotal,
                JBS_jTxtNumOs, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
    }//GEN-LAST:event_JBS_jBtnCancelarActionPerformed

    private void JBS_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnIncluirActionPerformed
        Util.habilitar(true,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, 
                JBS_jCboVendedor, JBS_jFmtTotal, JBS_jBtnCancelar, JBS_jBtnConfirmar,
                JBS_jBtnIncluirProd, JBS_jBtnExcluirProd, JBS_jBtnAlterarProd);
        Util.habilitar(false, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, 
                JBS_jBtnPesquisar);
//                incluindo = true;
        
        vendasProdutoControle.setList(new ArrayList()); 
        JBS_jTxtNumOs.grabFocus();
        incluindo = true;
        jbsVenda = new JbsVenda();
        Util.limparCampos(JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, JBS_jCboVendedor, 
                JBS_jFmtTotal);
    }//GEN-LAST:event_JBS_jBtnIncluirActionPerformed

    private void JBS_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnAlterarActionPerformed

//        Util.habilitar(true,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, 
//                JBS_jCboVendedor, JBS_jFmtTotal, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(false, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
//        incluindo = false;
//        
//            jbsVenda = new JbsVenda();
        if ( jbsVenda!= null) {
            vendasDAO = new VendasDAO();
            JbsVendaProduto jbsVendaProduto;
            for (int lin = 0; lin < jTable1.getRowCount(); lin++){
//                jbsVendasProduto = vendasProdutoControle.getBean(lin);
                jbsVendaProduto = vendasProdutoControle.getBean(lin);
//                vendasDAO.delete(jbsVendasProduto);
                vendasDAO.delete(jbsVendaProduto);
            }
//            habilitar(true);
               Util.habilitar(true,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, 
                JBS_jCboVendedor, JBS_jFmtTotal, JBS_jBtnCancelar, JBS_jBtnConfirmar, 
                JBS_jBtnAlterarProd, JBS_jBtnIncluirProd, JBS_jBtnExcluirProd);
               Util.habilitar(false, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        } else {
            Util.mensagem("Deve ser realizada uma pesquisa antes");
            Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        }
        incluindo = false;

        //        Util.habilitar(true,JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, 
//                JBS_jCboVendedor, JBS_jFmtTotal, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(false, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
//        incluindo = fal
    }//GEN-LAST:event_JBS_jBtnAlterarActionPerformed

    private void JBS_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnExcluirActionPerformed
//        Util.habilitar(false, JBS_jBtnAlterarProd, JBS_jBtnExcluirProd, JBS_jBtnIncluirProd);
//        //tá aqui
//        if (Util.perguntar("Deseja excluir o registro?") == true){
//            jbsVenda = ViewBean();
//            vendasDAO.delete(jbsVenda);
//        }else{
//            Util.mensagem("Exclusão cancelada");
//        }
//        Util.limparCampos(JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtTotal);
        vendasProdutoControle = new VendasProdutoControle();
        vendasProdutoDAO = new VendasProdutoDAO();
        jbsVenda = new JbsVenda();
        vendasDAO = new VendasDAO();
        if (jbsVenda != null) {
            if (Util.perguntar("Deseja excluir o pedido?") == true) {
                for (int lin = 0; lin < jTable1.getRowCount(); lin++) {
                    jbsVendasProduto = vendasProdutoControle.getBean(lin);
                    vendasProdutoDAO.delete(jbsVendasProduto);
                }
                vendasDAO.delete(jbsVenda);
            }
        } else {
            Util.mensagem("Deve ser realizada uma pesquisa antes");
        }
        vendasProdutoControle.setList(new ArrayList());
        Util.limparCampos(JBS_jTxtNumOs, JBS_jFmtDataVenda, JBS_jCboCliente, JBS_jCboVendedor, JBS_jFmtTotal);
        jbsVenda = null;
        
    }//GEN-LAST:event_JBS_jBtnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgVendas dialog = new JDlgVendas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBS_jBtnAlterar;
    private javax.swing.JButton JBS_jBtnAlterarProd;
    private javax.swing.JButton JBS_jBtnCancelar;
    private javax.swing.JButton JBS_jBtnConfirmar;
    private javax.swing.JButton JBS_jBtnExcluir;
    private javax.swing.JButton JBS_jBtnExcluirProd;
    private javax.swing.JButton JBS_jBtnIncluir;
    private javax.swing.JButton JBS_jBtnIncluirProd;
    private javax.swing.JButton JBS_jBtnPesquisar;
    private javax.swing.JComboBox<JbsCliente> JBS_jCboCliente;
    private javax.swing.JComboBox<JbsVendedor> JBS_jCboVendedor;
    private javax.swing.JFormattedTextField JBS_jFmtDataVenda;
    private javax.swing.JFormattedTextField JBS_jFmtTotal;
    private javax.swing.JTextField JBS_jTxtNumOs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
