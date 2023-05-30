package App;

import java.util.Scanner;

/**
 * Klasa główna, której uruchomienie rozpoczyna grę . Zawiera wszelkie menu w naszej grze oraz całą oprawę gry, która ma na celu zapewnić
 * większą imersję i frajdę z rozgrywki.
 */
public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_PINK = "\u001B[35;1m";
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;1m";
    public static final String ANSI_BROWN = "\u001b[38;5;94m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static int points=0;
    public static int musicSwitch=1;

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println(ANSI_CYAN + "===============================================" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "| ⭐⭐⭐⭐WITAJCIE W GRZE APRO CRUSH SAGA!!⭐⭐⭐⭐|" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "===============================================" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "1. Menu                            " + ANSI_RESET + ANSI_RED + " 2. Wyjdź" + ANSI_RESET);
                Scanner sc = new Scanner(System.in);
                int op = sc.nextInt();
                switch (op) {
                    case 1 -> mainMenu();
                    case 2 -> {
                        return;
                    }
                    default -> System.out.println("Brak takiej opcji, wybierz jeszcze raz!");
                }
            }
        }catch (NumberFormatException nfe){
            System.out.println("Błędny input!!!");
        }
    }

    private static void mainMenu() {
            String music = "";
            if (musicSwitch == 1) {
                music = "Town-Sylvan.wav";
            } else if (musicSwitch == 2) {
                music = "sound.wav";
            }
            AudioThread at = new AudioThread(music);
            at.run();
            try{
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(ANSI_CYAN + "====================================================" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "|  " + ANSI_RESET + ANSI_PINK + "⭐      ⭐" + ANSI_RESET + ANSI_ORANGE + "    \uD83C\uDFB5" + ANSI_RESET + ANSI_RED + "         \uD83D\uDC96" + ANSI_RESET + ANSI_YELLOW + "         4. Instrukcja  |" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "|" + ANSI_ORANGE + "\uD83C\uDFB5                                                 " + ANSI_YELLOW + "|");
                System.out.println("|             " + ANSI_PURPLE + "Wybierz poziom trudności           " + ANSI_PINK + "⭐  " + ANSI_YELLOW + "|");
                System.out.println("|   " + ANSI_PINK + "⭐                                    " + ANSI_RED + " \uD83D\uDC96       " + ANSI_YELLOW + " |");
                System.out.println("|               " + ANSI_PINK + "⭐                " + ANSI_RED + " 3.Trudny         " + ANSI_YELLOW + "|");
                System.out.println("|   " + ANSI_GREEN + "1. Łatwy                      ______           " + ANSI_YELLOW + " |");
                System.out.println("|                             " + ANSI_GREEN + "   |      |    " + ANSI_PINK + "  ⭐ " + ANSI_YELLOW + "  | ");
                System.out.println("|  " + ANSI_RESET + "    /\\         " + ANSI_BLUE + " 2.Średni     " + ANSI_GREEN + " |      |    " + ANSI_YELLOW + "       |");
                System.out.println("|   " + ANSI_RESET + "  /__\\           " + ANSI_GREEN + "            |_    _|     " + ANSI_YELLOW + "      |");
                System.out.println("|  " + ANSI_WHITE + "  /    \\                        " + ANSI_BROWN + "|  |    " + ANSI_ORANGE + "  \uD83C\uDFB5   " + ANSI_YELLOW + "  |");
                System.out.println("|" + ANSI_YELLOW + " __" + ANSI_WHITE + "/      \\" + ANSI_YELLOW + "_______________________" + ANSI_BROWN + "|  |" + ANSI_YELLOW + "_________    |");
                System.out.println("|/                                              \\   |");
                System.out.println("|  " + ANSI_RED + "5. Wyjdź                  " + ANSI_PURPLE + "   6. Sklep" + ANSI_YELLOW + "         \\  |");
                System.out.println(ANSI_CYAN + "==================| Punkty: " + points + " |======================" + ANSI_RESET);
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        at.stop();
                        easyMenu();
                        at.run();
                    }
                    case 2 -> {
                        at.stop();
                        mediumMenu();
                        at.run();
                    }
                    case 3 -> {
                        at.stop();
                        hardMenu();
                        at.start();
                    }
                    case 4 -> Instruction.displayInstruction();
                    case 5 -> {
                        at.stop();
                        at.close();
                        return;
                    }
                    case 6 -> {
                        at.stop();
                        shop();
                        at.start();
                    }
                    case 1234 -> {
                        at.stop();
                        secret1();
                        at.start();
                    }
                    default -> System.out.println("Brak takiej opcji, wybierz jeszcze raz!");
                }
            }
        }catch (NumberFormatException nfe){
                at.close();
            System.out.println("Błędny input!!");
        }
    }
