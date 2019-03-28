package zoie;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Sets;

public class K_November
{

    public static void main1(String[] args)
    {
        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder().maximumSize(5).expireAfterWrite(5L, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, Integer>()
                {
                    @Override
                    public void onRemoval(RemovalNotification<String, Integer> notification)
                    {
                        System.out.println("delete " + notification.getKey());
                    }
                }).build(new CacheLoader<String, Integer>()
                {
                    @Override
                    public Integer load(String key) throws Exception
                    {
                        return Integer.valueOf(key) + 1000;
                    }
                });
        
        for (int i = 11; i < 18; i++)
        {
            cache.put(String.valueOf(i), i + 500);
            System.out.println(cache.size());
            try
            {
                System.out.println(cache.get(String.valueOf(i - 1)));
            } catch (ExecutionException e)
            {
                System.out.println("error, " + i);
            }
        }
        
        for (int i = 19; i > 9; i--)
        {
            try
            {
                System.out.println(cache.get(String.valueOf(i)));
            } catch (ExecutionException e)
            {
                System.out.println(i + " .. error");
            }
        }
        
        try
        {
            System.out.println("sleep");
            Thread.sleep(6000L);
        } catch (InterruptedException e)
        {
            System.out.println("fail to sleep 6s.");
        }
        
        System.out.println("after sleep, " + cache.size());         // 5
        
        cache.put("1", 11);
        System.out.println("after put 1, " + cache.size());
        
        int i = 0;
        while (i < 7)
        {
            i++;
            try
            {
                Thread.sleep(1000L);
            } catch (InterruptedException e)
            {
                System.out.println(" ??? ");
            }
            try
            {
                System.out.println(i + " .. " + cache.get("1"));
            } catch (ExecutionException e)
            {
                System.out.println(" !!! ");
            }
        }
        System.out.println(cache.size());           // 1
        cache.invalidate("1");
        System.out.println(cache.size());           // 0
        
        System.out.println(cache.getIfPresent("2"));        // 没有异常。异常是CacheLoader.Load抛出的。只要那里不抛，get也不需要try
        
        try
        {
            System.out.println(cache.get("3", new Callable<Integer>()
            {
                @Override
                public Integer call() throws Exception          // 比CacheLoader.Load优先。
                {
                    return 22222;
                }
            }));
        } catch (ExecutionException e)
        {
            System.out.println(" !~! ");
        }
        
        
        // expire应该是延迟执行的。每次put，get之前会刷新一遍，所以不执行这些动作的话，size永远不会变。可以用removelListener
        // 上面的cache，put，get都会刷新缓存，如果get没有对应的数据，就用load生成一条，并缓存+返回。
        // 不知道有没有 get不到就返回null的。load直接return null，会报错。可以用getIfPresent
        
    }

    
    public static void main2(String[] args)
    {
//        int[] arr1 = {1,2};
//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr1, 0, 1)));
        
        int n = 15;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));
        
        Integer[] arr2 = new Integer[n];
        
//        System.arraycopy(arr, 0, arr2, 0, n);
//        Sort.<Integer>BubbleSort(arr2);
//        System.out.println(Arrays.toString(arr2));
//        
//        System.arraycopy(arr, 0, arr2, 0, n);
//        Sort.<Integer>InsertionSort(arr2);
//        System.out.println(Arrays.toString(arr2));
        
        System.arraycopy(arr, 0, arr2, 0, n);
        Instant instant = Instant.now();
        Sort.<Integer>QuickSort(arr2);
        Instant instant2 = Instant.now();
        System.out.println(Arrays.toString(arr2));
        System.out.println(Duration.between(instant2, instant).toMillis());
        
        System.arraycopy(arr, 0, arr2, 0, n);
        Sort.<Integer>InsertionSort(arr2);
        System.out.println(Arrays.toString(arr2));
        
        System.arraycopy(arr, 0, arr2, 0, n);
//        Sort.ThreadSort(arr2);
        Sort.ShellSort(arr2);
        System.out.println(Arrays.toString(arr2));
        
        System.arraycopy(arr, 0, arr2, 0, n);
        Sort.MergeSort(arr2);
        System.out.println(Arrays.toString(arr2));
        
        
