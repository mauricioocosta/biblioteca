package com.exemplo.biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.biblioteca.ConexaoDB;
import com.exemplo.biblioteca.exception.LivroNaoEncontradoException;
import com.exemplo.biblioteca.model.Livro;

public class BibliotecaDAO {

    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.executeUpdate();

            // Recupera o ID gerado pelo banco
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    livro.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano")
                );
                livro.setId(rs.getLong("id")); // seta o id depois
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE titulo LIKE ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano")
                );
                livro.setId(rs.getLong("id"));
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE autor LIKE ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + autor + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano")
                );
                livro.setId(rs.getLong("id"));
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public void removerLivro(int id) throws LivroNaoEncontradoException {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            if (linhas == 0) {
                throw new LivroNaoEncontradoException("Livro com id " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}