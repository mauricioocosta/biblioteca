import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private List<Livro> livros;
    private final String arquivo = "livros.txt";

    public Biblioteca() {
        livros = new ArrayList<>();
        carregarLivros();
    }

    // Adicionar livro de forma sincronizada
    public synchronized void adicionarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros();
    }

    // Listar livros
    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro na biblioteca.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    // Buscar livros por título usando Stream
    public List<Livro> buscarPorTitulo(String titulo) {
        return livros.stream()
                     .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                     .collect(Collectors.toList());
    }

    // Buscar livros por autor usando Stream
    public List<Livro> buscarPorAutor(String autor) {
        return livros.stream()
                     .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                     .collect(Collectors.toList());
    }

    // Ordenar livros
    public void ordenarPorTitulo() {
        livros.sort((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()));
    }

    public void ordenarPorAutor() {
        livros.sort((l1, l2) -> l1.getAutor().compareToIgnoreCase(l2.getAutor()));
    }

    public void ordenarPorAno() {
        livros.sort(Comparator.comparingInt(Livro::getAno));
    }

    // Salvar livros no arquivo de forma sincronizada
    private synchronized void salvarLivros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Livro livro : livros) {
                writer.write(livro.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
        }
    }

    // Carregar livros do arquivo
    private void carregarLivros() {
        File file = new File(arquivo);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                livros.add(Livro.fromFileString(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }
}
