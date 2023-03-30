import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDB implements ExtratorConteudo{
    
    public List<Conteudo> extratorConteudos(String json) {

        // extrair os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        //popular a lista de conteudos
        return listaDeAtributos.stream()
            .map(atributos -> new Conteudo(
                atributos.get("title"), 
                atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg")))
            .toList();
    }
}
