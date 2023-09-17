/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import tools.Util;
import bean.JbsCliente;
import dao.ClienteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Janaína B da Silva
 */
public class JDlgCliente extends javax.swing.JDialog {

    private boolean incluindo;
        MaskFormatter mascaraCpf;
        MaskFormatter mascaraRg;
        MaskFormatter mascaraData;
        MaskFormatter mascaraCelular;
        MaskFormatter mascaraCep;
        MaskFormatter mascaraNumCasa;
    /**
     * Creates new form JDlgCliente
     */
    public JDlgCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Util.habilitar(false,JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        setTitle("Cadastro de Cliente");
        setLocationRelativeTo(null);
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraRg = new MaskFormatter("#.###.###");
            mascaraData = new MaskFormatter("##/##/####");
            mascaraCelular = new MaskFormatter("(##) #########");
            mascaraCep = new MaskFormatter("#####-###");
            mascaraNumCasa = new MaskFormatter("#########"); //falar com o marcos pra ver como fica
        } catch (ParseException ex) {
            Logger.getLogger(JDlgCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        JBS_jFmtCPF.setFormatterFactory(new DefaultFormatterFactory(mascaraCpf));
        JBS_jFmtRG.setFormatterFactory(new DefaultFormatterFactory(mascaraRg));
        JBS_jFmtDataNasc.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
        JBS_jFmtCelular.setFormatterFactory(new DefaultFormatterFactory(mascaraCelular));
        JBS_jFmtCEP.setFormatterFactory(new DefaultFormatterFactory(mascaraCep));
        JBS_jFmtNumCasa.setFormatterFactory(new DefaultFormatterFactory(mascaraNumCasa));  
    }
    
    public JbsCliente ViewBean(){
        JbsCliente jbsCliente = new JbsCliente();
        int id = Integer.valueOf(JBS_jTxtCodigo.getText());
        jbsCliente.setJbsIdCliente(id);
        jbsCliente.setJbsNome(JBS_jTxtNome.getText());
        jbsCliente.setJbsCpf(JBS_jFmtCPF.getText());
        jbsCliente.setJbsRg(JBS_jFmtRG.getText());
//        cliente.setData_nasc(jFmtDataNasc.getText());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            jbsCliente.setJbsDataNasc(formato.parse(JBS_jFmtDataNasc.getText()));
        } catch (ParseException ex) {
            //Logger.getLogger(JDlgVendas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("por favor funciona: " + ex.getMessage());
        }
        jbsCliente.setJbsGenero(JBS_jTxtGenero.getText());
        jbsCliente.setJbsEmail(JBS_jTxtEmail.getText());
        jbsCliente.setJbsNaturalidade(JBS_jTxtNaturalidade.getText());
        jbsCliente.setJbsCelular(JBS_jFmtCelular.getText());
        jbsCliente.setJbsCep(JBS_jFmtCEP.getText());
        jbsCliente.setJbsRua(JBS_jTxtRua.getText());
        jbsCliente.setJbsBairro(JBS_jTxtBairro.getText());
        jbsCliente.setJbsCidade(JBS_jTxtCidade.getText());
        jbsCliente.setJbsEstado(JBS_jTxtEstado.getText());
        jbsCliente.setJbsNumcasa(JBS_jFmtNumCasa.getText());
        return jbsCliente;
    }
    
