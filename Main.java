package To_Do_List_java;

import java.util.*;
public class Main{
    

    public static void main(String[] args){
        int opcao = -1;
        System.out.println("Hello word!");

        Scanner leitor = new Scanner(System.in); 
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        while(opcao!=0){
            
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas");
            System.out.println("3 - Marcar tarefa como concluida");
            System.out.println("0 - Sair!");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Inisira a tarefa desejada: ");
                    String textoDaTarefa = leitor.nextLine();
                    Tarefa novaTarefa = new Tarefa(textoDaTarefa);
                    tarefas.add(novaTarefa);
                    System.out.println("Tarefa adicionada com sucesso!");
                    System.out.print("\n");
                    break;
                case 2:
                System.out.print("\n");
                    System.out.println("Listando todas as tarefas: ");
                    int i = 1;
                    for(Tarefa t : tarefas){
                        if(t.isConcluida()){
                            System.out.println(i+" - "+ t.getDescricao() + "[Concluida]");
                        }else{
                            System.out.println(i+" - "+ t.getDescricao() + "[Pendente]");
                        }
                        System.out.print("\n");
                        i++;
                    }
                    break;
                case 3:
                int j = 1;
                    for(Tarefa t : tarefas){
                        if(t.isConcluida()){
                            System.out.println(j+" - "+ t.getDescricao() + "[Concluida]");
                        }else{
                            System.out.println(j+" - "+ t.getDescricao() + "[Pendente]");
                        }
                        System.out.print("\n");
                        j++;
                    }
                    System.out.print("\n");
                    System.out.println("Qual das tarefas acima voce quer marcar como conluida: ");
                    int numTarefa = leitor.nextInt();
                    leitor.nextLine();
                    int index = numTarefa - 1;
                    Tarefa tarefaParaMarcar = tarefas.get(index);
                    tarefaParaMarcar.setConcluida(true);
                    break;
                case 0:
                    System.out.println("Saindo... ");
                    break;
            }
           
        }
    }
}