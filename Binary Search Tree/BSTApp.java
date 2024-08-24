import java.util.Scanner;

import BinarySearchTree.BST;

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
					System.out.print("Digite o valor a ser inserido:");
					num = scanner.nextInt();
					tree.insert(num);
					break;
				case 2:
					System.out.print("Digite o valor a ser pesquisado:");
					num = scanner.nextInt();
					tree.contain(num);
					break;
				case 3:
					tree.min();
					break;
				case 4:
					tree.max();
					break;
				case 5:
					tree.byLevel();
				break;
				case 6:
					tree.inOrder();
				break;
				case 7:
					tree.preOrder();
				break;
				case 8:
					System.out.println("O número de nós é " + tree.numberOfNodes());
				break;
				case 9:
					System.out.println("O número de folhas é " + tree.numberOfLeafs());
				break;
				case 10:
					System.out.println("O número de nós não terminais é " + tree.numberOfNonTerminals());
				break;
				case 0:
					scanner.close();
					System.out.println("Tchau!");
					break;
				default:
				System.out.println("Essa não é uma opção válida.");
					break;
			}
		} while (choice != 0);
	}
	public static void printMenu(){
		System.out.print("Opções:\n1 - Inserir valor\n2 - Confirmar presença de um valor\n3 - Exibir menor valor na árvore\n4 - Exibir maior valor na árvore\n5 - Exibir nós em ordem de nível\n" + //
						"6- Exibir nós em ordem crescente\n7- Exibir nós verticalmente (pré-ordem)\n8- Exibir número de nós\n9- Exibir número de folhas\n \\n" + //
						"10- Exibir número de nós não terminais\n0 - Encerrar programa\nInforme a opção: ");
	}
}