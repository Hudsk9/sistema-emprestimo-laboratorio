package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import br.edu.ifpa.laboratorio.model.Emprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoDAO {

    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_aluno, id_equipamento, status) VALUES (?, ?, 'ATIVO')";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getIdAluno());
            stmt.setInt(2, emprestimo.getIdEquipamento());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }
}