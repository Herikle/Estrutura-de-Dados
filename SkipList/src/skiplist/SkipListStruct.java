
package skiplist;

import java.util.Random;

public class SkipListStruct {
    private Node head;
    private Node tail;
    private final Node basepos = new Node(true,false);
    private final Node baseneg = new Node(false,true);
    private int heigth = 0;
    private int size = 0;
    public SkipListStruct()
    {     
        baseneg.setRight(basepos);
        basepos.setLeft(baseneg);
        head = baseneg;
        tail = basepos;
    }
    public void insert(int key, Object item)
    {
        Node newNode = new Node(false,false);
        newNode.setKey(key);
        newNode.setElement(item);
        Random rand = new Random();
        int alt = 0;
        int randnum;
        do{
            randnum = rand.nextInt(2);
            if(randnum==1) alt++;
        }while(randnum !=0);
        insert(alt,newNode);
        size++;
    }
    public Object search(int key)
    {
        Node toreturn = search(key, head);
        if (toreturn!=null) return toreturn.getElement();
        
        return "Chave não encontrada";
    }
    
    public int getHeigth()
    {
        return this.heigth+1;
    }
    public Object removenode(int key)
    {
        Node toremove = search(key, head);
        Object toreturn;
        if (toremove!=null)
        {
            toreturn = toremove.getElement();
            removenode(toremove);
            size--;
            return toreturn;
        }
        
        return "Chave não encontrada.,,,";
        
    }
    public void PrintSkipList()
    {
        int x = this.heigth+1;
        int y = this.size+2;
        Object[][] matrix = new Object[x][y];
        for(int xx = 0; xx < x;xx++)
        {
            for(int yy = 0; yy < y; yy++)
            {
                matrix[xx][yy] = String.format("%5c", '-');
            }
        }
        
       Node current = this.baseneg;
       
       for(int yy = 0;yy<y;yy++)
       {
           int xx = x-1;
           Node nextmove = current.getRight();
           while(current!=null)
           {
               if(current.isNegInfinity())
               {
                   matrix[xx][yy] = String.format("%5s", "-oo");
               }else if(current.isPosInfinity())
               {
                   matrix[xx][yy] = String.format("%5s", "+oo");
               }else
               {
                   matrix[xx][yy] = String.format("%5d", current.getKey());
               }
               xx--;
               current = current.getAbove();
           }
           current = nextmove;
       }
        
        for(int xx = 0; xx < x;xx++)
        {
            for(int yy = 0; yy < y; yy++)
            {
                System.out.print(matrix[xx][yy]);
            }
            System.out.println();
        }
    }
    //--------------------------------PRIVATE METHODS ----------------------------------------
    private void removenode(Node toremove)
    {
       if(toremove!=null)
       {
           toremove.getLeft().setRight(toremove.getRight());
           toremove.getRight().setLeft(toremove.getLeft());
           toremove.setLeft(null);
           toremove.setRight(null);
           Node aux = toremove.getBelow();
           toremove.setAbove(null);
           toremove.setBelow(null);
           removenode(aux);
       }
    }    
    private Node search(int key, Node current)
    {
        if (current != null)
        {
            if(!current.getRight().isPosInfinity())
            {
                if(key>current.getRight().getKey())
                {
                    return search(key, current.getRight());
                }
                else if(key<current.getRight().getKey())
                {
                    return search(key, current.getBelow());
                }else
                    return current.getRight();
            }
            return search(key, current.getBelow());
        }
        return null;
    }
    
    private void insert(int alt, Node newNode)
    {
        int altaux = alt;        
        while(altaux>=this.heigth)
        {
            head.setAbove(new Node(false,true));
            tail.setAbove(new Node(true,false));
            head.getAbove().setBelow(head);
            tail.getAbove().setBelow(tail);
            head = head.getAbove();
            tail = tail.getAbove();
            head.setRight(tail);
            tail.setLeft(head);
            altaux--;            
        }
        if(alt>=this.heigth) this.heigth = alt+1;
        
        Node here = whereInsert(newNode);
        newNode.setLeft(here);
        newNode.setRight(here.getRight());
        here.getRight().setLeft(newNode);
        here.setRight(newNode);       
        while(alt>0)
        {
            Node reference = newNode.getLeft();
            while(reference.getAbove()==null)
            {
                reference = reference.getLeft();
            }
            reference = reference.getAbove();
            Node newNodeAbove = new Node(false,false);
            newNodeAbove.setKey(newNode.getKey());
            newNodeAbove.setElement(newNode.getElement());
            newNodeAbove.setBelow(newNode);
            newNode.setAbove(newNodeAbove);
            newNodeAbove.setLeft(reference);
            newNodeAbove.setRight(reference.getRight());
            reference.getRight().setLeft(newNodeAbove);
            reference.setRight(newNodeAbove);
            newNode = newNodeAbove;   
            alt--;
        }       
    }
    
    private Node whereInsert(Node here)
    {
        return whereInsert(here,head);      
    }
    
    private Node whereInsert(Node here, Node current)
    {
        if(current.getRight().compareTo(here)==1)
        {
            if(current.getBelow()==null)
            {
                return current;
            }
            return whereInsert(here, current.getBelow());
        }
        return whereInsert(here, current.getRight());
    }
}
