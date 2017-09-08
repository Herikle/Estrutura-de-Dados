package fila.estrutura;


public class Fila {
    class No{
        No next;
        int item;
    }
    private final No head = new No();
    private No last = head;
    private int size;
    public void queue(int x)
    {
        No no = new No();
        no.item = x;
        last.next = no;
        last = no;
        size++;
    }
    public int inqueue() throws FilaVaziaException
    {
        if (isEmpty()) throw new FilaVaziaException("A fila está vazia");
        int x = head.next.item;
        head.next = head.next.next;
        size--;
        return x;
    }
    public int first() throws FilaVaziaException
    {
        if (isEmpty()) throw new FilaVaziaException("A fila está vazia");
        return head.next.item;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public int size()
    {
        return size;
    }  
}
