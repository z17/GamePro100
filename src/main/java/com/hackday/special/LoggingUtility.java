package com.hackday.special;

// todo: configure LOG4J2 and use it
public final class LoggingUtility {

//    private static final Logger LOG = LogManager.getLogger(LoggingUtility.class);

    public static void i(Object... o) {
        for(Object ob : o) {
            System.out.println(ob);
        }
        //LOG.info(o.toString());
    }

    public static void w(Object... o) {
        for(Object ob : o) {
            System.out.println(ob);
        }
        //LOG.warn(o);
    }

    public static void e(Object... o) {
        for(Object ob : o) {
            System.out.println(ob);
        }
        //LOG.error(o);
    }
}
