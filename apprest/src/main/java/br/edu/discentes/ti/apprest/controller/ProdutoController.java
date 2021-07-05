package br.edu.discentes.ti.apprest.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.discentes.ti.apprest.controller.utils.RespostaOperaçao;
import br.edu.discentes.ti.apprest.entity.Produto;
import br.edu.discentes.ti.apprest.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping("/produtos")
	public List<Produto> listarProdutos() {
		return produtoService.listarTodos();

	}

	@PostMapping("/produtos")
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
		produtoService.salvar(produto);
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> pegaProdutosId(@PathVariable (value = "id")long id) {
		var produto = produtoService.pegaProdutoPorId(id);
		return ResponseEntity.ok().body(produto);
	}

	/*@GetMapping("/produto/{nome}")
	public ResponseEntity<Produto> pegaProdutosNome(@PathVariable String nome) {
		var produto = produtoService.pegaProdutoPorNome(nome);
		return ResponseEntity.ok().body(produto);
	}*/

	@PutMapping("/produtos/{id}")
	@Transactional
	public ResponseEntity<Produto> atualizarProdutos(@PathVariable Long id, @RequestBody Produto produtoform,
			UriComponentsBuilder uriBuilder) {
		var produto = produtoService.pegaProdutoPorId(id);
		produto.setNome(produtoform.getNome());
		produto.setDescriçao(produtoform.getDescriçao());
		produto.setPreço(produtoform.getPreço());
		produtoService.salvar(produto);
		URI uri = uriBuilder.path("/produtos/{Id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@DeleteMapping("/produtos/{id}")
	public @ResponseBody RespostaOperaçao removerProduto(@PathVariable Long id) {
		if (produtoService.removerporId(id)) {
			return new RespostaOperaçao("Foi removido com sucesso!! :-)");
		}
		throw new UnsupportedOperationException("Ocorreu um erro, verifique se a URI e o parametro.");

	}

}
