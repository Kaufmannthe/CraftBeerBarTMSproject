function checker(element) {
    if (element === 'SNACKS'){
        document.getElementById('alcDiv').style.display = 'block';
        document.getElementById('IBUDiv').style.display = 'block';
    }else {
        document.getElementById('alcDiv').style.display = 'none';
        document.getElementById('IBUDiv').style.display = 'none';
        document.getElementById('paramsAlc').value = "0";
        document.getElementById('paramsIBU').value = "0"
    }
}