private static void easyMenu(){
            AudioThread at = new AudioThread("CuteEasyLevelMusic.wav");
            at.run();

            try {
            while (true) {
                System.out.println(ANSI_RED + "====================================================");
                System.out.println(ANSI_PINK + "|              ❤                     ❤            |");
                System.out.println(ANSI_PINK + "|     ❤                     ❤                ❤   |");
                System.out.println(ANSI_PINK + "|                                                   |");
                System.out.println(ANSI_PINK + "|     " + ANSI_RESET + "   Poziom 1.  " + ANSI_PURPLE + "     Poziom 2.  " + ANSI_RED + "    Poziom 3.  " + ANSI_PINK + " |");
                System.out.println("|                                                   |");
                System.out.println("|    ❤                      ❤                     |");
                System.out.println("|                  ❤  " + ANSI_RED + "  ___  ___    " + ANSI_PINK + "              |");
                System.out.println("|               ❤  " + ANSI_RED + "    /   \\/   \\     " + ANSI_PINK + "        ❤  |");
                System.out.println("|    ❤             " + ANSI_RED + "    \\        /  " + ANSI_PINK + "    ❤         |");
                System.out.println("|                 ❤   " + ANSI_RED + "  \\      /        " + ANSI_PINK + "          |");
                System.out.println("|          ❤         " + ANSI_RED + "    \\    /      " + ANSI_PINK + "      ❤     |");
                System.out.println("|  ❤                 " + ANSI_RED + "     \\  /     " + ANSI_PINK + "               |");
                System.out.println("|        ❤         " + ANSI_RED + "        \\/     " + ANSI_PINK + "  ❤            |");
                System.out.println(ANSI_RED + "=======================4. Powrót====================" + ANSI_RESET);
                Scanner sc = new Scanner(System.in);
                int easyChoice = Integer.parseInt(sc.nextLine());
                switch (easyChoice) {
                    case 1 -> {
                        GameHandler gameHandler1;
                        gameHandler1 = new GameHandler("\\App\\resources\\LevelOne.properties");
                        gameHandler1.launchGame();
                        gameHandler1.playGame();
                        points += GameHandler.getPointCounter();
                    }
                    case 2 -> {
                        GameHandler gameHandler2;
                        gameHandler2 = new GameHandler("\\App\\resources\\LevelTwo.properties");
                        gameHandler2.launchGame();
                        gameHandler2.playGame();
                        points += GameHandler.getPointCounter();
                    }
                    case 3 -> {
                        GameHandler gameHandler3;
                        gameHandler3 = new GameHandler("\\App\\resources\\LevelThree.properties");
                        gameHandler3.launchGame();
                        gameHandler3.playGame();
                        points += GameHandler.getPointCounter();
                    }
                    case 4 -> {
                        at.stop();
                        at.close();
                        return;
                    }
                    default -> System.out.println("Brak takiej opcji, wybierz jeszcze raz!");
                }
            }
        }catch (NumberFormatException nfe){
                at.close();
            System.out.println("Błędny input!!");
        }
}

