package crud.lucas.crudprod.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.lucas.crudprod.Entity.Produto;
import crud.lucas.crudprod.Repository.ProdutoRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProdutoController {

    private Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoRepository produtoRepository;


    //Metodo GET
    @GetMapping(value="/produtos")
    public ResponseEntity<Object> getAllProdutos(){
        try{
            Iterable<Produto> produtos = produtoRepository.findAll();
			return new ResponseEntity<Object>(produtos, HttpStatus.OK);
        } catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }   
    }

    //Metodo GET ID
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> getPrdutoById(@PathVariable("id") Long id) {
        try {
            Produto produto = produtoRepository.findById(id).get();
            if(produto != null) {
                return new ResponseEntity<Object>(produto, HttpStatus.OK);				
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
}

    //Metodo POST
    @PostMapping("/produtos")
	public ResponseEntity<Object> createProduto(@RequestBody Produto produto) {
		try {
			Produto savedProduto = produtoRepository.save(produto);
			return new ResponseEntity<Object>(savedProduto, HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

    //Metodo UPDATE
    @PutMapping("/produtos/{id}")
	public ResponseEntity<Object> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
		try {
			produto.setId(id);
			Produto savedProduto = produtoRepository.save(produto);
			return new ResponseEntity<Object>(savedProduto, HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

    //Metodo DELETE
    @DeleteMapping("/produtos/{id}")
	public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("id") Long id) {
		try {
			produtoRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

}

}
