import java.io.*;
import javax.swing.*;
//--------------------------------------------------------------------
// Programmet läser av från en fil (infil), KRYPTERAR och sen skriver ut  
// den krypterade texten i en annan fill (utfil)!
//--------------------------------------------------------------------
public class Caesar{
    //--------------------------------------------------------------------
// Deklaratioiner 
//--------------------------------------------------------------------
    String infil;
    String utfil = "utfil.txt";
    String rad;
    String[] alfabet = {"a","b","c","d","e","f","g","h","i","j","k","l",
            "m","n","o","p","q","r","s","t","u","v","w",
            "x","y","z","å","ä","ö"};
    String kryptoNyckel, bokstav;
    String intext;
    String kryptoBokstav;
    String kryptoText = "";
    int    nyckel;
    int	   bokstavNr;

    BufferedReader lasfil;
    PrintWriter skrivfil;

    JFileChooser fileChooser = new JFileChooser();
    int resultat;
    //--------------------------------------------------------------------
// Konstruktor 
//--------------------------------------------------------------------
    public Caesar() throws IOException{
//System.out.println("konstruktor");

        oppnaInFil();
        oppnaUtFil();
        bearbeta();
        skrivfil.close();

    }//end konstruktor
    //--------------------------------------------------------------------
//Oppna infil och läsa från filen
//ifall det inte finns någon fil, skapa en!
//--------------------------------------------------------------------
    public void oppnaInFil() throws IOException{
//System.out.println("oppnaInFil()");

//Variant där man väljer fil från fildialogruta
/*
		resultat = fileChooser.showOpenDialog(null);
		if (resultat != JFileChooser.APPROVE_OPTION){
			System.out.println("Ingen fil valdes.");
			System.exit(0);
		}//end indata
		infil = fileChooser.getSelectedFile().getAbsolutePath();
*/

//Variant där filnamnet är hårdkodat
        infil = "infil.txt";


        lasfil = 	new BufferedReader(
                new FileReader(infil));
    }//oppnaInFil  ()
    //--------------------------------------------------------------------
// Öppna utfilen (på två sätt). 
// Finns inte filen skapas den.
//--------------------------------------------------------------------
    public void oppnaUtFil() throws IOException{
//System.out.println("oppnaUtfil()");	

        skrivfil = 	new PrintWriter(
                new BufferedWriter(

//Skriver över det som redan finns-om det finns					
                        new FileWriter (utfil)));

//Lägg till det som redan finns om det finns 	
//					new FileWriter (utfil,true)));	

    }//oppnaUtFil
    //--------------------------------------------------------------------
//Bearbetning
//--------------------------------------------------------------------
    public void bearbeta() throws IOException{
//System.out.println("bearbeta()");	

        hamtaNyckel();
        lasRad();
        while(rad!= null){
            kryptera();
            skrivRad();
            lasRad();
        }//end while

    }//bearbeta()
    //--------------------------------------------------------------------
//Kryptering
//--------------------------------------------------------------------
    public void kryptera() {
//System.out.println("skrivRad()");
        for(int i=0; i<rad.length(); i=i+1){
            bokstav = rad.substring(i , i+1);
            omvandla();
            kryptoText = kryptoText + kryptoBokstav;
        }//end for
    }//end kryptera
    //--------------------------------------------------------------------
//omvandling av bokstäver
//--------------------------------------------------------------------
    public void omvandla() {
//System.out.println("skrivRad()");

        for(int i=0; i<29; i=i+1){
            if(bokstav.equals(alfabet[i])){
                bokstavNr = i + nyckel;
                bokstavNr = bokstavNr % 29;
            }//end if
        }//end for
        kryptoBokstav = alfabet[bokstavNr];
    }//end kryptera
    //--------------------------------------------------------------------
//Hämtar nyckeln
//--------------------------------------------------------------------
    public void hamtaNyckel(){
//System.out.println("hamtaNyckel()");
        kryptoNyckel = JOptionPane.showInputDialog("KryptoNyckel?");
        nyckel = Integer.parseInt(kryptoNyckel);
    }//end nyckell
    //--------------------------------------------------------------------
//läser texten innan kryptering
//--------------------------------------------------------------------
    public void lasRad() throws IOException{
//System.out.println("lasRad()");

        rad = lasfil.readLine();

    }//lasRad()
    //--------------------------------------------------------------------
// Skriver ut krypterade texten
//--------------------------------------------------------------------
    public void skrivRad() throws IOException{
//System.out.println("skrivRad()");				

        skrivfil.println(kryptoText);
        kryptoText = ""; //det e för nästa rad

    }//skrivRad()
    //--------------------------------------------------------------------
// Drar igång programet
//--------------------------------------------------------------------
    public static void main(String[]args)throws IOException{
        Caesar p = new Caesar();
    }//end main
//--------------------------------------------------------------------	
}//end class
//--------------------------------------------------------------------
//--------------------------------------------------------------------