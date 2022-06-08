//programmed by Wasiqul Islam - 6:40 PM 3/21/2008
//updating 5:39 PM 3/30/2008
//updated 8:27 PM 3/30/2008
//updated 12:51 AM 4/2/2008

/*

Algorithm:

Combined with some simple algorithm of encryption
this algorithm is established.

Simple algorithm:
Text
AAAABBBB
Key
AB(suppose that A = 1(character value of A is 1(actually 65)) and B = 2 )
Then Output should be
BCBCCDCD
When 
key
AC(say A=1,C= 3)
Output should be
BDBDCECE
and so on
here the actual key is
13131313( same length as the text, generated
by repeating 13 as long as it reaches same length of the text )
This is simple algorithm
method definition is
simpleEncrypt( String text, String key ) returns, String encryptedText
simpleDecrypt( String text, String key ) returns, String decryptedText

Complex algorithm:
Suppose 7 predefined keys are
abcdefgh
abcdtyui
rtygsdfaw
PLPLOKIK
aerstdyf
ebrnmfmg
ixocmsakll

and user key(password) is
sadfsdafd

text is
this is a sample text

here length of text is 21 (a)
length of user key is 9   (b)
So first number is ( 21 * 2 ) + 9 = 51 ---> 51%7 = 1               [ (2a+b)%7 ] = x
So second number is ( 9 * 2 ) + 21 = 40 ---> 40%7 = 5              [ (a + 2b ) % 7 ] = y
And the third one is ( absolute ( 21 - 9 ) ) = 12 ---> 12%7 = 5    [ abs( a - b ) % 7 ] = z

now we get three numbers as 1, 5, 5 ( x, y, z )
now take the (x+1) th predefined key,
(y+1) th and (z+1) th key;

They are: abcdtyui( tk1 ), ebrnmfmg( tk2 ), ebrnmfmg( tk3 )  -- tk = temporary key
Preliminary key is generated as:

pk = simpleEncrypt( userKey, simpleEncrypt( tk1, simpleEncrypt( tk2, tk3 ) ) )

the finel key will be a string buffer which is the same length of
the whole text to be encrypted.
the string buffer will be firstly empty.
a loop will take the 7 predefined keys one after another
when a predefined key is taken, the 'pk' (Preliminary key) is encrypted
by that predefined key, thus a new key is generated,
this new key is appended to the string buffer as a final key and is also
used as a new 'pk'. At the next round the next predefined key is taken
and used as a key for encrypting the new 'pk'[ new pk = basicEncrypt( oldPK, currentPredefinedKey ]
In each round of loop, one predefined key is selected 
as per sequence and is used for generating a portion of the final key.
This final key part is added to the string buffer.

This loop will end when the buffer will be completely filled with final
keys(fk).

The final encryption is done by
simpleEncrypt( userTexts, fk )
and decryption:
simpleDecrypt( userTexts, fk )
here length(string length) of userTexts and fk is same

Benfits of this complex algorithm:
1. Each time new fk is generated though userKey is same
( as long as length of text to be encrypted is different ).
2. New fk has no repetivive sequence.

With these benefits:
a pattern analyser computer cannot easily break
the encryption key. If it is possible, then it may
take sufficient amount of time to process
[even though if the processor knows this algorithm and
7 predefined keys].

-Wasiq - 3:28 PM 3/27/2008

*/

public class Encryptor
{

   private String key = null; //this is user key
   private int lengthOfKey = 0;
   private int predefinedKeyCount = 0;
   private final int characterMax = 65536; //pow( 2, 16 ) = 65536 = 2 bytes
   // as a char is made of two bytes

