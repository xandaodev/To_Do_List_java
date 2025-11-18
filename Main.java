package To_Do_List_java;

import java.util.*;
import java.io.*;
public class Main{
    

    public static void main(String[] args){
        int opcao = -1;

        Scanner leitor = new Scanner(System.in); 
        ArrayList<Tarefa> tarefas = carregarDados();

        while(opcao!=0){
            System.out.println("======================================");
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas");
            System.out.println("3 - Marcar tarefa como concluida");
            System.out.println("4 - Remover tarefa");
            System.out.println("0 - Sair!");
            System.out.println("======================================");
            opcao = leitor.nextInt();
            leitor.nextLine();
            

            switch(opcao){
                case 1:
                    System.out.print("\n");
                    System.out.print("Insira a tarefa desejada: ");
                    String textoDaTarefa = leitor.nextLine();
                    Tarefa novaTarefa = new Tarefa(textoDaTarefa);
                    tarefas.add(novaTarefa);
                    System.out.print("\n");
                    System.out.println("..................................");
                    System.out.println("Tarefa adicionada com sucesso!");
                    System.out.println("..................................");
                    System.out.print("\n");
                    salvarDados(tarefas);
                    break;
                case 2:
                    if(listaNaoVazia(tarefas)){
                    System.out.print("\n");
                    System.out.println("Listando todas as tarefas: ");
                    listarTarefas(tarefas);
                    }
                    break;
                case 3:
                    if(listaNaoVazia(tarefas)){
                    listarTarefas(tarefas);
                    System.out.print("\n");
                    System.out.print("Qual das tarefas acima voce quer marcar como conluida: ");
                    int numTarefa = leitor.nextInt();
                    leitor.nextLine();
                    int index = numTarefa - 1;
                    if(index>=0 && index < tarefas.size()){
                        Tarefa tarefaParaMarcar = tarefas.get(index);
                        tarefaParaMarcar.setConcluida(true);
                        System.out.println("Tarefa concluida com sucesso!" + "\n");
                        salvarDados(tarefas);
                    }else{
                        System.out.print("\n");
                        System.out.println("Tarefa inválida, digite outro numero");
                        System.out.print("\n");
                    }
                    }
                    break;
                case 4:
                    if(listaNaoVazia(tarefas)){
                    listarTarefas(tarefas);
                    System.out.print("Qual das tarefas acima voce quer remover: ");
                    int numTarefaRemover = leitor.nextInt();
                    leitor.nextLine();
                    int indexRemocao = numTarefaRemover - 1;
                    if(indexRemocao>=0 && indexRemocao<tarefas.size()){
                        tarefas.remove(indexRemocao);
                        System.out.print("\n");
                        System.out.println("Tarefa removida com sucesso!");
                        System.out.print("\n");
                        salvarDados(tarefas);
                    }else{
                        System.out.print("\n");
                        System.out.println("Tarefa inválida, digite outro numero");
                        System.out.print("\n");
                    }
                    }
                    break;
                case 0:
                    System.out.println("Saindo... ");
                    break;
            }
           
        }
    }

    public static void listarTarefas(ArrayList<Tarefa> listaParaImprimir){
        System.out.print("\n");
        int i = 1;
        for(Tarefa t : listaParaImprimir){
            if(t.isConcluida()){
                System.out.println(i+" - "+ t.getDescricao() + "[Concluida]");
            }else{
                System.out.println(i+" - "+ t.getDescricao() + "[Pendente]");
            }
            System.out.print("\n");
            i++;
        }
    }

    public static boolean listaNaoVazia(ArrayList<Tarefa> lista){
        if(lista.size()<=0){
            System.out.print("\n");
            System.out.println("Voce nao tem tarefas!");
            System.out.print("\n");
            return false;
        }else{
            return true;
        }
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