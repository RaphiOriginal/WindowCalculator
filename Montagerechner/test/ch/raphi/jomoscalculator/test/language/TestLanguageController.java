package ch.raphi.jomoscalculator.test.language;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.raphi.jomoscalculator.language.Language;
import ch.raphi.jomoscalculator.language.LanguageController;

public class TestLanguageController {
	private final String path1 = "testFolder/test.json";
	private final String path2 = "testFolder/testFolder";
	private final String listString = "{\"languages\":[{\"file\":\"deutsch.json\","
			+ "\"name\":\"Deutsch\"}]}";
	private final String languageString ="{\"language\":\"Deutsch\",\"menu\":"
			+ "{\"file\":\"Datei\",\"info\":\"Info\",\"exit\":\"Schliessen\""
			+ ",\"languages\":\"Sprachen\"}}";
	private JSONObject testList;
	private JSONObject testLanguage;
	
	@Before
	public void setUp(){
		testList = new JSONObject(listString);
		testLanguage = new JSONObject(languageString);
		writeFile(path1, testList);
		writeFile(path2 + "/deutsch.json", testLanguage);
	}
	@After
	public void cleanUp(){
		deleteFile(path1);
		deleteFile(path2 + "/deutsch.json");
	}
	@Test
	public void listFileNotFoundTest(){
		deleteFile(path1);
		File t = new File(path1);
		assertFalse(t.exists());
		@SuppressWarnings("unused")
		LanguageController classUnderTest = new LanguageController(path1, path2);
		File f = new File(path1);
		assertTrue(f.exists());
		assertEquals(readFile(path1), listString);
	}
	@Test
	public void getNameAndSetLanguageTest(){
		LanguageController classUnderTest = new LanguageController(path1, path2);
		classUnderTest.setLanguage(new Language("Deutsch", "deutsch.json"));
		assertEquals(classUnderTest.getName("menu", "file"),"Datei");
		assertEquals(classUnderTest.getName("menu", "info"),"Info");
		assertEquals(classUnderTest.getName("menu", "exit"),"Schliessen");
		assertEquals(classUnderTest.getName("menu", "languages"),"Sprachen");
	}
	@Test
	public void getLanguagesTest(){
		LanguageController classUnderTest = new LanguageController(path1, path2);
		List<Language> list = classUnderTest.getLanguages();
		assertEquals(list.size(),1);
	}
	
	private void writeFile(String path, JSONObject json){
		try {
			FileWriter file = new FileWriter(path);
			file.write(json.toString());
			file.close();
		} catch (IOException e) {
			// TODO something went wrong, maybe check write rights...
			e.printStackTrace();
		}
	}
	
	private void deleteFile(String path){
		File file = new File(path);
		if(file.exists()) file.delete();
	}
	private String readFile(String path){
		StringBuffer json = new StringBuffer();
		try{
			BufferedReader jsonReader = new BufferedReader(new FileReader(new File(path)));
			String line = jsonReader.readLine();
			while(line != null){
				json.append(line);
				line = jsonReader.readLine();
			}
			jsonReader.close();
		} catch (IOException e){
			//TODO something went wrong, maybe check read rights...
			e.printStackTrace();
		}
		return json.toString();
	}
}
