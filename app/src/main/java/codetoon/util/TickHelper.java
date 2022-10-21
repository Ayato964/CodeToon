package codetoon.util;

import java.util.function.Supplier;

public interface TickHelper {

        public abstract <T extends IsTick> void tick( T entity);

}