private static void mediumMenu(){
    AudioThread at = new AudioThread("MediumLevelMusic.wav");
    at.run();
    try{
        while (true) {
    System.out.println(ANSI_GREEN+"========================================================"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|                                            "+ANSI_RED+"4. Powrót"+ANSI_GREEN+" |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|                                                      |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|  "+ANSI_BLUE+"Poziom Średni:"+ANSI_RESET+"                                      "+ANSI_GREEN+"|"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|                                                      |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|    "+ANSI_YELLOW+"1. poziom 4"+ANSI_RESET+"              "+ANSI_RED+"       ▄█▀ ▄▄▄▄▄▄▄ ▀█▄   "+ANSI_GREEN+"|"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|    "+ANSI_YELLOW+"2. poziom 5"+ANSI_RESET+"           "+ANSI_RED+"          ▀█████████████▀   "+ANSI_GREEN+"|"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|    "+ANSI_YELLOW+"3. poziom 6"+ANSI_RESET+"           "+ANSI_RED+"              █▄███▄█     "+ANSI_GREEN+"  |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|                                  "+ANSI_RED+"       █████        "+ANSI_GREEN+"|"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|                                  "+ANSI_RED+"       █▀█▀█       "+ANSI_GREEN+" |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|"+ANSI_ORANGE+"  █▄██▄█         █▄██▄█         █▄██▄█"+ANSI_RED+"               "+ANSI_GREEN+" |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"|  "+ANSI_ORANGE+"▐█┼██▌█▄█▄█▄█▄█▐████▌█▄█▄█▄█▄█▐██┼█▌               "+ANSI_GREEN+" |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"| "+ANSI_ORANGE+" ▐████▌█████████▐█┼┼█▌█████████▐████▌               "+ANSI_GREEN+" |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"| "+ANSI_ORANGE+" ▐████▌█████████▐█┼┼█▌█████████▐████▌              "+ANSI_GREEN+"  |"+ANSI_RESET);
    System.out.println(ANSI_GREEN+"========================================================"+ANSI_RESET);


            Scanner sc = new Scanner(System.in);
            int mediumChoice = Integer.parseInt(sc.nextLine());

            switch (mediumChoice) {
                case 1 -> {
                    GameHandler gameHandler7;
                    gameHandler7 = new GameHandler("\\App\\resources\\LevelFour.properties");
                    gameHandler7.launchGame();
                    gameHandler7.playGame();
                    points += GameHandler.getPointCounter();
                }
                case 2 -> {
                    GameHandler gameHandler8;
                    gameHandler8 = new GameHandler("\\App\\resources\\LevelFive.properties");
                    gameHandler8.launchGame();
                    gameHandler8.playGame();
                    points += GameHandler.getPointCounter();
                }
                case 3 -> {
                    GameHandler gameHandler9;
                    gameHandler9 = new GameHandler("\\App\\resources\\LevelSix.properties");
                    gameHandler9.launchGame();
                    gameHandler9.playGame();
                    points += GameHandler.getPointCounter();
                }
                case 4 -> {
                    at.stop();
                    at.close();
                    return;
                }
                default -> System.out.println("Brak takiej opcji, wybierz jeszcze raz!");
            }
        }
    } catch (NumberFormatException  nfe){
            at.close();
            System.out.println("Błędny input!!");
        }
}

