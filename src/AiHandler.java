/*This Class handles the AI part of the application
*/
import java.util.Objects;
import java.util.regex.Pattern;
public class AiHandler {

    /*---------------- unknown finder-------------------------------------------
                returns a string containg the unknown variable
                Takes problem string as input
    */
    public String unknownFnd(String ps) {
        String lasttokens[],sentences[];
        String unknown = null,lastsen;
        int slen,ltlen;
        sentences = ps.split("[.?!]"); //array of sentences
        slen = sentences.length;
        lastsen = sentences[slen-1]; // last sentence
        //System.out.println(lastsen);
        lasttokens = lastsen.split("\\s+");
        ltlen = lasttokens.length; //length of tokens of last sentence
        for(int i=0;i<ltlen;i++) {
            if(null != lasttokens[i])
                switch (lasttokens[i]) {
                    case "initial":
                        unknown = "u";
                        break;
                    case "final":
                        unknown = "v";
                        break;
                    case "acceleration":
                        unknown = "a";
                        break;
                    case "time":
                        unknown = "t";
                        break;
                    case "displacement":
                        unknown = "s";
                        break;
                    default:
                        break;
                }
        }
        return(unknown);
    }


    /*------------------------ sentence splitter -------------------------------
                reruns an array of string containg sentences
    */
    public String[] sentenceSplit(String s) {
        String[] sentences;
        sentences = s.split("[.?!]");
        return(sentences);
    }


    /*----------------------------- tokenizer ----------------------------------
                returns an array containing tokens
    */
    public String[] tokenizer(String s) {
        String[] tokens;
        tokens = s.split("\\s+");
        return(tokens);
    }


    /*------------------------------- data extractor ---------------------------
               returns a string array containg extracted data
               takes array of tokens and length as params
    */
    public String[] strDataExtract(String[] tokens,int len) {
        String data[];
        int n=0,j=0;
        for(int i=0;i<len;i++) {
            if(Pattern.matches("[0-9].+", tokens[i]))
                n+=1;
        }
        data = new String[n];
        for(int i=0;i<len;i++) {
            if(Pattern.matches("[0-9].+", tokens[i])) {
                data[j] = tokens[i];
                j++;
            }
        }
        return(data);
    }

    /*--------------------------------- unknown flag assigner ----------------------
                returns an integer that corresponds to the identifier value to be assigned to the unknown var
                takes unknown var string as input
     */

    public int unFlag(String u) {
        int unknown = 0,i;
        if (Objects.equals(u, "v"))
            unknown = 1;
        else if (Objects.equals(u, "u"))
            unknown = 2;
        else if (Objects.equals(u, "a"))
            unknown = 3;
        else if (Objects.equals(u, "t"))
            unknown = 4;
        else if (Objects.equals(u, "s"))
            unknown = 5;
        return unknown;
    }
}
