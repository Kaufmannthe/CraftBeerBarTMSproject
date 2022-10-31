function checker(element) {
    if (element === 'SNACKS' ){
        document.getElementById('alcDiv').style.display = 'none';
        document.getElementById('IBUDiv').style.display = 'none';
        document.getElementById('paramsAlc').value = "0";
        document.getElementById('paramsIBU').value = "0"
    }else {
        document.getElementById('alcDiv').style.display = 'block';
        document.getElementById('IBUDiv').style.display = 'block';
        document.getElementById('paramsAlc').value = "";
        document.getElementById('paramsIBU').value = ""
    }
    if (element !== 'BEER' && element !== 'SNACKS'){
        document.getElementById('IBUDiv').style.display = 'none';
        document.getElementById('paramsIBU').value = "0";
    }else {
        document.getElementById('paramsIBU').value = "";

    }
}
