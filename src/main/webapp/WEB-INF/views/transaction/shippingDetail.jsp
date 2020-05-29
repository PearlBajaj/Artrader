
<div class="container mt-3 mb-3">
    <div class="card">
        <h5 class="card-header">Shipping Details<small class="text-muted float-right">Order #: ${transaction.transactionId}</small></h5>
        <div class="card-body">
            <div class="row">
                <div class="col-5">
                    <img class="card-img" src="data:image/jpg;base64,${transaction.artwork.photo}">
                </div>
                <div class="col-7">
                    <h5 class="card-title"><small>Artwork name: </small>${transaction.artwork.name}</h5>
                    <p class="card-text">Creator name: ${transaction.artist.name}</p>
                    <p class="card-text">Seller name: ${transaction.seller.name} </p>
                    <p class="card-text">Receiver name: ${transaction.buyer.name}</p>
                    <p class="card-text" id="address">Address: ${transaction.address}</p>
                    <p class="card-text">Phone: ${transaction.mobileNumber}</p>
                </div>
            </div>
            <div class="rounded border mt-3 mb-3 p-2">
                <div id="map" style="min-height: 300px"></div>
                <script>
                    var map;
                    var geocoder;
                    function initMap() {
                        map = new google.maps.Map(document.getElementById('map'), {
                            center: {lat: -33.865143, lng: 151.209900},
                            zoom: 13
                        });
                        geocoder = new google.maps.Geocoder();
                        const address = document.getElementById('address').innerHTML;
                        codeAddress(address);
                    }
                    function codeAddress(address) {
                        geocoder.geocode( { 'address': address}, function(results, status) {
                            if (status === 'OK') {
                                map.setCenter(results[0].geometry.location);
                                var marker = new google.maps.Marker({
                                    map: map,
                                    position: results[0].geometry.location
                                });
                            } else {
                                alert('Geocode was not successful for the following reason: ' + status);
                            }
                        });
                    }
                </script>
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBtBsTHPzrZa3Vsx15GUxIZ0-POev3l-3k&callback=initMap" async defer></script>
            </div>
            <div class="text-center">
                <form method="post" action="${pageContext.request.contextPath}/transaction/shipping/${transaction.transactionId}">
                    <input type="hidden" name="_method" value="put"/>
                    <button type="submit" class="btn btn-primary">Complete to ship</button>
                    <a href="${pageContext.request.getHeader("referer")}" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</div>