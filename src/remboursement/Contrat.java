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

public interface Contrat {

    public static final String CONTRAT_TYPE_A = "A";
    
    public static final String CONTRAT_TYPE_B = "B";
    
    public static final String CONTRAT_TYPE_C = "C";
    
    public static final String CONTRAT_TYPE_D = "D";

    public static final int[] NUMERO_SOIN_ARRAY = {0, 100, 200, 300, 399, 400, 500, 600, 700};

    public static final double[] POURCENTAGE_REMBOURSABLE = {0.0, 0.25, 0.40, 0.50, 0.70, 0.90, 1.0};

    public static final double[] MONTANT_MAX = {40.0, 50.0, 65.0, 70.0, 75.0, 85.0, 90.0, 100.0};

}
