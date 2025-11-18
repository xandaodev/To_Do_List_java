package To_Do_List_java;

import javax.swing.*; 
import java.io.*;
import java.awt.*;
import java.util.ArrayList; 
public class ToDoListGui extends JFrame {

    private JTextField campoTarefa;
    private DefaultListModel<String> listModel;

    private ArrayList<Tarefa> tarefas;

    public ToDoListGui(){
        super("Minha Lista de Tarefas - GUI"); 
        
        // Configurações da Janela
        this.setSize(400, 600); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout()); // Layout principal

        this.campoTarefa = new JTextField(); 
        this.listModel = new DefaultListModel<>(); 
        
        JButton botaoAdicionar = new JButton("Adicionar"); 


        // painel superior, entrada
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new BorderLayout());

        painelEntrada.add(this.campoTarefa, BorderLayout.CENTER); 
        painelEntrada.add(botaoAdicionar, BorderLayout.EAST);

        this.add(painelEntrada, BorderLayout.NORTH);
        
        // lista visual
        JList<String> listaVisual = new JList<>(this.listModel); 
        JScrollPane scrollLista = new JScrollPane(listaVisual); 
        this.add(scrollLista, BorderLayout.CENTER);


        // conectando os botoes ao eventos
        botaoAdicionar.addActionListener(e -> {
            String novaTarefa = this.campoTarefa.getText();
            
            if (!novaTarefa.trim().isEmpty()) {
                this.listModel.addElement(novaTarefa);
                this.campoTarefa.setText("");
            }
        });
        
        // torna a janela visível
        this.setVisible(true); 
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new ToDoListGui();
        });
    }

    public static void salvarDados(ArrayList<Tarefa> tarefas){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("tarefas.txt"));
            for(Tarefa t : tarefas){
                writer.write(t.getDescricao() + ";" + t.isConcluida());
                writer.newLine();
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Tarefa> carregarDados(){
        ArrayList<Tarefa> tarefasCarregadas = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("tarefas.txt"));
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");// no arquivo tarefas.txt cada tarefa é separada por ';', ent temos que separar
                String descricao = partes[0];
                boolean status = Boolean.parseBoolean(partes[1]);

                Tarefa t = new Tarefa(descricao);
                t.setConcluida(status);
                tarefasCarregadas.add(t);
            }
            reader.close();
        }catch(IOException e){
            System.out.println("Nenhuma lista salva encontrada. Iniciando nova lista.");
        }
        return tarefasCarregadas;
    }
}