    public void beanView(JbsCliente jbsCliente){
        String valor = String.valueOf(jbsCliente.getJbsIdCliente());
        JBS_jTxtCodigo.setText(valor);
        JBS_jTxtNome.setText(jbsCliente.getJbsNome());
        JBS_jFmtCPF.setText(jbsCliente.getJbsCpf());
        JBS_jFmtRG.setText(jbsCliente.getJbsRg());
//        JBS_jFmtDataNasc.set(jbsCliente.getJbsDataNasc());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        String dataStr = formato.format();
        JBS_jFmtDataNasc.setText(formato.format(jbsCliente.getJbsDataNasc()));
        JBS_jTxtGenero.setText(jbsCliente.getJbsGenero());
        JBS_jTxtEmail.setText(jbsCliente.getJbsEmail());
        JBS_jTxtNaturalidade.setText(jbsCliente.getJbsNaturalidade());
        JBS_jFmtCelular.setText(jbsCliente.getJbsCelular());
        JBS_jFmtCEP.setText(jbsCliente.getJbsCep());
        JBS_jTxtRua.setText(jbsCliente.getJbsRua());
        JBS_jTxtBairro.setText(jbsCliente.getJbsBairro());
        JBS_jTxtCidade.setText(jbsCliente.getJbsCidade());
        JBS_jTxtEstado.setText(jbsCliente.getJbsEstado());
        JBS_jFmtNumCasa.setText(jbsCliente.getJbsNumcasa());
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBS_jTxtNome = new javax.swing.JTextField();
        JBS_jFmtCPF = new javax.swing.JFormattedTextField();
        JBS_jFmtCEP = new javax.swing.JFormattedTextField();
        JBS_jFmtRG = new javax.swing.JFormattedTextField();
        jLabelNome = new javax.swing.JLabel();
        JBS_jFmtDataNasc = new javax.swing.JFormattedTextField();
        JBS_jTxtBairro = new javax.swing.JTextField();
        JBS_jTxtCodigo = new javax.swing.JTextField();
        jLabelCPF = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JBS_jTxtCidade = new javax.swing.JTextField();
        JBS_jTxtRua = new javax.swing.JTextField();
        jLabelRG = new javax.swing.JLabel();
        JBS_jTxtGenero = new javax.swing.JTextField();
        JBS_jTxtEstado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JBS_jTxtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JBS_jTxtNaturalidade = new javax.swing.JTextField();
        jLabelGenero = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelNaturalidade = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        JBS_jFmtCelular = new javax.swing.JFormattedTextField();
        JBS_jFmtNumCasa = new javax.swing.JFormattedTextField();
        JBS_jBtnIncluir = new javax.swing.JButton();
        JBS_jBtnAlterar = new javax.swing.JButton();
        JBS_jBtnExcluir = new javax.swing.JButton();
        JBS_jBtnConfirmar = new javax.swing.JButton();
        JBS_jBtnCancelar = new javax.swing.JButton();
        JBS_jBtnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JBS_jTxtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jTxtNomeActionPerformed(evt);
            }
        });

        JBS_jFmtRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jFmtRGActionPerformed(evt);
            }
        });

        jLabelNome.setText("Nome");

        JBS_jTxtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jTxtCodigoActionPerformed(evt);
            }
        });

        jLabelCPF.setText("CPF");

        jLabel1.setText("Codigo");

        JBS_jTxtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jTxtRuaActionPerformed(evt);
            }
        });

        jLabelRG.setText("RG");

        jLabel4.setText("Data de Nascimento");

        jLabel5.setText("Celular");

        jLabelGenero.setText("Genêro");

        jLabelEmail.setText("Email");

        jLabelNaturalidade.setText("Naturalidade");

        jLabel9.setText("Rua");

        jLabel10.setText("Bairro");

        jLabel11.setText("Número da Casa");

        jLabel12.setText("Cidade ");

        jLabel13.setText("Estado");

        jLabel14.setText("CEP");

        JBS_jFmtNumCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jFmtNumCasaActionPerformed(evt);
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

        JBS_jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        JBS_jBtnConfirmar.setText("Confirmar");
        JBS_jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnConfirmarActionPerformed(evt);
            }
        });

        JBS_jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        JBS_jBtnCancelar.setText("Cancelar");
        JBS_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnCancelarActionPerformed(evt);
            }
        });

        JBS_jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        JBS_jBtnPesquisar.setText("Pesquisar");
        JBS_jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBS_jBtnPesquisarActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBS_jBtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBS_jBtnPesquisar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(JBS_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(117, 117, 117)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelGenero)
                                    .addComponent(JBS_jTxtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBS_jFmtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(JBS_jFmtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JBS_jFmtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JBS_jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNome)
                                    .addComponent(jLabelCPF))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBS_jFmtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(JBS_jTxtNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNaturalidade)
                                    .addComponent(JBS_jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEmail)
                                    .addComponent(jLabel14)
                                    .addComponent(JBS_jFmtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelRG))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JBS_jTxtCidade, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBS_jTxtBairro, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBS_jTxtRua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBS_jFmtNumCasa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JBS_jTxtEstado))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelGenero)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBS_jTxtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JBS_jTxtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JBS_jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEmail)
                            .addComponent(jLabel10)
                            .addComponent(jLabelNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBS_jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jTxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNaturalidade)
                            .addComponent(jLabel12)
                            .addComponent(jLabelCPF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBS_jTxtNaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jTxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jFmtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelRG)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBS_jFmtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jFmtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jTxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBS_jFmtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBS_jFmtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JBS_jFmtNumCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBS_jBtnIncluir)
                            .addComponent(JBS_jBtnAlterar)
                            .addComponent(JBS_jBtnExcluir)
                            .addComponent(JBS_jBtnCancelar)
                            .addComponent(JBS_jBtnPesquisar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(JBS_jBtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBS_jTxtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jTxtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jTxtNomeActionPerformed

    private void JBS_jFmtRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jFmtRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jFmtRGActionPerformed

    private void JBS_jTxtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jTxtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jTxtCodigoActionPerformed

    private void JBS_jTxtRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jTxtRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jTxtRuaActionPerformed

    private void JBS_jFmtNumCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jFmtNumCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBS_jFmtNumCasaActionPerformed

    private void JBS_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnIncluirActionPerformed
        // TODO add your handling code here:
        Util.habilitar(true,JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(false, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        Util.limparCampos(JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa);
        incluindo = true;
        //Double.parseDouble(String string);
    }//GEN-LAST:event_JBS_jBtnIncluirActionPerformed

    private void JBS_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
        
        ClienteDAO clienteDAO = new ClienteDAO();
        if(incluindo == true){
            clienteDAO.insert(ViewBean());//bean com bean retorna bean
        } else {
            clienteDAO.update(ViewBean());
        }
        Util.habilitar(false,JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        Util.limparCampos(JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa);
    }//GEN-LAST:event_JBS_jBtnConfirmarActionPerformed

    private void JBS_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnExcluirActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
//        habilitar();
//        int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
//
//        if (resposta == JOptionPane.YES_OPTION){
//            Cliente_DAO cliente_DAO = new Cliente_DAO();
//            cliente_DAO.delete(ViewBean()); //bean com bean retorna bean / sbean ao quadrado
//        }else{
//            JOptionPane.showMessageDialog(null, "Exclusão cancelada", "Alerta", 2);
//        }
//        limparCampos();
        if (Util.perguntar("Deseja excluir o registro?") == true){

        }else{
            Util.mensagem("Exclusão cancelada");
        }
        Util.limparCampos(JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa);
    }//GEN-LAST:event_JBS_jBtnExcluirActionPerformed

    private void JBS_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        Util.habilitar(false,JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(true, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        Util.mensagem("Operação Cancelada");
        Util.limparCampos(JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa);
    }//GEN-LAST:event_JBS_jBtnCancelarActionPerformed

    private void JBS_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        //        habilitar();
        //        String pesquisar = JOptionPane.showInputDialog(null, "Entre com o código (FK)");
        //        Cliente_DAO cliente_DAO = new Cliente_DAO();
        //        int id = Integer.valueOf(pesquisar);
        //        Cliente cliente = (Cliente) cliente_DAO.list(id); //list retorna o bean e ttansforma o bean em objeto
        //        beanView(cliente);
        //        ALterarExcluir();
        JDlgClientePesquisa jDlgClientePesquisa = new JDlgClientePesquisa(null, true);
        jDlgClientePesquisa.setTelaAnterior(this);
        jDlgClientePesquisa.setVisible(true);
        
        JBS_jBtnAlterar.setEnabled(true);
        JBS_jBtnPesquisar.setEnabled(true);
        JBS_jBtnCancelar.setEnabled(true);
        JBS_jBtnConfirmar.setEnabled(false);
        JBS_jBtnExcluir.setEnabled(true);
    }//GEN-LAST:event_JBS_jBtnPesquisarActionPerformed

    private void JBS_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBS_jBtnAlterarActionPerformed
        // TODO add your handling code here:
        
        Util.habilitar(true,JBS_jTxtCodigo, JBS_jTxtNome, JBS_jFmtCPF, JBS_jFmtRG, JBS_jFmtDataNasc,
            JBS_jTxtGenero, JBS_jTxtEmail, JBS_jTxtNaturalidade, JBS_jFmtCelular, JBS_jFmtCEP, JBS_jTxtRua, 
            JBS_jTxtBairro, JBS_jTxtCidade, JBS_jTxtEstado, JBS_jFmtNumCasa, JBS_jBtnCancelar, JBS_jBtnConfirmar);
        Util.habilitar(false, JBS_jBtnIncluir, JBS_jBtnAlterar, JBS_jBtnExcluir, JBS_jBtnPesquisar);
        incluindo = false;
    }//GEN-LAST:event_JBS_jBtnAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgCliente dialog = new JDlgCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton JBS_jBtnCancelar;
    private javax.swing.JButton JBS_jBtnConfirmar;
    private javax.swing.JButton JBS_jBtnExcluir;
    private javax.swing.JButton JBS_jBtnIncluir;
    private javax.swing.JButton JBS_jBtnPesquisar;
    private javax.swing.JFormattedTextField JBS_jFmtCEP;
    private javax.swing.JFormattedTextField JBS_jFmtCPF;
    private javax.swing.JFormattedTextField JBS_jFmtCelular;
    private javax.swing.JFormattedTextField JBS_jFmtDataNasc;
    private javax.swing.JFormattedTextField JBS_jFmtNumCasa;
    private javax.swing.JFormattedTextField JBS_jFmtRG;
    private javax.swing.JTextField JBS_jTxtBairro;
    private javax.swing.JTextField JBS_jTxtCidade;
    private javax.swing.JTextField JBS_jTxtCodigo;
    private javax.swing.JTextField JBS_jTxtEmail;
    private javax.swing.JTextField JBS_jTxtEstado;
    private javax.swing.JTextField JBS_jTxtGenero;
    private javax.swing.JTextField JBS_jTxtNaturalidade;
    private javax.swing.JTextField JBS_jTxtNome;
    private javax.swing.JTextField JBS_jTxtRua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelGenero;
    private javax.swing.JLabel jLabelNaturalidade;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelRG;
    // End of variables declaration//GEN-END:variables
}
