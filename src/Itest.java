import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import java.util.Scanner;

/**
 * Created by Administrator on 5/16/2018.
 */
public class Itest {
    public static void main(String[] args) {
        AiHandler aiHandler = new AiHandler();
        SolveEquation solveEquation = new SolveEquation();
        float[][] uv;
        String[] tokens, strData;
        String ps = "sdasfas 3m/s sdgsagd 44.3s sdgsg 88.9m";
        tokens = aiHandler.tokenizer(ps);
        strData = aiHandler.strDataExtract(tokens,tokens.length);
        for (String data: strData)
            System.out.println(data);
        uv = solveEquation.valunitSeparate(strData, ps);
        for (int i=0;i<3;i++) {
            for (int j=0;j<2;j++) {
                System.out.print(uv[i][j]+" ");
            }
            System.out.println();
        }
    }
}
