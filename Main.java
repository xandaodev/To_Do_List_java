package To_Do_List_java;

import java.util.*;
public class Main{
    

    public static void main(String[] args){
        int opcao = -1;
        System.out.println("Hello word!");

        Scanner leitor = new Scanner(System.in); 
        ArrayList<String> tarefas = new ArrayList<>();
        String tarefa;

        while(opcao!=0){
            
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas");
            System.out.println("0 - Sair!");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Inisira a tarefa desejada: ");
                    tarefa = leitor.nextLine();
                    tarefas.add(tarefa);
                    break;
                case 2:
                    System.out.println("Listando todas as tarefas: ");
                    System.out.println(" - Minhas Tarefas -");
                    int i = 1;
                    for(String t : tarefas){
                        System.out.println(i + " - " + t);
                        System.out.print("\n");
                        i++;
                    }
                    break;
                case 0:
                    System.out.println("Saindo... ");
                    break;
            }
           
        }
    }
}