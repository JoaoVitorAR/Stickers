import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        /*API api = API.IMDB_TOP_TVS;
        String url = api.getUrl();
        ExtratorConteudo extrator = api.getExtrator();*/

        String url = "http://localhost:8080/linguagens";
        ExtratorConteudo extrator = new ExtratorConteudoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extratorConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();
        
        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.titulo().replace(":", " -") +".png";
            String legendaFigurinha = conteudo.titulo();
            geradora.cria(inputStream, nomeArquivo, legendaFigurinha);
            
            System.out.println("\u001b[1mTítulo:\u001b[m " + (conteudo.titulo()));
            System.out.println("\n"); 
        }
    }
}
