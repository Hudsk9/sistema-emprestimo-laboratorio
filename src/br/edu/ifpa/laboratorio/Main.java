package br.edu.ifpa.laboratorio;

import br.edu.ifpa.laboratorio.view.TelaEmprestimo;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEmprestimo tela = new TelaEmprestimo();
            tela.setVisible(true);
        });
    }
}