//        try
//        {
//            arr2.wait();        // java.lang.IllegalMonitorStateException
//        } catch (InterruptedException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        
        try
        {
            Thread.sleep(3000);
            System.out.println("==================================");
        } catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                } catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("QQQQ");
            }}).start();
        
        new Thread() {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                    synchronized (arr2)
                    {
                        arr2.notify();
                    }
                    System.out.println("<<<");
                } catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        }.start();
        
        Integer a = 0;
        synchronized (arr2)
        {
            try
            {
                System.out.println("...");
                arr2.wait();
                System.out.println(">>>");
            } catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    private synchronized void Test1()
    {
        
    }
    
    private void Test2()
    {
        Integer a = 1;
        synchronized (a)
        {
            
        }
        synchronized (this)
        {
            
        }
    }
    
    public static void main3(String[] args)
    {
//        Method[] mArr = Sort.class.getMethods();
//        for (Method m : mArr)
//        {
////            System.out.println(m.getParameterTypes());
//            System.out.println(m.getName());
//            System.out.println(m.toString());
//        }
        
        String[] methodName = {"BubbleSort", "SelectionSort", "QuickSort", "MergeSort", "InsertionSort", "ShellSort", "", "", ""};
        Set<String> methodNameSet = Sets.newHashSet(methodName);
        List<Method> methodList = new LinkedList<>();
        
//        for (Method m : mArr)
//        {
//            if (methodNameSet.contains(m.getName()))
//            {
//                methodList.add(m);
//            }
//        }
        
//        System.out.println(methodList);
        
        for (String m : methodName)
        {
            if (StringUtils.isEmpty(m))
                break;
            try
            {
//                methodList.add(Sort.class.getMethod(m));
//                methodList.add(Class.forName(Sort.class.getName()).getMethod(m, Integer[].class));
//                methodList.add(Sort.class.getMethod(m, Object[].class));      // Object[].class,Object.class都不行
                methodList.add(Sort.class.getMethod(m, Object[].class));    // Object[].class是报java.lang.IllegalArgumentException: argument type mismatch
                                                                                // Object.class是找不到方法。
                                                // ...写了methodNameSet之后才发现Object[].class是能获得方法的，IllegalArgumentException是invoke的时候报错。
                                    // 泛型直接用Object代替就可以反射获得方法了。
                
                Class s = int.class;        // 存在int.class....class不是Object，自然是存在的。Integer既可以作为Integer.class, 又可以作为new Integer(1);
                                        // int只能int.class, 不能new。
            } catch (NoSuchMethodException | SecurityException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        Instant instant1 = null;
        Instant instant2 = null;
        Random random = new Random();
        
        
        // =============1============
//        // --------------------1--------------------
////        int bound = 100;
//////        int len = 100_0000;
//////        int len = 1000;             // MergeSort 最快。BubbleSort最慢。Shell大部分时间(>80%)倒数第二。其他平均下来差不多。
//////        int len = 10000;        // MergeSort 最快，基本甩其他排序一个数量级(10)。Bubble最慢。。Shell稳定倒数第二。其他几个差距不大(Select快，Quick中，插入慢)
////        
////        
//////        int len = 10_0000;      
////// BubbleSort : 14730; SelectionSort : 7538; MergeSort : 93; InsertionSort : 5665; ShellSort : 11892
//////        BubbleSort : 16275
//////        SelectionSort : 4336
//////        MergeSort : 86
//////        InsertionSort : 5713
//////        ShellSort : 11252
////// Quick：StackOverflowError。。。Bubble最慢，Shell倒数第二，Merge最快，甩其他2个数量级(100)。。其他差距不大(Select快于插入)
////        // Shell优化后不知道能不能吊打Merge。。。不过Merge的空间是O(n)的。
////        
//////        int len = 100_0000;   // Merge: 441...其他的没反映，时间太长，放弃了。。。
////        
//////        int len = 1000_0000;        // Merge: 4596
////        int len = 1_0000_0000;      // new arr2的时候 OutOfMemoryError: Java heap space
//        // ----------------------1-------------------
//        
//        // ----------------------2-------------------
////        int bound = 10000;
////        int len = 10000;        // merge >>>>>>>> quick ~> select > insert >>> shell >>>> bubble
////                            // 但是quick有时(<10%)会stackoverflow...
//        // ----------------------2-------------------
//        
////        // ----------------------3-------------------
////        int bound = 100_0000;
////        int len = 10000;
////                // quick必然stackoverflow, 无法理解，数目相同，quick栈溢出的几率为什么不同。quick和数值的分布没有关系吧。
////            // Merge >>>>>>>>> select >>> insert >>>> shell > bubble
////        // ----------------------3-------------------
//        
//        int[] arr = new int[len];
//        instant1 = Instant.now();
//        for (int i = 0; i < len; i++)
//        {
//            arr[i] = random.nextInt(bound);
//        }
//        instant2 = Instant.now();
////        System.out.println(instant1 + ", " + instant2);
//        System.out.println("init : " + Duration.between(instant1, instant2).toMillis());
        // ==============1===============
        
        
        // 上面是纯随机，这里不是全部随机。
        // ==============2==============
//        int len = 10000;
//        int bound = 1000;
//        int bound2 = 10;
//        // merge >>>>>> quick ~> select >> insert >>> bubble >> shell
//        // quick可能出现栈溢出。。
//        
//        int[] arr = new int[len];
//        for (int i = 0; i < len; i++)
//        {
//            arr[i++] = random.nextInt(bound);
//            arr[i] = arr[i - 1] + random.nextInt(bound2);
//        }
        // ==============2==============
        
        // ================3==============
//        int len = 10000;
//        int bound = 1000;
//        int bound2 = 10;
////        int step = 100;     // merge >>>>> quick > select >> insert ~> bubble >>> shell
//        int step = 1000;        // quick大几率栈溢出  merge >>> select >> insert >> bubble > shell
//        
//        int arr[] = new int[len];
//        for (int i = 0; i < len;)
//        {
//            arr[i++] = random.nextInt(bound);
//            int j = 0;
//            while (j < step && i < len)
//            {
//                arr[i] = arr[i - 1] + random.nextInt(bound2); 
//                j++;
//                i++;
//            }
//        }
        // ================3==============
        
        // ===============4==============
        int len = 10000;
        int bound = 1000;
        int bound2 = 10;
//        int step = 100;     // merge >>>>>> select >> insert > bubble >>> shell
//        int step = 1000;     // merge >>> select >> insert >> bubble >>> shell
        int step = 10000;       // merge >>> select > insert >> shell >> bubble
        
        int arr[] = new int [len];
        for (int i = 0; i < len;)
        {
            arr[i++] = random.nextInt(bound) + 10000;
            int j = 0;
            while (j < step && i < len)
            {
                arr[i] = arr[i - 1] - random.nextInt(bound2);
                j++;
                i++;
            }
        }
        // ===============4==============
        
        
        Integer[] arr2 = new Integer[len];
        for (int i = 0; i < len; i++)
        {
            arr2[i] = arr[i];
        }
        
        int i = 0;
        for (Method m : methodList)
        {
            try
            {
                if ("QuickSort".equals(m.getName()) 
//                        || "BubbleSort".equals(m.getName())
                        )
                    continue;
//                
//                if (!"MergeSort".equals(m.getName()))
//                    continue;
                
                instant1 = Instant.now();
//                m.invoke(null, (Object[]) arr);           // int[] cannot convert to Object[]
//                m.invoke(null, arr);                // argument type mismatch
//                m.invoke(null, arr2);           // wrong number of arguments
//                System.out.println(m.getParameterCount() + ", " + Arrays.toString(m.getParameters()) + ", " + Arrays.toString(m.getGenericParameterTypes()));
//                m.invoke(null, (Object[]) arr2);    // wrong number of arguments
                m.invoke(null, new Object[] {arr2});        // ok.... 数组的数组 和 数组 的区别。。
                               // 是不是invoke的...不定长参数导致，(Object[]) arr2当成len个参数。而不是1个。
                instant2 = Instant.now();
                System.out.println(m.getName() + " : " + Duration.between(instant1, instant2).toMillis());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i++;
        }
    }
    
    
    // 4
    public static void main(String[] args)
    {
        int a = 1024;
        int b = Math.negateExact(a);
        int c = 0;
        
        System.out.println(a >> 1);
        System.out.println(b >> 1);
        System.out.println(a >>> 1);        // 带符号，其他都是无符号。
        System.out.println(b >>> 1);        // 2147483136
        System.out.println(a << 1);
        System.out.println(b << 1);
//        System.out.println(a >> 1);
//        System.out.println(a >> 1);
        
    }
    
    
    
}

// descend

class MyThread<T> implements Runnable
{
    private T i;
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(((int) i) * 10 + 30);
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(i);
    }
    
    public MyThread(T arr)
    {
        this.i = arr;
    }
}

// ascend
class Sort
{
    
    // 发明的真鬼才。。这就是标准的O(n)排序啊。。。呃。不过实际运行时间太慢了。sleep的时间够其他排序排n个来回了。。
    public static <T> void ThreadSort(T[] arr)
    {
        for (int i = 0; i < arr.length; i++)
            new Thread(new MyThread<>(arr[i])).start();
        
//            new Thread(new Runnable()         // 好像没有办法写匿名类实现某个接口啊。。必然，实现接口必然要类名，所以没办法，只能写方法内部类。
//                    {
//                        @Override
//                        public void run()
//                        {
//                            // TODO Auto-generated method stub
//                            
//                        }
//                    }).start();
//            new Thread()
//            {
//                @Override
//                public synchronized void start()
//                {
//                    try
//                    {
//                        Thread.sleep(i + 2);          // 这个线程看不到i。。。
//                    } catch (InterruptedException e)
//                    {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
    }
    
    private static <T> void swap(T[] arr, int i, int j)
    {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static <T> int compare(T a, T b)
    {
        if (a instanceof Integer)
        {
            return ((Integer) a == (Integer) b ? 0 : ((Integer) a > (Integer) b ? 1 : -1));
        }
        return 0;
    }
    
    public static <T> void BubbleSort(T[] arr)
    {
        for (int i = 0; i < arr.length; i++)
//            for (int j = i + 1; j < arr.length; j++)      // 这个更像选择排序。。i位放最低的值。
//               if (compare(arr[i], arr[j]) > 0)
            for (int j = 0; j < arr.length - 1 - i; j++)
                if (compare(arr[j], arr[j + 1]) > 0)
                    swap(arr, i, j);
    }
    
    public static<T> void SelectionSort(T[] arr)
    {
        int t = 0;
        for (int i = 0; i < arr.length; i++)
        {
            t = i;
            for (int j = i + 1; j < arr.length; j++)
                if (compare(arr[j], arr[t]) < 0)
                    t = j;
            if (t != i)
                swap(arr, t, i);
        }
    }
    
    public static<T> void QuickSort(T[] arr)
    {
        QuickSortRecursion2(arr, 0, arr.length - 1);
    }
    
    private static <T> void QuickSortRecursion2(T[] arr, int s, int e)
    {
        if (s >= e)
            return;
        T flag = arr[e];
        int ss = s;
        int ee = e - 1;             // 
        while (ss <= ee)
        {
            if (compare(arr[ss], flag) > 0)
            {
                swap(arr, ss, ee);
                ee--;
            }
            else
            {
                ss++;
            }
        }
        swap(arr, ss, e);               // 
        QuickSortRecursion2(arr, s, ss - 1);
        QuickSortRecursion2(arr, ss + 1, e);
    }
    
    @Deprecated
    private static<T> void QuickSortRecursion(T[]arr, int s, int e)
    {
        if (s == e)
            return;
        T a = arr[s];
        T b = arr[(s + e) / 2];
        T c = arr[e];
        T flag;
        if (compare(a, b) > 0)
            if (compare(a, c) > 0)
                if (compare(b, c) > 0)
                    flag = b;
                else
                    flag = c;
            else
                flag = a;
        else
            if (compare(a, c) > 0)
                flag = a;
            else
                if (compare(b, c) > 0)
                    flag = c;
                else
                    flag = b;
        
        int ss = s;
        int ee = e;
        
        while (ss != ee)
        {
            if (compare(arr[ss], flag) >= 0)
            {
                
            }
            else
            {
                
            }
            if (compare(arr[ee], flag) >= 0)
            {
                
            }
            else
            {
                
            }
        }
    }
    
    public static<T> void MergeSort(T[] arr)
    {
        MergeSortRecursion(arr, 0, arr.length - 1);
    }
    
    private static<T> T[] MergeSortRecursion(T[] arr, int s, int e)
    {
        if (s == e)
            return Arrays.copyOfRange(arr, s, s + 1);
        int m = (s + e) / 2;
        T[] arr1 = MergeSortRecursion(arr, s, m);
        T[] arr2 = MergeSortRecursion(arr, m + 1, e);
        int i1 = 0;
        int i2 = 0;
        for (int i = s; i <= e; i++)
        {
            arr[i] = (i1 >= arr1.length ? arr2[i2++] : (i2 >= arr2.length ? arr1[i1++] : (compare(arr1[i1], arr2[i2]) < 0 ? arr1[i1++] : arr2[i2++])));
        }
        return Arrays.copyOfRange(arr, s, e + 1);
    }
    
    public static<T> void InsertionSort(T[] arr)
    {
        int j = 0;
        int k = 0;
        T temp;
        for (int i = 1; i < arr.length; i++)
        {
            for (j = 0; j <= i; j++)
            {
                if (compare(arr[i], arr[j]) < 0)
                    break;
            }
            if (j > i)
                continue;
            temp = arr[i];
            for (k = i; k > j; k--)
            {
                arr[k] = arr[k - 1];
            }
            arr[j] = temp;
        }
    }
    
    
    /*
    
    #define MAXNUM 10
    
    void main()
    {
        void shellSort(int array[],int n,int t);//t为排序趟数
        int array[MAXNUM],i;
        for(i=0;i<MAXNUM;i++)
            scanf("%d",&array[i]);
        shellSort(array,MAXNUM,(int)(log(MAXNUM+1)/log(2)));//排序趟数应为log2(n+1)的整数部分
        for(i=0;i<MAXNUM;i++)
            printf("%d ",array[i]);
        printf("\n");
    }
    
    //根据当前增量进行插入排序
    void shellInsert(int array[],int n,int dk)
    {
        int i,j,temp;
        for(i=dk;i<n;i++)//分别向每组的有序区域插入
        {
            temp=array[i];
            for(j=i-dk;(j>=i%dk)&&array[j]>temp;j-=dk)//比较与记录后移同时进行
                array[j+dk]=array[j];
            if(j!=i-dk)
                array[j+dk]=temp;//插入
        }
    }
     
    //计算Hibbard增量
    int dkHibbard(int t,int k)
    {
        return (int)(pow(2,t-k+1)-1);
    }
     
    //希尔排序
    void shellSort(int array[],int n,int t)
    {
        void shellInsert(int array[],int n,int dk);
        int i;
        for(i=1;i<=t;i++)
            shellInsert(array,n,dkHibbard(t,i));
    }
    */
    
    // 分组 插入排序
    public static<T> void ShellSort(T[] arr)
    {
        int gap = arr.length;
        int i, j, k;
        T temp;
        while (gap > 1)
        {
            gap /= 2;
            for (i = 0; i < gap; i++)
            {
                for (j = i + gap; j < arr.length; j += gap)
                {
                    for (k = i; k < arr.length; k += gap)
                    {
                        if (compare(arr[j], arr[k]) < 0)
                            break;
                    }
                    if (k > j)
                        continue;
                    temp = arr[j];
                    for (int l = j; l > k; l -= gap)
                    {
                        arr[l] = arr[l - gap];
                    }
                    arr[k] = temp;
                }
            }
        }
    }
    
    public static<T> void HeapSort(T[] arr)
    {
        // https://www.cnblogs.com/dolphin0520/p/3938991.html
        // TaskQueue.fixDown + 评论，数组实现最小堆。
    }
    
    public static<T> void RadixSort(T[] arr)
    {
        
    }
    
    public static<T> void CountingSort(T[] arr)
    {
        
    }
    
    public static<T> void BucketSort(T[] arr)
    {
        
    }
}



