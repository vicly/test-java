package vic.test.spring.hello.tmp;

/**
 * @author Vic Liu
 */
public class BeanA {
    private BeanB b;

    public BeanA(BeanB b) {
        this.b = b;
    }

    public BeanB getB() {
        return this.b;
    }
}
