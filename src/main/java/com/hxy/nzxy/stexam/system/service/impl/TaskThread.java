/*
//注意这里是否添加@Scope("prototype")注解
@Component
@Scope("prototype")
public class TaskThread implements Runnable{

    protected int value=0；

    @Autowired
    private XxxService xxxService;

    //ThreadLocal 对象，单例模式下可以保证成员变量的线程安全和独立性。
    public ThreadLocal<Integer> valueLocal = new ThreadLocal < Integer > () {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    protected static final Logger LOG = LoggerFactory.getLogger(GpsTaskThread.class);

    @Override
    public final void run() {
        try {
            LOG.info(value+"");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void init(int Value) {
        this.value=Value;
    }
}*/
