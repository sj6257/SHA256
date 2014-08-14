package com.sandeep.sha256;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.io.Console;
public class SHA256 {

	public static final int BUFFER_SIZE = 2048;
	 
	 public static byte[] getDigest(InputStream in, String algorithm) throws Throwable {
	  MessageDigest md = MessageDigest.getInstance(algorithm);
	  try {
	   DigestInputStream dis = new DigestInputStream(in, md);
	   byte[] buffer = new byte[BUFFER_SIZE];
	   while (dis.read(buffer) != -1) {
	    //
	   }
	   dis.close();
	  } finally {
	   in.close();
	  }
	  return md.digest();
	 }
	 
	 public static String getDigestString(InputStream in, String algorithm) throws Throwable {
	  byte[] digest = getDigest(in, algorithm);
	  StringBuilder sb = new StringBuilder();
	  for (int i = 0; i < digest.length; i++) {
	   sb.append(String.format("%x", digest[i]));
		//  sb.append(Integer.toHexString(0xFF & digest[i]));
		 // sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
	  }
	  return sb.toString();
	 }
	 
	 public static void main(String[] args) throws Throwable {
	  Console console = System.console();
	  String workingdirectory = System.getProperty("user.dir");
	  String file = console.readLine("FileName? ");
	  String filepath="";
	  //filepath="C:\\Data\\LMSCRW_FXIR_TRADES.TXT";
	  filepath=workingdirectory+"\\"+file;
	  File f = new File(filepath);
	  
	  
	  System.out.println("workingdirectory:"+workingdirectory);
	  System.out.println("File:"+file);
	  System.out.println("MD5: " + getDigestString(new FileInputStream(f), "MD5"));
	  System.out.println("SHA-256: " + getDigestString(new FileInputStream(f), "SHA-256"));
	  System.out.println("SHA-1: " + getDigestString(new FileInputStream(f), "SHA-1"));
      

	  
	  PrintWriter writer = new PrintWriter(file.substring(0,file.indexOf("."))+"_CheckSum.txt", "UTF-8");
	  writer.println("MD5: " + getDigestString(new FileInputStream(f), "MD5"));;
	  writer.println("SHA-256: " + getDigestString(new FileInputStream(f), "SHA-256"));
	  writer.println("SHA-1: " + getDigestString(new FileInputStream(f), "SHA-1"));
	  writer.close();
	  
	 }
	 
	 
}
