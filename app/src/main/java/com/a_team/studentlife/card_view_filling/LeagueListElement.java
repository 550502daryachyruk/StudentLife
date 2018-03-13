package com.a_team.studentlife.card_view_filling;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ListLeaguesResponse;
import com.a_team.studentlife.adapter.leagues.LeaguesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueListElement {
    private Integer leagueImageId;
    private String leagueName;
    public static final ArrayList<LeagueListElement> leagueListElements = new ArrayList<>();

    public LeagueListElement() {}

    public LeagueListElement(Integer leagueImageId, String leagueName) {
        this.leagueImageId = leagueImageId;
        this.leagueName = leagueName;
    }

    public Integer getLeagueImageId() {
        return leagueImageId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public static void getLeagueListElements(final Context context,
                                             final LeaguesAdapter leaguesAdapter,
                                             final RecyclerView recyclerView,
                                             int userId) {

        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.getListOfLeagues(userId).enqueue(new Callback<ListLeaguesResponse>() {
            @Override
            public void onResponse(Call<ListLeaguesResponse> call, Response<ListLeaguesResponse> response) {
                if (response.isSuccessful()) {
                    ListLeaguesResponse listFromServer = response.body();
                    updateLeagueList(listFromServer, leagueListElements);
                    leaguesAdapter.addAllLeagues(leagueListElements);
                    recyclerView.setAdapter(leaguesAdapter);
                    Toast.makeText(context, "Успешное соединенние с сервером", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Сервер вернул ошибку", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListLeaguesResponse> call, Throwable t) {
                Toast.makeText(context, "Ошибка соединения с сервером", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void updateLeagueList(ListLeaguesResponse listFromServer,
                                         ArrayList<LeagueListElement> leagueListElements) {
        for (int i = 0; i < listFromServer.getIndexes().size(); i++) {
            leagueListElements.add(new LeagueListElement(listFromServer.getIndexes().get(i),
                    listFromServer.getNames().get(i)));
            //leagueListElements.add(new LeagueListElement(15, "dede"));
        }
    }

}