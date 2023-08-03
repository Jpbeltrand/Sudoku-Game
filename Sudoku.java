import java.util.Scanner;
/**
 * @author João Pedro Beltrand(22103079)
 * Thiago Espirito Santo Miro(22103151)
 */
  public class Sudoku {
    //Criação da matriz
    /**
     * Recebe a matriz com o tabuleiro escolhido pelo jogador, adiciona nome as linhas (ex: L1-,L4-) e desenha as divisorias internas.
     */
     public static void exibeMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("L" + (i+1) + " - ");
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
                if (j == 2 || j == 5) System.out.print("| ");
            }
            System.out.println();
            if (i == 2 || i == 5)
                System.out.print("     ------|-------|------\n");
        }
    }
    /**
     *  Esse metodo copia a matriz original caso a jogada não seja possivel, 
     *  permitindo o jogador continuar sem alterar o tabuleiro erroneamente.
     */
     public static int[][] copiarMatriz(int[][] matriz) {
        int[][] novaMatriz = new int[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                novaMatriz[i][j] = matriz[i][j];
            }
        }
        return novaMatriz;
    }

    //Verificação
    /**
     *  O metodo recebe a jogada(linha,coluna e numero) e 
     *  verifica na linha, na coluna e no box se é possivel inseri-lo naquele local. 
     */
    public static boolean inserirNumeroNaPosição(int[][] matriz, int linha, int coluna, int numero) {
        for (int i = 0; i < matriz[0].length; i++) {
            if (matriz[linha][i] == numero)
                return false;
        }      
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][coluna] == numero)
                return false;
        }
        int boxLinha = linha - linha % 3;
        int boxColuna = coluna - coluna % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[boxLinha + i][boxColuna + j] == numero)
                    return false;
            }
        }    
        return true;
    }
    /**
     *  Esse metodo tambem recebe a jogada e faz a verificação, 
     *  porem ele mostra ao usuario se o moviemnto esta errado e 
     *  diz para o usuario se o numero não se encaixa na linha, na coluna ou no boxe.
     */
     public static void exibeMovimentoBloqueado(int[][] matriz, int linha, int coluna, int numero) {
        int[][] novaMatriz = copiarMatriz(matriz);
        System.out.println(" Movimento inválido!");
        System.out.println(" Onde:");
        novaMatriz[linha][coluna] = numero;
        
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[linha][i] == numero) { 
                System.out.println("-> Linha");
            }
        }
        
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][coluna] == numero) { 
                System.out.println("-> Coluna");
            }
        }
        
        int boxLinha = linha - linha % 3;
        int boxColuna = coluna - coluna % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[boxLinha + i][boxColuna + j] == numero) {
                  System.out.println("-> Box");  
                }
            }
        }
    }
    /**
     * Esse metodo recebe a matriz, passa por todas as posições e se estiverem todas ocupadas retorna true.E no main ele printa fim de jogo.
     */
    public boolean verificarTabuleiro(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //Tabuleiro Fixo
    /**
     * Esse metodo gera um tabuleiro fixo que não vai poder ser alterado e 
     * serve para quando uma jogada for feita em uma posição ja ocupada inicialmente, 
     * o jogo mostre que aquela posição é fixa do tabuleiro.
     */
    //Função Adicional
     public static void posFixasTabuleiro(boolean [][] posFixa, int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print((i+1) + " - ");
            for (int j = 0; j < matriz.length; j++) {
                if (posFixa[i][j])            
                        System.out.print(matriz[i][j] + " ");
                else
                        System.out.print(matriz[i][j] + " ");
                if (j == 2 || j == 5) System.out.print("| "); 
            }
            System.out.println();
            if (i == 2 || i == 5)
                System.out.print("    ------|-------|-------\n"); 
        }
    }
    /**
     * Esse metodo compara a matriz que esta sendo alterada com a matriz fixa, 
     * se o valor da posição for 0 ele pode ser alterado, 
     * porem se ja for ocupado por outro numero a jogada é invalida.
     */
    private static boolean[][] ocuparPos(boolean[][] posFixa, int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != 0)
                    posFixa[i][j] = true;
            }
        }
        return posFixa;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int linha = -1;
        int coluna = -1;
        int numEscolha= -1;
        int tabEscolha = -1;
        Sudoku jogo = new Sudoku();
        boolean verificar = true;      
        int[][] matriz = new int[9][9]; 
        boolean[][] posFixa = new boolean[9][9]; 
  
        int[][] tabuleiro1 = {{8,1,0,0,0,0,0,2,7},{0,0,4,0,0,0,1,0,0},{2,3,0,0,0,0,0,4,5},{0,0,0,1,7,4,0,0,0},
            {4,0,0,5,0,6,0,0,9},{0,7,0,0,3,0,0,1,0},{0,0,0,0,1,0,0,0,0},{0,4,3,0,0,0,6,5,0},{1,0,0,3,0,7,0,0,8}};   
        int[][] tabuleiro2 = {{7,8,0,0,0,0,0,3,0},{0,0,0,0,3,0,0,0,0},{0,6,3,5,0,2,0,0,0},{3,0,0,0,0,1,9,4,0},
            {0,0,0,4,0,5,0,0,0},{0,4,2,3,0,0,0,0,8},{0,0,0,2,0,9,3,6,0},{0,0,0,0,8,0,0,0,0},{0,5,0,0,0,0,0,1,4}};
        int[][] tabuleiro3 = {{0,8,0,0,0,5,0,0,0},{0,3,9,2,0,1,0,8,7},{0,0,6,0,8,0,9,2,0},{7,2,0,0,0,0,0,3,0},
            {0,0,4,0,0,0,1,0,0},{0,6,0,0,0,0,0,7,2},{0,4,5,0,3,0,7,0,0},{8,7,0,1,0,9,2,6,0},{0,0,0,5,0,0,0,9,0}};
            
        System.out.println("Escolha o tabuleiro 1 , 2 ou 3:");
        do {
            tabEscolha = in.nextInt();
            if (tabEscolha < 1 || tabEscolha > 3)
                System.out.println("Tabuleiro inválido! Escolha 1, 2 ou 3");
            else
                verificar = false;
        } while (verificar);

        if (tabEscolha == 1) {
            matriz = tabuleiro1;
            System.out.print("      Tabuleiro: 1     ");
        }
        if (tabEscolha == 2) {
            matriz = tabuleiro2;
            System.out.print("      Tabuleiro: 2     ");
        }
        if (tabEscolha == 3) {
            matriz = tabuleiro3;
            System.out.print("      Tabuleiro: 3     ");
        }
        
        posFixa = ocuparPos(posFixa, matriz);  
        
        do {
            verificar = true;
            System.out.println();
            exibeMatriz(matriz);
            
            System.out.println("Digitar números de 1 a 9 para linha, coluna e numero");
            do {
                System.out.print("Linha: ");
                linha = in.nextInt()- 1;
            
                System.out.print("Coluna: ");
                coluna = in.nextInt()- 1;
            
                System.out.print("Número: ");
                numEscolha = in.nextInt();
                
                if(linha < 0 || linha > 8 || coluna < 0 || coluna > 8 || numEscolha < 1 || numEscolha > 9)
                {
                 System.out.println("`Apenas números de 1 a 9 são válidos!");
                }else{
                    verificar = false;}
                
            } while (verificar);
            
            verificar = true; 
            
            if(posFixa[linha][coluna]) {
                System.out.println(" As posicões fixas, não podem ser alterádas! ");
                posFixasTabuleiro(posFixa, matriz);
            } else {
                if (inserirNumeroNaPosição(matriz, linha, coluna, numEscolha)) {
                    matriz[linha][coluna] = numEscolha;
                } else {
                    exibeMovimentoBloqueado(matriz, linha, coluna, numEscolha);
                }
            }
        } while (!jogo.verificarTabuleiro(matriz));
        System.out.println("Fim de Jogo!");
    }
}

