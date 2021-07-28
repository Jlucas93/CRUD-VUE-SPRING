package crud.lucas.crudprod.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import crud.lucas.crudprod.ProdutoCategoria;
import crud.lucas.crudprod.ProdutoStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private double preco;

    @Column(name = "codigo")    
    private String codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name= "categoria")
    private ProdutoCategoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(name= "status")
    private ProdutoStatus status;
    
}
