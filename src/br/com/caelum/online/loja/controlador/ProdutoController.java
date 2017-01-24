package br.com.caelum.online.loja.controlador;

import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.online.loja.repositorio.RepositorioDeProdutos;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private RepositorioDeProdutos produtoDAO;
	private Result result;
	private Validator validator;

	public ProdutoController(RepositorioDeProdutos produtoDao, Result result, Validator validator){
		this.result = result;
		this.produtoDAO = produtoDao;
		this.validator = validator;
	}

	public List<Produto> lista() {
		System.out.println("chamou a listagem");
		return produtoDAO.pegaTodos();
	}
	
	@Path("/produto/{id}")
	public Produto exibe(Long id) {
		return produtoDAO.pegaPorId(id);
	}
	
	@Path("/produto/{id}/xml")
	public void exibeComXml(Long id) {
		 Produto produto = produtoDAO.pegaPorId(id);
		 result.use(Results.xml()).from(produto).serialize();
	}
	
	@Path("/produto/{id}/json")
	public void exibeComJson(Long id) {
		Produto produto = produtoDAO.pegaPorId(id);
		result.use(Results.json()).from(produto).serialize();
	}
	
	@Post
	public void adiciona(final Produto produto) {
		
		validator.checking(new Validations() {
			{
				that(produto.getPreco() > 0.1, "erro", "produto.preco.invalido");
			}
		});
		
		
		validator.onErrorUsePageOf(this).formulario();
		
		produtoDAO.salva(produto);
		result.include("mensagem", "Novo produto adicionado com sucesso.");
		result.redirectTo(this).lista();
	}
	
	public void formulario(){}
}
