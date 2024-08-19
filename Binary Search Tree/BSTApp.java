import java.util.Scanner;

public class BSTApp {
	public static void main(String[] args){
		BST tree = new BST();
		int num, choice;
		do {
			Scanner scanner = new Scanner(System.in);
			printMenu();
			choice = scanner.nextInt();
			switch (choice) {
				case 1:
					System.out.println("Digite o valor a ser inserido:");
					num = scanner.nextInt();
					tree.insert(num);
					break;
				case 2:
					System.out.println("Digite o valor a ser pesquisado:");
					num = scanner.nextInt();
					tree.contain(num);
					break;
				case 3:
				tree.min();
				case 4:
				tree.max();
				break;
				case 0:
					System.out.println("Tchau!");
					break;
				default:
				System.out.println("Essa não é uma opção válida.");
					break;
			}
		} while (choice != 0);
		tree.min();
	}
	public static void printMenu(){
		System.out.println("Opções:\n1 - Inserir valor\n2 - Confirmar presença de um valor\n3 - Exibir menor valor na árvore\n4 - Exibir maior valor na árvore\n0 - Encerrar programa\nInforme a opção: ");
	}
}