package com.example.demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableScheduling
public class AutoDelFileApplication {

	public static void main(String[] args) {
		autoDeleteFile();
		SpringApplication.run(AutoDelFileApplication.class, args);
	}

	
	@Scheduled(cron = "0 0/30 * * * ?")
	public  static void autoDeleteFile() {
		String defaultBaseDir = System.getProperty("java.io.tmpdir");
        System.out.println("defaultBaseDir "+defaultBaseDir);
		File dir = new File(defaultBaseDir);
		deleteFolder(dir);
	}
	
	private static void deleteFolder(File file) 
	{
		
		File[] listFiles = file.listFiles();
		if(file.isDirectory()==false)
		{
			System.out.println("Not a directory. Do nothing");
			return;
		}
		for (File subFile : listFiles) {
		       if(subFile.isDirectory()) {
		    	   System.out.println("sb--->"+subFile);
		          deleteFolder(subFile);
		       }
		       else {
		    	   System.out.println("Deleting "+file.getName());
		         subFile.delete();
		       }
		    }

		   file.delete();
	 }
	
	
}
	

