
package skiplist;

import java.util.Random;

public class SkipListStruct {
    private Node head;
    private Node tail;
    private final Node basepos = new Node(true,false);
    private final Node baseneg = new Node(false,true);
    private int heigth=0;
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
    }
    
    public int getHeigth()
    {
        return this.heigth;
    }
    //--------------------------------PRIVATE METHODS ----------------------------------------
    
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
