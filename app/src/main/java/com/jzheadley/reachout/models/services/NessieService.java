package com.jzheadley.reachout.models.services;

import com.jzheadley.reachout.App;
import com.jzheadley.reachout.R;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

public class NessieService {
    private static NessieClient client = NessieClient.getInstance(App.get().getResources().getString(R.string.nessie_api_key));


    private NessieService() {
    }

    public static NessieClient getInstance() {
        return client;
    }
}
