function replacer() {
    const strength = document.getElementById('paramsAlc').value.replace('/,', '.');
    const ibu = document.getElementById('paramsIBU').value.replace('/,', '.');
    const weight = document.getElementById('paramsWeight').value.replace('/,', '.');
    const price = document.getElementById('paramsPrice').value.replace('/,', '.');

    document.getElementById('paramsAlc').innerText = strength;
    document.getElementById('paramsIBU').innerText = ibu;
    document.getElementById('paramsWeight').innerText = weight;
    document.getElementById('paramsPrice').innerHTML = price;
}