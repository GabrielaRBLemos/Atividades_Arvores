import BTree.BTree;
import java.util.Scanner;

public class BApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BTree tree = new BTree(5);

        while (true) {
            printMenu();

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Informe o valor a ser inserido: ");
                    int value = sc.nextInt();
                    tree.insert(value);
                    break;
                case 2:
                    tree.displayMax();
                    break;
                case 3:
                    tree.displayMin();
                    break;
                case 4:
                    System.out.println("Altura da árvore: " + tree.getHeight());
                    break;
                case 5:
                    System.out.print("Informe o valor a ser procurado: ");
                    value = sc.nextInt();
                    tree.searchKey(value);
                    break;
                case 6:
                    tree.displayByLevel();
                    break;
                case 7:
                    tree.displayInOrder();
                    break;
                case 8:
                    System.out.print("Informe o valor a ser removido: ");
                    value = sc.nextInt();
                    tree.remove(value);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Opções:");
        System.out.println("1 – Inserir valor na árvore");
        System.out.println("2 – Exibir a maior chave armazenada na árvore");
        System.out.println("3 – Exibir a menor chave armazenada na árvore");
        System.out.println("4 – Exibir a altura da árvore");
        System.out.println("5 – Procurar um valor na árvore");
        System.out.println("6 – Exibir as chaves por nível");
        System.out.println("7 – Exibir as chaves em ordem");
        System.out.println("8 – Remover um valor da árvore");
        System.out.print("Informe a opção desejada: ");
    }
}
