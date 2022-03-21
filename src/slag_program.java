import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import slag_ulti.*;


public class slag_program {
	public static void main(String[] arg) {
		String fileName = "./src/data/Slag.txt";
		HashMap<String, String> data = ulti.ReadFileData(fileName);
		List<String> history = ulti.readHistory("./src/history/history.txt");
		
		
		boolean condition = true;
		
		while(condition) {
			Scanner sc = new Scanner(System.in);
		
			System.out.println("-------------------Program SLANG-WORD!!!!------------------------");
			System.out.println("Choose a feature: ");
			System.out.println("1. Search by Slang-Word.");
			System.out.println("2. Search by Definition.");
			System.out.println("3. Show history: Show all slang-word had been searched.");
			System.out.println("4. Add new Slang-Word.");
			System.out.println("5. Edit a Slang-Word.");
			System.out.println("6. Delete a Slang-Word.");
			System.out.println("7. Reset all data.");
			System.out.println("8. Random a Slang-Word.");
			System.out.println("9. Funny Question: Show a random Slang-word, choose right the definition match Slang-word.");
			System.out.println("10. Funny Question: Show a random Definition, choose right the slang-word match Slang-word.");
			System.out.println("11. Exit the program!!");	
		
			System.out.println("Choose the feature: Enter 1 to 10!");
			System.out.println("Your choice: ");
		
			String choice = sc.next();
			sc.nextLine();
			boolean condition_feature = true;
		
			switch(choice) {
				case "1":
					System.out.println("------------------Search by Slang-Word--------------------------");
					while(condition_feature) {
						
						System.out.print("Enter Slang-word: ");
						String slang = sc.nextLine();
						String[] result = ulti.findDefinition(data, slang);
				
						history.add(slang);
						ulti.writeHistory(history);
					
				
						if(result.length>0) {
							System.out.print("Definition \""+slang +  "\": ");
							for(int i=0; i<result.length; i++) {
								System.out.print(result[i]+"\t");
							}
						}
						else {
							System.out.println("\""+slang + "\" no exist in data");
						}
						//Exit condition
						if(ulti.exit("Search by Slang-Word")) {
							condition_feature = false;
						}
					}
				
				break;
				
				case "2":
				
					System.out.println("------------------Search by Definition--------------------------");
				
					while(condition_feature) {
					
						System.out.print("Enter Definition: ");
						String definition = sc.nextLine();
						Set<String> key_result = ulti.getKeyByValue(data, definition);
						String[] arrKey = new String[key_result.size()];
						key_result.toArray(arrKey);
				
						if(key_result.size()>0) {
							System.out.println("All Slang-word in definition has contain \""+definition +  "\": ");
							for(int i=0; i<arrKey.length; i++) {
								System.out.print("\"" + arrKey[i]+"\" ");
							}
						}
						else {
							System.out.println("\""+definition + "\" no exist in data");
						}
						//Exit condition
						if(ulti.exit("Search by Definition")) {
							condition_feature = false;
						}
					}
				break;
				
				case "3"://Show history
					System.out.println("------------------Show history-------------------------------");
					while(condition_feature) {
						for(int i=0; i<history.size();i++) {
							System.out.println(history.get(i));
						}
					
						//Exit condition
						if(ulti.exit("Show history")) {
							condition_feature = false;
						}
					}
					break;
				case "4"://Add Slang-word
					System.out.println("------------------Add new Slang-Word--------------------------");
					while(condition_feature) {
					
						System.out.print("Enter new slang word: ");
						String newSlang = sc.nextLine();

						System.out.print("Enter new definition: ");
						String newDefine = sc.nextLine();
				
						ulti.addSlangWord(data, newSlang, newDefine);
				
						//Exit condition
						if(ulti.exit("Add new Slang-Word")) {
							condition_feature = false;
						}
						ulti.WriteFileData(data);
					}
				
					break;
				case "5"://Edit Slang-word
					System.out.println("------------------Edit a Slang-Word--------------------------");
					while(condition_feature) {
					
						System.out.print("Enter slang want to edit: ");
						String slang_edit = sc.nextLine();
						ulti.editSlangWord(data,slang_edit);
						//Exit condition
						if(ulti.exit("Edit a Slang-Word")) {
							condition_feature = false;
						}
						ulti.WriteFileData(data);
					}
				
				break;
				
				case "6"://delete slang
					System.out.println("------------------Delete a Slang-Word-------------------------------");
					while(condition_feature) {
					
						ulti.deleteSlang(data);
						//Exit condition
						if(ulti.exit("Delete a Slang-Word")) {
							condition_feature = false;
						}
						ulti.WriteFileData(data);
					}
					
				break;
				case "7"://reset data
					System.out.println("------------------Reset all data------------------------------");
					while(condition_feature) {
					
						String fileNameOriginal = "./src/data/Slag_Original.txt";
						
						data = ulti.ReadFileData(fileNameOriginal);
						//Exit condition
						if(ulti.exit("Reset all data")) {
							condition_feature = false;
						}
						ulti.WriteFileData(data);
					}
				
				break;
				case "8"://random 1 slang-word
					System.out.println("------------------Random a Slang-Word------------------------------");
					while(condition_feature) {
					
						String random_slang = ulti.randomSlang(data);
						String[] DefRanSlang = ulti.findDefinition(data, random_slang);
						if(DefRanSlang.length>0) {
							System.out.println("Random Slang: \""+random_slang +  "\" ");
							System.out.print("Definition: ");
							for(int i=0; i<DefRanSlang.length; i++) {
								System.out.print(DefRanSlang[i]+"\t");
							}
						}
						//Exit condition
						if(ulti.exit("Random a Slang-Word")) {
							condition_feature = false;
						}
					}
				
				break;
				case "9":
					System.out.println("------------------Funny Question: Show a random Slang-word ------------------------------");
					while(condition_feature) {
					
						ulti.questionFunny_ChooseDef(data);
					
						//Exit condition
						if(ulti.exit("Funny Question: Show a random Slang-word")) {
							condition_feature = false;
						}
					}	
				
					break;
					
				case "10":
					System.out.println("------------------Funny Question: Show a random Definition-------------------------------");
					while(condition_feature) {
					
					
						ulti.questionFunny_ChooseSlang(data);
						//Exit condition
						if(ulti.exit("Funny Question: Show a random Definition")) {
							condition_feature = false;
						}
					}
				break;
				
				case "11"://exit
					condition = false;
					ulti.WriteFileData(data);
				break;
				
				default:
					System.out.println("Wrong Input!!!");
				break;
		}
		
	
			if(!condition)
				sc.close();
		}	
		
		
		
	}
}