private static void hardMenu(){
            AudioThread at = new AudioThread("HardLevelMusic.wav");
            at.run();
            try {
            while (true) {
                System.out.println(ANSI_YELLOW + "===========================================================================" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "|                                                                         |" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + ANSI_BLUE + "                              Poziom trudny                              " + ANSI_RESET + ANSI_YELLOW + "|" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "|                                                                         |" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "|                                                                         |" + ANSI_RESET);
                System.out.println(ANSI_ORANGE + "| " + ANSI_RESET + ANSI_YELLOW + "         1. poziom 7 " + ANSI_RESET + ANSI_ORANGE + "         2. poziom 8    " + ANSI_RESET + ANSI_RED + "     3. poziom 9    " + ANSI_RESET + ANSI_ORANGE + "       |" + ANSI_RESET);
                System.out.println(ANSI_ORANGE + "|   " + ANSI_RESET + ANSI_RED + "                                               /|        |\\  " + ANSI_RESET + ANSI_ORANGE + "         |" + ANSI_RESET);
                System.out.println(ANSI_ORANGE + "|                                       " + ANSI_RESET + ANSI_RED + "          //          \\\\     " + ANSI_RESET + ANSI_ORANGE + "     |" + ANSI_RESET);
                System.out.println(ANSI_ORANGE + "|     " + ANSI_RESET + ANSI_YELLOW + "       ______ " + ANSI_RESET + ANSI_ORANGE + "             /|______|\\ " + ANSI_RESET + ANSI_RED + "      \\\\  ______  //  " + ANSI_RESET + ANSI_ORANGE + "        |" + ANSI_RESET);
                System.out.println(ANSI_ORANGE + "|  " + ANSI_RESET + ANSI_YELLOW + "         /      \\  " + ANSI_RESET + ANSI_ORANGE + "           \\/_    _\\/  " + ANSI_RESET + ANSI_RED + "      \\\\/      \\// " + ANSI_RESET + ANSI_ORANGE + "          |" + ANSI_RESET);
                System.out.println(ANSI_RED + "|    " + ANSI_RESET + ANSI_YELLOW + "      / __  __ \\     " + ANSI_RESET + ANSI_ORANGE + "       //_/  \\_\\\\     " + ANSI_RESET + ANSI_RED + "    /  X  X  \\            |" + ANSI_RESET);
                System.out.println(ANSI_RED + "|     " + ANSI_RESET + ANSI_YELLOW + "    /          \\       " + ANSI_RESET + ANSI_ORANGE + "   /          \\    " + ANSI_RESET + ANSI_RED + "   /          \\           |" + ANSI_RESET);
                System.out.println(ANSI_RED + "|      " + ANSI_RESET + ANSI_YELLOW + "   \\   ____   /       " + ANSI_RESET + ANSI_ORANGE + "   \\  ______  /     " + ANSI_RESET + ANSI_RED + "  \\   ____   /           |" + ANSI_RESET);
                System.out.println(ANSI_RED + "|       " + ANSI_RESET + ANSI_YELLOW + "   \\ /    \\ /       " + ANSI_RESET + ANSI_ORANGE + "     \\        /       " + ANSI_RESET + ANSI_RED + "  \\ /    \\ /            |" + ANSI_RESET);
                System.out.println(ANSI_RED + "|        " + ANSI_RESET + ANSI_YELLOW + "   \\______/          " + ANSI_RESET + ANSI_ORANGE + "    \\______/         " + ANSI_RESET + ANSI_RED + "  \\______/  4. Wyjście |" + ANSI_RESET);
                System.out.println(ANSI_BLACK + "===========================================================================" + ANSI_RESET);
                Scanner sc = new Scanner(System.in);
                int hardChoice = Integer.parseInt(sc.nextLine());

                switch (hardChoice) {
                    case 1 -> {
                        GameHandler gameHandler7;
                        gameHandler7 = new GameHandler("\\App\\resources\\LevelSeven.properties");
                        gameHandler7.launchGame();
                        gameHandler7.playGame();
                        points += GameHandler.getPointCounter();
                    }
                    case 2 -> {
                        GameHandler gameHandler8;
                        gameHandler8 = new GameHandler("\\App\\resources\\LevelEight.properties");
                        gameHandler8.launchGame();
                        gameHandler8.playGame();
                        points += GameHandler.getPointCounter();
                    }
                    case 3 -> {
                        GameHandler gameHandler9;
                        gameHandler9 = new GameHandler("\\App\\resources\\LevelNine.properties");
                        gameHandler9.launchGame();
                        gameHandler9.playGame();
                        points += GameHandler.getPointCounter();
                    }
                    case 4 -> {
                        at.stop();
                        at.close();
                        return;
                    }
                    default -> System.out.println("Brak takiej opcji, wybierz jeszcze raz!");
                }
            }
        }catch (NumberFormatException  nfe){
                at.close();
            System.out.println("Błędny input!!");
        }
}
private static void shop(){
            AudioThread at = new AudioThread("shop.wav");
            at.run();
            try {
            while (true) {
                System.out.println(ANSI_BROWN + "=====================================================");
                System.out.println(ANSI_RED + "|          " + ANSI_GREEN + "       Witaj w sklepie !!!!      " + ANSI_YELLOW + "  _  " + ANSI_RED + "   |");
                System.out.println("|                                   " + ANSI_YELLOW + "         /" + ANSI_ORANGE + "|" + ANSI_YELLOW + "\\ " + ANSI_RED + "   |");
                System.out.println("|    " + ANSI_RESET + "   ___       " + ANSI_YELLOW + "  Co chcesz kupić:        |" + ANSI_ORANGE + "\\/ " + ANSI_YELLOW + "\\  " + ANSI_RED + " |");
                System.out.println("|   " + ANSI_RESET + "    | |                        " + ANSI_RED + "         \\_" + ANSI_ORANGE + "|" + ANSI_RED + "_/   |");
                System.out.println("|      /" + ANSI_RESET + " " + ANSI_RED + "  \\" + ANSI_BLUE + "     1.Ciekawostka:  1000p     " + ANSI_BROWN + "  | |" + ANSI_RED + "    |");
                System.out.println("|     |" + ANSI_RESET + ANSI_RED_BACKGROUND + " Sok " + ANSI_RESET + ANSI_RED + "|          " + ANSI_BROWN + "                      | | " + ANSI_RED + "   |");
                System.out.println("|      \\___/   " + ANSI_PINK + "  2. Nowa muzyka: 2000p  " + ANSI_BROWN + "     | |  " + ANSI_RED + "  | ");
                System.out.println("|                                " + ANSI_BROWN + "            |_|  " + ANSI_RED + "  |");
                System.out.println("|      " + ANSI_ORANGE + "     3. Krótkie opowiadanie: 3000p     " + ANSI_RED + "      |");
                System.out.println("|                                                   |");
                System.out.println("|                                  " + ANSI_WHITE + "  4. Wyjdź  " + ANSI_RED + "     |");
                System.out.println("|                                                   |");
                System.out.println(ANSI_BROWN + "==================|" + ANSI_CYAN + "Dostępne punkty: " + points + ANSI_BROWN + "|=============" + ANSI_RESET);

                Scanner sc = new Scanner(System.in);
                int shopChoice = Integer.parseInt(sc.nextLine());
                switch (shopChoice) {
                    case 1 -> {
                        if (points >= 1000) {
                            System.out.println(ANSI_RED + "Ciekawostka:");
                            System.out.println(ANSI_GREEN + "W celu odnalezienia sekretnego znaku w głównym menu wpisz " + ANSI_CYAN + "1234" + ANSI_RESET);
                            points = points - 1000;
                        } else {
                            System.out.println("Posiadasz za mało pieniędzy!!!!");
                        }
                    }
                    case 2 -> {
                        if (points >= 2000) {
                            musicSwitch = 2;
                            points = points - 2000;
                            System.out.println(ANSI_CYAN + "Muzyka została zmieniona w menu głównym!!!\n Wystarczy powrócić do menu wejściowego i stamtąd wejść do menu głównego, a muzyka zacznie grać!");
                        } else {
                            System.out.println("Posiadasz za mało pieniędzy!!!!");
                        }
                    }
                    case 3 -> {
                        if (points >= 3000) {
                            at.stop();
                            opowiadanie();
                            at.start();
                            points = points - 3000;
                        } else {
                            System.out.println("Posiadasz za mało pieniędzy!!!!");
                        }
                    }
                    case 4 -> {
                        at.stop();
                        at.close();
                        return;
                    }
                    default -> System.out.println("Brak takiej opcji, wybierz jeszcze raz!");
                }
            }
        }catch (NumberFormatException nfe){
                at.close();
            System.out.println("Błędny input!!");
        }
}

