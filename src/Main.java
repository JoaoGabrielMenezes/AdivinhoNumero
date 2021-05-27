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
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void main(String[] args) throws Exception {
        init();
    }

    public static void init(){
       System.out.println("+-----------------Bem-Vindo-----------------+\n");
       System.out.print("\tPressione ENTER para começar\n");
       sc = new Scanner (System.in);
       sc.nextLine ();
       aleatorio();
    }

    public static void aleatorio(){

        String maior="#";
        System.out.print('[');
		for(int i = 0; i <43; i++)
		{   
			System.out.print(maior);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
		}    
        System.out.print(']');

     System.out.println(ANSI_PURPLE+"\n\nTente adivinhar o numero em que estou pensando?\n"+ANSI_RESET);
     random = new Random();
     numero_aleatorio = random.nextInt(101);

     while(escolha != numero_aleatorio){
         tentativas++;
         escolha = sc.nextInt();
        if(escolha < numero_aleatorio){
            System.out.println("O numero é maior q "+escolha);
         }
         else if(escolha > numero_aleatorio){
            System.out.println("O numero é menor q "+escolha);
         }
         else{
             System.out.println(ANSI_GREEN+"\nParabéns 🎉"+ANSI_RESET+"\nSeu numero de tentativas foi: "+ANSI_GREEN+tentativas+ANSI_RESET);
             System.out.println("Gostaria de jogar novamente? (s/n)");
             playAgain = sc.next();
             if(playAgain.equals("s"))
             {
                try {
                    //temporizador de execucao da tarefa
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                init();
             }else{
                System.out.println("Obrigado por jogar");                
                try {
                    //temporizador de execucao da tarefa
                    Thread.sleep(1000);
                    //limpar o terminal
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 System.exit(0);
          }
       }
     }
   }
}
