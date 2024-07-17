//package com.hepengju.java22.new01_unnamed_variables;
//
//import java.util.ArrayDeque;
//import java.util.Queue;
//
///**
// * 未命名变量与模式
// */
//public class _UnnamedVariables {
//
//    // 使用未命名变量的增强 for 循环
//    int count(Iterable<Order> orders) {
//        int total = 0;
//        for (Order _ : orders) { // 使用未命名变量
//            total++;
//        }
//        return total;
//    }
//
//    // 在 while 循环中使用未命名变量
//    void other() {
//        // 在 while 循环中使用未命名变量
//        Queue<Integer> q = new ArrayDeque<>();
//        while (q.size() >= 3) {
//            var x = q.remove();
//            var _ = q.remove(); // 使用未命名变量
//            var _ = q.remove(); // 使用未命名变量
//        }
//
//        // 使用未命名变量的 catch 块
//        String s = "";
//        try {
//            int i = Integer.parseInt(s);
//        } catch (NumberFormatException _) { // 使用未命名变量
//            System.out.println("Bad number: " + s);
//        }
//
//        // 在 try-with-resources 语句中使用未命名变量
//        //        try (var _ = ScopedContext.acquire()) { // 使用未命名变量
//        //        }
//
//        // 使用未命名变量的 lambda 表达式
//        // ...stream.collect(Collectors.toMap(String::toUpperCase, _ -> "NODATA")) // 使用未命名变量
//
//        // 使用未命名模式变量的 switch 语句
//        //        switch (ball) {
//        //            case RedBall _ -> process(ball);
//        //            case BlueBall _ -> process(ball);
//        //            case GreenBall _ -> stopProcessing();
//        //        }
//
//        // 使用未命名模式的 instanceof 检查
//        //        if (r instanceof ColoredPoint(Point(int x, _), _)) { // 未使用的组件使用未命名模式
//        //        }
//    }
//}
//
