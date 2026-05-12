package br.edu.ifpa.laboratorio.view;

import br.edu.ifpa.laboratorio.controller.ControleEmprestimo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEmprestimo extends JFrame {

    private JTextField txtIdAluno;
    private JTextField txtIdEquipamento;
    private JButton btnEmprestar;
    private ControleEmprestimo controle;

    public TelaEmprestimo() {

        controle = new ControleEmprestimo();


        setTitle("Controle de Laboratório - Empréstimo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 15)); // Organiza os itens em grade
        setLocationRelativeTo(null); // Centraliza a janela na tela do PC

        // Criando e adicionando os componentes visuais na tela
        add(new JLabel("  ID do Aluno:"));
        txtIdAluno = new JTextField();
        add(txtIdAluno);

        add(new JLabel("  ID do Equipamento:"));
        txtIdEquipamento = new JTextField();
        add(txtIdEquipamento);

        add(new JLabel("")); // Espaço vazio para alinhar o botão à direita
        btnEmprestar = new JButton("Confirmar Empréstimo");
        btnEmprestar.setBackground(new Color(0, 153, 76)); // Cor verde
        btnEmprestar.setForeground(Color.WHITE);
        add(btnEmprestar);

        // Ação de Clique: O que acontece quando aperta o botão
        btnEmprestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarEmprestimoSeguro();
            }
        });
    }

    private void realizarEmprestimoSeguro() {
        try {
            // Pega o que o usuário digitou e converte para número
            int idAluno = Integer.parseInt(txtIdAluno.getText());
            int idEquipamento = Integer.parseInt(txtIdEquipamento.getText());

            // Chama a regra de negócio que você construiu na Etapa 4
            boolean sucesso = controle.realizarEmprestimo(idAluno, idEquipamento);

            if (sucesso) {
                JOptionPane.showMessageDialog(this,
                        "Empréstimo salvo no MySQL com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                // Limpa os campos após o sucesso
                txtIdAluno.setText("");
                txtIdEquipamento.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Operação negada! Verifique se o equipamento existe e está disponível.",
                        "Erro de Regra de Negócio",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            // Segurança na interface gráfica: impede que o programa feche se o usuário digitar letras no lugar de números
            JOptionPane.showMessageDialog(this,
                    "Por favor, digite apenas números válidos nos campos de ID.",
                    "Entrada Inválida",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}