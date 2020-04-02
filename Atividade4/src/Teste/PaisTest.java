package Teste;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import Modelo.Pais;
import Service.PaisService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {

	Pais Pais,copia;
	PaisService PaisService;
	static int id = 0;
	static long populacao;
	static double area;
	static String nome;

	
	@Before
	public void setUp() throws Exception {
		Pais = new Pais();
		Pais.setId(id);
		Pais.setNome("Deutschland");
		Pais.setPopulacao(83000000);
		Pais.setArea(357051);
		
		copia = new Pais();
		copia.setId(id);
		copia.setNome("France");
		copia.setPopulacao(67348000);
		copia.setArea(543965);
		
		PaisService = new PaisService();
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println(id);
	}
		
		@Test
		public void test01Criar() {
			System.out.println("\ncriar");
			Pais.criar();
			id = Pais.getId();
			System.out.println(id);
			copia.setId(id);
			assertEquals("Teste de Criacao", Pais, copia);
		}
		

		@Test
		public void test02Atualizar() {
			System.out.println("\natualizar");
			Pais.setId(1);
			copia.setId(1);
			Pais.setArea(9.9);
			copia.setArea(9.9);
			Pais.atualizar();
			Pais.carregar();
			assertEquals("Teste de Atualizacao", Pais, copia);
		
		}

		@Test
		public void test03Excluir() {
			System.out.println("\nexcluir");
			copia.setId(0);
			copia.setNome(null);
			copia.setPopulacao(0);
			copia.setArea(0.0);
			Pais.excluir();
			Pais.carregar();
			assertEquals("Teste para Excluir", Pais, copia);
		}
		
		@Test
		public void test04menorArea() {
			System.out.println("\nMenor Area");
			Pais.MenorArea();
			area = Pais.getArea();
			copia.setArea(area);
			assertEquals("Teste para informar a Menor Area", copia, Pais);
		}
		
		@Test
		public void test04maiorPopulacao() {
			System.out.println("\nMaiorPopulação");
			Pais.MaiorPopulacao();
			populacao = Pais.getPopulacao();
			copia.setPopulacao(populacao);
			assertEquals("Teste para informar a Maior Populacao", copia, Pais);
		}
		
		@Test
		public void test05testarVetor() {
			System.out.println("\nVetor Para Retornar 3 Paises");
			Pais[] vetor = model.Pais.vet3paises();
			for (Pais Pais : vetor) {
				id = Pais.getId();
				area = Pais.getArea();
				nome = Pais.getNome();
				populacao = Pais.getPopulacao();
				copia.setId(id);
				copia.setArea(area);
				copia.setNome(nome);
				copia.setPopulacao(populacao);
				assertEquals("Testa o menor valor da tabela", copia, Pais);
			}
		}
	}