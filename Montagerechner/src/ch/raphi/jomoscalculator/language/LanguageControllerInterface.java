package ch.raphi.jomoscalculator.language;

import java.util.List;

public interface LanguageControllerInterface {
	/**
	 * This method returns the name of an GUI element in the specific language
	 * @param type
	 * 		the type of an element (e.g. "menu" 
	 * @param element
	 * @return String: name of the GUI element
	 */
	public String getName(String type, String element);
	/**
	 * This method is to get a list of languages you can choose
	 * @return List<Language>
	 */
	public List<Language> getLanguages();
	/**
	 * sets the language
	 * @param l
	 */
	public void setLanguage(Language l);
}
