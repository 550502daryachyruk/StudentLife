package com.a_team.studentlife.Server;

import com.a_team.studentlife.Server.ServerResponse.ChangeUserInformationResponse;
import com.a_team.studentlife.Server.ServerResponse.CreateLeagueResponse;
import com.a_team.studentlife.Server.ServerResponse.ListAllUserNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeagueNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeaguesResponse;
import com.a_team.studentlife.Server.ServerResponse.LoginResponse;
import com.a_team.studentlife.Server.ServerResponse.RegistrationResponse;
import com.a_team.studentlife.Server.ServerResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @FormUrlEncoded
    @POST("api/")
    Call<ServerResponse> sendMessagePOST(@Field("message") String message);

    @FormUrlEncoded
    @POST("api/getLeagues/")
    Call<ListLeaguesResponse> getListOfLeagues(@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("api/getListOfEvent/")
    Call<ListLeagueNewsResponse> getListOfLeagueNews(@Field("leagueId") Integer leagueId);

    @FormUrlEncoded
    @POST("api/userEvents/")
    Call<ListAllUserNewsResponse> getAllUserNews(@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("api/createChildLeague/")
    Call<CreateLeagueResponse> createChildLeague(@Field("parentLeagueId") Integer parentLeagueId,
                                                 @Field("leagueName") String leagueName,
                                                 @Field("description") String description);

    @GET("api/login")
    Call<LoginResponse> login(@Query("username") String userName,
                              @Query("password") String password);

    @GET("api/register")
    Call<RegistrationResponse> sendFirstName(@Query("firstname") String firstname,
                                             @Query("lastname") String lastname,
                                             @Query("username") String username,
                                             @Query("password") String password,
                                             @Query("email") String email,
                                             @Query("sex") int sex,
                                             @Query("birthday") String birthday);

    @POST("/api/user/edit")
    Call<ChangeUserInformationResponse> changeUserInformation(@Query("email") String email,
                                                              @Query("firstname") String firstname,
                                                              @Query("secondname") String secondname,
                                                              @Query("newpassword") String newpassword);
}
