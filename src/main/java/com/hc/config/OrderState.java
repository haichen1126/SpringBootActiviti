package com.hc.config;

public enum OrderState {
    /**
     * 已取消
     */
    CANCEL {
        @Override
        public String getName() {
            return "已取消";
        }
    },
    /**
     * 待审核
     */
    WAITCONFIRM {
        @Override
        public String getName() {
            return "待审核";
        }
    },
    /**
     * 等待付款
     */
    WAITPAYMENT {
        @Override
        public String getName() {
            return "等待付款";
        }
    },
    /**
     * 正在配货
     */
    ADMEASUREPRODUCT {
        @Override
        public String getName() {
            return "正在配货";
        }
    },
    /**
     * 等待发货
     */
    WAITDELIVER {
        @Override
        public String getName() {
            return "等待发货";
        }
    },
    /**
     * 已发货
     */
    DELIVERED {
        @Override
        public String getName() {
            return "已发货";
        }
    },
    /**
     * 已收货
     */
    RECEIVED {
        @Override
        public String getName() {
            return "已收货";
        }
    };

    public abstract String getName();


//    PLUS("+") {
//        @Override
//        public double apply(double x, double y) {
//            return x + y;
//        }
//    }, MINUS("-") {
//        @Override
//        public double apply(double x, double y) {
//            return x - y;
//        }
//    }, TIMES("*") {
//        @Override
//        public double apply(double x, double y) {
//            return x * y;
//        }
//    }, DIVIDE("/") {
//        @Override
//        public double apply(double x, double y) {
//            return x / y;
//        }
//    };
//
//    private final String symbol;
//
//    OrderState(String symbol) {
//        this.symbol = symbol;
//    }
//
//    public abstract double apply(double x, double y);
//
//    @Override
//    public String toString() {
//        return symbol;
//    }
//
//    public static void main(String[] args) {
//        double x = 4;
//        double y = 2;
//        OrderState.DIVIDE.apply(x,y);
//        for (OrderState operation : OrderState.values()) {
//            System.out.printf("%f %s %f = %f%n",
//                    x, operation, y, operation.apply(x, y));
//        }
//    }

}
