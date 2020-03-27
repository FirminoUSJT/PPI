package paises;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {

	Pais pais,copia;
	static int id = 0;
	static long populacao;
	static double area;
	static String nome;
	

	@Before
	public void setUp() throws Exception {
		System.out.println("\nsetup");
		pais = new Pais(id, "Netherlands", 17000000, 41528.00);
		copia = new Pais(id, "Netherlands", 17000000, 41528.00);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test01Criar() {
		System.out.println("\ncriar");
		pais.criar();
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("Teste de Criacao", pais, copia);
	}
	

	@Test
	public void test02Atualizar() {
		System.out.println("\natualizar");
		pais.setId(1);
		copia.setId(1);
		pais.setArea(9.9);
		copia.setArea(9.9);
		pais.atualizar();
		pais.carregar();
		assertEquals("Teste de Atualizacao", pais, copia);
	
	}

	@Test
	public void test03Excluir() {
		System.out.println("\nexcluir");
		copia.setId(0);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0.0);
		pais.excluir();
		pais.carregar();
		assertEquals("Teste para Excluir", pais, copia);
	}
	
	@Test
	public void test04menorArea() {
		System.out.println("\nMenor Area");
		pais.MenorArea();
		area = pais.getArea();
		copia.setArea(area);
		assertEquals("Teste para informar a Menor Area", copia, pais);
	}
	
	@Test
	public void test04maiorPopulacao() {
		System.out.println("\nMaiorPopulação");
		pais.MaiorPopulacao();
		populacao = pais.getPopulacao();
		copia.setPopulacao(populacao);
		assertEquals("Teste para informar a Maior Populacao", copia, pais);
	}
	
	@Test
	public void test05testarVetor() {
		System.out.println("\\Vetor Para Retornar 3 Paises");
		Pais[] vetor = Pais.vet3paises();
		for (Pais pais : vetor) {
			id = pais.getId();
			area = pais.getArea();
			nome = pais.getNome();
			populacao = pais.getPopulacao();
			copia.setId(id);
			copia.setArea(area);
			copia.setNome(nome);
			copia.setPopulacao(populacao);
			assertEquals("Testa o menor valor da tabela", copia, pais);
		}
	}
}