   String[] predefinedKeys = {
      "iucdghdfskjaplkdfjasioufhsxicjvnsdflkfdasijcnbdjcjsaopdfjrodinsdjkfnds384528fbweiry7487qwidbedfy3427ry273egdwqidiewdfhuewifew78grfo3829ry23789ry2378gdehdbwe",
      "cv98df7ge7hfemf09sdhfcqafnpowe243yrhqubhsaddfjsahfgy9wa0dfhqew897gr3i21ur9uewy7aesdvisahfbdusfwfgqw87dg2yfbesaio;fhqw0f8y298rkscjnasmcnsaiochaiudfbewifde df",
      "3489trwjfndfv  jdfh9ufhwe08fhdsu8cvhxzcjcvmdslk;jfio9isfdfjdfsifdsnfjksaddsdddsfdfogyre9fuweofme-txv szlkcjd9fywe87rt2grhesnfoisdhf87tdsgfyefhbesuifyher9ghb",
      "43895ubfoisdnfweyfdsfl;kvmfdiohdskjfhdsuifhndslkgjmfdkgnsdjkfhsadiufhsdoiufhndsiouhdsuifhsdiufhadsufhdsufhdisughdsjfnbsdjfhdsjkfhdskufhduihgjksdfhdshfdsiofh",
      "rthsdfjhfsdfndkjfgvknsdiufbkdsgnvjifdhdkszjmnhgdfbsdzjklhfisdufbnsdji dsuyiufhdsifgsdifjdsifrdiufuisfhsdiufyre98hew9r743tyfdnciousadhf9weu4823yqpfnsdjvsdoij",
      "4385798rfhiuwrfgjfkjgdkjfndnsdklcnwauhdfdskjfheriuowtywbfdsjkfncdsiucgsdijfmnkasdfsduifhfjsoiufheiufhirusdghreoighndsjlkfbdhufgdsigjerngwlkbndsifjgbsdjfrlgj",
      "cxcnsdncvlsdkvniudffhweyfbdsvnfdjv xzm,niduscdsicjhdsfuydvjs kjshisdufdsfndusifhisdbfdsyfds fjsdfyusdfgdsufuey87324gewdvsicfhe89ry23oczx8uycbs98dcdnew;fn cs"
   };

   public Encryptor( String key )
   {
      this.key = key;
      lengthOfKey = key.length();
      predefinedKeyCount = predefinedKeys.length;
   }
   public String encrypt( String text )
   {
      String finalKey = getFinalKey( text );
      return basicEncrypt( text, finalKey );
   }
   public String decrypt( String text )
   {
      String finalKey = getFinalKey( text );
      return basicDecrypt( text, finalKey );
   }

   private String getFinalKey( String text )
   {

      //initial part
      int lengthOfText = text.length();
      int firstIndex, secondIndex, thirdIndex;
      firstIndex = ( ( 2 * lengthOfText ) + lengthOfKey ) % predefinedKeyCount;
      secondIndex = ( lengthOfText + ( 2 * lengthOfKey ) ) % predefinedKeyCount;
      thirdIndex = Math.abs( lengthOfText - lengthOfKey ) % predefinedKeyCount;

      //get the initial current key
      String currentKey = 
         basicEncrypt
         (
         key, basicEncrypt( 
         predefinedKeys[ firstIndex ] , 
         basicEncrypt( predefinedKeys[ secondIndex ], 
         predefinedKeys[ thirdIndex ] ) ) 
         );

      //create a final key storage buffer      
      StringBuilder stringBuilder = new StringBuilder();
      int i = 0;
      while( true )
      {
         //get the final key from the current key
         currentKey = basicEncrypt( currentKey, predefinedKeys[ i ] );
         stringBuilder.append( currentKey );
         //check exit condition
         if( stringBuilder.length() >= lengthOfText )
         {
            break;
         }
         //calculate next i
         i++;
         if( i >= predefinedKeyCount )
         {
            i=0;
         }
      }
      //return the final key
      return stringBuilder.toString();
   }

   private String basicEncrypt( String text, String key )
   {
      return basicEncryptOrDecrypt( text, key, true );
   }

   private String basicDecrypt( String text, String key )
   {
      return basicEncryptOrDecrypt( text, key, false );
   }

   private String basicEncryptOrDecrypt( String text, 
   String key, boolean encrypt )
   {
      StringBuilder allTexts = new StringBuilder();
      int tmp;
      int j = 0;
      for( int i = 0; i < text.length(); i++, j++ )
      {
         if( j >= key.length() )
         {
            j = 0;
         }
         tmp = (int)text.charAt( i );
         if( encrypt )
         {
            tmp += (int)key.charAt(j);
         }
         else  //decrypt
         {
            tmp += characterMax - (int)key.charAt(j);
         }
         tmp %= characterMax;
         allTexts.append( (char) tmp );
      }
      return allTexts.toString();
   }

}