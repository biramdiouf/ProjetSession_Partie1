/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Refund;

import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 */
public class Refund {
     public static void main(String[] args) {
         
     }
     
   public static JSONObject traiterReclamations( String inputFile ){
        final String CONTRAT_TYPE_A = "A";
        final String CONTRAT_TYPE_B = "B";
        final String CONTRAT_TYPE_C = "C";
        final String CONTRAT_TYPE_D = "D";
        
        String mnt;
        double montant ;
        double montantRemboursement;
        int numeroSoin;
        DecimalFormat dec = new DecimalFormat("0.00");        
        JSONObject reclamationsRemboursements = JSONObject.fromObject(inputFile);
        JSONObject remboursements = new JSONObject();
        remboursements.accumulate("client", reclamationsRemboursements.getString("client") );
        remboursements.accumulate("mois", reclamationsRemboursements.getString("mois") );
        JSONArray reclamations = new JSONArray();
        JSONArray reclamationsArray = JSONArray.fromObject(reclamationsRemboursements.get("reclamations"));

        for(int i = 0; i < reclamationsArray.size(); i++) {

            JSONObject reclamationArray = reclamationsArray.getJSONObject(i);
            // Build an object for representing the remboursement
            JSONObject remboursementArray = new JSONObject();
            remboursementArray.accumulate("soin", reclamationArray.getInt("soin") );
            remboursementArray.accumulate("date", reclamationArray.getString("date") );
            mnt = reclamationArray.getString("montant") ;
            mnt = mnt.substring(0, mnt.length() - 1);
            montant = Double.parseDouble(mnt);
            montantRemboursement = 0.0;
            numeroSoin = reclamationArray.getInt("soin");            
            String contrat = reclamationsRemboursements.getString("contrat");
            
            switch(contrat) {
                
                case CONTRAT_TYPE_A:
                      if( numeroSoin == 0 || numeroSoin == 100 || numeroSoin == 200 || numeroSoin == 500 ){
                         montantRemboursement = montant * 0.25 ;
                      } else if( numeroSoin >= 300 && numeroSoin <= 400  || numeroSoin == 700 ) {
                         montantRemboursement = 0 ;
                      } else if( numeroSoin == 600 ) {
                         montantRemboursement = montant * 0.40 ;
                      }
                    break;
                    
                case CONTRAT_TYPE_B:    
                       if( numeroSoin == 0 ){
                            montantRemboursement = montant * 0.50 ;
                            if( montantRemboursement > 40 ){
                                montantRemboursement = 40;
                           }
                        } else if( numeroSoin == 100 || numeroSoin == 500) {
                           montantRemboursement = montant * 0.50 ;
                           if( montantRemboursement > 50 ){
                               montantRemboursement = 50;
                           }
                        } else if( numeroSoin == 200 ) {
                           montantRemboursement = montant ;
                           if( montantRemboursement > 70 ){
                               montantRemboursement = 70;
                           }
                        } else if( numeroSoin >= 300 && numeroSoin <= 399) {
                            montantRemboursement = montant * 0.50 ;
                        }else if( numeroSoin == 400 ) {
                            montantRemboursement = 0 ;
                        }else if( numeroSoin == 600 ) {

                            montantRemboursement = montant ;
                        }else if( numeroSoin == 700 ) {
                            montantRemboursement = montant * 0.70 ;
                        }
                    break;
                    
                case CONTRAT_TYPE_C:
                        if(validerNumeroSoin(numeroSoin)){
                            montantRemboursement = montant * 0.90 ;
                        }
                    break;
                    
                case CONTRAT_TYPE_D:
                        montantRemboursement = montant ;
                        if( numeroSoin == 0 && montant > 85 ){
                            montantRemboursement = 85 ;
                        }else if( numeroSoin == 100 || numeroSoin == 500 && montant > 75 ){
                            montantRemboursement = 75 ;
                        }else if( numeroSoin == 200 || numeroSoin == 600 && montant > 100 ){
                            montantRemboursement = 100 ;
                        }else if( numeroSoin == 400 && montant > 65 ){
                            montantRemboursement = 65 ;
                        }else if( numeroSoin == 700 && montant > 90 ){
                            montantRemboursement = 90 ;
                        }
                    break;            
                    
            }

            remboursementArray.accumulate("montant", dec.format(montantRemboursement)  + "$" );
            reclamations.add(remboursementArray);
        }

        remboursements.accumulate("remboursements", reclamations);

        return remboursements;
    }     
   
   public static boolean validerNumeroSoin(int numeroSoin){
       return false;
   }
   
}
