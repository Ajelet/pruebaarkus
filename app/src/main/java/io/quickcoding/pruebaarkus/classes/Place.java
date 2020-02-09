package io.quickcoding.pruebaarkus.classes;

import org.json.JSONObject;

public class Place {


   private String placeId = "";
   private String placeName = "";
   private String address = "";
   private String category = "";
   private String isOpenNow = "";
   private double latitude = 0.0;
   private double longitude = 0.0;
   private String thumbnail = "";
   private float rating = 0.0f;
   private boolean isPetFriendly = false;
   private String addressLine1 = "";
   private String addressLine2 = "";
   private String phoneNumber = "";
   private String site = "";


   public Place()
   {}

   public Place(JSONObject item)
   {
       placeId = item.optString("PlaceId");
       placeName = item.optString("PlaceName");
       address = item.optString("Address");
       category = item.optString("Category");
       isOpenNow = item.optString("IsOpenNow");
       latitude = item.optDouble("Latitude");
       longitude = item.optDouble("Longitude");
       thumbnail = item.optString("Thumbnail");
       rating = (float)item.optDouble("Rating");
       isPetFriendly = item.optBoolean("IsPetFriendly");
       addressLine1 = item.optString("AddressLine1");
       addressLine2 = item.optString("AddressLine2");
       phoneNumber = item.optString("PhoneNumber");
       site = item.optString("Site");

   }

   public String getPlaceId() {
      return placeId;
   }

   public void setPlaceId(String placeId) {
      this.placeId = placeId;
   }

   public String getPlaceName() {
      return placeName;
   }

   public void setPlaceName(String placeName) {
      this.placeName = placeName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getIsOpenNow() {
      return isOpenNow;
   }

   public void setIsOpenNow(String isOpenNow) {
      this.isOpenNow = isOpenNow;
   }

   public double getLatitude() {
      return latitude;
   }

   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }

   public double getLongitude() {
      return longitude;
   }

   public void setLongitude(double longitud) {
      this.longitude = longitud;
   }

   public String getThumbnail() {
      return thumbnail;
   }

   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }

   public boolean isPetFriendly() {
      return isPetFriendly;
   }

   public void setPetFriendly(boolean petFriendly) {
      isPetFriendly = petFriendly;
   }

   public String getAddressLine1() {
      return addressLine1;
   }

   public void setAddressLine1(String addressLine1) {
      this.addressLine1 = addressLine1;
   }

   public String getAddressLine2() {
      return addressLine2;
   }

   public void setAddressLine2(String addressLine2) {
      this.addressLine2 = addressLine2;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getSite() {
      return site;
   }

   public void setSite(String site) {
      this.site = site;
   }


   @Override
   public String toString()
   {
      return "placeId: "+placeId
              +"\n placeName: "+placeName
              +"\n address: "+address
              +"\n category: "+category
              +"\n isOpenNow: "+isOpenNow
              +"\n latitude: "+latitude
              +"\n longitude: "+longitude
              +"\n thumbnail: "+thumbnail
              +"\n rating: "+rating
              +"\n isPetFriendly: "+isPetFriendly
              +"\n addressLine1: "+addressLine1
              +"\n addressLine2: "+addressLine2
              +"\n phoneNumber: "+phoneNumber
              +"\n site: "+site;

   }
}
