package main.project.indice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String text = "Good programming is not learned from\n"
				+ "generalities, but by seeing how significant\n"
				+ "programs can be made clean, easy to\n"
				+ "read, easy to maintain and modify,\n"
				+ "human-engineered, efficient, and reliable,\n"
				+ "by the application of common sense and\n"
				+ "by the use of good programming practices.\n";
		
		String[] auxlist = new String[] {
				"programming", "programs","easy", "by", 
				"human-engineered", "and", "be", "to"};
		
		List <String> keys = new ArrayList<String>();
		keys.addAll(Arrays.asList(auxlist));
		//System.out.println(keys);
		
		Collections.sort(keys, new Comparator<String>() {
		    public int compare(String s1, String s2) {
		        return s1.compareToIgnoreCase(s2);
		    }
		});
		//System.out.println(keys);
		System.out.println("Criação do índice:\n");
		
		IndiceHash hash = new IndiceHash(text);
		hash.printIndex(keys);
		while(true){
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\nDigite uma palavra a ser pesquisada:");
	        String word = null;
	        try {
	        	word = reader.readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        hash.search(word);
		}
		
	}
	
	public static class IndiceHash{
		
		private Map<String, List<String>> myMap = new HashMap<String,List<String>>();;
		
		
		public IndiceHash(String input){
			
			//Limpar caracteres como virgulas e pontos
			input = input.replaceAll(",","").replaceAll("\\.", "");
			//System.out.println(Arrays.asList(input));
			
			String lines[] = input.split("\\r?\\n");
			
			for(int i = 0; i < lines.length; i++){
				
				String[] words = lines[i].split("\\s+");
				
				for (int j = 0; j < words.length; j++) {
					
					List<String> aux = myMap.get(words[j]);
					if(aux==null){
						aux = new ArrayList<String>();
						aux.add(String.valueOf(i));
					}
					else
						aux.add(String.valueOf(i));
						myMap.put(words[j], aux);
					}		
			}
			//System.out.println(Collections.singletonList(myMap));		
		}
		
		public void printIndex(List <String> keys){
			for(int i = 0; i < keys.size(); i++){
				System.out.println(keys.get(i)+" "+myMap.get(keys.get(i)));
			}
		}
		
		public void search(String word) {
			
			List<String> aux = myMap.get(word);
			
			if(aux!=null)
				System.out.println("Palavra encontrada em: " + aux);
			else
				System.out.println("Palavra nao encontrada");
		}
	}
}
