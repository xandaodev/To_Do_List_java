package To_Do_List_java;

import javax.swing.*;
import java.awt.event.*; 
import java.util.stream.Collectors;

import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator; 
import java.util.Collections; 

public class ToDoListGui extends JFrame{

    // variaveis DA CLASSE
    private JTextField campoTarefa;
    private DefaultListModel<String> listModel;
    private JComboBox<String> filtroStatus;
    private JComboBox<String> ordenacao;

    // lista principal que armazena todas as tarefas
    private ArrayList<Tarefa> tarefas;

    public ToDoListGui(){
        super("Lista de Tarefas"); 
        
        // configuracoes basicas da janela principal
        this.setSize(550, 650); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // inicializa os dados e componentes da interface
        this.tarefas = carregarDados(); 
        this.listModel = new DefaultListModel<>();
        this.campoTarefa = new JTextField(); 
        
        JButton botaoAdicionar = new JButton("Adicionar"); 
        
        // combobox para filtrar por status da tarefa
        this.filtroStatus = new JComboBox<>(new String[]{"Todos", "Pendentes", "Concluídas"});
        // combobox para ordenar as tarefas
        this.ordenacao = new JComboBox<>(new String[]{"Nenhuma", "Descrição (A-Z)", "Status"});


        // montagem da interface grafica

        // painel superior para entrada de dados
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new BorderLayout());
        painelEntrada.add(this.campoTarefa, BorderLayout.CENTER); 
        painelEntrada.add(botaoAdicionar, BorderLayout.EAST);
        this.add(painelEntrada, BorderLayout.NORTH);
        
        // area central com a lista de tarefas
        JList<String> listaVisual = new JList<>(this.listModel); 
        JScrollPane scrollLista = new JScrollPane(listaVisual); 
        this.add(scrollLista, BorderLayout.CENTER);

        // rodape com controles de filtro e ordenacao
        JPanel painelRodape = new JPanel();
        painelRodape.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botaoRemover = new JButton("Remover Tarefa"); 
        
        painelRodape.add(new JLabel("Filtrar por:"));
        painelRodape.add(this.filtroStatus);
        painelRodape.add(new JLabel("Ordenar por:"));
        painelRodape.add(this.ordenacao);
        painelRodape.add(botaoRemover);

        this.add(painelRodape, BorderLayout.SOUTH);

        // configuracao dos eventos

        // evento para adicionar nova tarefa
        botaoAdicionar.addActionListener(e ->{
            String texto = this.campoTarefa.getText().trim();
            if(!texto.isEmpty()){
                Tarefa novaTarefa = new Tarefa(texto); 
                this.tarefas.add(novaTarefa); 
                this.campoTarefa.setText("");
                this.aplicarFiltroEAtualizar(); 
            }
        });

        // evento para marcar e desmarcar tarefa com duplo clique
        listaVisual.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){ 
                    int index = listaVisual.locationToIndex(e.getPoint());
                    if(index >= 0){
                        // alterna o status de concluida da tarefa
                        Tarefa tarefaSelecionada = tarefas.get(index);
                        tarefaSelecionada.setConcluida(!tarefaSelecionada.isConcluida());
                        aplicarFiltroEAtualizar();
                    }
                }
            }
        });


        // evento para remover tarefa selecionada
        botaoRemover.addActionListener(r ->{
            int index =listaVisual.getSelectedIndex(); 
            
            if(index >= 0){
                this.tarefas.remove(index);
                this.aplicarFiltroEAtualizar();
            }else{
                JOptionPane.showMessageDialog(this, 
                    "Selecione uma tarefa para remover.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });


        // evento para ordenar a lista
        this.ordenacao.addActionListener(e ->{
            this.aplicarFiltroEAtualizar(); 
        });

        // evento para filtrar a lista
        this.filtroStatus.addActionListener(e ->{
            this.aplicarFiltroEAtualizar(); 
        });


        // atualiza a interface e torna a janela visivel
        this.aplicarFiltroEAtualizar(); 
        this.setVisible(true);
    }
    
    // metodo principal que controla a ordenacao filtragem e atualizacao da interface
    private void aplicarFiltroEAtualizar(){
        // obtem os criterios de filtro e ordenacao selecionados
        String filtro =(String) this.filtroStatus.getSelectedItem();
        String ordem =(String) this.ordenacao.getSelectedItem();

        // aplica a ordenacao se for necessario
        Comparator<Tarefa> comparador = null;
        if(ordem.equals("Descrição (A-Z)")){
            comparador = Comparator.comparing(Tarefa::getDescricao);
        }else if(ordem.equals("Status")){
            comparador = Comparator.comparing(Tarefa::isConcluida);
        }

        if(comparador != null){
            Collections.sort(this.tarefas, comparador);
        }

        // aplica o filtro selecionado
        ArrayList<Tarefa> listaFiltrada = this.tarefas.stream()
            .filter(t ->{
                if(filtro.equals("Todos")){
                    return true;
                }
                if(filtro.equals("Pendentes")){
                    return !t.isConcluida();
                }
                return t.isConcluida();
            })
            .collect(Collectors.toCollection(ArrayList::new));

        // atualiza a interface e salva os dados
        this.atualizarListaVisual(listaFiltrada);
        salvarDados(this.tarefas);
    }
    

    // metodo principal que inicia a aplicacao
    public static void main(String[] args){
        SwingUtilities.invokeLater(() ->{
            new ToDoListGui();
        });
    }

    // atualiza a lista visual na interface grafica
    private void atualizarListaVisual(ArrayList<Tarefa> listaParaExibir){
        this.listModel.clear();
        int i = 1;
        for(Tarefa t : listaParaExibir){
            String status = t.isConcluida() ? "[CONCLUÍDA]" : "[PENDENTE]";
            this.listModel.addElement(i + ". " + t.getDescricao() + " " + status);
            i++;
        }
    }

    // salva as tarefas em um arquivo de texto
    public static void salvarDados(ArrayList<Tarefa> tarefas){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("tarefas.txt"));
            for(Tarefa t : tarefas){
                writer.write(t.getDescricao() + ";" + t.isConcluida());
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // carrega as tarefas de um arquivo de texto
    public static ArrayList<Tarefa> carregarDados(){
        ArrayList<Tarefa> tarefasCarregadas = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("tarefas.txt"));
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");
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