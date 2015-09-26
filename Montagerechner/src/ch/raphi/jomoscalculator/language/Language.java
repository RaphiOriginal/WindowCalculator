package ch.raphi.jomoscalculator.language;

public class Language {
	private String language;
	private String file;
	
	public Language(String language, String file){
		this.language = language;
		this.file = file;
	}
	
	public String getName(){
		return language;
	}
	public String getFileName(){
		return file;
	}
}
