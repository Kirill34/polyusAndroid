package APILayer;

import java.util.List;

import model.Event;
import model.Tag;
import model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {

    // User
    @GET("/users/login")
    Call<User> login(@Header("Authorization") String string);

    @POST("/users/signup")
    Call<User> signup(@Body User user);

    @POST("/users/{id}/verify")
    Call<User> verify(
            @Header("Authorization") String string,
            @Path(value = "id") Long id, @Body String code
    );

    @GET("/users/newToken")
    Call<User> requestNewCode(@Header("Authorization") String string);

    @DELETE("/users/{id}")
    Call<Void> deleteUserById(
            @Header("Authorization") String string,
            @Path("id") Long id
    );

    @PUT("/users")
    Call<User> updateUser(
            @Header("Authorization") String string,
            @Body User user
    );

    @GET("/users/signup")
    Call<User> signupGet();

    // Tags
    @GET("/tags/getAll")
    Call<List<Tag>> getAllTags(@Header("Authorization") String string);

    @DELETE("/tags/{id}")
    Call<Void> deleteTagById(
            @Header("Authorization") String string,
            @Path("id") Long id
    );

    @PUT("/tags")
    Call<Tag> updateTag(
            @Header("Authorization") String string,
            @Body Tag tag
    );

    @POST("/tags")
    Call<Tag> saveTag(
            @Header("Authorization") String string,
            @Body Tag tag
    );

    // Events
    @POST("/events")
    Call<Event> saveEvent(
            @Header("Authorization") String string,
            @Body Event event
    );

    @GET("/events/author/{authorId}")
    Call<List<Event>> getEventsByAuthorId(
            @Header("Authorization") String string,
            @Path("authorId") Long id
    );

    @DELETE("/events/delete/{id}")
    Call<Void> deleteEventById(
            @Header("Authorization") String string,
            @Path("id") Long id
    );

    @GET("/events/get/{id}")
    Call<Event> getEventById(
            @Header("Authorization") String string,
            @Path("id") Long id
    );

    @GET("/events/name/{name}")
    Call<List<Event>> getEventsByName(
            @Header("Authorization") String string,
            @Path("name") String name
    );

    @GET("/events/getAllApprovedAndActive")
    Call<List<Event>> getAllEvents(@Header("Authorization") String string);
}
