function getLocation() {
    if (navigator.geolocation) {
        showLoader();
        navigator.geolocation.getCurrentPosition(positionSuccess, positionError);
    }
}

function positionSuccess(position) {
    hideLoader();
    const lat = position.coords.latitude;
    const lnt = position.coords.longitude;
    document.getElementById("latitude").value = lat;
    document.getElementById("longitude").value = lnt;
}

function positionError(error) {
    hideLoader();
    alert("위치를 가져오는데 실패했습니다.");
}

function showLoader() {
    document.getElementById("loader").style.display = 'inline-block';
    document.getElementById("get-location-button").style.opacity = "0";
}

function hideLoader() {
    document.getElementById("loader").style.display = 'none';
    document.getElementById("get-location-button").style.opacity = "1";
}