package app;

import model.Tarefa;
import service.GerenciadorTarefas;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU GERENCIADOR DE TAREFAS ---");
            System.out.println("[1] Cadastrar novo registro");
            System.out.println("[2] Listar todos os registros");
            System.out.println("[3] Buscar registro por ID");
            System.out.println("[4] Atualizar registro");
            System.out.println("[5] Remover registro");
            System.out.println("[0] Sair do programa");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID da tarefa (número): ");
                    int idCad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o título da tarefa: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite a prioridade (ex: Alta, Média, Baixa): ");
                    String prioridade = scanner.nextLine();
                    System.out.print("Digite o status (ex: Pendente, Concluída): ");
                    String status = scanner.nextLine();

                    Tarefa nova = new Tarefa(idCad, titulo, prioridade, status);
                    if (gerenciador.cadastrar(nova)) {
                        System.out.println("Registro cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: Já existe uma tarefa com esse ID.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE TAREFAS ---");
                    if (gerenciador.listarTodos().isEmpty()) {
                        System.out.println("Nenhum item cadastrado.");
                    } else {
                        for (Tarefa t : gerenciador.listarTodos()) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID que deseja buscar: ");
                    int idBusca = scanner.nextInt();
                    Tarefa encontrada = gerenciador.buscarPorId(idBusca);
                    if (encontrada != null) {
                        System.out.println("Encontrado: " + encontrada);
                    } else {
                        System.out.println("Nenhum item encontrado com o ID informado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID da tarefa que deseja atualizar: ");
                    int idAtu = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (gerenciador.buscarPorId(idAtu) != null) {
                        System.out.print("Novo título: ");
                        String novoTitulo = scanner.nextLine();
                        System.out.print("Nova prioridade: ");
                        String novaPrioridade = scanner.nextLine();
                        System.out.print("Novo status: ");
                        String novoStatus = scanner.nextLine();

                        Tarefa dadosNovos = new Tarefa(idAtu, novoTitulo, novaPrioridade, novoStatus);
                        gerenciador.atualizar(idAtu, dadosNovos);
                        System.out.println("Registro atualizado com sucesso!");
                    } else {
                        System.out.println("Nenhum item encontrado com o ID informado.");
                    }
                    break;

                case 5:
                    System.out.print("Digite o ID da tarefa que deseja remover: ");
                    int idRem = scanner.nextInt();
                    if (gerenciador.remover(idRem)) {
                        System.out.println("Registro removido com sucesso!");
                    } else {
                        System.out.println("Nenhum item encontrado com o ID informado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do programa... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}