package br.edu.ifpa.laboratorio.controller;

import br.edu.ifpa.laboratorio.dao.EmprestimoDAO;
import br.edu.ifpa.laboratorio.dao.EquipamentoDAO;
import br.edu.ifpa.laboratorio.model.Emprestimo;
import br.edu.ifpa.laboratorio.model.Equipamento;

public class ControleEmprestimo {

    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    public boolean realizarEmprestimo(int idAluno, int idEquipamento) {
        // 1. Verifica se o equipamento existe e está disponível (Regra 7.1 e 7.4)
        Equipamento eq = equipamentoDAO.buscarPorId(idEquipamento);

        if (eq == null) {
            System.out.println("Erro: Equipamento inexistente.");
            return false;
        }

        if (!eq.isDisponivel()) {
            System.out.println("Erro: O equipamento '" + eq.getNome() + "' está indisponível!");
            return false;
        }

        // 2. Cria o objeto de empréstimo
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setIdAluno(idAluno);
        novoEmprestimo.setIdEquipamento(idEquipamento);

        // 3. Salva no banco (Regra 7.2 - Parte A)
        emprestimoDAO.salvar(novoEmprestimo);

        // 4. Atualiza o equipamento para indisponível (Regra 7.2 - Parte B)
        equipamentoDAO.atualizarDisponibilidade(idEquipamento, false);

        System.out.println("Sucesso: Empréstimo do equipamento '" + eq.getNome() + "' realizado com sucesso.");
        return true;
    }
}