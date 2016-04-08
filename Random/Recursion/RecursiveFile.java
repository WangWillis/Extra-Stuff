import java.io.File;

public class RecursiveFile{
	public static void dirTravel(File fl, String indent){
		if(fl != null){
			File[] listOfFiles = fl.listFiles();
			System.out.println(indent + fl.getName());
			if(listOfFiles != null){
				indent += '\t';
				for(int i = 0; i < listOfFiles.length; i++){
					if(!listOfFiles[i].isHidden()){
						if(listOfFiles[i].isDirectory()){
							dirTravel(listOfFiles[i], indent);
						}else{
							System.out.println(indent + listOfFiles[i].getName());
						}
					}
				}	
			}
		}
	}
	public static void main(String [] args){
		File file = new File("C:/Users/Willis Wang/Desktop/test");
		dirTravel(file, "");
	}
}