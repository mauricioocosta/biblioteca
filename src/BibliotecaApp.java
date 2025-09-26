import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Biblioteca ===");
            System.out.println("1 - Listar livros");
            System.out.println("2 - Adicionar livro");
            System.out.println("3 - Buscar livros");
            System.out.println("4 - Ordenar livros");
            System.out.println("5 - Simular múltiplos usuários");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Consumir newline

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Lista de Livros ---");
                    biblioteca.listarLivros();
                    break;

                case 2:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    sc.nextLine();

                    Livro livro = new Livro(titulo, autor, ano);
                    biblioteca.adicionarLivro(livro);
                    System.out.println("Livro adicionado!");
                    break;

                case 3:
                    System.out.println("1 - Buscar por título");
                    System.out.println("2 - Buscar por autor");
                    System.out.print("Escolha: ");
                    int busca = sc.nextInt();
                    sc.nextLine();

                    if (busca == 1) {
                        System.out.print("Digite o título: ");
                        String tituloBusca = sc.nextLine();
                        List<Livro> encontradosTitulo = biblioteca.buscarPorTitulo(tituloBusca);
                        if (encontradosTitulo.isEmpty()) {
                            System.out.println("Nenhum livro encontrado com esse título.");
                        } else {
                            encontradosTitulo.forEach(System.out::println);
                        }
                    } else if (busca == 2) {
                        System.out.print("Digite o autor: ");
                        String autorBusca = sc.nextLine();
                        List<Livro> encontradosAutor = biblioteca.buscarPorAutor(autorBusca);
                        if (encontradosAutor.isEmpty()) {
                            System.out.println("Nenhum livro encontrado com esse autor.");
                        } else {
                            encontradosAutor.forEach(System.out::println);
                        }
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 4:
                    System.out.println("1 - Ordenar por título");
                    System.out.println("2 - Ordenar por autor");
                    System.out.println("3 - Ordenar por ano");
                    System.out.print("Escolha: ");
                    int ordenar = sc.nextInt();
                    sc.nextLine();

                    if (ordenar == 1) biblioteca.ordenarPorTitulo();
                    else if (ordenar == 2) biblioteca.ordenarPorAutor();
                    else if (ordenar == 3) biblioteca.ordenarPorAno();
                    else System.out.println("Opção inválida!");

                    System.out.println("Livros ordenados!");
                    break;

                case 5:
                    System.out.println("\n--- Simulação de múltiplos usuários ---");
                    UsuarioThread user1 = new UsuarioThread(biblioteca, new Livro("Clean Code", "Robert C. Martin", 2008));
                    UsuarioThread user2 = new UsuarioThread(biblioteca, new Livro("Effective Java", "Joshua Bloch", 2018));
                    UsuarioThread user3 = new UsuarioThread(biblioteca, new Livro("Java Concurrency", "Brian Goetz", 2006));

                    user1.start();
                    user2.start();
                    user3.start();

                    try {
                        user1.join();
                        user2.join();
                        user3.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("\n--- Livros na Biblioteca após threads ---");
                    biblioteca.listarLivros();
                    break;

                case 6:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
