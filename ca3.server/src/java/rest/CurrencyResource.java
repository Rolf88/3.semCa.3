/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.CurrencyRate;
import facades.CurrencyFacade;
import facades.EntityFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import rest.models.CurrencyRateViewModel;
import rest.models.DailyCurrencyRateViewModel;

@Path("currency")
public class CurrencyResource {

    @Context
    private UriInfo context;

    private final Gson gson;

    public CurrencyResource() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    @Path("dailyrates")
    @Produces("application/json")
    public Response getDailyRates() {
        CurrencyFacade currencyFacade = new CurrencyFacade(EntityFactory.getInstance().createEntityManager());

        Date searchDate = new Date();

        DailyCurrencyRateViewModel viewModel = new DailyCurrencyRateViewModel(searchDate, MapToCurrencyRateViewModel(currencyFacade.find(searchDate)));
        return Response.ok(gson.toJson(viewModel)).build();
    } 

    private List<CurrencyRateViewModel> MapToCurrencyRateViewModel(List<CurrencyRate> currencyRates) {
        List<CurrencyRateViewModel> rates = new ArrayList<>();
        for (CurrencyRate rate : currencyRates) {
            rates.add(new CurrencyRateViewModel(rate.getCurrencyCode(), rate.getRate()));
        }

        return rates;
    }
}
