import java.util.Scanner;

import BinarySearchTree.BST;

public class BSTApp {
	public static void main(String[] args){
		BST<Integer> tree = new BST<Integer>();
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
					System.out.print("Digite o valor a ser removido:");
					num = scanner.nextInt();
					tree.remove(num);
				break;
				case 3:
					System.out.print("Digite o valor a ser pesquisado:");
					num = scanner.nextInt();
					tree.contain(num);
					break;
				case 4:
					tree.min();
					break;
				case 5:
					tree.max();
					break;
				case 6:
					tree.byLevel();
				break;
				case 7:
					tree.inOrder();
				break;
				case 8:
					tree.preOrder();
				break;
				case 9:
					System.out.println("O número de nós é " + tree.numberOfNodes());
				break;
				case 10:
					System.out.println("O número de folhas é " + tree.numberOfLeafs());
				break;
				case 11:
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
		System.out.print("Opções:\n1 - Inserir valor\n2 - Remover valor\n3 - Confirmar presença de um valor\n4 - Exibir menor valor na árvore\n5 - Exibir maior valor na árvore\n6 - Exibir nós em ordem de nível\n" + //
						"7 - Exibir nós em ordem crescente\n8 - Exibir nós verticalmente (pré-ordem)\n9 - Exibir número de nós\n10 - Exibir número de folhas\n" + //
						"11 - Exibir número de nós não terminais\n0 - Encerrar programa\nInforme a opção: ");
	}
}