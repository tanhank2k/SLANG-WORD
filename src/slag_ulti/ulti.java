package slag_ulti;

import java.io.*;
import java.util.*;


public class ulti {
	
	//Read file data
	public static HashMap<String, String> ReadFileData(String fileName) {
		FileInputStream fis = null;
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(fis);

		while (scanner.hasNextLine()) {
			String buffer = scanner.nextLine();
			String resultSplit[] = buffer.split("`");
			if (resultSplit.length > 1) {
				data.put(resultSplit[0], resultSplit[1]);
			} else {
				//System.out.println(resultSplit[0]);
			}
		}
		scanner.close();
		return data;
	}
	
	public static void WriteFileData(HashMap<String,String> data) {
		try	{
			File file = new File("./src/data/Slag.txt");
			
			FileWriter fw = new FileWriter(file);
			String[] arrKey = new String[data.size()];
			data.keySet().toArray(arrKey);
			for(int i=0;i<arrKey.length;i++) {
				String out = "";
				out = arrKey[i] + "`" + data.get(arrKey[i])+"\n";
				fw.write(out);
			}
			
			fw.close();
			
		}catch(IOException ex) {
			System.out.println("Error: "+ex);
		}
		
		
		
		
	}
	
	
	public static List<String>  readHistory(String fileName) {
		FileInputStream fis = null;
		List<String> history = new ArrayList<String>();
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(fis);

		while (scanner.hasNextLine()) {
			String buffer = scanner.nextLine();
			history.add(buffer);
		}
		scanner.close();
		
		
		return history;
	}
	
	public static void writeHistory ( List<String> history) {
		try	{
			File file = new File("./src/history/history.txt");
			
			FileWriter fw = new FileWriter(file);
			
			for(int i=0;i<history.size();i++) {
				String out = "";
				out = history.get(i) + "\n";
				fw.write(out);
			}
			
			fw.close();
			
		}catch(IOException ex) {
			System.out.println("Error: "+ex);
		}
		
		
		
	}
	
