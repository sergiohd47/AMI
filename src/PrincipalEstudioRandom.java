/**
 * @author Sergio Hernandez Dominguez
 */
public class PrincipalEstudioRandom {

    public static void main(String args[]){
        int intervalo0025=0;
        int intervalo025050=0;
        int intervalo050075=0;
        int intervalo0751=0;
        for(int i=0;i<3000;i++){
            float numeroAleatorio=(float)Math.random();
            if(numeroAleatorio>0&&numeroAleatorio<0.25){
                intervalo0025++;
            }else if(numeroAleatorio>0.25&&numeroAleatorio<0.50){
                intervalo025050++;
            }else if(numeroAleatorio>0.50&&numeroAleatorio<0.75){
                intervalo050075++;
            }else{
                intervalo0751++;
            }
        }
        float prob0025=(float)intervalo0025/3000;
        float prob025050=(float)intervalo025050/3000;
        float prob050075=(float)intervalo050075/3000;
        float prob0751=(float)intervalo0751/3000;

        System.out.println("INTERVALO 0-0.25: "+intervalo0025);
        System.out.println("INTERVALO 0.25-0.50: "+intervalo025050);
        System.out.println("INTERVALO 0.50-0.75: "+intervalo050075);
        System.out.println("INTERVALO 0.75-1: "+intervalo0751);
        System.out.println(" ");
        System.out.println("PROBABILIDAD 0-0.25: "+prob0025);
        System.out.println("PROBABILIDAD 0.25-0.50: "+prob025050);
        System.out.println("PROBABILIDAD 0.50-0.75: "+prob050075);
        System.out.println("PROBABILIDAD 0.75-1: "+prob0751);
    }
}
