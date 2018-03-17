//Main/Classe de teste

package arvoreavl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class ArvoreAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arvore_AVL arvore = new Arvore_AVL();   
        int choose,chave;
        Object objeto;
        arvore.insere(32,32);
        arvore.insere(31,31);
        arvore.insere(35,35);
        arvore.insere(33,33);
        arvore.insere(36,36);
        System.out.println("1 - Inserir\n2 - Remover\n3 - Pesquisar\n4 - Imprimir\n5 - Ver altura de um nó\n6 - Profundidade da arvore\n7 - Imprimir Arvore\n");
        choose = in.nextInt();    
        while(choose!=0)
        {
            switch (choose) {
                case 1:
                    System.out.println("Chave: ");
                    chave = in.nextInt();
                    System.out.println("Item: ");
                    objeto = br.readLine();
                    arvore.insere(chave, objeto);
                    break;
                case 2:
                    System.out.println("Chave: ");
                    chave = in.nextInt();
                    arvore.retira(chave);
                    break;
                case 3:
                    System.out.println("Chave: ");
                    chave = in.nextInt();
                    Object x = arvore.pesquisa(chave);
                    if (x==null)
                    {
                        System.out.println("Chave não encontrada");
                    }else
                    {
                        System.out.println(x);
                    }
                    
                    break;
                case 4:
                    arvore.imprime();
                    break;
                case 5:
                    System.out.println("Chave: ");
                    chave = in.nextInt();
                    System.out.println(arvore.AlturaNo(chave));
                    break;
                case 6:
                    System.out.println(arvore.ProfundidadeTree());
                    break;
                case 7:
                    arvore.ImprimeArvore();
                default:
                    break;
            }
            System.out.println("1 - Inserir\n2 - Remover\n3 - Pesquisar\n4 - Imprimir\n5 - Ver altura de um nó\n6 - Profundidade da arvore\n7 - Imprimir Arvore\n");
            choose = in.nextInt(); 
        }
        
        
    }
    
}
