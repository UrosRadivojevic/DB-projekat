/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikaciona_logika;

import domen.Camac;
import domen.Kupac;
import domen.MarkaCamca;
import domen.Prodavac;
import domen.Ugovor;
import java.util.ArrayList;
import java.util.List;
import pomocne.OdgovorUtil;
import pomocne.OperacijaUtil;
import pomocne.PretragaUtil;
import pomocne.ZahtevUtil;
import ugovori.IzmeniCamacUgovor;
import ugovori.OpstaSistemskaOperacija;
import ugovori.PretraziCamceUgovor;
import ugovori.PretraziKupacUgovor;
import ugovori.PretraziProdavceUgovor;
import ugovori.UcitajCamacUgovor;
import ugovori.VratiCamceUgovor;
import ugovori.VratiKupceUgovor;
import ugovori.VratiMarkeUgovor;
import ugovori.VratiProdavceUgovor;
import ugovori.ZapamtiCamacUgovor;
import ugovori.ZapamtiKupcaUgovor;
import ugovori.ZapamtiProdavcaUgovor;
import ugovori.ZapamtiUgovor;

/**
 *
 * @author PC
 */
public class Kontroler {
    private static Kontroler instance; 
    private Kontroler() {
        
    }

    public static Kontroler getInstance() {
        if(instance == null) instance = new Kontroler();
        return instance;
    }

    OdgovorUtil ObradiZahtev(ZahtevUtil zahtev) throws Exception{
        try{
            OpstaSistemskaOperacija oso;
            switch(zahtev.getSo()){
                case OperacijaUtil.ZapamtiProdavca     : 
                    oso = new ZapamtiProdavcaUgovor((Prodavac)zahtev.getObjekat());
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,"");
                case OperacijaUtil.VratiMarke      :
                    List<MarkaCamca> lista =new ArrayList();
                    oso = new VratiMarkeUgovor(new MarkaCamca(),lista);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista);
                case OperacijaUtil.ZapamtiCamac      : 
                    oso = new ZapamtiCamacUgovor((Camac)zahtev.getObjekat());
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,"");
                case OperacijaUtil.ZapamtiKupca      :
                    oso = new ZapamtiKupcaUgovor((Kupac)zahtev.getObjekat());
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,"");
                case OperacijaUtil.VratiCamce       :
                    List<Camac> lista2 =new ArrayList();
                    oso = new VratiCamceUgovor(new Camac(),lista2);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista2);
                case OperacijaUtil.VratiKupce       :
                    List<Kupac> lista3 =new ArrayList();
                    oso = new VratiKupceUgovor(new Kupac(),lista3);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista3);
                case OperacijaUtil.VratiProdavce        :
                    List<Prodavac> lista4 =new ArrayList();
                    oso = new VratiProdavceUgovor(new Prodavac(),lista4);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista4);
                case OperacijaUtil.ZapamtiUgovor        :
                    oso = new ZapamtiUgovor((Ugovor)zahtev.getObjekat());
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,"");
                case OperacijaUtil.PretraziProdavce      :
                    List<Prodavac> lista5 =new ArrayList();
                    oso = new PretraziProdavceUgovor((PretragaUtil) zahtev.getObjekat(),lista5);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista5);
                case OperacijaUtil.PretraziCamce      :
                    List<Camac> lista6 =new ArrayList();
                    oso = new PretraziCamceUgovor((PretragaUtil) zahtev.getObjekat(),lista6);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista6);
                case OperacijaUtil.PretraziKupac      :
                    List<Kupac> lista7 =new ArrayList();
                    oso = new PretraziKupacUgovor((PretragaUtil) zahtev.getObjekat(),lista7);
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,lista7);
                case OperacijaUtil.UcitajCamac      :
                    oso = new UcitajCamacUgovor((Camac)zahtev.getObjekat());
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,oso.getOdo());
                case OperacijaUtil.IzmeniCamac       :
                    oso = new IzmeniCamacUgovor((Camac)zahtev.getObjekat());
                    oso.izvrsiSO();
                    return new OdgovorUtil(true,"");
            }
            return null;
        }catch(Exception e){
            return new OdgovorUtil(false,e.getMessage());
        }
        
    }
    
}
