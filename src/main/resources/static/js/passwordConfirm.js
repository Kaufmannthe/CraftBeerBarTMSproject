function verifyPassword() {
    const password = document.getElementById("password").value;
    const passwordConf = document.getElementById("passwordConfirm").value;

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