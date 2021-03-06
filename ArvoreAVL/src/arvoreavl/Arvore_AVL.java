package arvoreavl;
import static java.lang.Math.pow;
import java.util.ArrayList;

public class Arvore_AVL {
    private class No
    {
        Object elemento;
        int chave, FB;
        No esq, dir,pai;
        public No(Object elemento, int chave,No esq,No dir,No pai){
            this.elemento = elemento;
            this.chave = chave;
            this.esq = esq;
            this.dir = dir;
            this.pai = pai;
        }
        public void setDir(No no){
            this.dir = no;
        }
        public void setEsq(No no){
            this.esq = no;
        }
    }
    private No raiz;
    private int size;
    public Arvore_AVL()
    {
        raiz = null;
    }  
    public int size()
    {
        return size;
    }
    public Object pesquisa(int chave)
    {
        No x = this.pesquisa(chave, raiz);
        if (x!=null)
            return x.elemento;
        else 
            return null;
    }
    public void insere(int chave,Object elemento)
    {
       if (this.raiz == null)
            this.raiz = this.insere(chave, elemento, this.raiz,null);
       else
           this.insere(chave, elemento, this.raiz,null);
       this.size++;
    }
    public void retira (int chave)
    {
       this.raiz = this.retira(chave,this.raiz);
       this.size--;
    }
    public void imprime()
    {
        ArrayList<No> array = new ArrayList();
        this.Inorder(raiz, array);
        for(int x = 0; x < this.size;x++)
        {
            System.out.println(array.get(x).chave+" : "+array.get(x).elemento+" | Altura: " + this.AlturaNo(array.get(x)) + " | FB: " + array.get(x).FB);
        }
        
    }
    public int AlturaNo(int chave)
    {
        No no = this.pesquisa(chave,this.raiz);
        if (no!=null)
        {
            return this.AlturaNo(no);
        }
        else
        {
            return -1;
        }
    }
    public void ImprimeArvore()
    {   
       Object[][] arvore;
       double deep = (double) this.ProfundidadeTree();
       
       int l = this.ProfundidadeTree() + 1;
       
       int c = ((int)pow(2.0,deep)*2) + 1;
       
       arvore = new Object[l][c];
       
       No no = this.raiz;
             
       //----------------------------------------------------------- 
       for(int x = 0; x < l;x++)                                    //
       {                                                            //
           for(int y = 0; y < c;y++)                                // 
           {                                                        //
               arvore[x][y] = '-';                                  //
           }                                                        // 
       }                                                            //
       //-------------------------------------------------------------
        this.PreencheMatriz(arvore,c/2, c,this.raiz);
         //----------------------------------------------------------- 
       for(int x = 0; x < l;x++)                                    //
       {                                                            //
           for(int y = 0; y < c;y++)                                // 
           {   
               if (arvore[x][y].equals('-'))
                   System.out.print(String.format("%4c", arvore[x][y]));   
               else
                   System.out.print(String.format("%4d", arvore[x][y]));  
           }                                                        //
           System.out.println();                                    // 
       }                                                            //
       //-------------------------------------------------------------
        
    }
    public int ProfundidadeTree()
    {
        return this.ProfundidadeTree(this.raiz)-1;
    }
   //-------------------------------PRIVATE-------------------------------------
    private No pesquisa(int chave,No p)
    {
       if (p==null) return null;
       else if (chave > p.chave) return pesquisa (chave,p.dir);
       else if (chave < p.chave) return pesquisa (chave,p.esq);
       else return p;           
    }
    private No insere(int chave, Object elemento, No p,No pai)
    {
        if (p==null)
        {
            p = new No(elemento, chave,null,null,pai);
            if(pai!=null){
                if(isLeft(p))
                {
                    pai.setEsq(p);
                }else
                    pai.setDir(p);
            }
            changeFb(p, true);
        }
        else if (chave > p.chave) insere(chave,elemento,p.dir,p);
        else if (chave < p.chave) insere(chave,elemento,p.esq,p);
        else System.out.println("Erro: Chave já existente");
        
        return p;
    }
    private No retira (int chave,No p)
    {
      if (p == null) 
          System.out.println("Erro: Chave não encontrada");
      else if (chave > p.chave) 
          retira (chave,p.dir);
      else if (chave < p.chave) 
          retira (chave,p.esq);
      else
      {
          if (p.dir == null) 
          {
              changeFb(p, false);
              if(p.esq!=null)
              {
                p.esq.pai = p.pai;
                if(isLeft(p)){
                    p.pai.esq = p.esq;
                }else{
                    p.pai.dir = p.esq;
                }
                  p = p.esq;
              }else
              {
                if(isLeft(p)){
                    p.pai.esq = null;
                }else{
                    p.pai.dir = null;
                }
                p = p.esq;
              }
          }
          else if (p.esq == null) 
          {
              changeFb(p, false);
              if(p.dir!=null)
              {
                p.dir.pai = p.pai;
                if(isLeft(p)){
                    p.pai.esq = p.dir;
                }else{
                    p.pai.dir = p.dir;
                }
                p = p.dir;
              }else
              {
                if(isLeft(p)){
                    p.pai.esq = null;
                }else{
                    p.pai.dir = null;
                }
                p = p.dir;
              }

          }
          else 
              doisFilhos (p,p.dir);

      }
      return p;
    }
    private void Inorder(No p, ArrayList a)
    {
        if(p!=null)
        {
            Inorder(p.esq,a);
            a.add(p);
            Inorder(p.dir,a);
        }
    }
    private No doisFilhos (No q, No r)
    {
        if (r.esq != null) 
            r.esq = doisFilhos(q, r.esq);
        else
        {
            q.chave = r.chave; 
            q.elemento = r.elemento; 
            if(q.dir == r)
                q.dir = null;
            try{
                changeFb(r,false);
                r = r.dir;
            }
            catch(NullPointerException ex)
            {
                r = null;
            }
        }
        return r;
    }
    private int AlturaNo(No no)
    {
        if (no.pai!=null)
        {
            return 1 + AlturaNo(no.pai);
        }
        return 0;
    }
    private void PreencheMatriz(Object[][] matriz, int pos, int tam, No no)
    {
        int alt = this.AlturaNo(no);
        matriz[alt][pos] = no.chave;
        if(no.dir!=null)
        {
            if (alt!=0)
                this.PreencheMatriz(matriz, pos+((this.ProfundidadeTree())-alt), tam, no.dir);
            else
                this.PreencheMatriz(matriz, pos+(((tam-pos)/2)), tam, no.dir);
        }
        if(no.esq!=null)
        {
            if (alt!=0)
                this.PreencheMatriz(matriz, pos-((this.ProfundidadeTree())-alt), tam, no.esq);
            else
                this.PreencheMatriz(matriz, pos-((pos/2)), tam, no.esq);
        }          
    }
    private int ProfundidadeTree(No no)
    {
        if (no == null) return 0;
  
        int Esquerda = ProfundidadeTree(no.esq);
        int Direita = ProfundidadeTree(no.dir);
        
        if (Esquerda > Direita ) return Esquerda+1;
            
        return Direita+1;              
    }
    
