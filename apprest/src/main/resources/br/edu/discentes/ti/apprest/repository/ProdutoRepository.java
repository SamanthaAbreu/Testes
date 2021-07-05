package br.edu.discentes.ti.apprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.discentes.ti.apprest.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
