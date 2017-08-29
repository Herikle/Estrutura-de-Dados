
package pilha.array;
//teste
public class PilhaArray {


    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();
        for(int x = 0;x<50;x++)
        {
            System.out.println("Adicionando " + x); pilha.push(x);
        }
            System.out.println("Tamanho = " + pilha.size());
    }
    
}
