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
import net.sf.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        JSONObject fichierSortie;

        if ((args.length != 2) || !args[0].endsWith(".json")
                || !args[1].endsWith(".json")) {
            fichierSortie = new JSONObject();
            fichierSortie.accumulate("message:", "Données invalides");
        } else {
            try {
                String fichierEntree = FileReader.loadFileIntoString(args[0],
                        "utf-8");

                if (Utilites.validerFichierJson(fichierEntree)) {
                    Reclamations reclam = new Reclamations();
                    fichierSortie = reclam.traiterReclamations(fichierEntree);
                } else {
                    fichierSortie = new JSONObject();
                    fichierSortie.accumulate("message:", "Données invalides");
                }

            } catch (Exception ex) {
                fichierSortie = new JSONObject();
                fichierSortie.accumulate("message:", "Données invalides");
            }
        }

        Utilites.creerFichierSortie(fichierSortie, args[1]);
    }

}
