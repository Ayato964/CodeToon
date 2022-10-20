package codetoon.util;

import java.util.function.Supplier;

public interface TickHelper {

        public abstract <T> void tick(@IsTick T entity);

}
