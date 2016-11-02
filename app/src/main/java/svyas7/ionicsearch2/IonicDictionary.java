package svyas7.ionicsearch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class IonicDictionary {
    private Random random = new Random();
    private ArrayList<String> ionicList = new ArrayList<>();
    private HashSet<String> wordSet;
    private HashMap<String,String> ionicCompounds;
    //private HashMap<Integer,ArrayList<String>> sizeToWords;

    public IonicDictionary(InputStream wordListStream) throws IOException {
        wordSet= new HashSet<String>();
        ionicCompounds = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            String name,formula;
            int index = line.indexOf(",");
            word = line.substring(0,index);
            formula=line.substring(index+1);
            ionicCompounds.put(formula,word);
        }
    }

    public ArrayList<String> getList(String targetSymbol)
    {
        for(String key: ionicCompounds.keySet()){
            if(key.toLowerCase().contains(targetSymbol.toLowerCase())) {
                ionicList.add("\u2022 "+key+": \t"+ionicCompounds.get(key));
            }
        }
        return ionicList;
    }
}

