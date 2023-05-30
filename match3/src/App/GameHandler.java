package App;

import Library.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Klasa obejmująca wszystkie rzeczy związane z samą rozgrywką, czyli tym co dzieje się po włączeniu poziomu.
 */
public class GameHandler {
    private final String TILE_SYMBOL = "#";
    private final String configFile;
    private GameLevel gameLevel;
    private GameLevelController glc;
    private Tile[][] board;
    private static Map<Integer,String> vertical =new TreeMap<>();
    private static int pointCounter;


    public GameHandler(String configFile){
        this.configFile =  configFile;

        try {
            gameLevel = new GameLevel(configFile);
        } catch (IOException io) {
            System.out.println("Probably cannot locate config file");
            io.printStackTrace();
        }

        glc = new GameLevelController(gameLevel);
        board = glc.getBoard();
    }

    public void launchGame(){
        System.out.println(ANSI_Colours.RED.getColour() + "Rozpoczęto nową grę!" + ANSI_Colours.RESET.getColour());
        glc.populateBoard();
    }
    private static int getKey(String letter){
        for(int i=1;i<21;i++){
            if (letter.equals(vertical.get(i))){
                return i;
            }
        }
         return 0;
    }

    public void playGame(){
        try {
        vertical.put(1,"A");
        vertical.put(2,"B");
        vertical.put(3,"C");
        vertical.put(4,"D");
        vertical.put(5,"E");
        vertical.put(6,"F");
        vertical.put(7,"G");
        vertical.put(8,"H");
        vertical.put(9,"I");
        vertical.put(10,"J");
        vertical.put(11,"K");
        vertical.put(12,"L");
        vertical.put(13,"M");
        vertical.put(14,"N");
        vertical.put(15,"O");
        vertical.put(16,"P");
        vertical.put(17,"R");
        vertical.put(18,"S");
        vertical.put(19,"T");
        vertical.put(20,"U");

        Properties properties = loadProperties();
        Scanner moveReader = new Scanner(System.in);
        int moves;

        try {
            moves = Integer.parseInt(properties.getProperty("moves"));
        } catch (NullPointerException npe){
            System.out.println("Cannot load the game. Aborting...");
            return;
        }

        int counter = 0;
        while(glc.deleteTilesWithoutPoints()){
            glc.dropTiles();
            glc.populateBoard();
        }
        printGameBoard();
        System.out.println("Punkty do zdobycia w tym poziomie: "+properties.getProperty("goal"));
        while (counter < moves) {
            System.out.println("Pozostałe ruchy: "+(moves-counter));
            System.out.println("Obecne punkty: "+glc.getPoints());
            System.out.println("Sklep: Przetasuj planszę (500p) - W celu zakupu wpisz 20 w pierwszym polu a następnie dowolne wartości w kolejnych.");

            System.out.println("Podaj współrzędną liczbową pierwszej płytki:");
            int tileChosen1 = Integer.parseInt(moveReader.nextLine());
            System.out.println("Podaj współrzędną literową pierwszej płytki:");
            int tileChosen2 = getKey(moveReader.nextLine());
            System.out.println("Podaj współrzędną liczbową drugiej płytki:");
            int tileChosen3 = Integer.parseInt(moveReader.nextLine());
            System.out.println("Podaj współrzędną literową drugiej płytki:");
            int tileChosen4 = getKey(moveReader.nextLine());

            if(tileChosen1==20) {
                if (glc.getPoints()>=500){
                    System.out.println(ANSI_Colours.CYAN.getColour() + "Nowa plansza!" + ANSI_Colours.RESET.getColour());
                    glc.generateNewBoard();
                    while(glc.deleteTiles()){
                        glc.dropTiles();
                        glc.populateBoard();
                    }
                    board = glc.getBoard();
                    printGameBoard();
                    glc.setPoints(glc.getPoints()-500);
                }else {
                    System.out.println("Nie masz wystarczająco pieniędzy w tej rundzie!");
                }

            }else if (glc.isMoveValid(tileChosen1 - 1, tileChosen2 - 1, tileChosen3 - 1, tileChosen4 - 1)|glc.isMoveValid(tileChosen3 - 1, tileChosen4 - 1, tileChosen1 - 1, tileChosen2 - 1)) {
                    glc.swap(tileChosen1 - 1, tileChosen2 - 1, tileChosen3 - 1, tileChosen4 - 1);
                    while (glc.deleteTiles()) {
                        glc.dropTiles();
                        glc.populateBoard();
                        printGameBoard();
                        Thread.sleep(1000);
                    }
                    if (!glc.hasValidMoves()) {
                        glc.generateNewBoard();
                        while (glc.deleteTiles()) {
                            glc.dropTiles();
                            glc.populateBoard();
                        }
                    }
                    counter++;
                } else {
                    System.out.println(ANSI_Colours.RED.getColour()+"Taki ruch nie jest możliwy!!!!\n"+ANSI_Colours.RESET.getColour());
                    printGameBoard();
                }
            if (glc.getPoints()>Integer.parseInt(properties.getProperty("goal"))){
                System.out.println("Brawo! Przeszedłeś poziom!!! Twoje punkty to: "+glc.getPoints() );
                pointCounter =+ glc.getPoints();
                break;
            }
        }
        System.out.println("\nTwoje punkty na koniec poziomu: "+glc.getPoints()+"\n");
        pointCounter =+ glc.getPoints();
        }catch (InputMismatchException ime){
            System.out.println("Złe dane!!!!");
        } catch (NumberFormatException nme){
            System.out.println("Zły input!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void instruction(){
        Properties properties = loadProperties();
        int moves;

        try {
            moves = Integer.parseInt(properties.getProperty("moves"));
        } catch (NullPointerException npe){
            System.out.println("Cannot load the game. Aborting...");
            return;
        }

        int counter = 0;
        while(glc.deleteTilesWithoutPoints()){
            glc.dropTiles();
            glc.populateBoard();
        }
        printGameBoard();
        System.out.println(ANSI_Colours.GREEN.getColour()+"""
                ^         ^
                |_________|
                     |
                Plansza do gry zawierająca płytki oznaczone liczbą oraz literą alfabetu.
                 
                """);
        System.out.println(ANSI_Colours.RESET.getColour()+"Punkty do zdobycia w tym poziomie: "+properties.getProperty("goal")+ ANSI_Colours.GREEN.getColour()+" <- Informacja ile punktów musisz zebrać w danym poziomie.");
        System.out.println(ANSI_Colours.RESET.getColour()+"Pozostałe ruchy: "+(moves-counter)+ANSI_Colours.GREEN.getColour()+" <- Informacja na temat tego, ile ruchów jeszcze pozostało.");
        System.out.println(ANSI_Colours.RESET.getColour()+"Obecne punkty: "+glc.getPoints()+ANSI_Colours.GREEN.getColour()+" <- Informacja na temat tego ile obecnie udało nam sie zebrać punktów w danym poziomie");
        System.out.println(ANSI_Colours.RESET.getColour()+"Sklep: Przetasuj planszę (500p) - W celu zakupu wpisz 20 w pierwszym polu a następnie dowolne wartości w kolejnych.");
        System.out.println(ANSI_Colours.GREEN.getColour()+"^ Za zarobione na tym poziomie punkty (punkty z innych poziomów się nie liczą) możesz zakupić specjalne przetasowanie planszy (polecamy:))^"+ANSI_Colours.RESET.getColour());
    }

    public static int getPointCounter(){
        return pointCounter;
    }
    public static void setPointCounter(int newValue){
        pointCounter = newValue;
    }
    private Properties loadProperties(){
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(getConfigFile());
        try {
            if (is != null) {
                properties.load(is);
                is.close();
            } else {
                System.out.println("Cannot find .properties file");
            }
        } catch (IOException io){
            System.out.println("Something went wrong");
        }
        return properties;
    }

    private void printGameBoard(){
        String[] vertical = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","R","S","T","U"};
        if (board[0].length>9){
            String ones="";
            for (int x=9;x<board[0].length;x++){
              ones += "1";
            }
            System.out.println("          "+ones);
        }
        System.out.print(" ");
        for (int j = 0; j < board[0].length; j++){
            if(j>=9){
                System.out.print(j-9);
            }else {
                System.out.print(j + 1);
            }
        }
        System.out.println();
        for (int i = 0; i < board.length; i++){
            System.out.print(vertical[i]);
            for (int j = 0; j < board[0].length; j++){
                Tile tile = board[i][j];
                switch (tile.getColor()){
                    case BLUE -> System.out.print(ANSI_Colours.BLUE.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                    case PINK -> System.out.print(ANSI_Colours.RED.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                    case BROWN -> System.out.print(ANSI_Colours.BROWN.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                    case GREEN -> System.out.print(ANSI_Colours.GREEN.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                    case ORANGE -> System.out.print(ANSI_Colours.ORANGE.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                    case PURPLE -> System.out.print(ANSI_Colours.PURPLE.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                    case YELLOW -> System.out.print(ANSI_Colours.YELLOW.getColour() + TILE_SYMBOL + ANSI_Colours.RESET.getColour());
                }
            }
            System.out.print("\n");
        }
    }

    public String getConfigFile() {
        return configFile;
    }
}
