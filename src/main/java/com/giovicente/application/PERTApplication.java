package com.giovicente.application;

import com.giovicente.controller.PERTMenu;

import java.util.Locale;

public class PERTApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        PERTMenu.runPERT();
    }
}
