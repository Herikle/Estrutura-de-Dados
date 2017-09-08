package fila.estrutura;


public class FilaEstrutura {

    /**
     * @param args the command line arguments
     * @throws fila.estrutura.FilaVaziaException
     */
    public static void main(String[] args) throws FilaVaziaException {
        Fila f = new Fila();
        
        System.out.println("Inserindo... 6"); f.queue(6);
        System.out.println("Inserindo... 5"); f.queue(5);
        System.out.println("Inserindo... 4"); f.queue(4);
        System.out.println("Inserindo... 8"); f.queue(8);
        System.out.println("Removendo..." + f.inqueue());
        System.out.println("Inserindo... 10"); f.queue(10);
        System.out.println("Removendo..." + f.inqueue());
        System.out.println("----------------------");
        System.out.println("Primeiro = " + f.first());
        System.out.println("Tamanho = " + f.size());
        System.out.println("----------------------");
        System.out.println("Inserindo... 6"); f.queue(6);
        System.out.println("Inserindo... 5"); f.queue(5);
        System.out.println("Primeiro = " + f.first());
        System.out.println("Tamanho = " + f.size());
        System.out.println("Removendo..." + f.inqueue());
        System.out.println("Inserindo... 5"); f.queue(5);      
        System.out.println("----------------------");
        System.out.println("Primeiro = " + f.first());
        System.out.println("Tamanho = " + f.size());
    }
    
}
