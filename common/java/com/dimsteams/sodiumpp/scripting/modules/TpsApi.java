package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.controllers.TpsCounterController;

public class TpsApi {

    public double get() {
        return TpsCounterController.instance.getTps();
    }
}