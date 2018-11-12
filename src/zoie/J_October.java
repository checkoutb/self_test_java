package zoie;

import static java.lang.System.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.stream.Stream;



public class J_October
{
    enum EnumJ
    {
        AA, BB, CC
    }

    
    public static void main(String[] args)
    {
        List<String> list10 = new LinkedList<>();
        List<String> list12 = Arrays.asList("asd", "zxc", "qwe", "tyu", "hjk"); 
        list12.forEach(System.out::println);
        list12.forEach(a -> System.out.println(".. " + a));
        
        J_October jo = new J_October();
        list12.forEach(jo::fun1);
//        "aa" -> System.out.println("; ");
        
        Iterator<String> it = list12.iterator();
//        list12.add("iop");          // when java execute next line, java.lang.UnsupportedOperationException
        it.forEachRemaining(ele -> System.out.println(ele + "..."));
//        it.forEachRemaining(System.out::println);         // no print
        
        List<String> list27 = new ArrayList<>();
        List<String> list29 = new LinkedList<>();
        Deque<String> deq31 = new ArrayDeque<>();       // 循环数组 双端队列。
        Set<String> set34 = new HashSet<>();
        Set<String> set36 = new TreeSet<>();        // 有序集合
//        Set<String> set38 = new EnumSet<>();      // constructor is not public.
        Set<EnumJ> set40 = EnumSet.noneOf(EnumJ.class);      // or allOf(EnumJ.class)   // 包含枚举类型的集合
        Set<String> set41 = new LinkedHashSet<>();      // 记住元素插入次序的集合
        Queue<String> que44 = new PriorityQueue<>();        // 高效删除最小元素的集合
        Map<String, String> map48 = new HashMap<>();
        Map<String, String> map49 = new TreeMap<>();        // 有序映射表
        Map<EnumJ, String> map51 = new EnumMap<>(EnumJ.class);   
        Map<String, String> map57 = new EnumMap(EnumJ.class); // new EnumMap<>, Cannot infer type arguments for EnumMap<>.
                                                        // 可能因为前面的Map<String, String>。
        map51.put(EnumJ.BB, "ss");
        
        Map<String, String> map61 = new LinkedHashMap<>();      // 记住键值对插入顺序
        Map<String, String> map62 = new WeakHashMap<>();        // 值无用后，立刻被回收,不会因为这个map的存在而放弃回收。
        Map<String, String> map65 = new IdentityHashMap<>();    // 用==，而不是equal比较键
        
        ListIterator<String> it69 = list12.listIterator(list12.size());
        while (it69.hasPrevious())
        {
            System.out.println(it69.previous() + ".....it");
        }
        
        ListIterator<String> it75 = list12.listIterator(list12.size() - 1);
        System.out.println(it75.next());
        System.out.println(it75.previous());
        System.out.println(it75.previous());
        System.out.println(it75.previous());
        
        List<String> list81 = new ArrayList<>(list12);
        list81.iterator().next();
        
//        TreeMap<String, String> map84 = null;
//        map84.comparator();
        
        map48.put("111", "AAAA");
        map48.put("222", "ZZZ");
        map48.put("333", "TTTT");
        map48.putIfAbsent("333", "CCC");
        map48.putIfAbsent("444", "QQQ");
//        map48.merge("", value, remappingFunction)
        map48.forEach((kk, vv) -> System.out.println(kk + ", " + vv));
        
        out.println(map48.getOrDefault("aaa", "nullll"));
        
        Map map100 = new HashMap();
        map100.put(EnumJ.AA, "zzz");
        map100.forEach((k, v) -> out.println(k + "..." + v));       // right.
        
        Map map104 = new EnumMap(EnumJ.class);
//        map104.putIfAbsent("XSDX", 1);            // when forEach print, throw an exception:string cannot cast to java.lang.Enum
        map104.putIfAbsent(EnumJ.CC, "zzz");
        map104.forEach((k, v) -> out.println(k + " : " + v));
        
        Map<Integer, String> map109 = new HashMap<>();
        for (int i = 0; i < 10; i++)
        {
            map109.putIfAbsent(i, String.valueOf(i) + "...");
        }
        map109.forEach((k, v) -> out.println(k + " = " + v));
        map109.computeIfPresent(5, (k, v) -> v += "zzzzz");                       // present -> (k ,v)  absent -> v
//        map109.computeIfPresent(6, v -> "asd");       // error
        out.println(map109.getOrDefault(5, "nil"));
        out.println(map109.getOrDefault(100, "nilzz"));
        map109.computeIfAbsent(100, v -> "100!");       // just for initialize?
//        map109.computeIfAbsent(100, cc -> cc == "A");     // error
//        map109.computeIfAbsent(100, (k, v) -> "100");     // error
        map109.computeIfAbsent(101, kk -> "10!");
        map109.put(102, null);
        map109.computeIfAbsent(102, kv -> new J_October().fun2("!02"));     // null is absent
        out.println(map109.get(100));
        out.println(map109.get(101));
        out.println(map109.get(102));
        
//        map109.remove(key, value);
//        map109.remove(key);
        
        map109.merge(1, "tail", (v, v1) -> v += v1);        // v:original value; v1:2nd parameter ("tail")
        out.println(map109.get(1));
        map109.merge(2, "xx", (vvv, vv) -> vvv.concat(vv));     // 2nd parameter must value's class type.
        out.println(map109.get(2));
        map109.merge(3, "zzz", (v, vv) -> new J_October().fun2(v));     // fun1 is error. Cannot return a void result... "zzz" is useless..
        out.println(map109.get(3));
        map109.merge(4, "qqq", (v, vv) -> new J_October().fun2(vv));    // lost original value
        out.println(map109.get(4));                     // hello qqq
        
        long l141 = 1111_111_1;
        out.println(l141);      // 11111111
        
        LocalTime lt144 = LocalTime.now();
        out.println(lt144);
        LocalTime lt149 = LocalTime.of(1,3);        // LocalTime just hour minute second nano
        out.println(lt149);
//        out.println(ChronoUnit.YEARS.between(lt144, lt149));     // exception: unsupported unit: Years
        out.println(ChronoUnit.MINUTES.between(lt144, lt149));  // lt144 -> 10:11:43.575  print -548
        out.println(ChronoUnit.HOURS.between(lt149, lt144));        // 9
        
        LocalDate ld156 = LocalDate.now();
        LocalDate ld158 = LocalDate.of(2016, 5, 6);     // year, month, day.
        out.println(ld156 + ", " + ld158);
        out.println(ChronoUnit.YEARS.between(ld158, ld156));    // 2
        out.println(ChronoUnit.MONTHS.between(ld156, ld158));       // -29
        
        out.println(ChronoUnit.MONTHS.between(ld158, LocalDate.of(2016, 4, 3)));    // -1       是否满一个月，而不是单纯的月份差
        out.println(ChronoUnit.MONTHS.between(ld158, LocalDate.of(2016, 4, 7)));    // 0
        out.println(ld158);     // 5-6
        out.println(ld158.minusDays(2));        // 5-4        return a now object;
        out.println(ld158);     // 5-6
        
        List<Integer> list169 = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list169.add(i);
        list169.sort((Integer i1, Integer i2) -> {return i1 > i2 ? -1 : 1;});
        out.println(list169);
        List<String> list174 = new ArrayList<>();
        list174.add("NNNn");
        list174.add("KKKk");
        list174.forEach(a -> new J_October().new AAAImpl().funA(a));        // ..................
        
        int int179 = 0b111;     // 二进制
        int int180 = 0B111;
        System.out.println(int179 + ", " + int180);
        
        int int183 = 0b1_1_1;
        System.out.println(int183);
        
        // try with resource. 结束时自动关闭。resource必须是java.lang.AutoCloseable的子类对象
//        try (FileReader fr = new FileReader("a.txt"); FileWriter fw = new FileWriter("b.txt");)
//        {
//            int ch = 0;
//            while ((ch = fr.read()) != -1)
//            {
//                fw.write(ch);
//            }
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        
        String[] strArr202 = { "aaaa", "aaa", "aaaa", "aa", "aaaa" };
        long l203 = Stream.of(strArr202).filter(a -> a.length() > 3).count();       // parallelStream
        out.println(l203);
        Arrays.stream(strArr202);
        list174.stream();
        /*
         * 中间操作包括：map (mapToInt, flatMap 等)、 filter、distinct、sorted、peek、limit、skip、parallel、sequential、unordered。
         * 终止操作包括：forEach、forEachOrdered、toArray、reduce、collect、min、max、count、anyMatch、allMatch、noneMatch、findFirst、findAny、iterator。
         * */
        
        Instant ins213 = Instant.now();
        try
        {
            Thread.sleep(555);
        } catch (Exception e)
        {
            
        }
        Instant ins222 = Instant.now();
        Duration dur223 = Duration.between(ins222, ins213);
        out.println(ins213 + "\n" + ins222);
        out.println(dur223.toMillis());
        
        Map<String, String> map228 = new LinkedHashMap<>();
        map228.put("", "");
        map228.get("z");
        
        
        
        
        
    }
    
//    public static void main (String[] args) throws Exception
//    {
//        System.out.println("main exception");
//    }
    
//    public String fun3(String s)
//    {
//        return "fun3" + s;
//    }
//    
//    public String fun3(String s) throws Exception
//    {
//        return "fun3__" + s;
//    }
    
    public String fun2(String s)
    {
        return "hello " + s;
    }
    
    public void fun1(String s)
    {
        System.out.println("hello, " + s);
    }
    
    class AAAImpl implements AAA
    {
        @Override
        public void funA(String s)
        {
            out.println("funA : " + s);
        }
    }
    
    @FunctionalInterface
    interface AAA
    {
        void funA(String s);
//        void funB();      // 有且只能有一个抽象方法
//        void funC();
        
        default String funD()
        {
            return "hello";
        }
        
        static void funE() {}
        
        boolean equals(Object obj);         // Object的可以声明 不会算入抽象方法,因为所有的对象都是Object，都实现了Object的方法。
    }
    
    interface BBB
    {
        void funA();
        void funB();
        void funZ();
        
        default void funC() {}
        
        static void funD() {}
        
        boolean equals(Object obj);
    }
    
}
