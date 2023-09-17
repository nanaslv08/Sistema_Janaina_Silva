package bean;
// Generated 12/09/2023 09:26:38 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JbsVendaProduto generated by hbm2java
 */
@Entity
@Table(name="jbs_venda_produto"
    ,catalog="db_janaina_silva"
)
public class JbsVendaProduto  implements java.io.Serializable {


     private int jbsIdVendaProduto;
     private JbsProduto jbsProduto;
     private JbsVenda jbsVenda;
     private String jbsQuantida;
     private double jbsVUnitario;

    public JbsVendaProduto() {
    }

    public JbsVendaProduto(int jbsIdVendaProduto, JbsProduto jbsProduto, JbsVenda jbsVenda, String jbsQuantida, double jbsVUnitario) {
       this.jbsIdVendaProduto = jbsIdVendaProduto;
       this.jbsProduto = jbsProduto;
       this.jbsVenda = jbsVenda;
       this.jbsQuantida = jbsQuantida;
       this.jbsVUnitario = jbsVUnitario;
    }
   
     @Id 

    
    @Column(name="jbs_id_venda_produto", unique=true, nullable=false)
    public int getJbsIdVendaProduto() {
        return this.jbsIdVendaProduto;
    }
    
    public void setJbsIdVendaProduto(int jbsIdVendaProduto) {
        this.jbsIdVendaProduto = jbsIdVendaProduto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="jbs_produto", nullable=false)
    public JbsProduto getJbsProduto() {
        return this.jbsProduto;
    }
    
    public void setJbsProduto(JbsProduto jbsProduto) {
        this.jbsProduto = jbsProduto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="jbs_venda", nullable=false)
    public JbsVenda getJbsVenda() {
        return this.jbsVenda;
    }
    
    public void setJbsVenda(JbsVenda jbsVenda) {
        this.jbsVenda = jbsVenda;
    }

    
    @Column(name="jbs_quantida", nullable=false, length=100)
    public String getJbsQuantida() {
        return this.jbsQuantida;
    }
    
    public void setJbsQuantida(String jbsQuantida) {
        this.jbsQuantida = jbsQuantida;
    }

    
    @Column(name="jbs_v_unitario", nullable=false, precision=13)
    public double getJbsVUnitario() {
        return this.jbsVUnitario;
    }
    
    public void setJbsVUnitario(double jbsVUnitario) {
        this.jbsVUnitario = jbsVUnitario;
    }




}


