# Implement Google Maps with Custom Marker

## `Implement MapsActivity`
### Step - 1 (Project SetUp)
  - Create Google MapsActivity 
  - create API Key
  - initialise the API_KEY in your local.properties file
  - mention the API_KEY in your manifest file as - ${YOUR_API_KEY}

### Step - 2 (Customize your onMapsReady method)
  - Initialise LatLng
  - Add a marker to that position
  - Move the camera to that position

        // Set Map Type (Optional)
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Initialise LatLng
        LatLng latLng = new LatLng(27.1751, 78.0421);
        
        // Add a marker
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Taj Mahal").snippet("Wonder of the world");
        mMap.addMarker(markerOptions);
        
        // move the camera
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        mMap.animateCamera(cameraUpdate);

 ## `Get LatLng from a place Name` (Customize your onMapsReady method)
   - As we were entring our Lat Lng Manually, Now we will extract our lat lng from a place name.
   - Here We will use GeoCoder to get our address from a name.

   - Initialise your geoCoder

   - Now make some changes in onMapReady method.
     
            // Get LatLng from a place Name
            List<Address> addresses = geocoder.getFromLocationName("Kanpur", 1);
            Address address = addresses.get(0);

            // Initialise LatLng
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
     - And Everything will remains same.

## `Set marker and get location on long click` 
  - Implement onMapLongClickListener interface
  - override method onMapLongClick
  - use geoCoder to get address from Latlng

        List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        Address address = addresses.get(0);

## `Drag Marker`
   - Implement OnMarkerDragListener interface
   - inside onLongClick method set MarkerOptions

    .draggable(true);
   - update onMarkerDragStart method
    
    addresses = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
    Address address = addresses.get(0);
    marker.setTitle(address.getAddressLine(0));

## `Set Current Location to the Map`
   - implement location dependency.

    implementation ("com.google.android.gms:play-services-location:21.1.0")
   - Ask for Permission.

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
   - Show a dialog to ask location permission.
   - if permission is granted call enableUserLocation method.
          
    public void enableUserLocation() {
      mMap.setMyLocationEnabled(true);
    }
  - Now if permission is already provoided on opening the app than move camera to the current location.
  - Initialise FusedLocationProviderClient.
  - Create a task to get last location using FusedLocationProviderClient.
  - and update the camera on taskSuccessful.

## `Custom icon for MARKER`
  - Iniitialise locationRequest

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(500);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

  - Create LocationCallback
  - create startLocationUpdate and stopLocationUpdate methods for fusedLocationProviderClient
  - Now create setUserLocationMarker method and initialise Marker and Circle.
  
        private void setUserLocationMarker(Location location) {
          LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
          if (userLocationMarker == null) {
              // Create new MarkerOption
              MarkerOptions markerOptions = new MarkerOptions().position(latLng);
              markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car2));
              markerOptions.rotation(location.getBearing());
              markerOptions.anchor((float) 0.5, (float) 0.5);
              userLocationMarker = mMap.addMarker(markerOptions);
              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
          } else{
              // use previous marker
              userLocationMarker.setPosition(latLng);
              userLocationMarker.setRotation(location.getBearing());
              mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
          }
          if (userLocationAccuracyCircle == null) {
              CircleOptions circleOptions = new CircleOptions();
              circleOptions.center(latLng);
              circleOptions.strokeWidth(4);
              circleOptions.strokeColor(ContextCompat.getColor(this, R.color.black));
              circleOptions.fillColor(ContextCompat.getColor(this, R.color.black));
              circleOptions.radius(location.getAccuracy());
              userLocationAccuracyCircle = mMap.addCircle(circleOptions);
          } else {
              userLocationAccuracyCircle.setCenter(latLng);
              userLocationAccuracyCircle.setRadius(location.getAccuracy());
          }
        }

 - That's It.

          
