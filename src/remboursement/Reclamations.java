package remboursement;
/*
 * Projet de session INF2015
 ***
 **** Biram DIOUF DIOB06127605
 *
 **** Anoushka J. GUIBOLA MALEKOU GUIA10618201
 *
 **** Latifa DAMOUH DAML11538206
 *
 **** Siham Kadi KADS06568801
 *
 */
import java.text.DecimalFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Reclamations implements Contrat{
    public JSONObject traiterReclamations(String fichier) {

        String mnt;
        double montant;
        double montantRemboursement;
        int numeroSoin;
        DecimalFormat dec = new DecimalFormat("0.00");
        JSONObject reclamationsRemboursements = JSONObject.fromObject(fichier);
        JSONObject remboursements = new JSONObject();
        remboursements.accumulate("client", reclamationsRemboursements.getString("client"));
        remboursements.accumulate("mois", reclamationsRemboursements.getString("mois"));
        JSONArray reclamations = new JSONArray();
        JSONArray reclamationsArray = JSONArray.fromObject(reclamationsRemboursements.get("reclamations"));

        for (int i = 0; i < reclamationsArray.size(); i++) {
        	
            JSONObject reclamationArray = reclamationsArray.getJSONObject(i);
            // Creation de tableau representant un remboursement
            JSONObject remboursementArray = new JSONObject();
            remboursementArray.accumulate("soin", reclamationArray.getInt("soin"));
            remboursementArray.accumulate("date", reclamationArray.getString("date"));
            mnt = reclamationArray.getString("montant");
            mnt = Utilites.getMontantSoin(mnt);
            montant = Double.parseDouble(mnt);
            montantRemboursement = 0.0;
            numeroSoin = reclamationArray.getInt("soin");
            String contrat = reclamationsRemboursements.getString("contrat");

            switch (contrat) {

                case CONTRAT_TYPE_A:
                    if (numeroSoin == NUMERO_SOIN_ARRAY[0]
                            || numeroSoin == NUMERO_SOIN_ARRAY[1]
                            || numeroSoin == NUMERO_SOIN_ARRAY[2]
                            || numeroSoin == NUMERO_SOIN_ARRAY[6]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[1];
                    } else if (numeroSoin >= NUMERO_SOIN_ARRAY[3] && numeroSoin <= NUMERO_SOIN_ARRAY[5] || numeroSoin == NUMERO_SOIN_ARRAY[8]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[0];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[7]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[2];
                    }
                    break;

                case CONTRAT_TYPE_B:
                    if (numeroSoin == NUMERO_SOIN_ARRAY[0]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[3];
                        if (montantRemboursement > MONTANT_MAX[0]) {
                            montantRemboursement = MONTANT_MAX[0];
                        }
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[1] || numeroSoin == NUMERO_SOIN_ARRAY[6]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[3];
                        if (montantRemboursement > MONTANT_MAX[1]) {
                            montantRemboursement = MONTANT_MAX[1];
                        }
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[2]) {
                        montantRemboursement = montant;
                        if (montantRemboursement > MONTANT_MAX[3]) {
                            montantRemboursement = MONTANT_MAX[3];
                        }
                    } else if (numeroSoin >= NUMERO_SOIN_ARRAY[3] && numeroSoin <= NUMERO_SOIN_ARRAY[4]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[3];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[5]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[0];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[7]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[6];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[8]) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[4];
                    }
                    break;

                case CONTRAT_TYPE_C:
                    if (Utilites.validerNumeroSoin(numeroSoin + "")) {
                        montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[5];
                    }
                    break;

                case CONTRAT_TYPE_D:
                    montantRemboursement = montant * POURCENTAGE_REMBOURSABLE[6];
                    if (numeroSoin == NUMERO_SOIN_ARRAY[0] && montant > MONTANT_MAX[5]) {
                        montantRemboursement = MONTANT_MAX[5];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[1] || numeroSoin == NUMERO_SOIN_ARRAY[6] && montant > MONTANT_MAX[4]) {
                        montantRemboursement = MONTANT_MAX[4];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[2] || numeroSoin == NUMERO_SOIN_ARRAY[7] && montant > MONTANT_MAX[7]) {
                        montantRemboursement = MONTANT_MAX[7];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[5] && montant > MONTANT_MAX[2]) {
                        montantRemboursement = MONTANT_MAX[2];
                    } else if (numeroSoin == NUMERO_SOIN_ARRAY[8] && montant > MONTANT_MAX[6]) {
                        montantRemboursement = MONTANT_MAX[6];
                    }
                    break;
            }

            remboursementArray.accumulate("montant", dec.format(montantRemboursement) + "$");
            reclamations.add(remboursementArray);
        }

        remboursements.accumulate("remboursements", reclamations);

        return remboursements;
    }
    
}
