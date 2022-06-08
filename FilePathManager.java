//programmed by Wasiqul Islam -7:48 AM 3/20/2008
//updated - 6:59 PM 3/21/2008
//updated - 5:38 PM 3/26/2008

import java.util.*;
import java.io.*;

public class FilePathManager
{

   private String rootDirectory = null;
   private String fileExtension = null;
   private String specialFileName = null;

   public FilePathManager( String rootDirectory,
      String fileExtension, String specialFileName )
   throws IOException
   {

      this.rootDirectory = rootDirectory;
      this.fileExtension = fileExtension;
      this.specialFileName = specialFileName;

      //create special file if not exists
      String fileNamePart = "";
      for( int i = 1; i <= 4; i ++ )
      {
         if( i != 1 )
         {
            fileNamePart = "" + i;
         }
         File file = new File( rootDirectory + specialFileName
            + fileNamePart + fileExtension );
         File parentDirectory = file.getParentFile();
         if( !parentDirectory.exists() )
         {
            parentDirectory.mkdirs();
         }
         if( !file.exists() )
         {
            new FileWriter( file, false ).close();
         }
      }

   }

   public String getRootDirectory()
   {
      return rootDirectory;
   }

   public String getFileExtension()
   {
      return fileExtension;
   }


   public String getTodaySPath()
   {
      Calendar rightNow = Calendar.getInstance();
      String path = String.format( "%1$tY/%1$tm/%1$td", rightNow );
      path = rootDirectory + path + fileExtension;
      return path;
   }

   public String getSpecialFilePath( int fileNo )  //no from 1 to 4
   {
      if( fileNo >= 1 && fileNo <= 4 )
      {
         String fileNamePart = "";
         if( fileNo != 1 )
         {
            fileNamePart = "" + fileNo;
         }
         return rootDirectory + specialFileName
            + fileNamePart + fileExtension;
      }
      return null;
   }

}