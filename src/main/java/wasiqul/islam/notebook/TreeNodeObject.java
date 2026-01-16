package wasiqul.islam.notebook;

//programmed by Wasiqul Islam - 11:02 PM 3/23/2008
//updated 11:50 PM 3/23/2008
//Update started by Wasiqul Islam on 16/01/2026

public class TreeNodeObject
{

   private String text = null;
   private String innerData = null;

   public TreeNodeObject( String text )
   {
      this.text = text;      
   }

   public TreeNodeObject( String text, String innerData )
   {
      this.text = text;
      this.innerData = innerData;
   }

   public String toString()
   {
      return text;
   }

   public String getInnerData()
   {
      return innerData;
   }

}