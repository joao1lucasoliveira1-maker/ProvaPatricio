package service;

import model.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    
    private List<Tarefa> listaTarefas = new ArrayList<>();

    // cadastro com ID único
    public boolean cadastrar(Tarefa novaTarefa) {
        for (Tarefa t : listaTarefas) {
            if (t.getId() == novaTarefa.getId()) {
                return false; // ID duplicado não cadastra
            }
        }
        listaTarefas.add(novaTarefa);
        return true;
    }

    //  ler   vai listar
    public List<Tarefa> listarTodos() {
        return listaTarefas;
    }

    // ler  busca por id
    public Tarefa buscarPorId(int id) {
        for (Tarefa t : listaTarefas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // atualiza
    public boolean atualizar(int id, Tarefa novosDados) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefa.setTitulo(novosDados.getTitulo());
            tarefa.setPrioridade(novosDados.getPrioridade());
            tarefa.setStatus(novosDados.getStatus());
            return true;
        }
        return false;
    }

    // exclui
    public boolean remover(int id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            listaTarefas.remove(tarefa);
            return true;
        }
        return false;
    }
}