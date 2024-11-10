import java.util.Scanner;

import RedBlackTree.RBT;

public class RBTApp {
	public static void main(String[] args){
		RBT<Integer> tree = new RBT<Integer>();
		Integer num, choice;
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
					tree.byLevel();
				break;
				case 3:
					tree.inOrder();
				break;
				case 4:
					System.out.print("Digite o valor a ser removido:");
					num = scanner.nextInt();
					tree.logicalDeleteNode(num);
				break;
				case 5:
					tree.effectiveDelete();
					System.out.print("Nós removidos deletados");
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
		System.out.print("Opções:\n1 - Inserir valor\n2 - Exibir por ordem de nível\n3 - Exibir em ordem crescente\n4 - Remover Nó\n5 - Deletar Nós Removidos\n0 - Encerrar programa\nInforme a opção: ");
	}
}