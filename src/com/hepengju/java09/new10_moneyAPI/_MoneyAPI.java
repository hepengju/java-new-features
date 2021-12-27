//package com.hepengju.java09.new10_moneyAPI;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import javax.money.CurrencyUnit;
//import javax.money.MonetaryAmount;
//import javax.money.MonetaryAmounts;
//import javax.money.MonetaryCurrencies;
//import javax.money.MonetaryException;
//import javax.money.MonetaryOperator;
//import javax.money.MonetaryRoundings;
//import javax.money.NumberValue;
//import javax.money.format.AmountFormatQueryBuilder;
//import javax.money.format.MonetaryAmountFormat;
//import javax.money.format.MonetaryFormats;
//
//import org.javamoney.moneta.FastMoney;
//import org.javamoney.moneta.Money;
//import org.javamoney.moneta.format.CurrencyStyle;
//import org.javamoney.moneta.function.MonetaryFunctions;
//import org.javamoney.moneta.function.MonetarySummaryStatistics;
//import org.junit.Test;
//
///**
// * JSR 354: “金钱和货币”解决了Java中货币和货币金额的标准化问题。
// * <pre>
// *  实现目的：
// *            * 为了Java生态系统添加一个灵活的可扩展的API，并使货币量更简单，更安全。
// *      背景：
// *            * JSR没有进入JDK 9，而是未来JDK版本的候选人。
// *            * 虽然在Java9有Currency类简单实现但是实际开发中满足不了需求。
// * </pre>
// *
// * @see <a href="http://www.importnew.com/14474.html">
// * @see <a https://github.com/mscharhag/java-9-playground/blob/master/money-api/src/main/java/com/mscharhag/java9/money/Main.java">
// *
// * @see <a href="https://www.jb51.net/article/148199.htm">Java11新特性之HttpClient小试牛刀</a>
// *
// * @author WGR
// *
// * Update by WGR 20190315: 解决冲突，不报错
// */
//
//public class _MoneyAPI {
//
//       /**
//        * CurrencyUnit有点类似于现在的java.util.Currency类，
//        * 不同之处在于它支持自定义的实现。
//        */
//       @Test
//       public void currencyUnit() {
//
//        CurrencyUnit euro = MonetaryCurrencies.getCurrency("EUR");
//        CurrencyUnit usDollar = MonetaryCurrencies.getCurrency("USD");
//
//        CurrencyUnit yen = MonetaryCurrencies.getCurrency(Locale.JAPAN);
//        CurrencyUnit canadianDollar = MonetaryCurrencies.getCurrency(Locale.CANADA);
//
//        System.out.println("euro = " + euro);           // euro = EUR
//        System.out.println("usDollar = " + usDollar); // usDollar = USD
//        System.out.println("yen = " + yen);             // yen = JPY
//        System.out.println("canadianDollar = " + canadianDollar); // canadianDollar = CAD
//    }
//
//    /**
//     * MonetaryAmount是货币金额的数字表示。
//     * 它始终与CurrencyUnit 关联，并定义货币的货币表示形式。
//     */
//    @Test
//    public void monetaryAmount() {
//        CurrencyUnit euro = MonetaryCurrencies.getCurrency("EUR");
//        MonetaryAmount fiveEuro = Money.of(5, euro);
//
//        MonetaryAmount tenUsDollar = Money.of(10, "USD");
//        MonetaryAmount sevenEuro = FastMoney.of(7, euro);
//
//        System.out.println("fiveEuro = " + fiveEuro);          // fiveEuro = EUR 5
//        System.out.println("tenUsDollar = " + tenUsDollar);  // tenUsDollar = USD 10
//        System.out.println("sevenEuro = " + sevenEuro);       // sevenEuro = EUR 7.00000
//
//        MonetaryAmount specAmount = MonetaryAmounts.getDefaultAmountFactory()
//                                                             .setNumber(123.45)
//                                                             .setCurrency("USD")
//                                                             .create();
//        System.out.println("specAmount = " + specAmount);   // specAmount = USD 123.45
//
//
//        MonetaryAmount monetaryAmount = Money.of(123.45, euro);
//        CurrencyUnit currency = monetaryAmount.getCurrency();
//        NumberValue numberValue = monetaryAmount.getNumber();
//
//        int intValue = numberValue.intValue(); // 123
//        double doubleValue = numberValue.doubleValue(); // 123.45
//        long fractionDenominator = numberValue.getAmountFractionDenominator(); // 100
//        long fractionNumerator = numberValue.getAmountFractionNumerator(); // 45
//        int precision = numberValue.getPrecision(); // 5
//
//        // NumberValue extends java.lang.Number. So we assign numberValue to a variable of type Number
//        Number number = numberValue;
//
//        MonetaryAmount oneEuro = Money.of(1, MonetaryCurrencies.getCurrency("EUR"));
//        boolean isEqual = oneEuro.equals(Money.of(1, "EUR"));
//        boolean fastEqual = oneEuro.equals(FastMoney.of(1, "EUR"));
//
//        System.out.println("currency = " + currency);              // currency = EUR
//        System.out.println("number = " + number);                   // number = 123.45
//        System.out.println("intValue = " + intValue);              // intValue = 123
//        System.out.println("doubleValue = " + doubleValue);      //  doubleValue = 123.45
//        System.out.println("fractionDenominator = " + fractionDenominator);  // fractionDenominator = 100
//        System.out.println("fractionNumerator = " + fractionNumerator);      // fractionNumerator = 45
//        System.out.println("precision = " + precision);           // precision = 5
//        System.out.println("isEqual = " + isEqual);                // isEqual = true
//        System.out.println("fastEqual = " + fastEqual);           // fastEqual = false
//    }
//
//    /**
//     * MonetaryAmount上进行算术运算
//     */
//    @Test
//    public void monetaryAmountOperations() {
//
//        MonetaryAmount fiveEuro = Money.of(5, "EUR");
//        MonetaryAmount tenUsDollar = Money.of(10, "USD");
//        MonetaryAmount sevenEuro = FastMoney.of(7, "EUR");
//
//        MonetaryAmount twelveEuro = fiveEuro.add(sevenEuro); // "EUR 12"
//        MonetaryAmount twoEuro = sevenEuro.subtract(fiveEuro); // "EUR 2"
//        MonetaryAmount sevenPointFiveEuro = fiveEuro.multiply(1.5); // "EUR 7.5"
//
//        // Monetary amount can have a negative NumberValue
//        MonetaryAmount minusTwoEuro = fiveEuro.subtract(sevenEuro); // "EUR -2"
//
//        System.out.println("twelveEuro = " + twelveEuro);   // twelveEuro = EUR 12
//        System.out.println("twoEuro = " + twoEuro);          // twoEuro = EUR 2.00000
//        System.out.println("sevenPointFiveEuro = " + sevenPointFiveEuro); // sevenPointFiveEuro = EUR 7.5
//        System.out.println("minusTwoEuro = " + minusTwoEuro);               // minusTwoEuro = EUR -2
//
//        boolean greaterThan = sevenEuro.isGreaterThan(fiveEuro);
//        boolean positive = sevenEuro.isPositive();
//        boolean zero = sevenEuro.isZero();
//
//        System.out.println("greaterThan = " + greaterThan); // greaterThan = true
//        System.out.println("positive = " + positive);        // positive = true
//        System.out.println("zero = " + zero);                  // zero = false
//
//
//        try {
//            fiveEuro.add(tenUsDollar);
//        } catch (MonetaryException e) {
//            System.out.println(e.getMessage());                // fails, Currency mismatch: EUR/USD
//        }
//    }
//
//    /**
//     * CurrencyUnit与MontetaryAmount的实现必须是不可变，线程安全且可比较的。
//     */
//    @Test
//    public void monetaryAmountReductionFunctions() {
//        List<MonetaryAmount> amounts = new ArrayList<>();
//        amounts.add(Money.of(10, "EUR"));
//        amounts.add(Money.of(7.5, "EUR"));
//        amounts.add(Money.of(12, "EUR"));
//
//        Optional<MonetaryAmount> max = amounts.stream().reduce(MonetaryFunctions.max()); // "EUR 7.5"
//        Optional<MonetaryAmount> min = amounts.stream().reduce(MonetaryFunctions.min()); // "EUR 12"
//        Optional<MonetaryAmount> sum = amounts.stream().reduce(MonetaryFunctions.sum()); // "EUR 29.5"
//
//        System.out.println("min = " + min);           // min = Optional[EUR 7.5]
//        System.out.println("max = " + max);           // max = Optional[EUR 12]
//        System.out.println("sum = " + sum);           // sum = Optional[EUR 29.5]
//    }
//
//    /**
//     * 操作MonetaryAmount集合时，有许多实用的工具方法可以用来进行过滤，排序以及分组
//     */
//    @Test
//    public void monetaryAmountFilterFunctions() {
//        List<MonetaryAmount> amounts = new ArrayList<>();
//        amounts.add(Money.of(2, "EUR"));
//        amounts.add(Money.of(42, "USD"));
//        amounts.add(Money.of(7, "USD"));
//        amounts.add(Money.of(13.37, "JPY"));
//        amounts.add(Money.of(18, "USD"));
//
//        CurrencyUnit yen = MonetaryCurrencies.getCurrency("JPY");
//        CurrencyUnit dollar = MonetaryCurrencies.getCurrency("USD");
//        List<MonetaryAmount> onlyDollar = amounts.stream()
//                .filter(MonetaryFunctions.isCurrency(dollar))
//                .collect(Collectors.toList());
//
//        List<MonetaryAmount> onlyDollarAndYen = amounts.stream()
//                .filter(MonetaryFunctions.isCurrency(dollar, yen))
//                .collect(Collectors.toList());
//        MonetaryAmount tenDollar = Money.of(10, dollar);
//
//        List<MonetaryAmount> greaterThanTenDollar = amounts.stream()
//                .filter(MonetaryFunctions.isCurrency(dollar))
//                .filter(MonetaryFunctions.isGreaterThan(tenDollar))
//                .collect(Collectors.toList());
//
//        List<MonetaryAmount> sortedByAmount = onlyDollar.stream()
//                .sorted(MonetaryFunctions.sortNumber())
//                .collect(Collectors.toList());
//
//        List<MonetaryAmount> sortedByCurrencyUnit = amounts.stream()
//                .sorted(MonetaryFunctions.sortCurrencyUnit())
//                .collect(Collectors.toList());
//
//        Map<CurrencyUnit, List<MonetaryAmount>> groupedByCurrency = amounts.stream()
//                .collect(MonetaryFunctions.groupByCurrencyUnit());
//
//        Map<CurrencyUnit, MonetarySummaryStatistics> summary = amounts.stream()
//               .collect(MonetaryFunctions.groupBySummarizingMonetary()).get();
//
//        MonetarySummaryStatistics dollarSummary = summary.get(dollar);
//        MonetaryAmount average = dollarSummary.getAverage();
//        MonetaryAmount min = dollarSummary.getMin();
//        MonetaryAmount max = dollarSummary.getMax();
//        MonetaryAmount sum = dollarSummary.getSum();
//        long count = dollarSummary.getCount();
//
//        System.out.println("average = " + average);    // average = USD 22.33333333333....
//        System.out.println("min = " + min);              // min = USD 7
//        System.out.println("max = " + max);              // max = USD 42
//        System.out.println("sum = " + sum);              // sum = USD 67
//        System.out.println("count = " + count);         // count = 3
//
//        System.out.println("onlyDollarAndYen = " + onlyDollarAndYen);              // onlyDollarAndYen = [USD 42, USD 7, JPY 13.37, USD 18]
//        System.out.println("onlyDollar = " + onlyDollar);                            // onlyDollar = [USD 42, USD 7, USD 18]
//        System.out.println("greaterThanTenDollar = " + greaterThanTenDollar);   // greaterThanTenDollar = [USD 42, USD 18]
//        System.out.println("sortedByAmount = " + sortedByAmount);                 // sortedByAmount = [USD 7, USD 18, USD 42]
//        System.out.println("sortedByCurrencyUnit = " + sortedByCurrencyUnit);  // sortedByCurrencyUnit = [EUR 2, JPY 13.37, USD 42, USD 7, USD 18]
//        System.out.println("groupedByCurrency = " + groupedByCurrency);          // groupedByCurrency = {EUR=[EUR 2], USD=[USD 42, USD 7, USD 18], JPY=[JPY 13.37]}
//    }
//
//
//    @Test
//    public void monetaryAmountRounding() {
//        CurrencyUnit usd = MonetaryCurrencies.getCurrency("USD");
//        MonetaryAmount dollars = Money.of(12.34567, "USD");
//
//        MonetaryOperator roundingOperator = MonetaryRoundings.getRounding(usd);
//        MonetaryAmount roundedDollars = dollars.with(roundingOperator);
//
//        System.out.println("roundedDollars = " + roundedDollars);      // roundedDollars = USD 12.35
//    }
//
//    /**
//     * 自定义的MonetaryAmount,MonetaryOperator是一个函数式接口，
//     * 它接收一个MonetaryAmount入参并返回一个新的MonetaryAmount对象。
//     */
//    @Test
//    public void monetaryOperators() {
//        MonetaryAmount dollars = Money.of(12.34567, "USD");
//
//        MonetaryOperator tenPercentOperator = (MonetaryAmount amount) -> {
//            BigDecimal baseAmount = amount.getNumber().numberValue(BigDecimal.class);
//            BigDecimal tenPercent = baseAmount.multiply(new BigDecimal("0.1"));
//            return Money.of(tenPercent, amount.getCurrency());
//        };
//
//        MonetaryAmount tenPercentDollars = dollars.with(tenPercentOperator); // USD 1.234567
//
//        System.out.println("tenPercentDollars = " + tenPercentDollars);
//    }
//
//    /**
//     * 格式化及解析
//     * MonetaryAmount可以通过MonetaryAmountFormat来与字符串进行解析/格式化。
//     */
//    @Test
//    public void formatting() {
//        MonetaryAmountFormat germanFormat = MonetaryFormats.getAmountFormat(Locale.GERMANY);
//        MonetaryAmountFormat usFormat = MonetaryFormats.getAmountFormat(Locale.CANADA);
//
//        MonetaryAmount amount = Money.of(12345.67, MonetaryCurrencies.getCurrency("USD"));
//
//        String usFormatted = usFormat.format(amount);
//        String germanFormatted = germanFormat.format(amount);
//
//        System.out.println("usFormatted = " + usFormatted);
//        System.out.println("germanFormatted = " + germanFormatted);
//
//        MonetaryAmount parsed = germanFormat.parse("12,4 USD");
//
//
//        MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(
//                AmountFormatQueryBuilder.of(Locale.US)
//                        .set(CurrencyStyle.NAME)
//                        .set("pattern", "00,00,00,00.00 ¤")
//                        .build());
//
//
//        String formatted = customFormat.format(amount);  // results in "00,01,23,45.67 US Dollar"
//
//        System.out.println("formatted: " + formatted);    // usFormatted = USD12,345.67
//        System.out.println("parsed = " + parsed);          // germanFormatted = 12.345,67?USD
//    }
//
//
//}
