import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence {

    private String sentence;

    public Sentence(String sentence_input){
        sentence = sentence_input;
    }
    
    public List<Integer> getBlankPositions(){
        List<Integer> blanks = new ArrayList<Integer>();
        for(int i = 0; i < sentence.length(); i++) {
            String character = sentence.substring(i, i+1);
            if(character.equals(" ")) blanks.add(blanks.size(), i);
        }
        return blanks;
    }

    public int countWords(){
        return getBlankPositions().size() + 1;
    }

    public String[] getWords(){
        List<Integer> blanks = getBlankPositions();
        blanks.add(0, -1);
        blanks.add(blanks.size(), sentence.length());
        String[] words = new String[countWords()];
        for(int i = 1; i <= countWords(); i++){
            words[i-1] = sentence.substring(blanks.get(i-1) + 1, blanks.get(i));
        }
        return words;
    }


    public static void main(String[] args) {
        Sentence sentence = new Sentence("jbbbobo j jd bdf  fvwv wwefw efwef wf wef ");
        System.out.println(Arrays.toString(sentence.getWords()));
    }

}
