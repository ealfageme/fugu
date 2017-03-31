package com.example;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.example.Entities.Booking;
import com.example.Entities.Restaurant;
import com.example.Entities.Review;
import com.example.Entities.User;
import com.example.Entities.Voucher;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class UserFollowers {
	public static class UserSerializer extends JsonSerializer<User> {

		@Override
		public void serialize(User user, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			try {
				jgen.writeStartObject();
				serializeObject(user, jgen, provider);
				jgen.writeEndObject();
			}
			catch (Exception ex) {
				if (ex instanceof IOException) {
					throw (IOException) ex;
				}
				throw new JsonMappingException(jgen, "Object serialize error", ex);
			}
			
		}

		private void serializeObject(User user, JsonGenerator jgen, SerializerProvider provider) throws IOException {
			jgen.writeNumberField("id", user.getId());
			jgen.writeStringField("name", user.getName());
			jgen.writeStringField("email", user.getEmail());
			jgen.writeNumberField("age", user.getAge());
			jgen.writeStringField("description", user.getDescription());
			jgen.writeStringField("favouriteFood", user.getFavouriteFood());
			jgen.writeArrayFieldStart("followingUsers");
			for(User follower: user.getFollowing()){
				jgen.writeStartObject();
				jgen.writeNumberField("id", follower.getId());
				jgen.writeStringField("nameFollowing", follower.getName());
				jgen.writeStringField("emailFollowing", follower.getEmail());
				jgen.writeEndObject();
			}
			jgen.writeEndArray();

			jgen.writeArrayFieldStart("followingRestaurants");
			for(Restaurant restaurantFollower: user.getRestaurants()){
				jgen.writeStartObject();
				jgen.writeNumberField("id", restaurantFollower.getId());
				jgen.writeStringField("nameFollowing", restaurantFollower.getName());
				jgen.writeStringField("emailFollowing", restaurantFollower.getEmail());
				jgen.writeEndObject();
			}
			jgen.writeEndArray();

			jgen.writeArrayFieldStart("vouchers");
			for(Voucher voucher: user.getUserVouchers()){
				jgen.writeStartObject();
				jgen.writeNumberField("id", voucher.getId());
				jgen.writeStringField("voucherName", voucher.getName());
				jgen.writeStringField("voucherDescription", voucher.getDescription());
				jgen.writeEndObject();
			}
			jgen.writeEndArray();

			jgen.writeArrayFieldStart("booking");
			for(Booking booking: user.getBookings()){
				jgen.writeStartObject();
				jgen.writeNumberField("id", booking.getId());
				jgen.writeNumberField("number", booking.getNumber());
				jgen.writeStringField("specialRequirements", booking.getSpecialRequirements());
				jgen.writeStringField("date", booking.getDate().toString());
				jgen.writeEndObject();
			}
			jgen.writeEndArray();

			jgen.writeArrayFieldStart("reviews");
			for(Review review: user.getReviews()){
				jgen.writeStartObject();
				jgen.writeNumberField("id", review.getId());
				jgen.writeStringField("date", review.getDate().toString());
				jgen.writeStringField("content", review.getContent());
				jgen.writeNumberField("rate", review.getRate());
				jgen.writeEndObject();
			}
			jgen.writeEndArray();
		}
    }
}
