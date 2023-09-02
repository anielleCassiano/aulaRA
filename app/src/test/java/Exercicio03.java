import aulara.Carrinho;
import aulara.Login;
import aulara.Produto;
import aulara.Usuario;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Exercicio03 {

    private Usuario usuario;
    private Produto produto;
    private Carrinho carrinho;
    private Login login;
    private Faker faker;

    private String productId;

    private String userID;

    private String userToken;

    @Before
    public void preCondition(){
        faker = new Faker();
        String userName = faker.name().firstName();
        String email = userName + "@qa.com.br";
        usuario = new Usuario(userName, email, "teste", "true");
        login = new Login(email, "teste");
        String productName = faker.pokemon().name();
        produto = new Produto(productName, 1000, "Pokemon", 100);
        carrinho = new Carrinho();
    }

    @Test
    public void verificarEstoque(){
        userID = usuario.cadastrarUsuario(usuario);
        userToken = login.efetuarLogin(login);
        productId = produto.cadastrarProduto(produto, userToken);
        carrinho.cadastrarCarrinho(productId, 5, userToken);
        produto.listarProdutosPorId(productId, 95);
        carrinho.cancelarCompra(userToken);
        produto.listarProdutosPorId(productId, 100);
    }

    @After
    public void posCondition(){
        produto.excluirProdutosPorId(productId, userToken);
        usuario.excluirUsuarioPorID(userID);
    }
}
