package com.bloodbank.util;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {

    private static boolean loaded = false;
    private static Dotenv dotenv;

    public static void load() {
        if (!loaded) {
            dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

            System.setProperty("DB_URL", dotenv.get("DB_URL"));
            System.setProperty("DB_USER", dotenv.get("DB_USER"));
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

            loaded = true;
            System.out.println("Variables d'environnement chargées depuis .env");
        }
    }

    public static String get(String key) {
        if (!loaded) {
            load();
        }
        return dotenv.get(key);
    }
}
