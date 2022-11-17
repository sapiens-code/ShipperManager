function initMap() {
    const uluru = { lat: 10.820084005694037, lng: 106.69064060863472 };
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: uluru,
        mapTypeId: "roadmap",
    });

    // TODO: Replace with your app's Firebase project configuration
    var firebaseConfig = {
        apiKey: "AIzaSyDydtdaqFseP31FMEtLQDqiUWT-8yyGq1Y",
        authDomain: "shippermanager-be26e.firebaseapp.com",
        // The value of `databaseURL` depends on the location of the database
        databaseURL: "https://shippermanager-be26e-default-rtdb.firebaseio.com",
        projectId: "shippermanager-be26e",
        storageBucket: "shippermanager-be26e.appspot.com",
        messagingSenderId: "403228950057",
        appId: "1:403228950057:android:f6161a111785bee6446638",
    };

    firebase.initializeApp(firebaseConfig);

    // Get a reference to the database service
    //var database = firebase.database();

    const markers = [];

    var starCountRef = firebase.database().ref('Shipper');
    
    starCountRef.on('value', (snapshot) => {
        var i = 0;
        snapshot.forEach((object) => {
            var data = object.val();
            var location = { lat: data.KinhDo, lng: data.ViDo }
            //nếu chưa có marker thì tạo mới
            if (markers[i] == null) {
                const marker = new google.maps.Marker({
                    position: location,
                    map: map,
                    icon: {
                        labelOrigin: new google.maps.Point(28, 64),
                        url: 'http://maps.google.com/mapfiles/kml/shapes/motorcycling.png'
                    },
                    label: { color: '#000000', fontWeight: 'bold', fontSize: '14px', text: data.Ten }
                });
 
                ////////////////
                const contentString =
                    '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<img src="'+data.ImagePath+'" height="100" width="100" asp-append-version="true" />'+
                    '<h1 id="firstHeading" class="firstHeading">'+data.Ten+'</h1>' +
                    '<div id="bodyContent">' +
                    "<p>quê quán: "+data.QueQuan+"</p>" +
                    "<p>ngày sinh: " + data.NgaySinh + "</p>" +
                    "</div>" +
                    "</div>";
                const infowindow = new google.maps.InfoWindow({
                    content: contentString,
                });

                //add marker event
                marker.addListener("click", () => {
                    infowindow.open({
                        anchor: marker,
                        map,
                        shouldFocus: false,
                    });
                });

                //////////
                markers.push(marker);
            }
            else//nếu có thì cập nhật vị trí
            {
                markers[i].setPosition(location)
            }
            i++;
        })

    });

}

