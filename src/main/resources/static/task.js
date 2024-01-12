//document.addEventListener("DOMContentLoaded",startTimer)
document.addEventListener("DOMContentLoaded", (event) => {
if(document.getElementById("taskIds")){
localStorage.setItem("taskIds", taskIds.value);
localStorage.setItem("expiredDate", expiredDate.value);
}
startTimer()
});
function startTimer(){
var countDownDate = new Date(localStorage.getItem("expiredDate")).getTime();

// Update the count down every 1 second
var x = setInterval(function() {

  // Get today's date and time
  var now = new Date().getTime();

  // Find the distance between now and the count down date
  var distance = countDownDate - now;

  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

  // Display the result in the element with id="demo"
  document.getElementById("demo").innerHTML = days + "d " + hours + "h "
  + minutes + "m " + seconds + "s ";

  // If the count down is finished, write some text
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("demo").innerHTML = "EXPIRED";
    localStorage.removeItem("taskIds")
    localStorage.removeItem("expiredDate")
    window.location.href=w.location.href + '/result'
  }
}, 1000);
}
function next(){
let array = JSON.parse(localStorage.getItem("taskIds"));
let nextIndex = array.indexOf(+taskId.value) + 1;
if(nextIndex != -1){
nextTaskId.value=array[nextIndex]
}else{
nextTaskId.value=null
}

}

function stopSurvey(){
window.location.href=window.location.href + '/result'
}