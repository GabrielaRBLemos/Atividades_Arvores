package ProductRegistry;

import java.util.Scanner;

public class ProductRegistryApp {
	public static void main(String[] args){
		ProductRegistry cadastro = new ProductRegistry();
		int choice, newProductQntInStock;
		String ProductCode, newProductDescription, newProductSupplier;
		double newProductPrice;
		do {
			Scanner scanner = new Scanner(System.in);
			printMenu();
			choice = scanner.nextInt();
			switch (choice) {
				case 1:
					System.out.print("Digite o código do novo produto:");
					scanner.nextLine();
					ProductCode = scanner.nextLine();
					System.out.print("Digite a descrição do novo produto:");
					newProductDescription = scanner.nextLine();
					System.out.print("Digite o fornecedor do novo produto:");
					newProductSupplier = scanner.nextLine();
					System.out.print("Digite o preço do novo produto:");
					newProductPrice = scanner.nextDouble();
					System.out.print("Digite a quantidade em estoque do novo produto:");
					newProductQntInStock = scanner.nextInt();
					if (cadastro.getTree().search(ProductCode)==null) {
						Product newProduct = new Product(ProductCode, newProductDescription, newProductSupplier, newProductPrice, newProductQntInStock);
						cadastro.getTree().insert(newProduct);
					}
					else{
						System.out.println("Esse código já está em uso.");
					}
					break;
				case 2:
					System.out.println("Digite o código do produto:");
					ProductCode = scanner.nextLine();
					cadastro.getTree().printNodeValue(ProductCode);
					break;
				case 3:
					cadastro.getTree().inOrder();
				break;
				case 4:
					System.out.println("Digite o código do produto a ser pesquisado:");
					ProductCode = scanner.nextLine();
					if (cadastro.getTree().search(ProductCode) != null) {
						System.out.println("Digite o novo preço:");
						newProductPrice = scanner.nextDouble();
						cadastro.getTree().alterValuePrice(ProductCode, newProductPrice);
					}
					else{
						System.out.println("Código não encontrado");
					}
					break;
				case 5:
					System.out.println("Digite o código do produto a ser pesquisado:");
					ProductCode = scanner.nextLine();
					if (cadastro.getTree().search(ProductCode) != null) {
						System.out.println("Digite a nova quantidade:");
						newProductQntInStock = scanner.nextInt();
						cadastro.getTree().alterValueQntInStock(ProductCode, newProductQntInStock);
					}
					else{
						System.out.println("Código não encontrado");
					}
					break;
				case 6:
					System.out.print("Digite o código a ser pesquisado:");
					ProductCode = scanner.nextLine();
					cadastro.getTree().contain(ProductCode);
					break;
				case 7:
					System.out.println("O número de nós é " + cadastro.getTree().numberOfNodes());
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
		System.out.print("Opções:\n1 - Inserir produto\n2 - Exibir dados de um produto\n3- Exibir produtos em ordem crescente\n4- Altere o preço de um produto\n5- Altere a quantidade em estoque de um produto\n"+//
		"6 - Confirmar presença de um produto\n7 - Exibir número de produtos cadastrados\n0 - Encerrar programa\nDigite a opção:");
	}
}