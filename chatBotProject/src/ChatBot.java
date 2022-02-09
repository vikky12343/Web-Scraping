import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Scanner;


public class ChatBot {
		 
    public static void main(String[] args) throws IOException {
       Method1();
       Method2();
     
    }

// Method definition to call in another Method
public static void Method1() throws IOException {
	PrintStream o = new PrintStream(new File("C:\\Users\\Asus\\eclipse-workspace\\chatBotProject\\src\\TextReader.txt"));
	 // get district number from user input
    System.out.println("Enter the  district number: ");
    Scanner input = new Scanner(System.in);
    String var1 = input.nextLine();
    var1 = "District " + var1;
    
    // set state_url
    String state_url = "https://www.scstatehouse.gov";
    String all_District_url = "https://www.scstatehouse.gov/member.php?chamber=H";
    
    // find district from user input district number from base url
    boolean f = false;
    Document data = Jsoup.connect(all_District_url).get();
    Elements state = data.getElementsByClass("memberOutline");
    for(Element p : state) {
        Elements var = p.getElementsByTag("a");
        for(Element r : var) {
            if(r.text().equals(var1)) {
                 state_url += r.attr("href");
                 f = true;
                 break;
            }
            if(f)
                break;
        }
    }
    
    // get data from user input district site
    ArrayList<String> result = new ArrayList<>();
    Document state_url_connect = Jsoup.connect(state_url).get();
    
    // get all info
    int count = 0;
    int otherCount = 1;
    Element other = state_url_connect.getElementById("contentsection");
    Elements temp = other.getElementsByTag("div");
    ArrayList<String> h2_list = new ArrayList<>();
    // get all bigger chars
    Elements subTemp1 = other.getElementsByTag("h2");
    for(Element r : subTemp1) 
        h2_list.add(r.text());
    // get all tabbed string
    for(Element l: temp) {
        if(count > 1 && count < 3) {
            Elements subTemp = l.getElementsByTag("p");
            for(Element r: subTemp) {
                result.add(r.text() + "\thaha");
            }
        }
        if (count >= 3) {
            boolean flag = false;
            Elements subTemp = l.getElementsByTag("p");
            for(Element r: subTemp) {
                if(!r.text().equals("") && flag == false) {
                    result.add(h2_list.get(otherCount) + "\thaha");
                    flag = true;
                    otherCount++;
                }
                result.add(r.text());
            }
        }
        count++;
    }        
    // get second topic
    Elements title = state_url_connect.getElementsByTag("tr");
    for(Element x: title) {
        Elements td = x.getElementsByTag("td");
        for(Element a: td) {
            ArrayList<String> tempList = new ArrayList<>();
            Elements h2 = a.getElementsByTag("h2");
            for(Element k: h2) {
                tempList.add(k.text());
            }
            Elements ul = a.getElementsByTag("ul");
            int c = 0;
            for(Element k: ul) {
                Elements li = k.getElementsByTag("li");
                String tempStr = "";
                result.add(tempList.get(c) + "\thaha");
                for(Element p: li) {
                    result.add(p.text());
                }
                c++;
            }
        }
    }
    // get name from url
    Elements h2_name = other.getElementsByClass("barheader");
    String temp_h2_name = "";
    for(Element l : h2_name) {
        temp_h2_name += l.text();
    }
    
//    // save into text file
    PrintStream console = System.out;
    System.setOut(console);
    System.out.println(temp_h2_name);

    for(int i = 0; i < result.size(); i++) {
      if(result.get(i).equals("") || i == 0)
          continue;
      else if(result.get(i).contains("\thaha")) {
    	  PrintStream console1 = System.out;
    	  System.setOut(o);
    	  System.out.println(result.get(i).split("\thaha")[0]);
    	  System.setOut(console1);
          System.out.println(result.get(i).split("\thaha")[0]);
      }
      else {
    	  PrintStream console1 = System.out;
    	  System.setOut(o);
    	  System.out.println("\t" + result.get(i));
    	  System.setOut(console1);
          System.out.println("\t" + result.get(i));
      }
     
  }
   
}

public static void Method2() throws IOException{
	File file = new File("C:\\Users\\Asus\\eclipse-workspace\\chatBotProject\\src\\TextReader.txt");
    FileInputStream fileInputStream = new FileInputStream(file);
    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String line;
    int wordCount = 0;
    int characterCount = 0;
    int paraCount = 0;
    int whiteSpaceCount = 0;
    int sentenceCount = 0;

    while ((line = bufferedReader.readLine()) != null) {
        if (line.equals("")) {
            paraCount += 1;
        }
        else {
            characterCount += line.length();
            String words[] = line.split("\\s+");
            wordCount += words.length;
            whiteSpaceCount += wordCount - 1;
            String sentence[] = line.split("[!?.:]+");
            sentenceCount += sentence.length;
        }
    }
    if (sentenceCount >= 1) {
        paraCount++;
    }
    System.out.println("Total word count = "+ wordCount);
    System.out.println("Total number of sentences = "+ sentenceCount);
    System.out.println("Total number of characters = "+ characterCount);
    System.out.println("Number of paragraphs = "+ paraCount);
    System.out.println("Total number of whitespaces = "+ whiteSpaceCount);
	 }



}

	
