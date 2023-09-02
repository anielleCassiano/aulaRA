import aulara.Carrinho;
import aulara.Login;
import aulara.Produto;
import aulara.Usuario;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

public class Exercicio04 {

    private Usuario usuario;
    private Produto produto;
    private Carrinho carrinho;
    private Login login;
    private Faker faker;

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
    public void verificarExclusaoUsuario(){
        String userID = usuario.cadastrarUsuario(usuario);
        String userToken = login.efetuarLogin(login);
        String productId = produto.cadastrarProduto(produto, userToken);
        carrinho.cadastrarCarrinho(productId, 5, userToken);
        usuario.excluirUsuarioFalha(userID);
        carrinho.cancelarCompra(userToken);
        produto.excluirProdutosPorId(productId, userToken);
        usuario.excluirUsuarioPorID(userID);
    }
}
