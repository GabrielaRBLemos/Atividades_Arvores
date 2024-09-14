import java.util.Scanner;

import AVLTree.AVLT;

public class AVLTApp {
	public static void main(String[] args){
		AVLT<Integer> tree = new AVLT<Integer>();
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
					tree.inOrder();
				break;
				case 3:
					tree.byLevel();
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
		System.out.print("Opções:\n1 - Inserir valor\n2- Exibir nós em ordem crescente\n3- Exibir nós por nível\n0 - Encerrar programa\nInforme a opção: ");
	}
}