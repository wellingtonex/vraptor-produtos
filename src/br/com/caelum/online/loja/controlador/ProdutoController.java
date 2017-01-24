package br.com.caelum.online.loja.controlador;

import java.util.List;



import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private ProdutoDao produtoDAO;
	private Result result;

	public ProdutoController(Result result){
		this.result = result;
		this.produtoDAO = new ProdutoDao();
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
	public void adiciona(Produto produto) {
		produtoDAO.salva(produto);
		result.include("mensagem", "Novo produto adicionado com sucesso.");
		result.redirectTo(this).lista();
	}
	
	public void formulario(){}
}
