//programmed by Wasiqul Islam - 11:02 PM 3/23/2008
//updated 11:50 PM 3/23/2008


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