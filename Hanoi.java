package com.mycompany.pilhaexercicios;

import java.util.Scanner;

public class Hanoi {

	Scanner scan = new Scanner(System.in);
	No topo;

	boolean pilhaVazia() {
		if (topo == null) {
			return true;
		} else {
			return false;
		}
	}

	void push(int e) {
		No elemento = new No();
		elemento.dado = e;
		if (pilhaVazia() == true) {
			topo = elemento;
		} else {
			elemento.proximo = topo;
			topo = elemento;
		}
	}

	int pop() {
		if (pilhaVazia() == true) {
			System.out.println("Não há elementos para desempilhar");
		}
		int valor = topo.dado;
		topo = topo.proximo;
		return valor;
	}

	int topo() {
		if (pilhaVazia() == true) {
			System.out.println("Não há elementos na pilha");
		}
		int valor = topo.dado;
		return valor;
	}

	int tamanho() {
		int cont = 0;
		if (pilhaVazia() == false) {
			No auxiliar = topo;
			cont = 1;
			while (auxiliar.proximo != null) {
				cont = cont + 1;
				auxiliar = auxiliar.proximo;
			}
		}
		return cont;
	}

	String imprimir() {
		String resposta = "";
		if (pilhaVazia() == false) {
			No auxiliar = topo;
			while (auxiliar.proximo != null) {
				resposta = resposta + auxiliar.dado + "";
				;
				auxiliar = auxiliar.proximo;
			}
			resposta = resposta + auxiliar.dado + "";
			return resposta;
		}
		return null;

	}

	Hanoi inverter() {
		if (pilhaVazia() == false) {
			Hanoi aux = new Hanoi();
			No auxiliar = topo;
			while (auxiliar.proximo != null) {
				aux.push(auxiliar.dado);
				auxiliar = auxiliar.proximo;
			}
			aux.push(auxiliar.dado);
			return aux;
		}
		return null;
	}

	void iniciarHanoi() {
		System.out.println("Digite o numero de discos de 3 a 7: ");
		Hanoi p = new Hanoi(), p2 = new Hanoi(), p3 = new Hanoi();
		int numDiscos;
		do {
			numDiscos = Integer.parseInt(scan.nextLine());
			if (numDiscos > 7 || numDiscos < 3) {
				System.out.println("Valor inválido, tente novamente");
			}
		} while (numDiscos > 7 || numDiscos < 3);

		for (int i = 0; i < numDiscos; i++) {
			p.push(i + 1);
		}
		p = p.inverter();
		p.mainHanoi(numDiscos, p, p2, p3);

	}

	int[] inputHanoi() {
		int[] input = new int[2];
		System.out.println("Torre de origem a tirar o disco: ");
		input[0] = Integer.parseInt(scan.nextLine());
		do {
			System.out.println("Torre a colocar o disco (nao pode ser o mesmo valor da primeira): ");
			input[1] = Integer.parseInt(scan.nextLine());
		} while (input[1] == input[0]);
		return input;
	}

	void mainHanoi(int tamanho, Hanoi p, Hanoi p2, Hanoi p3) {
			if (p.imprimir()==null &&(p2.imprimir()==null || p3.imprimir()==null)) {
				System.out.println("Parabens, voce ganhou! \n");
				p2.showHanoi(tamanho, p, p2, p3);
				System.exit(1);
			}

		p.showHanoi(tamanho, p, p2, p3);
		int[] input = p.inputHanoi();
		p.playHanoi(tamanho,input[0], input[1], p, p2, p3);
		p.mainHanoi(tamanho, p, p2, p3);

	}

	void showHanoi(int tamanho, Hanoi p, Hanoi p2, Hanoi p3) {
		// mostrando as pilhas
		No noaux = p.topo, noaux2 = p2.topo, noaux3 = p3.topo;
		for (int i = 0; i < tamanho; i++) {
			if (noaux != null) {
				System.out.print("     |" + noaux.dado + "|" + "    ");
				noaux = noaux.proximo;
			} else {
				System.out.print("     |" + " " + "|" + "    ");
			}
			if (noaux2 != null) {
				System.out.print("        |" + noaux2.dado + "|" + "    ");
				noaux2 = noaux2.proximo;
			} else {
				System.out.print("        |" + " " + "|" + "    ");
			}
			if (noaux3 != null) {
				System.out.print("        |" + noaux3.dado + "|" + "     ");
				noaux3 = noaux3.proximo;
			} else {
				System.out.print("        |" + " " + "|" + "    ");
			}
			System.out.println("\n");
		}
		System.out.println(" ___________    ___________    ___________    ");
		noaux = p.topo;
		noaux2 = p2.topo;
		noaux3 = p3.topo;
		System.out.println("\n  _Torre 1_      _Torre 2_      _Torre 3_    \n");
	}

	void playHanoi(int tamanho, int input, int input2, Hanoi p, Hanoi p2, Hanoi p3) {
	// mostrando as pilhas
	No noaux = p.topo, noaux2 = p2.topo;
	Hanoi paux1 = new Hanoi(), paux2 = new Hanoi();
	switch (input) {
	case 1:
		noaux = p.topo;
		paux1 = p;
		break;
	case 2:
		noaux = p2.topo;
		paux1 = p2;
		break;
	case 3:
		noaux = p3.topo;
		paux1 = p3;
		break;
	}
	switch (input2) {
	case 1:
		noaux2 = p.topo;
		paux2 = p;
		break;
	case 2:
		noaux2 = p2.topo;
		paux2 = p2;

		break;
	case 3:
		noaux2 = p3.topo;
		paux2 = p3;
		break;
	}

	if (noaux == null) {
		System.out.println("Pilha inválida para retirar, esta vazia");
	} else if (noaux2 == null || noaux.dado < noaux2.dado) {
		paux2.push(paux1.pop());
	} else
		System.out.println("Pilha inválida, o valor debaixo é maior do que o que voce quer colocar em cima");
	switch (input) {
	case 1:
		p = paux1;
		break;
	case 2:
		p2 = paux1;

		break;
	case 3:
		p3 = paux1;

		break;
	}
	switch (input2) {
	case 1:
		p = paux2;

		break;
	case 2:
		p2 = paux2;

		break;
	case 3:
		p3 = paux2;

		break;
	}}}

