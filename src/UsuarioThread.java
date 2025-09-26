public class UsuarioThread extends Thread {
    private Biblioteca biblioteca;
    private Livro livro;

    public UsuarioThread(Biblioteca biblioteca, Livro livro) {
        this.biblioteca = biblioteca;
        this.livro = livro;
    }

    @Override
    public void run() {
        biblioteca.adicionarLivro(livro);
        System.out.println(Thread.currentThread().getName() + " adicionou: " + livro);
    }
}