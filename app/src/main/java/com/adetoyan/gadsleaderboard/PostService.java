package com.adetoyan.gadsleaderboard;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostService {
    @POST(// GADS
             "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"

            // Mine
//            "1FAIpQLSdqeUwKoUk8lFFxe8rdrGc03JQHDn0i2SZGfR8SHXRLEIkjYg/formResponse"
    )
    @FormUrlEncoded
    Call<Void> postData(
            // GADS
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.284483984") String projectLink);

            // Mine
            /*@Field("entry.1682986292") String firstName,
            @Field("entry.1753421855") String lastName,
            @Field("entry.1395742045") String emailAddress,
            @Field("entry.1936805312") String projectLink);*/
}
