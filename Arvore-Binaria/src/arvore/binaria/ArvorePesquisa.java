package arvore.binaria;

import static java.lang.Math.pow;
import java.util.ArrayList;

public class ArvorePesquisa {
    private class No
    {
        Object elemento;
        int chave;
        No esq, dir,pai;
    }
    private No raiz;
    private int size;
    public ArvorePesquisa()
    {
        this.raiz = null;
    
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
       this.raiz = this.insere(chave, elemento, raiz,null);
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
            System.out.println(array.get(x).chave+" : "+array.get(x).elemento+" | Altura: " + this.AlturaNo(array.get(x)));
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
            p = new No();
            p.elemento = elemento; p.chave = chave;
            if (pai!=null) p.pai = pai;
            p.esq = null; p.dir = null;
        }
        else if (chave > p.chave) p.dir = insere(chave,elemento,p.dir,p);
        else if (chave < p.chave) p.esq = insere(chave,elemento,p.esq,p);
        else System.out.println("Erro: Chave já existente");
        return p;
    }
    private No retira (int chave,No p)
    {
      if (p == null) 
          System.out.println("Erro: Chave não encontrada");
      else if (chave > p.chave) 
          p.dir = retira (chave,p.dir);
      else if (chave < p.chave) 
          p.esq = retira (chave,p.esq);
      else
      {
          if (p.dir == null) 
          {
              if(p.esq!=null){
                  p.esq.pai = p.pai;
                  p = p.esq;
              }else
                  p = p.esq;
              
          }
          else if (p.esq == null) 
          {
              if(p.dir!=null){
                  p.dir.pai = p.pai;
                  p = p.dir;
              }else
                  p = p.dir;
          }
          else doisFilhos (p,p.dir);
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
                r.dir.pai = r.pai;
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

}
