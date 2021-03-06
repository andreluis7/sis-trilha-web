package br.com.alura.xtream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class CompraTest {

	@Test
	public void deveSerializarCadaUmDosProdutosDeUmaCompra() {

		String resultadoEsperado = "<compra>\n"+
								   "  <id>15</id>\n"+
								   "  <produtos>\n"+
								   "    <produto codigo=\"1587\">\n"+
								   "      <nome>geladeira</nome>\n"+
								   "      <preco>1000.0</preco>\n"+
								   "      <descri??o>geladeira duas portas</descri??o>\n"+
								   "    </produto>\n"+
								   "    <produto codigo=\"1588\">\n"+
								   "      <nome>ferro de passar</nome>\n"+
								   "      <preco>100.0</preco>\n"+
								   "      <descri??o>ferro com vaporizador</descri??o>\n"+
								   "    </produto>\n"+
								   "  </produtos>\n"+
								   "</compra>";

		Compra compra = compraComGeladeiraEFerro();

		XStream xstream = xstreamParaCompraEProduto();

		String xmlGerado = xstream.toXML(compra);

		assertEquals(resultadoEsperado, xmlGerado);
	}

	@Test
	public void deveGerarUmaCompraComOsProdutosAdequados() {
		String xmlDeOrigem = "<compra>\n"+
						     "  <id>15</id>\n"+
						     "  <produtos>\n"+
						     "    <produto codigo=\"1587\">\n"+
						     "      <nome>geladeira</nome>\n"+
						     "      <preco>1000.0</preco>\n"+
						     "      <descri??o>geladeira duas portas</descri??o>\n"+
						     "    </produto>\n"+
						     "    <produto codigo=\"1588\">\n"+
						     "      <nome>ferro de passar</nome>\n"+
						     "      <preco>100.0</preco>\n"+
						     "      <descri??o>ferro com vaporizador</descri??o>\n"+
						     "    </produto>\n"+
						     "  </produtos>\n"+
						     "</compra>";

		XStream xstream = xstreamParaCompraEProduto();

		// solu??o para erro de seguran?a xtream
		xstream.allowTypesByWildcard(new String[] { "br.com.alura.xtream.**" });

		Compra compraResultado = (Compra) xstream.fromXML(xmlDeOrigem);

		Compra compraEsperada = compraComGeladeiraEFerro();
		assertEquals(compraEsperada, compraResultado);
	}
	
	@Test
	public void deveSerializarDuasGeladeirasIguais() {
	    String resultadoEsperado = "<compra>\n" 
	            + "  <id>15</id>\n"
	            + "  <produtos>\n" 
	            + "    <produto codigo=\"1587\">\n"
	            + "      <nome>geladeira</nome>\n"
	            + "      <preco>1000.0</preco>\n"
	            + "      <descri??o>geladeira duas portas</descri??o>\n"
	            + "    </produto>\n"
	            + "    <produto codigo=\"1587\">\n"
	            + "      <nome>geladeira</nome>\n"
	            + "      <preco>1000.0</preco>\n"
	            + "      <descri??o>geladeira duas portas</descri??o>\n"
	            + "    </produto>\n"
	            + "  </produtos>\n" 
	            + "</compra>";

	    Compra compra = compraDuasGeladeirasIguais();

	    XStream xstream = xstreamParaCompraEProduto();
	    xstream.setMode(XStream.NO_REFERENCES);

	    String xmlGerado = xstream.toXML(compra);

	    assertEquals(resultadoEsperado, xmlGerado);
	}
	

    @Test
    public void deveSerializarColecaoImplicita() {
        String resultadoEsperado = "<compra>\n" 
                + "  <id>15</id>\n"
                + "  <produto codigo=\"1587\">\n"
                + "    <nome>geladeira</nome>\n"
                + "    <preco>1000.0</preco>\n"
                + "    <descri??o>geladeira duas portas</descri??o>\n"
                + "  </produto>\n" 
                + "  <produto codigo=\"1588\">\n"
                + "    <nome>ferro de passar</nome>\n"
                + "    <preco>100.0</preco>\n"
                + "    <descri??o>ferro com vaporizador</descri??o>\n"
                + "  </produto>\n"
                + "</compra>";

        Compra compra = compraComGeladeiraEFerro();

        XStream xstream = xstreamParaCompraEProduto();
        xstream.addImplicitCollection(Compra.class, "produtos");

        String xmlGerado = xstream.toXML(compra);

        assertEquals(resultadoEsperado, xmlGerado);
    }
    

    @Test
    public void deveSerializarLivroEMusica() {
        String resultadoEsperado = "<compra>\n" 
                + "  <id>15</id>\n"
                + "  <produtos>\n" 
                + "    <livro codigo=\"1589\">\n"
                + "      <nome>O P?ssaro Raro</nome>\n"
                + "      <preco>100.0</preco>\n"
                + "      <descri??o>dez hist?rias sobre a exist?ncia</descri??o>\n"
                + "    </livro>\n" 
                + "    <musica codigo=\"1590\">\n"
                + "      <nome>Meu Passeio</nome>\n"
                + "      <preco>100.0</preco>\n"
                + "      <descri??o>m?sica livre</descri??o>\n"
                + "    </musica>\n"
                + "  </produtos>\n" 
                + "</compra>";

        Compra compra = compraComLivroEMusica();

        XStream xstream = xstreamParaCompraEProduto();

        String xmlGerado = xstream.toXML(compra);

        assertEquals(resultadoEsperado, xmlGerado);
    }
    
    @Test
    public void deveUsarUmConversorDiferente() {

        String resultadoEsperado = "<compra estilo=\"novo\">\n" 
                + "  <id>15</id>\n"
                + "  <fornecedor>guilherme.silveira@caelum.com.br</fornecedor>\n"
                + "  <endereco>\n"
                + "    <linha1>Rua Vergueiro 3185</linha1>\n"
                + "    <linha2>8 andar - Sao Paulo - SP</linha2>\n"
                + "  </endereco>\n"
                + "  <produtos>\n" 
                + "    <produto codigo=\"1587\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descri??o>geladeira duas portas</descri??o>\n"
                + "    </produto>\n"
                + "    <produto codigo=\"1587\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descri??o>geladeira duas portas</descri??o>\n"
                + "    </produto>\n"
                + "  </produtos>\n" 
                + "</compra>";
    	
        Compra compra = compraDuasGeladeirasIguais();

        XStream xstream = xstreamParaCompraEProduto();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.registerConverter(new CompraDiferenteConverter());
        String xmlGerado = xstream.toXML(compra);

        assertEquals(resultadoEsperado, xmlGerado);
    }
	
	private Compra compraDuasGeladeirasIguais() {
	    Produto geladeira = geladeira();

	    List<Produto> produtos = new ArrayList<Produto>();
	    produtos.add(geladeira);
	    produtos.add(geladeira);

	    Compra compra = new Compra(15, produtos);
	    return compra;
	}

	private XStream xstreamParaCompraEProduto() {
	    XStream xstream = new XStream();
	    xstream.alias("compra", Compra.class);
	    xstream.alias("produto", Produto.class);
	    xstream.alias("livro", Livro.class);
	    xstream.alias("musica", Musica.class);
	    xstream.aliasField("descri??o", Produto.class, "descricao");
	    xstream.useAttributeFor(Produto.class, "codigo");
	    return xstream;
	}

	private Compra compraComGeladeiraEFerro() {
		Produto geladeira = geladeira();
		Produto ferro = ferro();
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(geladeira);
		produtos.add(ferro);

		Compra compra = new Compra(15, produtos);
		return compra;
	}
	
	private Compra compraComLivroEMusica() {
	    Produto passaro = new Livro("O P?ssaro Raro", 100.0, "dez hist?rias sobre a exist?ncia", 1589);
	    Produto passeio = new Musica("Meu Passeio", 100.0, "m?sica livre", 1590);
	    List<Produto> produtos = new ArrayList<>();
	    produtos.add(passaro);
	    produtos.add(passeio);

	    return new Compra(15, produtos);
	}

	private Produto ferro() {
		Produto ferro = new Produto("ferro de passar", 100, "ferro com vaporizador", 1588);
		return ferro;
	}

	private Produto geladeira() {
		Produto geladeira = new Produto("geladeira", 1000, "geladeira duas portas", 1587);
		return geladeira;
	}
}