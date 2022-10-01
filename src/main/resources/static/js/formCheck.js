

function formCheckConfirm() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const login = document.getElementById("login").value;
    const phoneNumber = document.getElementById("phoneNumber").value;
    const address = document.getElementById("address").value;
    const age = document.getElementById("age").value;
    const password = document.getElementById("password").value;
    const passwordConf = document.getElementById("passwordConfirm").value;

    if (firstName === "") {
        document.getElementById("messageFirstName").innerHTML = "Please, fill your first name";
        return false;
    }
    if (lastName === "") {
        document.getElementById("messageLastName").innerHTML = "Please, fill your last name";
        return false;
    }
    if (email === "") {
        document.getElementById("messageEmail").innerHTML = "Please, fill your email";
    }
    if (login === "") {
        document.getElementById("messageLogin").innerHTML = "Please, fill your login";
        return false;
    }
    if (phoneNumber === "") {
        document.getElementById("messagePhoneNumber").innerHTML = "Please, fill your phone number";
        return false;
    }
    if (address === "") {
        document.getElementById("messageAddress").innerHTML = "Please, fill your address";
        return false;
    }
    if (age === null) {
        document.getElementById("messageAge").innerHTML = "Please, fill your age";
        return false;
    }
    if(password === "") {
        document.getElementById("messagePassword").innerHTML = "Fill the password please!";
        return false;
    }

    if(password.length < 8) {
        document.getElementById("messagePassword").innerHTML = "Password length must be atleast 8 characters";
        return false;
    }

    if (password !== passwordConf){
        document.getElementById("messagePassword").innerHTML = "Password doesn't match";
        return false;
    }

}