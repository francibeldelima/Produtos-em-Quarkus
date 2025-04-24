package br.com.atlantic.stg;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Path("/produto")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class ProdutoResource {

    private static Map<Integer, String> produtos = new HashMap<>();
    private static int ultimoId = 7;

    static {
        produtos.put(1, "LapTop");
        produtos.put(2, "Armário");
        produtos.put(3, "Pia");
        produtos.put(4, "Cama");
        produtos.put(5, "Colchão");
        produtos.put(6, "Mesa");
        produtos.put(7, "Cadeira");
    }

    @GET
    @Path("/nome")
    public String getNomeProduto() {
        return String.join(", ", produtos.values());
    }

    @GET
    @Path("/nome/{id}")
    public String getNomeProdutoById(@PathParam("id") Integer id) {
        return produtos.getOrDefault(id, "Para esse id não existe nenhum produto");
    }

    @POST
    @Path("/nome")
    public String postNomeProduto(String nomeProduto) {
        ultimoId++;
        produtos.put(ultimoId, nomeProduto);
        return nomeProduto + " foi adicionado com o id " + ultimoId + " em " + LocalDateTime.now();
    }

    @PUT
    @Path("/nome/{id}")
    public String putNomeProduto(@PathParam("id") Integer id, String nomeProduto) {
        if (produtos.containsKey(id)) {
            produtos.put(id, nomeProduto);
            return nomeProduto + " foi atualizado com o id " + id + " em " + LocalDateTime.now();
        }
        return "Produto com id " + id + " não encontrado para atualização.";
    }

    @DELETE
    @Path("/{id}")
    public String deleteNomeProduto(@PathParam("id") Integer id) {
        if (produtos.containsKey(id)) {
            produtos.remove(id);
            return "O produto " + id + " foi excluído com sucesso";
        }
        return "O produto não existe";
    }
}
