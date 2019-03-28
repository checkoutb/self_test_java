package zoig;

public class A_January
{

    public static void main(String[] args)
    {
        TestSub ts = new TestSub();         // 这里父类构造器里的Say方法是 子类的Say方法，并且由于没有此时name还没被初始化，所以返回null。。。
                                            // debug 看父类构造器中this，是子类，而且此时子类的name还是null。
                                // before 是在super之后，子类构造器第二行之前 设置的。
        // 输出：
//        test.constructor
//        TestSub.say : null
//        testSub. constructor
//        TestSub.say :  before 
//        TestSub.say :  after 
        
        ts.Say();
        
    }
}

class Test
{
    public void Say()
    {
        System.out.println("Test.say");
    }
    
    public Test()
    {
        System.out.println("test.constructor");
        this.Say();
    }
}

class TestSub extends Test
{
    public void Say()
    {
        System.out.println("TestSub.say : " + name);
    }
    
    public TestSub()
    {
        super();
        System.out.println("testSub. constructor");
        this.Say();             // nams is before
        this.name = " after ";
        this.Say();
    }
    
    private String name = " before ";
}