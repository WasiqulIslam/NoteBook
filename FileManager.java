//programmed by Wasiqul Islam - 7:21 AM 3/20/2008
//updated - 7:46 AM 3/20/2008
//updated - 12:26 AM 4/1/2008
//updated - 1:25 AM 4/1/2008

import java.io.*;
import javax.swing.*;

public class FileManager
{

   public FileManager()
   {
      //do nothing for now
   }

   public void saveFile( String fileContent, String filePath )
      throws IOException
   {

      File file = new File( filePath );
      File parentDirectory = file.getParentFile();
      if( !parentDirectory.exists() )
      {
         parentDirectory.mkdirs();
      }

      DataOutputStream dataOutputStream = 
	 new DataOutputStream( new BufferedOutputStream( new FileOutputStream( filePath, false ) ) );
      for( int i = 0; i < fileContent.length(); i ++ )
      {
         dataOutputStream.writeChar( (int) fileContent.charAt( i ) );
      }
      dataOutputStream.close();

   }

   public String loadFile( String filePath )
      throws IOException
   {
      DataInputStream dataInputStream =
	new DataInputStream(
        new BufferedInputStream(
        new FileInputStream( filePath )
        ) );
      StringBuilder stringBuilder = new StringBuilder();
         // string builder is used
         //as there is not need to thread synchronization
      try
      {
         char c;
         while( true )
         {
            c = dataInputStream.readChar();
            stringBuilder.append( c );
         }
      }
      catch( EOFException eofException )
      {
         //when end of file, end of file exception happens
      }
      dataInputStream.close();
      return stringBuilder.toString();
   }

}