    private void changeFb(No no,boolean insert)
    {
        if(insert)
        {
            OUTER:
            while (no.pai!=null) {
                no.pai.FB += isLeft(no)? 1 : -1;
                switch (no.pai.FB) {
                    case 0:
                        break OUTER;
                    case -2:
                        if (no.pai.dir.FB<=0) {
                            SLR(no.pai);
                            break OUTER;
                        }else
                        {
                            No guarda = no.pai;
                            SRR(no.pai.dir);
                            SLR(guarda);
                        }
                        break;
                    case 2:
                        if (no.pai.esq.FB>=0) {
                            SRR(no.pai);
                            break OUTER;
                        }
                        else
                        {
                            No guarda = no.pai;
                            SLR(no.pai.esq);
                            SRR(guarda);
                        }
                        break;
                    default:
                        break;
                }
                no = no.pai;
                if(no==null) break;
            }
        }
        else
        {
            while(no.pai!=null)
            {
                no.pai.FB += isLeft(no)? -1 : 1 ;
                if(no.pai.FB==-2)
                {
                    if (no.pai.dir.FB<=0) 
                    {
                        SLR(no.pai);
                        break;
                    }
                    else
                    {
                        No guarda = no.pai;
                        SRR(no.pai.dir);
                        SLR(guarda);
                        break;
                    }
                }
                else if(no.pai.FB==2)
                {
                    if (no.pai.esq.FB>=0) 
                    {
                        SRR(no.pai);
                        break;
                    }
                    else
                    {
                        No guarda = no.pai;
                        SLR(no.pai.esq);
                        SRR(guarda);
                        break;
                    }
                    
                }else if(no.pai.FB!=0)
                {
                    break;
                }
                no = no.pai;
            }
        }
    }
    
    private boolean isLeft(No no)
    {
        return no.pai.chave>no.chave;
    }
    
    
    private void SLR(No no){ 
        No aux = no.dir;
        if(aux.esq!=null)          
            aux.esq.pai = no;        
        no.dir = aux.esq;
        No pai = no.pai;
        no.pai = aux;
        aux.pai = pai;
        if(pai!=null)
        {
            if(isLeft(aux))
                pai.esq = aux;
            else
                pai.dir = aux;
        }
        aux.esq = no;
        if(aux.pai==null) this.raiz = aux;
        no.FB = no.FB+1 - min(aux.FB,0);
        aux.FB = aux.FB + 1 + max(no.FB, 0);     
    }
    
    private void SRR(No no){
        No aux = no.esq;
        if(aux.dir!=null)
            aux.dir.pai = no;
        no.esq = aux.dir;
        No pai = no.pai;
        no.pai = aux;
        aux.pai = pai;
        if(pai!=null)
        {
            if(isLeft(aux))
                pai.esq = aux;
            else
                pai.dir = aux;
        }
        aux.dir = no;
        if(aux.pai==null) this.raiz = aux;
        no.FB = no.FB - 1 - max(aux.FB,0);
        aux.FB = aux.FB - 1 + min(no.FB,0);
    }
    
    private int max(int a, int b)
    {
        return a>b? a:b;
    }
    private int min(int a, int b)
    {
        return a<b? a:b;
    }
            
    
}
