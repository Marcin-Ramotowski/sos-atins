package pl.atins.sos.data.dao.util;

import jakarta.persistence.EntityManager;

public final class QueryUtils {
    private QueryUtils() {
    }

    public static void runDirectQuerySafely(EntityManager em, Runnable query) {
        em.flush();
        em.clear();
        query.run();
    }
}
