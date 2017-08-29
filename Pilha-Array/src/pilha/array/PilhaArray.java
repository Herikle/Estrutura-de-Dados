/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the temp7late in the editor.
 */
package pilha.array;

/**
 *
 * @author proprietario
 */
public class PilhaArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();
        for(int x = 0;x<50;x++)
        {
            System.out.println("Adicionando " + x); pilha.push(x);
        }
            System.out.println("Tamanho = " + pilha.size());
    }
    
}
