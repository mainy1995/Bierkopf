package Bierkopf;


public class Stich {
    Karte[] Karten;
    int punkte;
    int gewinner;
    Karte hoechsteKarte;
    Karte ersteKarte;
  
    public Stich() {
        Karten = new Karte[4];
        punkte = 0;
        hoechsteKarte = new Karte(Zahl.NEUN, FARBE.SCHELLEN);
    }
  
    public void karteGelegt(Karte newKarte,int spieler){
        Karten[spieler]=newKarte;
        punkte+=newKarte.wertigkeit;
        if (isthooechsteKarte(newKarte)){
            hoechsteKarte=newKarte;
            gewinner=spieler;
        }
    }
    public int getGewinner(){
        return gewinner;
    }
    public boolean isthoechsteKarte(Karte newKarte){          
        if(hoechsteKarte.getTrumpf()){
            if(newKarte.getTrumpf()){            
                int i=newKarte.zahl.compareTo(hoechsteKarte.zahl);
                if(i>0){
                    return true;
                }else if(i==0){
                    int j=newKarte.farbe.compareTo(hoechsteKarte.farbe);
                    if(j>0){
                       
                        return true;
                    }
                }               
                
            }
        }else{
            if(newKarte.getTrumpf()){
            hoechsteKarte=newKarte;
            return true;
            }
            else
            {          
                int i=newKarte.farbe.compareTo(hoechsteKarte.farbe);
                if(i==0){
                    int j=newKarte.zahl.compareTo(hoechsteKarte.zahl);
                    if(j>0){
                        return true;
                    }
                    return true;
                }
                    
                              
                
            }
        }
        return false;
    }
  
  }
  
  