import java.util.Scanner;

public class Main {
    public static int x = 0;
    public static int y = 0;
    public static boolean flag = false;
    public static boolean hasLost = false;
    public static boolean isBored =false;

    public Main() {
    }

    public static void extract(String var0) {
        String var1 = "";
        
        
        if(var0.toLowerCase().equals("exit")){
            
            System.exit(0);
        }else if (var0.substring(0, 1).toLowerCase().equals("f")) {
            flag = true;
            var1 = var0.substring(1);
            System.out.println(var1);
            x = Integer.parseInt(var1.substring(0, 1));
            y = Integer.parseInt(var1.substring(var1.length() - 1));
        } else {
            x = Integer.parseInt(var0.substring(0, 1));
            y = Integer.parseInt(var0.substring(var0.length() - 1));
            flag = false;
        }

    }

    public static void main(String[] var0) {

        while(!isBored){

            Scanner var1 = new Scanner(System.in);
            Game var2 = new Game();
            System.out.println("Welcome to Mine Sweeper!");
            System.out.println("Enter a coordinate '0-9 0-9' (Ex: 36) to begin.");
            extract(var1.nextLine());
            var2.addMines(x, y);
            var2.digSpot(x, y);
            System.out.println(var2.toString());

            while(!var2.checkWin() && !hasLost) {
                System.out.println("Enter a coordinate '0-9 0-9' ");
                System.out.println("Precede the coordinate with 'f' to place a flag");
                System.out.println(var2.getFlagCount() + " flags are available.");

                try{
                extract(var1.nextLine());
                }catch(Exception e){
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.println(var2.toString());
                    System.out.println("Not a valid input!");
                    continue;
                }
                System.out.print("\u001b[H\u001b[2J");
                if (!flag) {
                    hasLost = var2.digSpot(x, y);
                } else {
                    var2.addFlag(x, y);
                }

                System.out.println(var2.toString());
                if (hasLost || var2.checkWin()) {
                    break;
                }
            }

            if (!hasLost) {
                System.out.println("Congratulations, you won!\nPlay Again? (y/n)");
                String vargay = var1.nextLine();
                if(vargay.toLowerCase().equals("y")){
                    continue;
                }

            } 
            var1.close();
            System.exit(0);
        }
    }
}