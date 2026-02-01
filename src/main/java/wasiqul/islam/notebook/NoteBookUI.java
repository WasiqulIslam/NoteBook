package wasiqul.islam.notebook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.tree.*;
import java.util.*;

// programmed by Wasiqul Islam - 12:04 PM 3/21/2008
// updated 8:02 AM 3/24/2008
// updating 12:39 PM 3/26/2008
// updated - 5:38 PM 3/26/2008
// updated - 1:29 AM 4/1/2008
// updated - 9:45 PM 4/1/2008
// updated - 12:15 AM 4/2/2008
// updated - 9:17 PM 4/2/2008
// updated - 10:30 AM 5/4/2008
//Update started by Wasiqul Islam on 16/01/2026

public class NoteBookUI extends JFrame implements KeyListener,
   TreeSelectionListener, WindowListener
{

   private JTabbedPane jTabbedPane = null;
   private JTextArea jTextArea = null;
   private JTree jTree = null;
   private NoteBook noteBook = null;
   private boolean modified = false;
   String noteBookTitle = null;
   String helpMessage = "Note Book\n\r" +
      "This software is used to keep track of daily notes\n\r" +
      "ensuring security of the notes.\n\r" +
      "After you have completed writing your note\n\r" +
      "press Ctrl+S to save.\n\r" +
      "Press F5 to get time.\n\r" +
      "Press F1, F2, F3 or F4 to view corresponding special files.\n\r" +
      "Press F9 to view today's note.\n\r" +
      "You can navigate to the upper \"Browse\" TAB to view old files.\n\r" +
      "When you are on the Browse tab, double click on the tree nodes(FILES)\n\r" +
      "repeatedly to expand the nodes.\n\r"+
      "\n\r"+
      "\n\r"+
      "ADVANCED:\n\r"+
      "\n\r"+
      "You can customize your copy of this program\n\r"+
      "using the Settings.txt file.\n\r"+
      "Before modifying the settings file keep a backup copy\n\r"+
      "of the original file; as invalid setting file may cause this\n\r"+
      "program to not work.\n\r"+
      "In the settings file, every first line is a key\n\r"+
      "and second line is the value of that key.\n\r"+
      "Do not change the key, only change the value\n\r"+
      "(the 2nd, 4th, 6th, 8th line, and so on )\n\r"+
      "Encryption technique is used in the program.\n\r"+
      "This makes your files ambiguous to others.\n\r"+
      "Change the encryption password if needed\n\r"+
      "but change the password only once\n\r"+
      "(before you have started to use this program).\n\r"+
      "Or you can change the value of the key: \"useDefaultEncryptionPassword\"\n\r"+
      "to \"no\".\n\r"+
      "In that case, this program will always\n\r"+
      "prompt you for entering password(key) every time this\n\r"+
      "program is run.\n\r"+
      "It is upto you whether you keep your password in\n\r"+
      "settings file or provide it every time\n\r"+
      "this program runs.\n\r"+
      "If you do not change the settings file at all\n\r"+
      "this program will use the key \"password\"\n\r"+
      "as a password(according to the default settings).\n\r"+
      "You can also change the date/time format if you like.\n\r"+
      "\n\r" +
      "\n\r" +
      "WARNING:\n\r" +
      "If password(key) is invalid then the letters will be\n\r" +
      "in a format which is not readable by you.\n\r" +
      "In that case, do not save the document;\n\r" +
      "Otherwise that document will be corrupted.\n\r" +
      "So, if needed keep a backup of your notes.\n\r" +
      "\n\r" +
      "\n\r" +
      "Sample date/time format:\n\r" +
      "[%1$Td-%1$Tb-%1$TY %1$tI:%1$tM%1$Tp %1$Ta(GMT+6)]\n\r" +
      "or\n\r" +
      "[%1$Td/%1$Tm/%1$Ty %1$tH:%1$tM:%1$tS %1$TA(+6GMT)]\n\r" +
      "or\n\r" +
      "%1$tA, %1$tB %1$Td %1$TY %1$tH:%1$tM:%1$tS:%1$tL\n\r" +
      "\n\r" +
      "\n\r" +
      "If you change your note saving location to a drive that is already synced by Dropbox or Onedrive or somethig similar than the notes are saved in the cloud automatically." +
      "\n\r" +
      "\n\r" +
      "Programmed by Wasiqul Islam\n\r"+
      "e-mail:(islam.wasiqul@gmail.com)\n\r";
 
   public NoteBookUI()
   throws IOException, UserException
   {

      super( "Note Book" );

      noteBookTitle = this.getTitle();
      this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
      this.addWindowListener( this );
      this.setExtendedState( JFrame.MAXIMIZED_BOTH );
      jTabbedPane = new JTabbedPane( JTabbedPane.TOP );
      add( jTabbedPane );

      Font unicodeFont = 
         new Font("Arial Unicode MS", Font.PLAIN, 14); 
         //This font is hardcoded for Windows, you can change it if needed
      jTextArea = new JTextArea();
      jTextArea.setFont( unicodeFont.deriveFont( 18.0f) );
      jTextArea.addKeyListener( this );
      jTabbedPane.add( "Notes", new JScrollPane( jTextArea ) );

      noteBook = new NoteBook();
      jTextArea.setText( noteBook.getTodaySNote() );

      prepareTree();
      loadTodaySFile();

   }

   public void loadTodaySFile()
   throws IOException
   {
      jTextArea.setText( noteBook.getTodaySNote() );
      if( noteBook.getCurrentFile() == null )
      {
         this.setTitle( noteBookTitle );
      }
      else
      {
         this.setTitle( noteBook.getCurrentFile() );
      }
      modified = false;
   }

   public void prepareTree()
   {

      if( jTabbedPane.getTabCount() >=3 )
      {
         jTabbedPane.remove( 2 );  //3rd
         jTabbedPane.remove( 1 );  //2nd
      }   

      /*
      //This code is kept for a quick referance - Wasiq 5:21 PM 3/27/2008
      DefaultMutableTreeNode node = new DefaultMutableTreeNode();
      DefaultMutableTreeNode child1 = new DefaultMutableTreeNode();
      DefaultMutableTreeNode child2 = new DefaultMutableTreeNode();
      TreeNodeObject childObject1 = new TreeNodeObject( "Child1", "data1" );
      TreeNodeObject childObject2 = new TreeNodeObject( "Child2", "data2" );
      child1.setUserObject( childObject1 );
      child2.setUserObject( childObject2 );
      node.add( child1 );
      node.add( child2 );
      jTree = null;
      jTree = new JTree( node );
      jTabbedPane.add( jTree );
      */
      
      //create the Tree Nodes
      DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
      rootNode.setUserObject( new TreeNodeObject( "FILES" ) );
      String rootDirectory = noteBook.getRootDirectory();
      File rootFile = new File( rootDirectory );
      File[] firstLevelFiles = rootFile.listFiles();
      DefaultMutableTreeNode currentFirstLevelNode = null;
      DefaultMutableTreeNode currentSecondLevelNode = null;
      DefaultMutableTreeNode currentThirdLevelNode = null;
      int lengthOfFileExtension = noteBook.getFileExtension().length();
      for( int i = 0; i < firstLevelFiles.length; i ++ )
      {
         if( firstLevelFiles[i].isDirectory() )
         {
            currentFirstLevelNode = new DefaultMutableTreeNode();
            currentFirstLevelNode.setUserObject( new TreeNodeObject(
               firstLevelFiles[i].getAbsolutePath().substring
               ( 
               ( firstLevelFiles[i].getAbsolutePath().length() - 4 ) ) 
               ) 
               );
            rootNode.add( currentFirstLevelNode );
            File[] secondLevelFiles = firstLevelFiles[i].listFiles();
            for( int j = 0; j < secondLevelFiles.length; j ++ )
            {
               if( secondLevelFiles[j].isDirectory() )
               {
                  currentSecondLevelNode = new DefaultMutableTreeNode();
                  currentSecondLevelNode.setUserObject( new TreeNodeObject(
                     secondLevelFiles[j].getAbsolutePath().substring
                     ( 
                     ( secondLevelFiles[j].getAbsolutePath().length() - 2 ) ) 
                     ) 
                     );
                  currentFirstLevelNode.add( currentSecondLevelNode );
                  File[] thirdLevelFiles = secondLevelFiles[j].listFiles();
                  for( int k = 0; k < thirdLevelFiles.length; k ++ )
                  {
                     if( thirdLevelFiles[k].isFile() )
                     {
                        currentThirdLevelNode = new DefaultMutableTreeNode();
                        currentThirdLevelNode.setUserObject
                           ( 
                              new TreeNodeObject
                              (
                                 thirdLevelFiles[k].getAbsolutePath().substring
                                 ( 
                                 ( thirdLevelFiles[k].getAbsolutePath().length() - 2 - lengthOfFileExtension )
                                 )
                                 ,
                                 thirdLevelFiles[k].getAbsolutePath()
                              )
                           );
                        currentSecondLevelNode.add( currentThirdLevelNode );
                     }
                  }
               }
            }
         }
      }
      jTree = null;
      jTree = new JTree( rootNode );
      jTabbedPane.add( "Browse", new JScrollPane( jTree ) );

      jTree.getSelectionModel().setSelectionMode(
         TreeSelectionModel.SINGLE_TREE_SELECTION );
      jTree.addTreeSelectionListener( this );

      //help/about
      JTextArea helpArea = new JTextArea( helpMessage );
      helpArea.setEditable(false);
      jTabbedPane.add( "Help/About", new JScrollPane( helpArea ) );

   }

   public void keyPressed(KeyEvent e)
   {
   }
   public void keyReleased(KeyEvent e)
   {
      try
      {
         if( e.getKeyCode() == KeyEvent.VK_F1 )
         //open special file
         {
            if( isConfirmedToDoOperation() )
            {
               loadSpecialFile( 1 );
            }
         }
         else if( e.getKeyCode() == KeyEvent.VK_F2 )
         //open special file2
         {
            if( isConfirmedToDoOperation() )
            {
               loadSpecialFile( 2 );
            }
         }
         else if( e.getKeyCode() == KeyEvent.VK_F3 )
         //open special file3
         {
            if( isConfirmedToDoOperation() )
            {
               loadSpecialFile( 3 );
            }
         }
         else if( e.getKeyCode() == KeyEvent.VK_F4 )
         //open special file4
         {
            if( isConfirmedToDoOperation() )
            {
               loadSpecialFile( 4 );
            }
         }
         else if( e.getKeyCode() == KeyEvent.VK_F5 )
         //write formatted date time
         {
            insertDateIntoTextField();
         }
         else if( e.getKeyCode() == KeyEvent.VK_F9 )
         //open today's file
         {
            if( isConfirmedToDoOperation() )
            {
               loadTodaySFile();
            }
         }
         else if( e.isControlDown() && ( !e.isShiftDown() ) && ( ! e.isAltDown() ) &&
            ( e.getKeyCode() == KeyEvent.VK_S ) )
         //Ctrl+S
         //save file
         {
            saveNotebookTexts();
         }
      }
      catch( Exception exception )
      {
         showException( exception );
      }
   }
   public void keyTyped(KeyEvent e)
   {
      modified = true;
   }

   public void saveNotebookTexts()
   throws IOException
   {
      try
      {
         noteBook.save( jTextArea.getText() );
      }
      catch( UserException userException )
      {
         if( userException.getMessage().equals( "TreeUpdateNeeded" ) )
         {
            prepareTree();
         }
      }
      this.setTitle( noteBook.getCurrentFile() );
      modified = false;
   }

   public void valueChanged( TreeSelectionEvent e )
   {
      try
      {
         JTree tree = (JTree) e.getSource();
         DefaultMutableTreeNode node = ( DefaultMutableTreeNode )
            tree.getLastSelectedPathComponent();
         if( node == null )
         {
            return;
         }
         TreeNodeObject userObject = (TreeNodeObject) node.getUserObject();
         String text = userObject.getInnerData();
         if( text != null )
         {
            if( isConfirmedToDoOperation() )
            {
               noteBook.setCurrentFile( text );
               jTextArea.setText( noteBook.getNote() );
               this.setTitle( noteBook.getCurrentFile() );
               jTabbedPane.setSelectedIndex( 0 );
            }
            jTree.setSelectionRow( 0 );
         }
      }
      catch( Exception exception )
      {
         showException( exception );
      }
   }
   
   public void showException( Exception exception )
   {
      JOptionPane.showMessageDialog( 
         null, exception.getMessage()
         + "\n\r" + exception.getCause() + "\n\r" + exception.toString(),
         "Error", JOptionPane.ERROR_MESSAGE );
   }

   public void windowActivated(WindowEvent e){}
   public void windowClosed(WindowEvent e){}
   public void windowDeactivated(WindowEvent e){}
   public void windowDeiconified(WindowEvent e){}
   public void windowIconified(WindowEvent e){}
   public void windowOpened(WindowEvent e){}
   public void windowClosing(WindowEvent e)
   {
      try
      {
         if( isConfirmedToDoOperation() )
         {
            System.exit( 0 );
         }
      }
      catch( Exception exception )
      {
         showException( exception );
      }
   }

   public boolean isConfirmedToDoOperation()
   throws IOException
   {
      if( !modified )
      {
         return true;
      }
      else
      {
         int result = JOptionPane.showConfirmDialog( null,
            "Do you want to save?", "Not saved!", 
         JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE );
         if( result == JOptionPane.YES_OPTION )
         {
            saveNotebookTexts();
            return true;
         }
         else if( result == JOptionPane.NO_OPTION )
         {
            return true;
         }
         else if( result == JOptionPane.CANCEL_OPTION )
         {
            return false;
         }
         return false;
      }
   }

   public void insertDateIntoTextField()
   {
      String format = noteBook.getSettings( "dateFormat" );
      Calendar rightNow = Calendar.getInstance();
      String formattedDate = String.format( format, rightNow );
      String part1, part2;
      int selectionStart = jTextArea.getSelectionStart();
      part1 = jTextArea.getText().substring( 0, selectionStart );
      part2 = jTextArea.getText().substring( jTextArea.getSelectionEnd() );
      jTextArea.setText( part1 + formattedDate + part2 );
      selectionStart += formattedDate.length();
      jTextArea.select( selectionStart, selectionStart );
      modified = true;
   }

   public void loadSpecialFile( int fileNo )
   throws Exception
   {
      String filePath = noteBook.getSpecialFilePath( fileNo );
      noteBook.setCurrentFile( filePath );
      jTextArea.setText( noteBook.getNote() );
      this.setTitle( noteBook.getCurrentFile() );
      modified = false;
   }

   public static void main( String[] args )
   {

      try
      {

         NoteBookUI noteBookUi = new NoteBookUI();
         noteBookUi.setVisible( true );

      }
      catch( Exception exception )
      {
         JOptionPane.showMessageDialog( null, exception.getMessage()
            + "\n\r" + exception.getCause() + "\n\r" + exception.toString() , "Error",
            JOptionPane.ERROR_MESSAGE );
      }

   }
}