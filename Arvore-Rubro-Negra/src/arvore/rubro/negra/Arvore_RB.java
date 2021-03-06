package arvore.rubro.negra;

import static java.lang.Math.pow;
import java.util.ArrayList;

public class Arvore_RB {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private class No
    {
        Object elemento;
        int chave;
        boolean cor; //true = Rubro, false = Negro
        No esq, dir,pai;
        public No(Object elemento, int chave,No esq,No dir,No pai, boolean cor){
            this.elemento = elemento;
            this.chave = chave;
            this.esq = esq;
            this.dir = dir;
            this.pai = pai;
            this.cor = cor;
        }
        public void setDir(No no){
            this.dir = no;
        }
        public void setEsq(No no){
            this.esq = no;
        }
        public No getAvo(){
            return this.pai.pai;
        }
        public No getTio(){
            return isLeft(this.pai)? this.getAvo().dir : this.getAvo().esq;
        }
    }
    private No raiz;
    private int size;
    public Arvore_RB()
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
       if (this.raiz == null)
            this.raiz = this.insere(chave, elemento, this.raiz,null);
       else
           this.insere(chave, elemento, this.raiz,null);
       this.size++;
    }
    public void retira (int chave)
    {
       this.retira(chave,this.raiz);
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
       if (this.raiz == null) System.out.println("Arvore vazia");
       else{
       Object[][] arvore;
       boolean[][] cores;
       double deep = (double) this.ProfundidadeTree();
       
       int l = this.ProfundidadeTree() + 1;
       
       int c = ((int)pow(2.0,deep)*2) + 1;
       
       arvore = new Object[l][c];
       cores = new boolean[l][c];
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
        this.PreencheMatriz(arvore,cores,c/2, c,this.raiz);
         //----------------------------------------------------------- 
       for(int x = 0; x < l;x++)                                    //
       {                                                            //
           for(int y = 0; y < c;y++)                                // 
           {   
               if (arvore[x][y].equals('-'))
                   System.out.print(String.format("%4c", arvore[x][y]));   
               else
                   if(cores[x][y])
                        System.out.print(String.format(ANSI_RED + "%4d" + ANSI_RESET, arvore[x][y]));  
                   else
                       System.out.print(String.format("%4d", arvore[x][y]));
           }                                                        //
           System.out.println();                                    // 
       }                                                            //
       //-------------------------------------------------------------
       }
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
            p = new No(elemento, chave,null,null,pai,this.raiz!=null);
            if(pai!=null){
                if(isLeft(p))
                {
                    pai.setEsq(p);
                }else
                    pai.setDir(p);
            }
            if(p.pai!=null && p.pai.cor)
            {
                if(!p.getAvo().cor)
                    insertCaso2(p);
            }
            
            
        }
        else if (chave > p.chave) insere(chave,elemento,p.dir,p);
        else if (chave < p.chave) insere(chave,elemento,p.esq,p);
        else System.out.println("Erro: Chave já existente");
        return p;
    }
    
    private void insertCaso2(No no) 
    {
        try{
            if(no.getTio()!=null && (no.getTio()).cor){
                if(no.getAvo()!=this.raiz)
                {
                    (no.getAvo()).cor = true;
                }
                no.pai.cor = false;
                try{
                    (no.getTio()).cor = false;
                }catch(NullPointerException ex){}
            }else{
                insertCaso3(no);
                return;
            }
        }catch(NullPointerException ex){}
        if((no.getAvo()!=null) && ((no.getAvo()).pai!=null))  
        {
            if((no.getAvo()).pai.cor)
                insertCaso2((no.getAvo()));
        }
    }
    
    private void insertCaso3(No no){
       if(isLeft(no.pai))
       {
           if(isLeft(no))
           {
               No guarda = no.getAvo();
               SRR(guarda);
               guarda.cor = true;
               guarda.pai.cor = false;
           }else
           {
                No guarda = no.getAvo();
                SLR(no.pai);
                SRR(guarda);
                guarda.cor = true;
                guarda.pai.cor = false;
           }
       }else
       {
           if(!isLeft(no))
           {
               No guarda = no.getAvo();
               SLR(guarda);
               guarda.cor = true;
               guarda.pai.cor = false;
           }else
           {
                No guarda = no.getAvo();
                SRR(no.pai);
                SLR(guarda);
                guarda.cor = true;
                guarda.pai.cor = false;
           }
       }
       
    }
    
    private No retira (int chave,No p)
    {
      if (p == null) 
          System.out.println("Erro: Chave não encontrada");
      else if (chave > p.chave) 
          retira(chave,p.dir);
      else if (chave < p.chave) 
          retira(chave,p.esq);
      else
      {
          No guarda = p.pai;
          boolean wasLeft = isLeft(p);
          boolean doubleBlack = false;
           if (p.dir == null) 
          {
              if(p.esq!=null)
              {
                if(p.cor && !p.esq.cor || !p.cor && p.esq.cor) //cobre caso 2
                    p.esq.cor = false;  
                else if(!p.cor && !p.esq.cor)
                    doubleBlack = true;
                p.esq.pai = p.pai;
                if(isLeft(p))
                    p.pai.esq = p.esq;
                else
                    p.pai.dir = p.esq;
                p = p.esq;
              }
              else
              {
                if(!p.cor) doubleBlack = true;
                if(isLeft(p))
                    p.pai.esq = null;
                else
                    p.pai.dir = null;
                p = null;
              }
              if(doubleBlack)
              { 
                Situation3(guarda,wasLeft);
              }
          }
          else if (p.esq == null) 
          {
              if(p.dir!=null){
                  if(p.cor && !p.dir.cor || !p.cor && p.dir.cor) //cobre caso 2
                      p.dir.cor = false;
                  else if(!p.cor && !p.dir.cor)
                      doubleBlack = true;
                  p.dir.pai = p.pai;
                  if(isLeft(p))
                      p.pai.esq = p.dir;
                  else
                      p.pai.dir = p.dir;
                  p = p.dir;
              }else
              {
                if(!p.cor) doubleBlack = true;
                if(isLeft(p))
                    p.pai.esq = null;
                else
                    p.pai.dir = null;
                p = null;
              }
              if(doubleBlack)
              {
                  Situation3(guarda,wasLeft);
              }
          }
          else doisFilhos (p,p.dir);
      }
      return p;
    }
    
    private void Situation3(No pai, boolean left){
        if(pai==null){
            
        }
        else if(left)
        {
            if(!pai.dir.cor){
                if((pai.dir.dir!=null && pai.dir.dir.cor) || (pai.dir.esq!=null && pai.dir.esq.cor)){
                    if(pai.dir.dir!=null && pai.dir.dir.cor)
                    {
                        pai.dir.dir.cor = false;
                        SLR(pai);
                    }
                    else if(pai.dir.esq!=null && pai.dir.esq.cor)
                    {
                        No guarda = pai;
                        pai.dir.esq.cor = false;
                        SRR(pai.dir);
                        SLR(guarda);
                    }
                }
            }
        }else{
            if(!pai.esq.cor){
                if((pai.esq.dir!=null && pai.esq.dir.cor) || (pai.esq.esq!=null && pai.esq.esq.cor)){
                    if(pai.esq.esq!=null && pai.esq.esq.cor)
                    {
                        pai.esq.esq.cor = false;
                        SRR(pai);
                    }else if(pai.esq.dir!=null && pai.esq.dir.cor){
                        No guarda = pai;
                        pai.esq.dir.cor = false;
                        SLR(pai.esq);
                        SRR(guarda);
                    }
                }
            }
        }
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
            No guarda = q.pai;
            boolean wasLeft = isLeft(q);
            boolean doubleBlack = false;
            if(q.cor && !r.cor || !q.cor && r.cor)//cobre caso 1 e caso 2
                r.cor = false;    
            else if(!q.cor && !r.cor)
                doubleBlack = true;
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
            if(doubleBlack)
                Situation3(guarda,wasLeft);
        }
        return r;
    }
    private int AlturaNo(No no)
    {   try{
            if (no.pai!=null)
            {
                return 1 + AlturaNo(no.pai);
            }
        }catch(NullPointerException ex){         
            System.out.println(ex);
        }
        return 0;
    }
    private void PreencheMatriz(Object[][] matriz,boolean[][] cores, int pos, int tam, No no)
    {
        int alt = this.AlturaNo(no);
        matriz[alt][pos] = no.chave;
        cores[alt][pos] = no.cor;
        if(no.dir!=null)
        {
            if (alt!=0)
                this.PreencheMatriz(matriz, cores,pos+((this.ProfundidadeTree())-alt), tam, no.dir);
            else
                this.PreencheMatriz(matriz,cores, pos+(((tam-pos)/2)), tam, no.dir);
        }
        if(no.esq!=null)
        {
            if (alt!=0)
                this.PreencheMatriz(matriz,cores, pos-((this.ProfundidadeTree())-alt), tam, no.esq);
            else
                this.PreencheMatriz(matriz,cores, pos-((pos/2)), tam, no.esq);
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
    
    private boolean isLeft(No no)
    {
        if(no.pai==null) return false;
        
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

    }
}