private static void secret1(){
    System.out.println("                 /|");
    System.out.println("                / |");
    System.out.println("_____________   | |                              __ ");
    System.out.println("\\    ______  \\  | |   __  |\\   __________        \\ \\");
    System.out.println(" \\ \\       / /  | |  / /  | \\ |  _______ \\        \\ \\");
    System.out.println("  \\ \\     / /   | |_/ /   | | | |       \\ \\    __  \\ \\");
    System.out.println("   \\ \\   / /    |    /    | | | |        \\ \\  / /   \\ \\");
    System.out.println("    \\ \\ / /     |    \\    | | | | \\ \\    / / / /     \\ \\");
    System.out.println("     \\ \\ /      |  /\\ \\   \\ | | |  \\ \\__/ / / /       \\ \\");
    System.out.println("      \\ \\       | /  \\ \\   \\| | |   \\____/  \\ \\________\\ \\");
    System.out.println("       \\ \\      |/    \\ \\     | |            \\____________\\");
    System.out.println("        \\_\\            \\_\\    | |");
    System.out.println("                              \\ |");
    System.out.println("                               \\|");
}
private static void opowiadanie(){
            AudioThread at = new AudioThread("story.wav");
            at.run();
            try {
            while (true) {
                System.out.println("""
                                    
                                                    LEGENDA O SMOKU WAWELSKIM
                                                    autor: Dominika Strzelecka
                                                        
                        Dawno temu, gdy polskimi ziemiami rządził król Krak, w Krakowie pojawił się smok. Było to
                        ogromne zwierzę, o zielonej skórze, długim ogonie i paszczy wypełnionej ostrymi zębami.\s
                        Smok zadomowił się w jamie pod zamkiem i żądał, aby raz w tygodniu składano mu ofiarę w\s
                        postaci krowy. Jeżeli nie spełniono jego zachcianki, porywał ludzi.
                                    
                        Na mieszkańców Krakowa padł blady strach, jednak znalazło się kilku śmiałków, którzy\s
                        twierdzili, że zdołają pokonać smoka. Niestety żaden z nich nie wracał z wyprawy do jamy\s
                        potwora. Zarówno król, jak i poddani stracili już nadzieję na ratunek. Co tydzień stada bydła\s
                        boleśnie się kurczyły, gdyż smok wymagał zawsze najdorodniejszych sztuk. Martwiono się, co
                        będzie, gdy pożre już wszystkie krowy.
                                    
                        Gdy wydawało się, że wszystko już stracone i lud Krakowa czeka zagłada, na dworze
                         Kraka pojawił się ubogi szewczyk.
                        - Panie mój, myślę, że jestem w stanie pokonać dręczącego Was smoka - zwrócił się do
                        króla, nisko się kłaniając.
                        W królewskiej sali rozbrzmiały śmiechy rycerzy.
                        - Patrzcie go, śmiałek się znalazł.
                        - Nie wiesz, że smoka nikt nie jest w stanie pokonać?
                        - Zabił już wielu wybitnych wojaków! Jak możesz się z nimi równać?
                        Jednak Krak był mądrym władcą i wiedział, że nie można marnować żadnej szansy na
                        uwolnienie się od  groźnej bestii.
                        - Dobrze, szewczyku. Pokonaj smoka, a zostaniesz sowicie nagrodzony.
                                    
                        Szewczyk ukłonił się i odszedł, obmyślając swój plan. Niebawem wszystko miał już
                        przygotowane. Zabił najdorodniejszego barana, jakiego udało mu się znaleźć, a potem wypchał
                        go siarką i dokładnie zaszył. Zarzucił sobie go na plecy i udał się w kierunku smoczej
                        jamy. Najciszej jak tylko potrafił zakradł się do samego wejścia, rzucił wypchanego barana 
                        i uciekł. Wkrótce z groty wyszedł smok, zwabiony zapachem świeżego mięsa i dostrzegając
                        barana, natychmiast go pożarł. Siarka ukryta w zwierzęciu od razu zaczęła działać, powodując
                        u smoka ogromne pragnienie. Rzucił się w kierunku Wisły i pił, pił, pił, pił...Wydawało się, że
                        jeszcze chwila i wypije całą Wisłę! I wtedy nagle rozległ się ogromny huk. Smok wypił tak dużo
                        wody, że po prostu pękł. Pomysłowy chłopiec został bohaterem całego miasta, a król sowicie go
                        wynagrodził.
                              
                        W Krakowie zaś do dziś, u stóp Wawelu, można zobaczyć Smoczą Jamę i ziejącą ogniem figurę
                        wawelskiego smoka, upamiętniającą bohaterski czyn szewczyka.
                                    
                                                                                    1.Wyjdź        
                        """);
                Scanner sc = new Scanner(System.in);
                int readChoice = Integer.parseInt(sc.nextLine());
                switch (readChoice) {
                    case 1 -> {
                        at.stop();
                        at.close();
                        return;
                    }
                    default -> System.out.println("Zły przycisk");
                }
            }
        }catch (NumberFormatException nfe){
                at.close();
            System.out.println("Błędny input!!");
        }
}
}
