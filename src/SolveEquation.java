import java.util.Objects;
import java.util.regex.Pattern;
public class SolveEquation {
    AiHandler ai = new AiHandler();

    /*
    ------------------------------ value and unit separator ---------------------------------------
                   returns a 2D float array containing value in 1st col and unit flag in the 2nd col
                   takes array of extracted string and problem statement as input
     */
    public float[][] valunitSeparate(String[] data, String ps) {
        int eq=0,i;
        int u=0,sum=0;
        String units[],values[];
        String unknown;
        int un[];
        float[][] val = new float[3][2];
        un = new int[3];                              //stores integer values corresponding to each var
        units = new String[3];
        values = new String[3];

        for(i=0;i<3;i++) {
            units[i] = "";
            values[i] = "";
        }

        for(i=0;i<3;i++) {
            for(int j=0;j<data[i].length();j++) {
                String c = "";
                c=c+data[i].charAt(j);
                if(Pattern.matches("[0-9]*\\.?[0-9]*",c)) {
                    values[i] = values[i]+c;
                } else {
                    units[i] = units[i]+c;
                }
            }
        }

        unknown = ai.unknownFnd(ps);                        //contains the unknown var

        for (i=0;i<3;i++) {
            if (Objects.equals(units[i], "m/ssq"))
                un[i] = 3;
            else if (Objects.equals(units[i], "s"))
                un[i] = 4;
            else if(Objects.equals(units[i], "m"))
                un[i] = 5;
            else if (Objects.equals(units[i], "m/s")) {
                if (Objects.equals(unknown, "u"))
                    un[i] = 1;
                else if (Objects.equals(unknown, "v"))
                    un[i] = 2;
                else
                    un[i] = 2;
            }
        }

        for (i=0;i<3;i++) {
            val[i][0] = Float.parseFloat(values[i]);
            val[i][1] = un[i];
        }

        return (val);
    }


    /*
    ---------------------------------- equation decider ---------------------------------------
                returns the flag(int) that points to the equation to be used
                 takes a string array containg extracted data and problem statement
                 eq=1 => v=u+at
                 eq=2 => s=ut+0.5at*t
                 eq=3 => v*v-u*u=2as

                 v =>1
                 u =>2
                 a =>3
                 t =>4
                 s =>5
     */

    public int eqnDecide(float[][] val, String ps) {
        int eq=0,u=0,sum=0,i;
        String unknown;
        unknown = ai.unknownFnd(ps);                        //contains the unknown var
        u = ai.unFlag(unknown);
        for (i=0;i<3;i++)
            sum+=val[i][1];
        sum+=u;
        System.out.println(sum);
        if (sum == 10)
            eq = 1;
        else if (sum == 14)
            eq = 2;
        else if (sum == 11)
            eq =3;
        return eq;
    }


    /*
    ---------------------------------- equation solver ------------------------------------------
                returns a float value which is the solution to the problem
                takes 2D float array containing the value-unit pairs, the unknown flag, equation flag
     */

    public float solveEqun(float[][] values, int uf, int eq) {
        float sol=0;
        float v=0,u=0,a=0,t=0,s=0;
        for (int i=0;i<3;i++) {
            for (int j=0;j<2;j++) {
                if (values[i][1] == 1)
                    v = values[i][0];
                else if (values[i][1] == 2)
                    u = values[i][0];
                else if (values[i][1] == 3)
                    a = values[i][0];
                else if (values[i][1] == 4)
                    t = values[i][0];
                else if (values[i][1] == 5)
                    s = values[i][0];
            }
        }

        if (eq == 1) {
            if (uf == 1) {
                sol = u+(a*t);
                //System.out.println("u:"+u);
            }
            else if (uf == 2) {
                sol = v - (a*t);
                //System.out.println("V:"+v);
            }

            else if (uf == 3)
                sol = (v-u)/t;
            else if (uf == 4)
                sol = (v-u)/a;
        }

        else if (eq == 2) {
            if (uf == 5)
                sol = (float) ((u*t) + (0.5*a*t*t));
            else if (uf == 2)
                sol = (float) ((s-(0.5*a*t*t))/t);
            else if (uf == 3)
                sol = (float) ((s-(u*t))/(0.5*t*t));
            else if (uf == 4) {
                float sol1 = (float) ((-u+Math.sqrt(u*u+(4*0.5*a*t*t*s)))/(2*u));
                float sol2 = (float) ((-u-Math.sqrt(u*u+(4*0.5*a*t*t*s)))/(2*u));
                if (sol1<0)
                    sol = sol2;
                else
                    sol = sol1;
            }
        }

        else if (eq == 3) {
            if (uf == 1)
                sol = (float) Math.sqrt((2*a*s)-(u*u));
            else if (uf == 2)
                sol = (float) Math.sqrt((v*v)-(2*a*s));
            else if (uf == 3)
                sol = ((v*v)-(u*u))/(2*s);
            else if (uf == 5)
                sol = ((v*v)-(u*u))/(2*a);
        }

        return sol;
    }
}
