import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 5/21/2018.
 */
public class Test0 {
    public static void main(String[] args) {
        AiHandler aiHandler = new AiHandler();
        SolveEquation solveEquation = new SolveEquation();

        String unknown;
        String ps;
        float solution;
        int uflag;
        String[] tokens, strData;
        int tokenLen, eqn=0;
        float[][] value;
        ps = "sdfsafasd 3m/s fdsgsdfg";
        tokens = aiHandler.tokenizer(ps);
        tokenLen = tokens.length;
        strData = aiHandler.strDataExtract(tokens,tokenLen);
        value = solveEquation.valunitSeparate(strData,ps);
        unknown = aiHandler.unknownFnd(ps);
        uflag = aiHandler.unFlag(unknown);
        eqn = solveEquation.eqnDecide(value,ps);
        solution = solveEquation.solveEqun(value,uflag,eqn);
        System.out.print(solution);
        if (uflag == 1)
            System.out.println("m/s");
        else if (uflag == 2)
            System.out.println("m/s");
        else if (uflag == 3)
            System.out.println("m/ssq");
        else if (uflag == 4)
            System.out.println("s");
        else if (uflag == 5)
            System.out.println("m");

        for (int i=0;i<3;i++) {
            for (int j=0;j<2;j++) {
                System.out.print(value[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("uflag:"+uflag);
        System.out.println("eqf:"+eqn);
        System.out.println("unknown:"+unknown);
    }
}
