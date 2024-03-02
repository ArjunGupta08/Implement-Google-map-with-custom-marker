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
