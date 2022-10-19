function validation() {
    const age = document.getElementById("age").value;
    const phoneNumber = document.getElementById("phoneNumber").value;

    if (age < 18 || age > 90){
        document.getElementById("messageAge").innerText = "Age radius (Age >= 18 and Age < 90"
        return false;
    }

    if (phoneNumber.length !== 12) {
        document.getElementById("messagePhone").innerHTML = "Phone Number format: 375-XX-XXX-XX-XX";
        return false;
    }
}