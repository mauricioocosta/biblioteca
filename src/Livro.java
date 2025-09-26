public class Livro {
    private String titulo;
    private String autor;
    private int ano;

    public Livro(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAno() { return ano; }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + ano + ")";
    }

    // Converter para linha de arquivo
    public String toFileString() {
        return titulo + ";" + autor + ";" + ano;
    }

    // Criar Livro a partir de linha do arquivo
    public static Livro fromFileString(String line) {
        String[] parts = line.split(";");
        return new Livro(parts[0], parts[1], Integer.parseInt(parts[2]));
    }
}
