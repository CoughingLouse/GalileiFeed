package com.coughinglouse.rjko.galileifeed.model;

import com.coughinglouse.rjko.galileifeed.utils.Costanti;
import com.coughinglouse.rjko.galileifeed.utils.Utils;

/**
 * Created by rjko on 01/08/17.
 */

public class Circolare {

    private String nome;
    private String link;
    private String moddate;

    public Circolare (String l, String md) {

        nome = Utils.daUrlANome(l);
        link = Costanti.GALILEI_ROOT + l;
        moddate = md;
    }

}
