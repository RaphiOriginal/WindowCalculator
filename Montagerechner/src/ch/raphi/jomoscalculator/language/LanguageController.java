package ch.raphi.jomoscalculator.language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LanguageController implements LanguageControllerInterface{
	
	private final String LIST_PATH;
	private final String LANGUAGES_PATH;
//	private final String LIST_PATH = "language/languages.json";
//	private final String LANGUAGES_PATH = "language/languages";
	private final String EMPTY_JSON = "{\"languages\":[]}";
	
	private JSONObject languagesObject;
	private List<Language> languages;
	private JSONObject usingLanguage;
	
	
	public LanguageController(String listPath, String languagesPath){
		LIST_PATH = listPath;
		LANGUAGES_PATH = languagesPath;
		languages = readLanguageList();
		checkLanguages();
	}

	@Override
	public String getName(String type, String element) {
		// TODO Auto-generated method stub
		JSONObject elements = usingLanguage.getJSONObject(type);
		return elements.getString(element);
	}

	@Override
	public List<Language> getLanguages() {
		return languages;
	}

	@Override
	public void setLanguage(Language l) {
		String path = LANGUAGES_PATH + "/" + l.getFileName();
		try {
			usingLanguage = new JSONObject(readFile(new File(path)));
		} catch (JSONException e) {
			// TODO maybe bad json syntax
			e.printStackTrace();
		} catch (IOException e) {
			// TODO check read write rights!
			e.printStackTrace();
		}
		
	}

	private File[] getLanguagesFiles(){
		File folder = new File(LANGUAGES_PATH);
		return folder.listFiles();
	}
	private void checkLanguages(){
		File[] files = getLanguagesFiles();
		if(files.length != languages.size()){
			updateLanguagesList();
			languages = readLanguageList();
		}
	}
	private void updateLanguagesList(){
		File[] files = getLanguagesFiles();
		JSONArray languagesArray = new JSONArray();
		for(File f:files){
			try {
				JSONObject json = new JSONObject(readFile(f));
				JSONObject languagePack = new JSONObject();
				languagePack.put("name", json.get("language"));
				languagePack.put("file", f.getName());
				languagesArray.put(languagePack);
			} catch (IOException e) {
				// TODO: Logging, something really bad happened...
			}
		}
		JSONObject newJson = new JSONObject();
		newJson.put("languages", languagesArray);
		File list = new File(LIST_PATH);
		if(list.exists()) list.delete();
		try {
			FileWriter file = new FileWriter(LIST_PATH);
			file.write(newJson.toString());
			file.close();
		} catch (IOException e) {
			// TODO something went wrong, maybe check write rights...
		}
	}
	private String readFile(File f) throws IOException{
		StringBuffer json = new StringBuffer();
		BufferedReader jsonReader = new BufferedReader(new FileReader(f));
		String line = jsonReader.readLine();
		while(line != null){
			json.append(line);
			line = jsonReader.readLine();
		}
		jsonReader.close();
		return json.toString();
	}
	private List<Language> readLanguageList(){
		List<Language> list = new LinkedList<Language>();
		final String LANGUAGE_KEY = "languages";
		File jsonFile = new File(LIST_PATH);
		try {
			languagesObject = new JSONObject(readFile(jsonFile));
		} catch (IOException e) {
			languagesObject = new JSONObject(EMPTY_JSON);
		}
		JSONArray languagesArray = languagesObject.getJSONArray(LANGUAGE_KEY);
		for(int i = 0; i < languagesArray.length(); i++){
			JSONObject obj = languagesArray.getJSONObject(i);
			list.add(new Language(obj.getString("name"), obj.getString("file")));
		}
		return list;
	}
}
