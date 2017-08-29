
package pilha.array;

public class Pilha {
        int itens[] = new int[10];
        int index = -1;
        public void push(int x)
        {
            if (itens.length == index+1)
            {
                int novoarray[] = new int[itens.length*2];
                for(int y = 0;y<itens.length;y++)
                {
                    novoarray[y] = itens[y];
                }
                itens = novoarray;
            }
            itens[++index] = x;
        }
        
        public int pop() throws Exception
        {
            if (index==-1) throw new Exception("Pilha está vazia");
            return itens[index--];
        }
        
        public int top() throws Exception
        {
            if (index==-1) throw new Exception("Pilha está vazia");
            return itens[index];       
        }
        
        public int size()
        {
            return index+1;
        }
        
}
