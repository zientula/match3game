package App;

import java.util.Scanner;

/**
 * Klasa zawierająca wyświetlanie instrukcji. Stworzona w celu odciążenia klasy Main.
 */
public class Instruction {

    public static void displayInstruction(){
        System.out.println("Apro Crush Saga");
        System.out.println("Instrukcja Gry\n");
        System.out.println("1. WPROWADZENIE");
        System.out.println("""
                
                Apro Crush Saga jest grą typu Match 3.
                Zadaniem gracza jest zamienianie płytek będących obok siebie tak,
                aby płytki tego samego koloru były ułożone obok siebie w tym samym
                rzędzie lub kolumnie w zbiór o liczności 3 lub 4.  
                Takie złączenie płytek skutkuje usunięciem tych płytek (w przypadku 3)
                lub usunięciem całej kolumny/rzędu (w przypadku 4) oraz otrzymaniem 
                odpowiedniej ilości punktów za wykonany ruch. Celem gry jest zdobycie
                ilości punktów określonej dla każdego ruchu przy ograniczonej ilości 
                ruchów możliwych do wykonania.
                
                """);
        System.out.println("2. PORUSZANIE SIĘ PO GRZE");
        System.out.println("""
                
                W celu poruszania się po grze używamy odpowiednich numerów na klawiaturze
                i wciśnięcia ENTER. W każdym miejscu w grze widoczne są numery, które należy
                podać w celu przejścia dalej.
                W grze są dostępne takie miejsca jak:
                - menu wstępne,
                - menu główne, z którego możesz się dostać do wszystkich innych miejsc; pojawia sie tu także 
                 informacja na temat zebranej dotąd ilości punktów,
                - menu poziomów - trzy kolorowe menu z inną muzyką, które wyznaczają poziom
                 trudności danych poziomów,
                - instrukcja - właśnie ją czytasz 😉,
                - sklep, w którym możesz zakupić ciekawostki, zmianę muzyki w menu głównym
                 lub ciekawe opowiadanie.                
                
                """ );
        System.out.println("3. ROZGRYWKA");
        System.out.println("""
                
                W momencie uruchomienia się poziomu przykładowa plansza wygląda następująco:
                
                Informacja o rozpoczętej rozgrywce i planszy.
                                |
                                |
                                ⌄
                """);
        GameHandler gameHandler1;
        gameHandler1 = new GameHandler("LevelOne.properties");
        gameHandler1.launchGame();
        gameHandler1.instruction();
        System.out.println("""
                
                W celu zamiany płytek należy wpisać współrzędne płytek w następujący sposób:
                1. Współrzędna liczbowa pierwszej płytki + ENTER
                2. Współrzędna literowa pierwszej płytki + ENTER (PAMIĘTAJ O WILEKICH LITERACH)
                3. Współrzędna liczbowa pierwszej płytki + ENTER
                4. Współrzędna literowa pierwszej płytki + ENTER (PAMIĘTAJ O WILEKICH LITERACH)
                
                Gra kończy się gdy:
                1. Uzbierasz wystarczającą liczbę punktów.
                2. Skończą Ci sie ruchy.
                Nie martw się - w obu przypadkach zachowujesz zebrane punkty 😉
                
                """);
        System.out.println("4. TWÓRCY");
        System.out.println("""
                
                Dokumentacja:              Biblioteka:               Aplikacja:
                - Daniel Kuciński,         - Anna Satkowska,         - Adam Zientek,
                - Jan Sosnowski,           - Filip Perz,             - Konrad Dumin,
                - Konrad Pióro,            - Jakub Pilimon,          - Mateusz Buda,
                - Maciej Furtak.           - Filip Możdżonek.        - Kacper Małkiewicz.
                
                DZIĘKUJEMY ZA ZAGRANIE W NASZĄ GRĘ!!!!! MAMY NADZIEJĘ, ŻE SIĘ PODOBAŁO !!!!!
                """);

        System.out.println("\nwciśnij ENTER by kontynuować");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
