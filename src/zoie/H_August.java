package zoie;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Date;
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
        
        
        int a = 011;
        out.println(a);
        a = 0x11;
        out.println(a);
        
        out.format("%x\n", a);
        out.format("%o\n", a);
        out.format("%d\n", a);
        out.println(Integer.toBinaryString(a));
        
        
        Integer i31 = 1;
        Integer i32 = 1;
        out.println(i31 == i32);
        out.println(i31.hashCode() + ", " + i32.hashCode());        // 代码里直接返回value值
        
        String str36 = "1";
        String str38 = "1";
        out.println(str36.hashCode() + ", " + str38.hashCode());        // h = 31 * h + val[i];   "1"的hashCode就是字符1的ascii码
        
        // "11"的就应该是 49×31 + 49 = 49×32
        out.println("11".hashCode());
        
        out.println(((Object) str36).hashCode());       // Object的hashCode是native方法; 但是这里怎么确定用String的hashCode呢？
        
        
        Date date40 = new Date();
        out.println(date40.hashCode());         // 直接毫秒数long的前32位和后32位异或
        
        int[] arr50 = {1,2,3,4};
        int i51 = 1;
        arr50[i51] = arr50[++i51];      // arr50[1] = arr50[2];    优先级[] > ++ > = 
        out.println(i51);
        out.println(arr50[i51 - 1] + ", " + arr50[i51]);
        
        int[] arr56 = {1,2,3,4};
        int i57 = 1;
        arr56[i57] = arr56[i57++];      // arr[1] = arr[1]; i57++;
        out.println(i57 + ", " + arr56[i57 - 1] + ", " + arr56[i57]);
        
        int[] arr61 = {1,2,3,4};
        int i62 = 1;
        arr61[i62++] = arr61[i62];      // arr61[i62]; i62++; arr61[i62]; =
        out.println(arr61[i62 - 1] + ", " + arr61[i62]);
        
        int[] arr66 = {1,2,3,4};
        int i67 = 1;
        arr66[++i67] = arr66[i67];      // i67++; other...       
                                                // 像这种  高优先级 低优先级 高优先级的 运算方式，两个高优先级是谁先？
                                                // 结合性是 高优先级 高优先级 这种情况。。现在看来 高 低 高 应该是 从左到右。
        out.println(arr66[i67 - 1] + ", " + arr66[i67]);
        
        int i73 = 1;
        int i74 = 1;
        out.println((++i73) > (++i73));     // false
        out.println((++i74) < (++i74));     // true
                                            // 也是 高 低 高，并且++的结合性是 右->左，，，根据print的结果，前者小于后者，
                                            // 所以是 前者先运算的。 和结合性无关。，，，[]的结合性是 左到右。
                                            // 所以无论结合性是 左到右，还是右到左，都不影响 高 低 高 中2个高的运算顺序，高低高都是左到右
        
                // 次高 低 高，这种没有意义，因为 低的存在。导致。。找不出例子。。。没有结论。 
        
        int[] arr82 = {111,112,113,114,115,116};
        int i83 = 1;
        
        
/*******************************************
 *******************************************/
        arr82[i83++] = i83 << 1;            // ...arr[1] = 4...说明是先++，再<<... 这个++动作是 [] 完成后触发的？
/*******************************************
 *******************************************
 *******************************************/
        
        
        for (int i : arr82)
        {
            out.print(i + ", ");
        }
        
        int[] arr89 = {111,112,113,114,115,116};
        int i90 = 1;
        
        
        int[] arr95 = {1,2,3,4,5};
        int i96 = 1;
        
        int i98 = 4;
        int i99 = i98 << (1 - i98) << 2;
        out.println("\n" + i99 + ", " + i98);
        
        int i102 = 4;
//        i102 = i102 << -3 << 2;
        i102 = i102 << -3;      // 等于 循环 右移 3位。。。循环循环。。右侧出来的数字放到最左侧去。
        out.println(i102);
        i102 <<= 2;
        out.println(i102);
        
        int i109 = 4;
        i109 = i109 >> 3;       // 也是简单右移。。。带符号右移，，就是 高位补符号位
//        i109 >>= 3;         // 不是循环右移。简单的右移，直接抛弃
        out.println(i109);
        
        int i114 = 4;
        i114 = i114 >>> 3;      // 也是简单的右移啊。。无符号右移。。。高位补0... 没有 <<<
        out.println(i114);
        
        int i118 = Integer.MAX_VALUE;
        // 。。。 bit太长，没注意到 下面2个返回31个(应该是，没有数，比Min的bit少1位)。。。前导0不显示。。。。
        out.println(Integer.toBinaryString(i118));          // 11111...1111         31
        out.println(Integer.toBinaryString(i118 - 1));      // 1111...11110         31
        out.println(i118 << 3);             // -8
        out.println(Integer.toBinaryString(i118 << 3));     // 1111..111000
        // 01111111[31个1];  <<3;  11...1000 反码: 10...0111 ; +1 : 100...1000; 就是-8。。
        // 这样的话， << 就是简单左移，直接抛弃。低位补0.
        
        out.println(i118 >> 3);
        out.println(Integer.toBinaryString(Integer.MIN_VALUE));         // 10000...0000
        out.println(Integer.toBinaryString(Integer.MIN_VALUE + 1));     // 1000...0001
        
        out.println(Integer.toBinaryString(1023));          // 1111111111
        out.println(Integer.toBinaryString(Integer.MAX_VALUE / 2));
        
        // 高位补符号位0(>>) 或 0(>>>)
        out.println(Integer.toBinaryString(Integer.MAX_VALUE >> 12));       // same as below
        out.println(Integer.toBinaryString(Integer.MAX_VALUE >>> 12));      // same as above
        
        // MIN_VALUE 就是 符号位1,其他全0.。。。MIN_VALUE + 1 是符号位1,最低位1,其他全0.
        // 这个高位补符号位就是1.
        out.println(Integer.toBinaryString(Integer.MIN_VALUE >> 12));       // different from below
        
        // 这个高位补0.
        out.println(Integer.toBinaryString(Integer.MIN_VALUE >>> 12));
        
        
        
        
    }
    
    
}
