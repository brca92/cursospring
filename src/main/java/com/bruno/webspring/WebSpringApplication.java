package com.bruno.webspring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.webspring.domain.Categoria;
import com.bruno.webspring.domain.Produto;
import com.bruno.webspring.repositories.CategoriaRepository;
import com.bruno.webspring.repositories.ProdutoRepository;

@SpringBootApplication
public class WebSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private ProdutoRepository produtorepository;

	public static void main(String[] args) {
		SpringApplication.run(WebSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Categoria cat1 =new Categoria(null, "Ïnformatica");
		Categoria cat2 =new Categoria(null, "Escritorio");
		
		Produto p1 =new Produto(null,"Computador",2000.00);
		Produto p2 =new Produto(null,"Impressora",800.00);
		Produto p3 =new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
	
		categoriarepository.saveAll(Arrays.asList(cat1,cat2));
		produtorepository.saveAll(Arrays.asList(p1,p2,p3));
	
	}

}

