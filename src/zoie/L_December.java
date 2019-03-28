package zoie;

import java.io.PrintStream;
import java.util.function.Consumer;

public class L_December
{

    public static void main(String[] args)
    {
        Consumer<String> fun = System.out::println;
        fun.accept("hello, world");
        
        PrintStream ps1 = System.out;
        Consumer<String> fun2 = ps1::println;
        fun2.accept("hello, java");
        
        
        
        
        
    }

}
