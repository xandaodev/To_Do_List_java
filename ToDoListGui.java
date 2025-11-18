package To_Do_List_java;

import javax.swing.*; 
import java.awt.*;

public class ToDoListGui extends JFrame {

    public ToDoListGui(){
        super("Minha Lista de Tarefas"); 
        this.setSize(400, 600); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new BorderLayout());

        JTextField campoTarefa = new JTextField();
        JButton botaoAdicionar = new JButton("Adicionar");

        painelEntrada.add(campoTarefa, BorderLayout.CENTER);
        painelEntrada.add(botaoAdicionar, BorderLayout.EAST);

        this.add(painelEntrada, BorderLayout.NORTH);
        
        JList<String> listaVisual = new JList<>();
        JScrollPane scrollLista = new JScrollPane(listaVisual); 
        
        this.add(scrollLista, BorderLayout.CENTER);

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new ToDoListGui();
        });
    }


}