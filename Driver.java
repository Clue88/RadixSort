import java.util.*;
public class Driver{
  public static void main(String[]args){
    int maxValue = 1000000;
    int arrSize = 1000000;

    if(args.length==2){
      maxValue = Integer.parseInt(args[1]);
      arrSize = Integer.parseInt(args[0]);
    }else{
      System.out.println("Usage: java Driver ListSize MaxValue");
      System.exit(0);
    }

    SortableLinkedList nums = new SortableLinkedList();
    int[]arr = new int[arrSize];
    for(int i = 0; i < arrSize; i++){
      int value = (int)(Math.random()*maxValue);
      nums.add(value);
      arr[i]=value;
    }

    long start = System.currentTimeMillis();
    Radix.radixSortSimple(nums);
    double radixTime = (System.currentTimeMillis()-start)/1000.0;

    start = System.currentTimeMillis();
    Arrays.sort(arr);
    double builtinTime = (System.currentTimeMillis()-start)/1000.0;


    boolean correct =true;
    for(int i = 0; i < arr.length; i++){
      if( arr[i]!=nums.remove(0)){
        correct = false;
      }
    }

    if(correct){
      System.out.println("List size: "+arrSize);
      System.out.println("Largest value: "+(maxValue-1));
      System.out.println("Radix time: "+radixTime);
      System.out.println("Arrays.sort time: "+builtinTime);
    }else{
      System.out.println("FAIL! Please enter -1 for your values");
    }
  }
}
