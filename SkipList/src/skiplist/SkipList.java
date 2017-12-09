
package skiplist;


public class SkipList {


    public static void main(String[] args) {
       SkipListStruct skip = new SkipListStruct();
       
       skip.insert(10, "Treta");
       skip.insert(25, "Opa");
       skip.insert(15, "Texeira");
       skip.insert(30, "Microsoft");
       skip.insert(40, "Opa");
       skip.insert(75, "Nunca");
       skip.insert(5, "Rapaz");
       skip.insert(2, "Bing");
       skip.insert(35, "Google");
       System.out.println(skip.search(10));
       System.out.println(skip.removenode(15));
       System.out.println(skip.search(15));
       System.out.println(skip.getHeigth());
    }
    
}
