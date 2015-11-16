package sasscompiler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ziron_000
 */
import java.io.*;
//import java.text.SimpleDateFormat;

public class SassWatcher {
  private File file1;
  private File file2;
  private String sFile1;
  private String sFile2;
  public SassWatcher(String pFile1, String pFile2) {
    file1 = new File(pFile1);
    file2 = new File(pFile2);
    sFile1 = pFile1;
    sFile2 = pFile2;
    System.out.println("Loaded SassWatcher");
  //  System.out.println("Before Format : " + file1.lastModified());
  //  System.out.println(" File one is greater: " + checkFileDates());
    compile();
  }
  public boolean checkFileDates() {
  //  System.out.println("Before Format : " + file1.lastModified());

    if(file1.lastModified() > file2.lastModified()){

      return true;
    }
    return false;
  }
  public void compile() {
      String command = "cmd.exe /c sass -t compressed "+sFile1+" "+sFile2;
      try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  }
}

