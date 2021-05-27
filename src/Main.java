import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * 
 * @author Joao Menezes
 * 
 * */ 
public class Main{
    private static int numero_aleatorio;
    private static int escolha;
    private static int tentativas;
    private static String playAgain;
    private static Scanner sc;
    private static Random random;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args) throws Exception {
        _init_();
    }

    public static void _init_() throws InterruptedException, IOException{
       System.out.println("+-----------------Bem-Vindo-----------------+\n");
       System.out.print("\tPressione ENTER para começar\n");
       sc = new Scanner (System.in);
       sc.nextLine ();
       carregar();
       aleatorio();
    }

    public static void carregar() throws InterruptedException, IOException{
        String anim= "|/-\\";
        for (int i = 0 ; i < 101 ; i++) {
            String data = "\r" + anim.charAt(i % anim.length()) + " " + i;
            System.out.write(data.getBytes());
            Thread.sleep(65);
        }
    }

    public static void aleatorio() throws InterruptedException, IOException{

        try {
            System.out.println(ANSI_PURPLE+"\n\nTente adivinhar o numero em que estou pensando?\n"+ANSI_RESET);
            random = new Random();
            numero_aleatorio = random.nextInt(101);
    
         while(escolha != numero_aleatorio){
             tentativas++;
             escolha = sc.nextInt();
            if(escolha < numero_aleatorio){
                System.out.println("O numero é maior que -> "+ANSI_YELLOW+escolha+ANSI_RESET);
             }
             else if(escolha > numero_aleatorio){
                System.out.println("O numero é menor que -> "+ANSI_YELLOW+escolha+ANSI_RESET);
             }
             else{
                 System.out.println(ANSI_GREEN+"\nParabéns 🎉"+ANSI_RESET+"\nSeu numero de tentativas foi: "+ANSI_GREEN+tentativas+ANSI_RESET);
                 System.out.println("Gostaria de jogar novamente? (s/n)");
                 playAgain = sc.next();
                 if(playAgain.equals("s"))
                 {
                    //temporizador de execucao da tarefa
                    Thread.sleep(1000);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    _init_();
                 }else{
                    System.out.println("Obrigado por jogar");                
                    //temporizador de execucao da tarefa
                    Thread.sleep(1000);
                    //limpar o terminal
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.exit(0);
              }
           }
         }
        } catch (Exception e) {  
            System.err.println(ANSI_RED+"\n\nDigite apenas numeros"+ANSI_RESET);
            Thread.sleep(1000);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            _init_();
        }
   }
}
