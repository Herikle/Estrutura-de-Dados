package fila.array;


public class FilaArray {

    /**
     * @param args the command line arguments
     * @throws fila.array.FilaVaziaException
     */
    public static void main(String[] args) throws FilaVaziaException {
        Fila f = new Fila();
        System.out.println("Inserindo... "); f.queue(5);
        System.out.println("Inicio da fila..." + f.first());
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        for (int x = 0;x<=9;x++)
        {
            System.out.println("Inserindo... " + x); f.queue(x);
        }
        System.out.println("-------------------------------------");
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        for (int x = 6;x<=20;x++)
        {
            System.out.println("Inserindo... " + x); f.queue(x);
        }
        System.out.println("-------------------------------------");
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("Removendo... " + f.inqueue());
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        for (int x = 21;x<=60;x++)
        {
            System.out.println("Inserindo... " + x); f.queue(x);
        }
        System.out.println("-------------------------------------");
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        for (int x = 0;x<59;x++)
        {
            System.out.println("Removendo... " + f.inqueue());
        }
        System.out.println("-------------------------------------");
        System.out.println("Tamanho = " + f.size());
        System.out.println("-------------------------------------");
        //f.inqueue(); //exception
        
    }
    
}
