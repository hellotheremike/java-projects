package DYPL;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class NumberConverter {
	
	private String digit_map[] = {"E", "JNQ", "RWX", "DSY", "FT", "AM", "CIV", "BKU", "LOP","GHZ"};
	private HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

	public int load_file(String filePath){
	    File file = new File(filePath);
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    long start = System.currentTimeMillis();
	    try {
	        fis = new FileInputStream(file);
	        bis = new BufferedInputStream(fis);
	        dis = new DataInputStream(bis);

	        while (dis.available() != 0) {
	          String line = dis.readLine();
	          String number = convertToNumber(line);
	          if(dictionary.get(number) == null){
	        	  dictionary.put(number, new ArrayList<String>());
	        	  dictionary.get(number).add(line);
	          }
	          else
	        	  dictionary.get(number).add(line);
	        }
	        fis.close();
	        bis.close();
	        dis.close();

	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    long end = System.currentTimeMillis();
	    System.out.println("Load dictionary in: " + (end - start)+"ms"); 
		return dictionary.keySet().size();
	}	
	private String convertToNumber(String word){
		String result = "";
		for (char letter: word.toCharArray()) {
			for(int i = 0; i < this.digit_map.length; i++){
				if(digit_map[i].indexOf(Character.toString(letter).toUpperCase()) >= 0){
					result += i;
				}
			}
		}
		return result;
	}	
	public String convert(String number){
		long start = System.currentTimeMillis();
		ArrayList<ArrayList<String>> wordList = new ArrayList<ArrayList<String>>();
		ArrayList<String> combinations = new ArrayList<String> ();
		String front = number;
		for(char forwards: number.toCharArray()) {
			String back = front;
			for(char backwards: front.toCharArray()) {
				if(this.dictionary.get(back) != null){
					wordList.add(this.dictionary.get(back));
				}
				back = back.substring(0, back.length()-1);
			}
			front = front.substring(1);
		}
		
		for(int x = 0; x < wordList.size(); x++){
			for(int i = 0; i < wordList.get(x).size(); i++){
				String combination = number;
				combination = combination.replaceAll(convertToNumber(wordList.get(x).get(i).toString()), wordList.get(x).get(i).toString());
				for(int z = 0; z < wordList.size(); z++){
					for(int q = 0; q < wordList.get(z).size(); q++){
						combination = combination.replaceAll(convertToNumber(wordList.get(z).get(q).toString()), wordList.get(z).get(q).toString());
					}
				}
				combinations.add(combination);
			}
		}
		String result = filterCombinations(combinations);
		long end = System.currentTimeMillis();
		System.out.println("Calculate number combination: " + (end - start)+"ms"); 
		return result;
	}
	private int countLetters(String combination){
		int result = 0;
		for(char letter: combination.toCharArray()) {
			if(Character.isLetter(letter)){
				result++;
			}
		}
		return result;
	}
	private boolean countDigits(String combination){
		int result = 0;
		for(char digit: combination.toCharArray()) {
			if(Character.isDigit(digit)){
				result++;
				if(result == 2){
					return false;
				}
			}
			else if(Character.isLetter(digit)){
				result = 0;
			}
		}
		return true;
	}	
	private String filterCombinations(ArrayList<String> list){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for(int i = 0; i < list.size(); i++){		
			if(list.get(i).length() == countLetters(list.get(i))){
				if(result.get("1") == null){
					result.put("1", new ArrayList<String>());
					result.get("1").add(list.get(i));
				}else{
					result.get("1").add(list.get(i));
				}	
			}
			else if( Character.isLetter(list.get(i).charAt(0)) && countDigits(list.get(i))){
				
				if(result.get("2") == null){
					result.put("2", new ArrayList<String>());
					result.get("2").add(list.get(i));
				}else{
					result.get("2").add(list.get(i));
				}	
			}
			else if( Character.isDigit(list.get(i).charAt(0)) && countDigits(list.get(i)) && result.get("2") != null && result.get("1") != null){
				if(result.get("3") == null){
					result.put("3", new ArrayList<String>());
					result.get("3").add(list.get(i));
				}else{
					result.get("3").add(list.get(i));
				}	
			}
			else{
				if(result.get("Faulty") == null){
					result.put("Faulty", new ArrayList<String>());
					result.get("Faulty").add(list.get(i));
				}else{
					result.get("Faulty").add(list.get(i));
				}	
			}
		}
		if(result.size() > 0)
			return result.toString();
		else
			return "No valid combinations.";
		
	}

	
	public static void main(String[] args) {
		NumberConverter a = new NumberConverter();
		a.load_file("C:\\dic.txt");
		System.out.println(a.convert("4824"));
		System.out.println(a.convert("10789135"));
		System.out.println(a.convert("107835"));
		System.out.println(a.convert("07372192333"));
	}
}
