import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Lampada {
    private boolean ligada;
    private int brilho;

    public Lampada() {
        this.ligada = false;
        this.brilho = 0; // Brilho varia de 0 a 100
    }

    public void ligar() {
        if (!ligada) {
            ligada = true;
            brilho = 50; // Define brilho inicial ao ligar
            System.out.println("Lâmpada ligada com brilho 50%.");
        } else {
            System.out.println("A lâmpada já está ligada.");
        }
    }

    public void desligar() {
        if (ligada) {
            ligada = false;
            brilho = 0;
            System.out.println("Lâmpada desligada.");
        } else {
            System.out.println("A lâmpada já está desligada.");
        }
    }

    public void ajustarBrilho(int novoBrilho) {
        if (ligada) {
            if (novoBrilho >= 0 && novoBrilho <= 100) {
                brilho = novoBrilho;
                System.out.println("Brilho ajustado para " + brilho + "%.");
            } else {
                System.out.println("Valor inválido! Informe um brilho entre 0 e 100.");
            }
        } else {
            System.out.println("A lâmpada está desligada. Ligue-a antes de ajustar o brilho.");
        }
    }

    public boolean isLigada() {
        return ligada;
    }

    public int getBrilho() {
        return brilho;
    }
}

public class SistemaIluminacaoComLogin {
    private static Map<String, String> usuarios = new HashMap<>(); // Armazena usuários e senhas

    public static void main(String[] args) {
        inicializarUsuarios();

        if (!realizarLogin()) {
            System.out.println("Acesso negado. Encerrando o sistema...");
            return;
        }

        Lampada[] lampadas = new Lampada[8];
        for (int i = 0; i < lampadas.length; i++) {
            lampadas[i] = new Lampada();
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Sistema de Iluminação Inteligente ---");
            System.out.println("1. Ligar lâmpada");
            System.out.println("2. Desligar lâmpada");
            System.out.println("3. Ajustar brilho de lâmpada");
            System.out.println("4. Ver status das lâmpadas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o número da lâmpada (1 a 8) para ligar: ");
                    int numLampada = scanner.nextInt();
                    if (numLampada >= 1 && numLampada <= 8) {
                        lampadas[numLampada - 1].ligar();
                    } else {
                        System.out.println("Número de lâmpada inválido!");
                    }
                }
                case 2 -> {
                    System.out.print("Informe o número da lâmpada (1 a 8) para desligar: ");
                    int numLampada = scanner.nextInt();
                    if (numLampada >= 1 && numLampada <= 8) {
                        lampadas[numLampada - 1].desligar();
                    } else {
                        System.out.println("Número de lâmpada inválido!");
                    }
                }
                case 3 -> {
                    System.out.print("Informe o número da lâmpada (1 a 8) para ajustar o brilho: ");
                    int numLampada = scanner.nextInt();
                    if (numLampada >= 1 && numLampada <= 8) {
                        System.out.print("Informe o novo brilho (0 a 100): ");
                        int brilho = scanner.nextInt();
                        lampadas[numLampada - 1].ajustarBrilho(brilho);
                    } else {
                        System.out.println("Número de lâmpada inválido!");
                    }
                }
                case 4 -> {
                    System.out.println("\nStatus das Lâmpadas:");
                    for (int i = 0; i < lampadas.length; i++) {
                        System.out.println("Lâmpada " + (i + 1) + ": " +
                                (lampadas[i].isLigada() ? "Ligada (Brilho: " + lampadas[i].getBrilho() + "%)" : "Desligada"));
                    }
                }
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void inicializarUsuarios() {
        // Usuários pré-cadastrados
        usuarios.put("admin", "1234");
        usuarios.put("usuario1", "senha1");
        usuarios.put("usuario2", "senha2");
    }

    private static boolean realizarLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Login ---");

        System.out.print("Usuário: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (usuarios.containsKey(username) && usuarios.get(username).equals(password)) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + username + ".");
            return true;
        } else {
            System.out.println("Usuário ou senha inválidos.");
            return false;
        }
    }
}
