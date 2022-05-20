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

            if (markers[i] == null) {
                const marker = new google.maps.Marker({
                    position: location,
                    map: map,
                });
                markers.push(marker);
            }
            else {
                markers[i].setPosition(location)
            }
            i++;
        })

    });

    // Create the DIV to hold the control and call the makeInfoBox() constructor
    // passing in this DIV.
    //var infoBoxDiv = document.createElement('div');
    //var infoBox = new makeInfoBox(infoBoxDiv, map);
    //infoBoxDiv.index = 1;
    //map.controls[google.maps.ControlPosition.TOP_CENTER].push(infoBoxDiv)
}

    //function makeInfoBox(controlDiv, map) {
    //    // Set CSS for the control border.
    //    var controlUI = document.createElement('div');
    //    controlUI.style.boxShadow = 'rgba(0, 0, 0, 0.298039) 0px 1px 4px -1px';
    //    controlUI.style.backgroundColor = '#fff';
    //    controlUI.style.border = '2px solid #fff';
    //    controlUI.style.borderRadius = '2px';
    //    controlUI.style.marginBottom = '22px';
    //    controlUI.style.marginTop = '10px';
    //    controlUI.style.textAlign = 'center';
    //    controlDiv.appendChild(controlUI);

    //    // Set CSS for the control interior.
    //    var controlText = document.createElement('div');
    //    controlText.style.color = 'rgb(25,25,25)';
    //    controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
    //    controlText.style.fontSize = '100%';
    //    controlText.style.padding = '6px';
    //    controlText.innerText = 'The map shows all clicks made in the last 10 minutes.';
    //    controlUI.appendChild(controlText);
    //}
