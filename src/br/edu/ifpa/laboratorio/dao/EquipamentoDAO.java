package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import br.edu.ifpa.laboratorio.model.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipamentoDAO {

    // ALERTA RESOLVIDO: O try-with-resources abre e fecha a conexão automaticamente
    public Equipamento buscarPorId(int id) {
        String sql = "SELECT * FROM equipamento WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Equipamento(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getBoolean("disponivel")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar equipamento: " + e.getMessage());
        }
        return null;
    }

    public void atualizarDisponibilidade(int id, boolean disponivel) {
        String sql = "UPDATE equipamento SET disponivel = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar equipamento: " + e.getMessage());
        }
    }
}