//programmed by Wasiqul Islam - 6:15 PM 3/21/2008
//updated - 8:10 PM 3/21/2008
//updated - 11:56 PM 3/23/2008
//updated - 1:16 AM 3/25/2008
//updated - 5:38 PM 3/26/2008
//updated - 1:57 AM 4/1/2008
//updated - 12:35 AM 4/2/2008
//updated - 9:15 PM 4/2/2008
//updated - 10:37 AM 5/4/2008

import java.io.*;
import javax.swing.*;
import java.awt.*; //for GridLayout

public class NoteBook
{

   private String currentFile = null;
   private FilePathManager filePathManager = null;
   private Settings settings = null;
   private Encryptor encryptor = null;
   private FileManager fileManager = null;
   private final String defaultKeyUsed = "Default key is used!!!";

   public NoteBook()
   throws IOException, UserException
   {
      settings = new Settings();
      //rootDirectory is not null, which is checked in Settings constructor
      String rootDirectory = settings.getValue( "rootDirectory" );
      char c = rootDirectory.charAt( rootDirectory.length() - 1 );
      if( !( c == '/' || c == '\\' ) )
      {
         throw new UserException( "Root directory must end with a slash(/ or \\)\n\re.g. \"D:/NoteBook/\"." );         
      }
      filePathManager = new FilePathManager( 
         rootDirectory , settings.getValue( "fileExtension" ),
         settings.getValue( "specialFileName" ) );
      String useDefaultEncryptionPassword
         = settings.getValue( "useDefaultEncryptionPassword" ).toLowerCase().trim();
      String encryptionPassword
         = settings.getValue( "defaultEncryptionPassword" );
      if( !useDefaultEncryptionPassword.equals( "yes" )  )
      {
         //take key from the user( take key twice for typing error detection )
         JPasswordField keyField = new JPasswordField();
         JPasswordField keyField2 = new JPasswordField();
         JLabel label = new JLabel( "Key:" );        
         JLabel label2 = new JLabel( "Key(again):" );
         JPanel panel = new JPanel();
         panel.setLayout( new GridLayout( 2,2,0,0 ) );
         panel.add( label );
         panel.add( keyField );
         panel.add( label2 );
         panel.add( keyField2 );
         int result = 
            JOptionPane.showConfirmDialog( null, panel, "Please enter your key",
            JOptionPane.OK_CANCEL_OPTION );
         if( result == JOptionPane.OK_OPTION )
         {
            String encryptionPassword2 = null;
            encryptionPassword = new String( keyField.getPassword() );
            encryptionPassword2 = new String( keyField2.getPassword() );
            if( encryptionPassword == null ||
               encryptionPassword.trim().equals( "" ) )
            {
               encryptionPassword = "password";
               JOptionPane.showMessageDialog( null, defaultKeyUsed );
            }
            else
            {
               if( !encryptionPassword.equals( encryptionPassword2 ))
               {
                  encryptionPassword = "password";
                  JOptionPane.showMessageDialog( null, "Two keys are not same!\r\n" + defaultKeyUsed,
		     "Not matched!", JOptionPane.WARNING_MESSAGE );
               }
               //this else has to do nothing
            }         
         }
         else
         {
            encryptionPassword = "password";
            JOptionPane.showMessageDialog( null, defaultKeyUsed );
         }
      }
      encryptor = new Encryptor( encryptionPassword );
      fileManager = new FileManager();
   }

   public String getSettings( String key )
   {
      return settings.getValue( key );
   }

   public String getCurrentFile()
   {
      return currentFile;
   }

   public void setCurrentFile( String currentFile )
   throws Exception
   {
      File file = new File( currentFile );
      if( file.canRead() && file.canWrite() )
      {
         this.currentFile = currentFile;
      }
      else
      {
         throw new Exception( "Bad file name." );
      }
   }

   public void save( String texts )
   throws IOException, UserException
   {
      boolean willThrowException = false;
      if( currentFile == null )
      {
         currentFile = filePathManager.getTodaySPath();
         willThrowException = true;
      }
      texts = encryptor.encrypt( texts );
      fileManager.saveFile( texts, currentFile );
      if( willThrowException )
      {
         throw new UserException( "TreeUpdateNeeded" );
      }
   }

   public String getNote() //load the file and returns the file content as String - Wasiq - 6:58 PM 3/21/2008
   throws IOException
   {
      if( currentFile == null )
      {
         return null;
      }
      else
      {
         return encryptor.decrypt( fileManager.loadFile( currentFile ) );
      }
   }

   public String getTodaySNote() //load the file and returns the file content as String - Wasiq - 6:58 PM 3/21/2008
   throws IOException
   {
      currentFile = null;
      String currentFileTmp = filePathManager.getTodaySPath();
      File file = new File( currentFileTmp );
      if( file.exists() )
      {
         currentFile = currentFileTmp;
         return getNote();
      }
      else
      {
         return null;
      }
   }

   public String getRootDirectory()
   {
      return filePathManager.getRootDirectory();
   }

   public String getFileExtension()
   {
      return filePathManager.getFileExtension();
   }

   public String getSpecialFilePath( int fileNo )
   {
      return filePathManager.getSpecialFilePath( fileNo );
   }
   
}