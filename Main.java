import java.util.*;
public class Main{
    

    public static void main(String[] args){
        int opcao = -1;
        System.out.println("Hello word!");

        Scanner leitor = new Scanner(System.in); 

        while(opcao!=0){
            
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas");
            System.out.println("0 - Sair!");
            opcao = leitor.nextInt();

            switch(opcao){
                case 1:
                    System.out.println("Inisira a tarefa desejada: ");
                    //inserirTarefa();
                    break;
                case 2:
                    System.out.println("Listando todas as tarefas: ");
                    //listarTarefas();
                    break;
                case 0:
                    System.out.println("Saindo... ");
                    break;
            }
           
        }
    }
}