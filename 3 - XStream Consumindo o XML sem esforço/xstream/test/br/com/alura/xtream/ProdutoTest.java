
package br.com.alura.xtream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class ProdutoTest {

    @Test
    public void deveGerarOXmlComONomePrecoEDescricaoAdequados() {
    	String resultadoEsperado = "<produto codigo=\"1587\">\n" +
                "  <nome>geladeira</nome>\n" +
                "  <preco>R$ 1.000,00</preco>\n" +
                "  <descri??o>geladeira duas portas</descri??o>\n" +
                "</produto>";

    	Produto geladeira = new Produto("geladeira", 1000, "geladeira duas portas", 1587);

        XStream xstream = new XStream();
        xstream.alias("produto", Produto.class);
        xstream.aliasField("descri??o", Produto.class, "descricao");
        xstream.useAttributeFor(Produto.class, "codigo");
        xstream.registerLocalConverter(Produto.class, "preco", new PrecoSimplesConverter());

        String xmlGerado = xstream.toXML(geladeira);

        assertEquals(resultadoEsperado, xmlGerado);
    }

}