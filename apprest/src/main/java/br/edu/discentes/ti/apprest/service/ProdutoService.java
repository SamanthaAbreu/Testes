package br.edu.discentes.ti.apprest.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.discentes.ti.apprest.entity.Produto;
import br.edu.discentes.ti.apprest.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> listarTodos() {
		return Collections.unmodifiableList(produtoRepository.findAll());
	}

	public void salvar(Produto produto) {
		if (produto.getNome() != null)
			if (!produto.getNome().trim().equals(""))
				produtoRepository.save(produto);

	}

	public Produto pegaProdutoPorId(Long id) {
		if (id != 0)
			return produtoRepository.findById(id).get();
		throw new UnsupportedOperationException("O Id deve ser maior que zero!!");
	}

	public Produto pegaProdutoPorNome(String nome) {
		if (nome != null)
			if (!nome.trim().equals(""))
				return produtoRepository.findByNome(nome);
		throw new UnsupportedOperationException("O nome do produto não foi dito");
	}

	
	public boolean removerporId(Long id) {
		if (id > 0) {
			try {
				produtoRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				return false;
			}
			
			return true;
		}
		return false;
	}
	
	

}
