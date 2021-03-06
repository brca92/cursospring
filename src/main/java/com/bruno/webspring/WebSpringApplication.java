package com.bruno.webspring;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.webspring.domain.Categoria;
import com.bruno.webspring.domain.Cidade;
import com.bruno.webspring.domain.Cliente;
import com.bruno.webspring.domain.Endereco;
import com.bruno.webspring.domain.Estado;
import com.bruno.webspring.domain.ItemPedido;
import com.bruno.webspring.domain.Pagamento;
import com.bruno.webspring.domain.PagamentoComBoleto;
import com.bruno.webspring.domain.PagamentoComCartao;
import com.bruno.webspring.domain.Pedido;
import com.bruno.webspring.domain.Produto;
import com.bruno.webspring.domain.enums.EstadoPagamento;
import com.bruno.webspring.domain.enums.TipoCliente;
import com.bruno.webspring.repositories.CategoriaRepository;
import com.bruno.webspring.repositories.CidadeRepository;
import com.bruno.webspring.repositories.ClienteRepository;
import com.bruno.webspring.repositories.EnderecoRepository;
import com.bruno.webspring.repositories.EstadoRepository;
import com.bruno.webspring.repositories.ItemPedidoRepository;
import com.bruno.webspring.repositories.PagamentoRepository;
import com.bruno.webspring.repositories.PedidoRepository;
import com.bruno.webspring.repositories.ProdutoRepository;

@SpringBootApplication
public class WebSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private CidadeRepository cidaderepository;
	
	@Autowired
	private EstadoRepository estadorepository;
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private EnderecoRepository enderecorepository;
	
	@Autowired
	private PedidoRepository pedidorepository;
	
	@Autowired
	private PagamentoRepository pagamentorepository;
	
	
	@Autowired
	private ItemPedidoRepository itempedidorepository;

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
		
		Estado est1=new Estado(null,"Minas Gerais");
		Estado est2= new Estado(null,"Sao Paulo");
		
		
		Cidade c1=new Cidade(null,"Uberlandia",est1);
		Cidade c2=new Cidade(null,"Sao paulo",est2);
		Cidade c3=new Cidade(null,"Campinas",est2);
		
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2,c3));
		
		
		
		estadorepository.saveAll(Arrays.asList(est1,est2));
		cidaderepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1=new Cliente(null,"Maria","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		
		Endereco e1=new Endereco(null,"Rua flores","300","Apto 203","Jardim","38220834",c1,cli1);
		Endereco e2=new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",c2,cli1);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
	clienterepository.saveAll(Arrays.asList(cli1));
	enderecorepository.saveAll(Arrays.asList(e1,e2));
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido ped1=new Pedido(null, sdf.parse("30/09/2017 10:32"),  cli1, e1);
	Pedido ped2=new Pedido(null, sdf.parse("10/10/2017 19:35"),  cli1, e2);
	
	Pagamento pagto1=new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	ped1.setPagamento(pagto1);
	
	Pagamento pagto2=new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	ped2.setPagamento(pagto2);
	
	
	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
	
	pedidorepository.saveAll(Arrays.asList(ped1,ped2));
	
	pagamentorepository.saveAll(Arrays.asList(pagto1,pagto2));
	
	
	ItemPedido ip1=new ItemPedido(ped1,p1,0.00,1,2000.00);
	ItemPedido ip2=new ItemPedido(ped1,p3,0.00,2,80.00);
	ItemPedido ip3=new ItemPedido(ped2,p2,100.00,1,800.00);
	
	ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	ped2.getItens().addAll(Arrays.asList(ip3));
	
	
	p1.getItens().addAll(Arrays.asList(ip1));
	p2.getItens().addAll(Arrays.asList(ip3));
	p3.getItens().addAll(Arrays.asList(ip2));
	
	
	itempedidorepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}

