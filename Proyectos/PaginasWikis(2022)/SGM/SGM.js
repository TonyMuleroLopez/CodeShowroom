function ocultar() {
    document.getElementById('principal').style.display = 'none';
    document.getElementById('ocultar').style.display = 'none';
    document.getElementById('contenido').style.display = 'block';
    document.getElementById('mostrar').style.display = 'none'
    stop();
}
function autoplay()
{
    document.getElementById('audio').play();
}
function stop() {
    document.getElementById('audio').pause();
}
function mostrar()
{
    document.getElementById('intro').style.display = 'block';
    autoplay();
    document.getElementById('mostrar').style.display ='none';
    document.getElementById('ocultar').value = 'Continuar ';
}