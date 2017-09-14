package fila.array;


public class Fila {
    private int [] itens = new int[10];  //resolver tamanho aqui
    private int i=0, f=0;
    public void queue(int x)
    {
        if((f+1 % itens.length)==i)
        {
            int [] novo = new int[itens.length*2];
            for(int z = 0;i!=f;i = (i+1) % itens.length)
            {
                novo[z++] = itens[i];    
            } 
            i=0;
            f = itens.length-1;
            itens = novo;
        }  
        itens[f++] = x;
        f %= itens.length;      
    }
    public int inqueue() throws FilaVaziaException
    {
        if (isEmpty()) throw new FilaVaziaException("Fila está vazia");
        int it = itens[i++];
        i %= itens.length;
        return it;
    }
    public int first() throws FilaVaziaException
    {
        if (isEmpty()) throw new FilaVaziaException("Fila está vazia");
        return itens[i];
    }
    public int size()
    {
        return (itens.length - i + f) % itens.length;
    }
    public boolean isEmpty()
    {
        return i == f;
    }
    
}
