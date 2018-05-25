package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import model.entities.Vinho;
import util.ConnectionFactory;

public class MobiWineDAO {
	public int create(Vinho vinho) {
		try {
			//Obtém uma conexão com Banco de Dados
			Connection conn = ConnectionFactory.getConnection();
			
			//Define o comando SQL de inserção
			//Os pontos de integorração representam o mapeamento de valores
			//que iremos passar através das instruções abaixo
			String sql = "INSERT INTO bebida(nome, pais, regiao, nome_produtor, ano_safra, descricao, preco, tipo_vinho) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			//Prepara a instrução a ser executada no Banco de dados
			//Esta linha poderá causar uma exceção em tempo de compilação 
			//chamada SQLException			
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			

			//Define os valores para cada um dos pontos de interrogação
			stmt.setString(1, vinho.getNome());
			stmt.setString(2, vinho.getPais());
			stmt.setString(3, vinho.getRegiao());
			stmt.setString(4, vinho.getNomeProdutor());
			stmt.setString(5, vinho.getAnoSafra());
			stmt.setString(6, vinho.getDescricao());
			stmt.setString(7, vinho.getPreco());
			stmt.setString(8, vinho.getTipoVinho());
			
			System.out.println(stmt);
			
			//Executa a instrução SQL no Banco de Dados
			//Esta linha poderá causar uma exceção em tempo de compilação 
			//chamada SQLException
			stmt.executeUpdate();	
			
			
			// Recuperando o id cadastrado
			ResultSet rs = stmt.getGeneratedKeys();
			int id = -1;
			if (rs.next())
				id = rs.getInt(1);

			
			//Encerra a execução de instrução SQL
			//Encerra a conexão com o Banco
			stmt.close();
			conn.close();
			return id;
			
		} catch (Exception e) {
			//Caso uma das duas linhas especificada causem alguma exceção
			//este bloco irá tratar lançando uma exceção em tempo de execução.			
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Vinho retrieve(int id) {
		try {			
			//Define uma variável do tipo vinho com valor nulo
			//Caso o a execução deste método não retorne nenhum
			//dado de vinho retorna
			Vinho vinho = null;
			
			//Obtém uma conexão com Banco de Dados
			Connection conn = ConnectionFactory.getConnection();
			
			//Define o comando SQL de seleção
			String sql = "SELECT * FROM bebida WHERE id= ?";
			
			//Prepara a instrução a ser executada no Banco de dados
			//Esta linha poderá causar uma exceção em tempo de compilação 
			//chamada SQLException				
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			//Executa a instrução SQL no Banco de Dados e obtém o resultado da consulta
			//Esta linha poderá causar uma exceção em tempo de compilação 
			//chamada SQLException			
			ResultSet rs = stmt.executeQuery();
		
			
			//Caso exista algum dado de vinho retornado da consulta
			//cria um objeto do tipo Vinho com os dados
			if (rs.next()) {
				vinho = new Vinho();
				vinho.setId(id);
				vinho.setNome(rs.getString("nome"));
				vinho.setPais(rs.getString("pais"));
				vinho.setRegiao(rs.getString("regiao"));
				vinho.setNomeProdutor(rs.getString("nome_produtor"));
				vinho.setAnoSafra(rs.getString("ano_safra").substring(0, 4));
				vinho.setDescricao(rs.getString("descricao"));
				//vinho.setImagem(rs.getString("imagem"));
				vinho.setPreco(rs.getString("preco"));
				vinho.setTipoVinho(rs.getString("tipo_vinho"));
				
			}
			
			//Encerra a execução de instrução SQL
			//Encerra a conexão com o Banco			
			stmt.close();
			conn.close();
			
			
			//Retorna o objeto do tipo vinho
			return vinho;
			
		} catch (Exception e) {
			//Caso uma das duas linhas especificada causem alguma exceção
			//este bloco irá tratar lançando uma exceção em tempo de execução.			
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
//	
//	public Vinho retrieve(Vinho vinho) {
//		try {			
//			//Obtém uma conexão com Banco de Dados
//			Connection conn = ConnectionFactory.getConnection();
//			
//			//Define o comando SQL de seleção
//			String sql = "SELECT * FROM vinho WHERE rgm = ?";
//			
//			//Prepara a instrução a ser executada no Banco de dados
//			//Esta linha poderá causar uma exceção em tempo de compilação 
//			//chamada SQLException				
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, vinho.getRgm());
//			
//			//Executa a instrução SQL no Banco de Dados e obtém o resultado da consulta
//			//Esta linha poderá causar uma exceção em tempo de compilação 
//			//chamada SQLException			
//			ResultSet rs = stmt.executeQuery();
//			
//			//Caso exista algum dado de vinho retornado da consulta
//			//cria um objeto do tipo Vinho com os dados
//			if (rs.next()) {
//				vinho = new Vinho();
//				vinho.setNome(rs.getString("nome"));
//				vinho.setEmail(rs.getString("email"));
//				vinho.setEmail(rs.getString("foto"));
//			}
//			
//			//Encerra a execução de instrução SQL
//			//Encerra a conexão com o Banco			
//			stmt.close();
//			conn.close();
//			
//			//Retorna o objeto do tipo vinho
//			return vinho;
//			
//		} catch (Exception e) {
//			//Caso uma das duas linhas especificada causem alguma exceção
//			//este bloco irá tratar lançando uma exceção em tempo de execução.			
//			throw new RuntimeException(e.getMessage());
//		}
//	}
//	
	public int update(Vinho vinho) {
		try {
			//Obtém uma conexão com Banco de Dados
			Connection conn = ConnectionFactory.getConnection();
			
			//Define o comando SQL de atualização
			String sql = "UPDATE `bebida` SET `nome`= ?,`preco`= ?,`pais`=?,`regiao`=?,`nome_produtor`=?,`ano_safra`=?,`descricao`=?,`tipo_vinho`=? WHERE id = ?";
			
			//Prepara a instrução a ser executada no Banco de dados
			//Esta linha poderá causar uma exceção em tempo de compilação 
			//chamada SQLException				
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, vinho.getNome());
			stmt.setString(2, vinho.getPreco());
			stmt.setString(3, vinho.getPais());
			stmt.setString(4, vinho.getRegiao());
			stmt.setString(5, vinho.getNomeProdutor());
			stmt.setString(6, vinho.getAnoSafra());
			stmt.setString(7, vinho.getDescricao());
			stmt.setString(8, vinho.getTipoVinho());
			stmt.setInt(9, vinho.getId());
			
			
			System.out.println(stmt);
			//Executa a instrução SQL no banco de dados
			//e obtém a quantidade de linhas afetadas
			int result = stmt.executeUpdate();
			
			//Encerra a execução de instrução SQL
			//Encerra a conexão com o Banco				
			stmt.close();
			conn.close();
			
			
			
			//Devolve a quantidade de linhas para o método que o chamou
			return result;
			
		} catch (Exception e) {
			//Caso uma das duas linhas especificada causem alguma exceção
			//este bloco irá tratar lançando uma exceção em tempo de execução.			
			throw new RuntimeException(e.getMessage());
		}

	}
//	
//	public void delete(String rgm) {
//		try {
//			//Obtém uma conexão com Banco de Dados
//			Connection conn = ConnectionFactory.getConnection();
//			
//			//Define o comando SQL de exclusão
//			String sql = "DELETE FROM vinho WHERE rgm=" + rgm;
//			
//			//Prepara a instrução a ser executada no Banco de dados
//			//Esta linha poderá causar uma exceção em tempo de compilação 
//			//chamada SQLException				
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.execute();
//			
//			//Encerra a execução de instrução SQL
//			//Encerra a conexão com o Banco				
//			stmt.close();
//			conn.close();
//			
//		} catch (Exception e) {
//			//Caso uma das duas linhas especificada causem alguma exceção
//			//este bloco irá tratar lançando uma exceção em tempo de execução.			
//			throw new RuntimeException(e.getMessage());
//		}
//	}
//	
//	public ArrayList<Vinho> ListAll() {
//		try {			
//			//Cria um objeto que representa a lista de Vinhos
//			ArrayList<Vinho> vinhos = new ArrayList<>();
//			
//			//Obtém uma conexão com Banco de Dados
//			Connection conn = ConnectionFactory.getConnection();
//			
//			//Define o comando SQL de seleção
//			String sql = "SELECT * FROM vinho";
//			
//			//Prepara a instrução a ser executada no Banco de dados
//			//Esta linha poderá causar uma exceção em tempo de compilação 
//			//chamada SQLException				
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			
//			//Executa a instrução SQL no Banco de Dados e obtém o resultado da consulta
//			//Esta linha poderá causar uma exceção em tempo de compilação 
//			//chamada SQLException				
//			ResultSet rs = stmt.executeQuery();
//			
//			//Enquanto houver linhas de dados retornado da consulta
//			//cria um objeto com os dados de cada linha e adiciona à lista
//			while (rs.next()) {
//				Vinho a = new Vinho();
//				a.setRgm(rs.getString("rgm"));
//				a.setNome(rs.getString("nome"));
//				a.setEmail(rs.getString("email"));
//				a.setFoto(rs.getString("foto"));
//				
//				vinhos.add(a);
//			}
//			
//			//Encerra a execução de instrução SQL
//			//Encerra a conexão com o Banco				
//			stmt.close();
//			conn.close();
//			
//			//Retorna o objeto que representa a lista 
//			return vinhos;
//			
//		} catch (Exception e) {
//			//Caso uma das duas linhas especificada causem alguma exceção
//			//este bloco irá tratar lançando uma exceção em tempo de execução.			
//			throw new RuntimeException(e.getMessage());
//		}
//	}
}