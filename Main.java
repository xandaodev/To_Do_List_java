package To_Do_List_java;
import java.util.*;
public class Main{
    public int opcao;

    public static void main(String[] args){
        System.out.println("Hello word!");

        public Scanner leitor = new Scanner(System.in); 

        while(opcao!=0){
            leitor = nextInt();
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas");
            System.out.println("0 - Sair!");

            switch(opcao){
                case 1:
                    System.out.println("Inisira a tarefa desejada: ");
                    inserirTarefa();
                case 2:
                    System.out.println("Listando todas as tarefas: ");
                    listarTarefas();
                case 3:
                    System.out.println("Saindo... ");
                    break;
            }
           
        }
    }
}