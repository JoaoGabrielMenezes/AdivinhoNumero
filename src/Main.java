import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Joao Menezes
 * */ 
public class Main {
    private static int numero_aleatorio, escolha, tentativas,delay = 40;
    private static String playAgain = "",iniciar,anim,data;
    private static Scanner sc;
    private static Random random;
    //Cores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void main(String[] args) throws Exception {
        clear();
        Thread.sleep(500);
        _init_();
    }

    public static void clear() throws InterruptedException, IOException{
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }else{
            Runtime.getRuntime().exec("clear");
        }        
    }

    public static void jogar_novamente() throws InterruptedException, IOException {
        tentativas = 0;
        Thread.sleep(1000);
        clear();
        delay = 0;
        _init_();
    }

    public static void finalizar() throws InterruptedException, IOException {
        System.out.println("Obrigado por jogar"); 
        Thread.sleep(1000);
        clear();
        System.exit(0);
    }

    public static void perguntar() throws InterruptedException, IOException {
        System.out.print("Gostaria de jogar novamente? (y/n) ");
        playAgain = sc.next();
        playAgain.substring(0,1);
        playAgain = playAgain.substring(0,1);
           if(playAgain.toLowerCase().equals("s") || playAgain.toLowerCase().equals("y"))
           {  
               jogar_novamente();
           }
           else if(playAgain.toLowerCase().equals("n")){
               finalizar();
           }
           while (playAgain.toLowerCase() != "s" || playAgain.toLowerCase() != "n") {
           perguntar();
        }
    }

    public static void _init_() throws InterruptedException, IOException{
       if (playAgain.equals("s") || playAgain.equals("y")) {
            System.out.println("+-----------------Novo-Jogo-----------------+\n");
           }else{
            System.out.println("+-----------------Bem-Vindo-----------------+\n");
           }
       System.out.print("\tPressione ENTER para Iniciar\n");
       sc = new Scanner (System.in);
       iniciar = sc.nextLine ();
       if (iniciar.equals("")) {
        carregar();
        aleatorio(escolha);
       }
       clear();
       _init_();
    }

    public static void carregar() throws InterruptedException, IOException{
        anim= "|/-\\";
        for (int i = 0 ; i < 101 ; i++) {
            data = "\r" + anim.charAt(i % anim.length()) + " " + i;
            System.out.write(data.getBytes());
            Thread.sleep(delay);
        }
    }

    public static int aleatorio(int escolha) throws InterruptedException, IOException{
        try {
            System.out.println(ANSI_PURPLE+"\n\nTente adivinhar o numero em que estou pensando? (0 - 100)\n"+ANSI_RESET);
            random = new Random();
            numero_aleatorio = random.nextInt(101);
         while(escolha != numero_aleatorio){
             tentativas++;
             System.out.print(">_ ");
             escolha = sc.nextInt();
            if(escolha < numero_aleatorio){
                System.out.println("Maior que -> "+ANSI_YELLOW+escolha+ANSI_RESET);
             }
             else if(escolha > numero_aleatorio){
                System.out.println("Menor que -> "+ANSI_YELLOW+escolha+ANSI_RESET);
             }
             else{
                 System.out.println(ANSI_GREEN+"\n\tMUITO BEM"+ANSI_RESET+"\nSeu numero de tentativas foi: "+ANSI_GREEN+tentativas+ANSI_RESET);
                perguntar();
           }
         }
        } catch (Exception e) {  
            System.err.println(ANSI_RED+"\n\nDigite apenas numeros"+ANSI_RESET);
            Thread.sleep(1000);
            clear();
            delay = 0;
            _init_();
        }
    return escolha;
   }
}
