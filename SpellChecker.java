
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		//System.out.println(levenshtein("hello", "helo"));
		//System.out.println(tail("hello"));
	}

	public static String tail(String str) {
		String tail = "";
		if(str.length() > 1){
			tail = str.substring(1);
		}
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		if(word1.length() == 0){
			return word2.length();
		}
		if(word2.length() == 0){
			return word1.length();
		}
		if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}
		return 1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2)));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++){
			String word = in.readString();
			dictionary[i] = word;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String correctWord = word;
		int min = threshold;
		for(int i = 0; i < dictionary.length; i++){
			if(levenshtein(word, dictionary[i]) == min){
				correctWord = dictionary[i];
				if(levenshtein(word, dictionary[i]) < min){
					min = levenshtein(word, dictionary[i]);
				}
			}
		}
		return correctWord;
	}

}
