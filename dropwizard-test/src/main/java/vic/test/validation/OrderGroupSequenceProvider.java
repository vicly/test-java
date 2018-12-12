package vic.test.validation;

import com.google.common.collect.Lists;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vic Liu
 */
public class OrderGroupSequenceProvider implements DefaultGroupSequenceProvider<Order> {

    AtomicInteger counter = new AtomicInteger(0);

    @Override
    public List<Class<?>> getValidationGroups(Order order) {

        int n = counter.incrementAndGet();

        System.out.println(n + ": order: " + order);

        List<Class<?>> groups = Lists.newArrayList();

        if (order != null && "card".equals(order.getType())) {
            System.out.println(n + " adding card group");
            groups.add(CardPayment.class);
        }

        if (order != null && "voucher".equals(order.getType())) {
            System.out.println(n + " adding voucher group");
            groups.add(VoucherPayment.class);
        }

        groups.add(Order.class);

        return groups;
    }
}
