package zoie;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.List;

public class H_August
{
    public static void main(String[] args)
    {
        List<Integer> arrayList = new ArrayList<>(5);
        out.println(arrayList.indexOf(null));       // size == 0...although new Object[5];...so -1..
//        out.println(arrayList.get(3));        // index out of bounds...
        
        arrayList = new ArrayList<>();
        out.println(arrayList.indexOf(null));
        
        
        
        
        
    }
}
