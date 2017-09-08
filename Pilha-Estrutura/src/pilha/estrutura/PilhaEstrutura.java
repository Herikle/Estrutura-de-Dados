package pilha.estrutura;


public class PilhaEstrutura {

    /**
     * @param args the command line arguments
     * @throws pilha.estrutura.PilhaException
     */
    public static void main(String[] args) throws PilhaException {
        Pilha p = new Pilha();
        System.out.println("Inserindo 0..."); p.push(0);
        System.out.println("Removendo..." + p.pop());
        System.out.println("------------------------------------");
        for(int x = 1; x<=10;x++)
        {
             System.out.println("Inserindo " + x + "..."); p.push(x);          
        }
        System.out.println("------------------------------------");
        System.out.println("Mostrando topo..." + p.top());
        System.out.println("Tamanho = " + p.size());
        System.out.println("------------------------------------");
        for(int x = 1; x<=10;x++)
        {
             System.out.println("Removendo " + p.pop() + "...");           
        }
        System.out.println("Tamanho = " + p.size());
        //p.pop(); //exception
        
    }
    
}
