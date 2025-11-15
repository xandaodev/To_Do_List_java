package To_Do_List_java;

import java.util.ArrayList;

public class Tarefa {
    private String descricao;
    private boolean concluida;

    public Tarefa(String descricao){
        this.descricao = descricao;
        this.concluida = false;
    }

    public boolean isConcluida(){
        return concluida;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setConcluida(boolean concluida){
        this.concluida = concluida;
    }

}