	//find key by value
	public static Set<String> getKeyByValue(Map<String, String> map, String value) {
		Set<String> setKey = new HashSet<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue().toLowerCase().contains(value.toLowerCase())) {
				setKey.add(entry.getKey());
			}

		}

		return setKey;
	}
	
	//findDefinition
	public static String[] findDefinition(HashMap<String, String> map, String value) {

		String rawResult = map.get(value);
		if(rawResult!= null) {
			String[] result = rawResult.split("\\|");
			for(int i=0; i< result.length;i++) {
				result[i] = result[i].trim();
			}

			return result;
		}
		
		return new String[0];
	}
	
	

		
	//failure confirm to overwrite and duplicate
	public static void addSlangWord(HashMap<String, String> map, String slang, String define) {
		
		Scanner sc = new Scanner(System.in);
		
		if (map.get(slang)==null) {
			map.put(slang, define);
		} 
		else {
			System.out.println("Slang was exist!!!!. Choose action below:");
			System.out.println("1. Overwrite definition!! Enter key 1");
			System.out.println("2. Duplicate definition!! Enter key 2");
			System.out.println("Enter any key (except: key 1 and key 2) to exit!!!");			
			String choose = sc.next();
			
			switch (choose) {
				case "1":
					map.put(slang, define);
					System.out.println("Overwrite the slang successfully!!!");
					System.out.println(map.get(slang));
				break;
				case "2":
					String[] definition_ori = map.get(slang).split("\\|");
					String define_full = definition_ori[0];
					for (int i = 1; i < definition_ori.length; i++) {
						define_full = define_full + "|" + definition_ori[i];
					}

					define_full = define_full + "|" + define;
					System.out.println(define_full);
					
					map.put(slang, define_full);
					System.out.print("New definition of slang:");
					System.out.println(map.get(slang));
				break;
				
				default:
					System.out.println("Error data input!!. Exit feature add slang!!!");
				break;
			}
			
			//sc.close();
		}

	}
	
	//return true false to execute;
	public static void editSlangWord(HashMap<String, String> data, String slang) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose type edition: ");
		System.out.println("1. Choose edit title of slang: enter key 1!");
		System.out.println("2. Choose edit definition of slang:  enter key 2!");
		System.out.println("Exit to enter other key!!!");
		
		String choose  = sc.next();
		sc.nextLine();
		
		String definition  = "";
		switch(choose) {
			case "1":
				
				if(!data.get(slang).isEmpty()) {
					definition = data.get(slang);
					
					System.out.print("Enter new title slang: ");
					String new_slang = sc.nextLine();
					
					ulti.addSlangWord(data, new_slang, definition);
					data.remove(slang);
					System.out.print("Edit successfully!!!");
				}
				else {
					System.out.println(slang + "no exist in data");
				}
				
				break;
				
			case "2"://miss choose 2 method: insert new definition or create new definition
				System.out.print("Enter new definition: ");
				definition = sc.nextLine();
				data.put(slang, definition);
				System.out.println("Edit definition successfully. Exit method edit slang!!!");
				break;
				
			default:
				System.out.println("Exit method edit slang!!!");
				break;
		}
		
		//sc.close();
	}
	
	//delete Slang
	public static void deleteSlang(HashMap<String, String> data) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter slang want to delete: ");
		String slang = sc.nextLine();
		
		if(data.get(slang)!=null) {
			boolean condition = true;
			
			do {
				System.out.println("Do you want to delete " + slang+ " with definition: "+ data.get(slang)+"? [Y/N]");
				String choose = sc.nextLine();
				choose = choose.toLowerCase();
				if(choose.equals("y")) {
					condition = false;
					data.remove(slang);
					System.out.println("Delete successfully!!!");
				}
				else {
					if(choose.equals("n")) {
						condition = false;
						System.out.println("Exit delete slang!!!");
					}
					else{
						System.out.println("Input Wrong!!!");
					}
				
				}
			
			}
			while(condition);
		}
		else {
			System.out.println("The slang no exist in data!!!");
		}
		
		//sc.close();
	}
	
	
	//Reset Data
	public static void resetData(HashMap<String, String> data) {
		
		String fileNameOriginal = "./src/data/Slag_Original.txt";
		
		data = ulti.ReadFileData(fileNameOriginal);
	}
	
	//Random Slang return Slang
	public static String randomSlang(HashMap<String,String> data) {
		Set<String> keySet = data.keySet();
		String[] arrayKey = new String[keySet.size()];
		keySet.toArray(arrayKey);
		
		Random rand = new Random();
		int ranNum = rand.nextInt(keySet.size())+1;
		//System.out.println("On day slang word: " + arrayKey[ranNum] + ": "+ data.get(arrayKey[ranNum]));
		
		return arrayKey[ranNum];
		
	}
	
	public static boolean checkExistInArray(String[] arr, String value) {
		boolean result = false;
		
		for(int i=0; i<arr.length;i++) {
			if(arr[i].equals(value))
				result =true;
		}
		
		return result;
		
	}
	
	public static void questionFunny_ChooseDef(HashMap<String, String> data) {
		String ranSlang = ulti.randomSlang(data);
		
		String [] result = ulti.findDefinition(data, ranSlang);
		
		Random rand = new Random();
		
		String rand_result = result[rand.nextInt(result.length)];
		
		String [] wrongResult = new String[3];
		String [] wrongSlang = {"","",""};
		
		//bind wrongResult
		for(int i =0; i<3; i++){
			String slangWrong = "";
			
			do {
				slangWrong = ulti.randomSlang(data);
				
			}//Check duplicate slang def
			while(!slangWrong.equals(ranSlang)&& checkExistInArray(wrongSlang,slangWrong));
			
			wrongSlang[i] = slangWrong;
			
			String[] WrongResult_Def =   ulti.findDefinition(data, slangWrong);
			int ran_wrongResult = rand.nextInt(WrongResult_Def.length);
			wrongResult[i] = WrongResult_Def[ran_wrongResult];
		}
		
		int result_number = rand.nextInt(4);
		String [] choosen = new String[4];
		choosen[result_number] = rand_result;
		
		//bind choosen
		int k=0;
		for(int i =0 ; i<4; i++) {
			if(i!= result_number) {
				choosen[i] = wrongResult[k];
				k++;
			}
		}
		
		System.out.println("Slang "  + ranSlang + " mean: ");
		for(int i =0 ; i<4; i++) {
			System.out.println(i+1 + ". " + choosen[i]);
		}
		System.out.println("Your choose: ");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		sc.nextLine();
		if(choose == result_number+1) {
			System.out.println("You are right!!!");
		}
		else {
			System.out.println("You are wrong!!! The answer: "+ choosen[result_number]);
		}
		//sc.close();
	}
	
	public static void questionFunny_ChooseSlang(HashMap<String, String> data) {
		String ranSlang = ulti.randomSlang(data);
		
		String [] result = ulti.findDefinition(data, ranSlang);
		
		Random rand = new Random();
		
		String rand_result = result[rand.nextInt(result.length)];
		
		String [] wrongSlang = {"","",""};
		
		//bind wrongResult
		for(int i =0; i<3; i++){
			String slangWrong = "";
			
			do {
				slangWrong = ulti.randomSlang(data);
				
			}//Check duplicate slang def
			while(!slangWrong.equals(ranSlang)&& checkExistInArray(wrongSlang,slangWrong));
			
			wrongSlang[i] = slangWrong;
			
		}
		
		int result_number = rand.nextInt(4);
		String [] choosen = new String[4];
		choosen[result_number] = ranSlang;
		
		//bind choosen
		int k=0;
		for(int i =0 ; i<4; i++) {
			if(i!= result_number) {
				choosen[i] = wrongSlang[k];
				k++;
			}
		}
		
		System.out.println("Definition \""  + rand_result + "\" is mean of Slang: ");
		
		for(int i =0 ; i<4; i++) {
			System.out.println(i+1 + ". " + choosen[i]);
		}
		System.out.println("Your choose: ");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		if(choose == result_number+1) {
			System.out.println("You are right!!!");
		}
		else {
			System.out.println("You are wrong!!! The answer: "+ choosen[result_number]);
		}
		
		
		//sc.close();
	}
	 
	public static boolean exit(String nameFeature) {
		Scanner sc = new Scanner(System.in);
		boolean result = true;
	
		System.out.println("\n-----Do you want to exit " + nameFeature+ "? [Y/N]-----");
		String choose = sc.nextLine();
		choose = choose.toLowerCase();
		if(choose.equals("y")) {
			result = true;

		}
		else {
			if(choose.equals("n")) {
				result = false;
				
			}
			else{
				result = false;
			}
		
		}
		
		return result;
	}

}
