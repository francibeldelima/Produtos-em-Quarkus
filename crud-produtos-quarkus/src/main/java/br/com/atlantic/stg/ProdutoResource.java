// Pacote da classe
package br.com.atlantic.stg;

// Importação das bibliotecas 
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Define a classe como um recurso REST acessível pelo caminho "/produto"
@Path("/produto")

// Especifica que o formato de saída será texto simples (plain text)
@Produces(MediaType.TEXT_PLAIN)
// Especifica que o formato de entrada será texto simples (plain text)
@Consumes(MediaType.TEXT_PLAIN)
public class ProdutoResource {

    // Mapa estático para armazenar os produtos, onde a chave é o ID e o valor é o nome do produto
    private static Map<Integer, String> produtos = new HashMap<>();
    
    // Variável para controlar o último ID atribuído aos produtos
    private static int ultimoId = 7;

    // Bloco estático para inicializar alguns produtos no sistema
    static {
        produtos.put(1, "LapTop");
        produtos.put(2, "Armário");
        produtos.put(3, "Pia");
        produtos.put(4, "Cama");
        produtos.put(5, "Colchão");
        produtos.put(6, "Mesa");
        produtos.put(7, "Cadeira");
    }

    // Método GET para retornar os nomes de todos os produtos armazenados 
    @GET
    @Path("/nome")
    public String getNomeProduto() {
        // Retorna os nomes dos produtos, separados por vírgula 
        return String.join(", ", produtos.values());
    }

    // Método GET para retornar o nome de um produto específico pelo seu ID
    @GET
    @Path("/nome/{id}")
    public String getNomeProdutoById(@PathParam("id") Integer id) {
        // Verifica se o produto existe no mapa, senão retorna uma mensagem de erro
        return produtos.getOrDefault(id, "Para esse id não existe nenhum produto");
    }

    // Método POST para adicionar um novo produto 
    @POST
    @Path("/nome")
    public String postNomeProduto(String nomeProduto) {
        // Incrementa o ID para o próximo produto
        ultimoId++;
        // Adiciona o novo produto ao mapa com o ID gerado
        produtos.put(ultimoId, nomeProduto);
        // Retorna uma mensagem indicando que o produto foi adicionado com sucesso com o timestamp da operação
        return nomeProduto + " foi adicionado com o id " + ultimoId + " em " + LocalDateTime.now();
    }

    // Método PUT para atualizar o nome de um produto existente pelo seu ID
    @PUT
    @Path("/nome/{id}")
    public String putNomeProduto(@PathParam("id") Integer id, String nomeProduto) {
        // Verifica se o produto com o ID informado existe
        if (produtos.containsKey(id)) {
            // Atualiza o nome do produto no mapa
            produtos.put(id, nomeProduto);
            // Retorna uma mensagem indicando que o produto foi atualizado com o timestamp da operação
            return nomeProduto + " foi atualizado com o id " + id + " em " + LocalDateTime.now();
        }
        // Se o produto não existir, retorna uma mensagem de erro
        return "Produto com id " + id + " não encontrado para atualização.";
    }

    // Método DELETE para excluir um produto existente pelo seu ID
    @DELETE
    @Path("/{id}")
    public String deleteNomeProduto(@PathParam("id") Integer id) {
        // Verifica se o produto com o ID informado existe
        if (produtos.containsKey(id)) {
            // Remove o produto do mapa
            produtos.remove(id);
            // Retorna uma mensagem indicando que o produto foi excluído com sucesso
            return "O produto " + id + " foi excluído com sucesso";
        }
        // Se o produto não existir, retorna uma mensagem de erro
        return "O produto não existe";
    }
}
