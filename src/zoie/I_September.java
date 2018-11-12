package zoie;

import java.util.Arrays;
import java.util.List;

public class I_September
{

    public static void main(String[] args)
    {
        int i = 1;
        fun1(i);
        System.out.println(i);
        
        fun2(i);
        System.out.println(i);
        
        Integer i2 = 1;
        fun1(i2);
        System.out.println(i2);
        
        fun2(i2);
        System.out.println(i2);
        
        zoie.Node n1 = new zoie.Node(1);
        I_September iSep = new I_September();
        
        fun4(n1);
        System.out.println(n1.i);
        
        String s = "asd";
        fun5(s);
        System.out.println(s);
        
        int[] iArr = new int[3];
        fun6(iArr);
        System.out.println(iArr[1]);
        
        String[] strArr = {"aa", "bb", "cc"};
        List<String> list = Arrays.asList(strArr);
        for (String str : list)
        {
            System.out.println(str);
        }
        
        strArr = new String[5];
        strArr[4] = "zz";
        String[] strArr2 = list.toArray(strArr);
        for (String string : strArr)
        {
            System.out.print(string + ", ");
        }
        System.out.println();
        for (String string : strArr2)
        {
            System.out.print(string + ", ");
        }
        System.out.println("\n" + strArr + ", " + strArr2);
        
        strArr = new String[1];
        strArr2 = list.toArray(strArr);
        for (String string : strArr2)
        {
            System.out.print(string + ", ");
        }
        System.out.println(strArr + ", " + strArr2);
    }
    
    private static void fun6(int[] arr)
    {
        arr[1] = 111;
    }
    
    private static void fun5(String s)
    {
        s += "zzz";
//        System.out.println(s);
    }
    
    private static void fun4(zoie.Node n1)
    {
        n1.i++;
    }
    
    private static void fun1(int i)
    {
        i++;
    }
    
    private static void fun2(Integer i)
    {
        i++;
    }
    
    private void fun3(Integer i)
    {
        Node n2 = new Node(2);              // 非static方法中能新建Node，毕竟非static方法依赖于I_September对象。
        zoie.Node n3 = new zoie.Node(3);        // right
    }
    
    private class Node                  // 同名的话，main中访问的是这个。..  zoie.Node 就是 外面那个Node
    {
        int i;
        
        public Node(int i)
        {
            this.i = i;
        }
    }
}

                    // 这个Node 只能用 public abstract final 来修饰。。那为什么上面哪个可以private。。
                    // 应该是 上面的Node依赖于 I_September 吧。
class Node          // 放I_September中的话，main里访问不到。可能是Node依赖于I_September，但是main中还没有I_September的实例
{
    int i;
    
    public Node(int i)
    {
        this.i = i;
    }
}
