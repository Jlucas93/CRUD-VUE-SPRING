package crud.lucas.crudprod.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crud.lucas.crudprod.Entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
