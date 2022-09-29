package com.hepengju.java19;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        System.out.println(Locale.getDefault());
        System.out.println(DateTimeFormatter.ofLocalizedPattern("yMMM").format(LocalDateTime.now()));
        System.out.println(Arrays.toString(Locale.getAvailableLocales()));
    }
}
