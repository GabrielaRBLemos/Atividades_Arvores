import BTree.BTree;
import java.util.Scanner;

public class BApp {

    public static void main(String[] args) {
        BTree bTree = new BTree(3); // 2 * minDegree - 1 = Ordem da árvore
        int option,num;

        do {
            Scanner scanner = new Scanner(System.in);
            printMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    // Inserir valor na árvore
                    System.out.print("Informe o valor a ser inserido: ");
                    num = scanner.nextInt();
                    bTree.insert(num);
                    System.out.println("Valor inserido com sucesso.");
                    break;

                case 2:
                    // Exibir a maior chave na árvore
                    bTree.displayMax();
                    break;

                case 3:
                    // Exibir a menor chave na árvore
                    bTree.displayMin();
                    break;

                case 4:
                    // Exibir a altura da árvore
                    System.out.println("Altura da árvore: " + bTree.calculateHeight());
                    break;

                case 5:
                    // Procurar um valor na árvore
                    System.out.print("Informe o valor a ser procurado: ");
                    num = scanner.nextInt();
                    bTree.check(num);
                    break;

                case 6:
                    // Exibir as chaves por nível
                    System.out.print("Chaves por nível: ");
                    bTree.printLevelOrder();  // This will print the tree level-by-level
                    break;

                case 7:
                    // Exibir as chaves em ordem
                    System.out.print("Chaves em ordem: ");
                    bTree.printInOrder();
                    break;
                case 8:
                    // Remover um valor da árvore
                    System.out.print("Informe o valor a ser removido: ");
                    num = scanner.nextInt();
                    bTree.remove(num);
                    break;
                case 0:
                    // Encerar programa
                    scanner.close();
                    System.out.println("Tchau!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }

    public static void printMenu() {
        System.out.println("Opções:");
        System.out.println("1 – Inserir valor na árvore");
        System.out.println("2 – Exibir a maior chave na árvore");
        System.out.println("3 – Exibir a menor chave na árvore");
        System.out.println("4 – Exibir a altura da árvore");
        System.out.println("5 – Procurar um valor na árvore");
        System.out.println("6 – Exibir as chaves por nível");
        System.out.println("7 – Exibir as chaves em ordem");
        System.out.println("8 – Remover um valor da árvore");
        System.out.println("0 – Encerar programa");
        System.out.print("Informe a opção desejada: ");
    }
}