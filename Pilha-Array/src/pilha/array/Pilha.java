/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha.array;
/**
 *
 * @author proprietario
 */
public class Pilha {
        int itens[] = new int[10];
        int size = -1;
        public void push(int x)
        {
            if (itens.length == size+1)
            {
                int novoarray[] = new int[itens.length*2];
                for(int y = 0;y<itens.length;y++)
                {
                    novoarray[y] = itens[y];
                }
                itens = novoarray;
            }
            itens[++size] = x;
        }
        
        public int pop() throws Exception
        {
            if (size==-1) throw new Exception("Pilha está vazia");
            return itens[size--];
        }
        
        public int top() throws Exception
        {
            if (size==-1) throw new Exception("Pilha está vazia");
            return itens[size];       
        }
        
        public int size()
        {
            return size+1;
        }
        
}
