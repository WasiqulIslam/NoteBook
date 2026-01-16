package wasiqul.islam.notebook;

import java.io.*;
import java.util.*;
import javax.swing.*;

//programmed by Wasiqul Islam - 5:34 PM 3/21/2008
// updated - 6:11 PM 3/21/2008
// updated - 12:00 AM 4/2/2008
//Update started by Wasiqul Islam on 16/01/2026

public class Settings
{

   private Hashtable hashTable = null;

   public Settings()
   throws IOException, UserException
   {
      BufferedReader bufferedReader = 
         new BufferedReader( new FileReader( "Settings.txt" ) );
      hashTable = new Hashtable();
      String key = null, value = null;
      for( int i  = 0; i < 200; i++ ) //max 100 settings are allowed
      {
         key = null;
         value = null;
         key = bufferedReader.readLine();
         value = bufferedReader.readLine();
         if( key == null || value == null )
         {
            break;
         }
         hashTable.put( key.trim(), new String( value ) );
      }
      bufferedReader.close();

      checkKey( "rootDirectory" );
      checkKey( "specialFileName" );
      checkKey( "fileExtension" );
      checkKey( "fileExtension" );
      checkKey( "dateFormat" );
      checkKey( "useDefaultEncryptionPassword" );
      checkKey( "defaultEncryptionPassword" );

   }

   public String getValue( String key )
   {
      return "" + hashTable.get( key );
   }

   public void checkKey( String key )
   throws UserException
   {
      String keyValue = (String)hashTable.get( key );
      if( keyValue == null || keyValue.trim().equals("") )
      {
         throw new UserException( "Settings key not found:\n\r" + key );
      }
   }

}