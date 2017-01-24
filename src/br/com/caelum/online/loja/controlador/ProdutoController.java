package br.com.caelum.online.loja.controlador;

import java.util.List;



import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class ProdutoController {
	
	private ProdutoDao produtoDAO;

	public ProdutoController(){
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
	
	@Post
	public void adiciona(Produto produto) {
		produtoDAO.salva(produto);
	}
	
	public void formulario(){}
}
